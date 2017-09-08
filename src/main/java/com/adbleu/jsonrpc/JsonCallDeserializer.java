package com.adbleu.jsonrpc;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonCallDeserializer extends JsonDeserializer<JsonCall> {

	@Override
	public JsonCall deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		JsonCall call = new JsonCall();
		call.setJsonrpc(node.get("jsonrpc").asText());
		call.setId(node.get("id").asText());
		call.setMethod(node.get("method").asText());
		JsonNode params = node.get("params");
		Iterator<JsonNode> it = params.elements();
		while (it.hasNext()) {
			JsonNode p = it.next();
			if (p.isTextual()) {
				call.addStringParam(p.textValue());
			}
			if (p.isObject()) {
				Iterator<String> fields = p.fieldNames();
				while (fields.hasNext()) {
					String field = fields.next();
					call.addObjectParam(field, p.get(field).asText());
				}
			}
		}
		return call;
	}
}
