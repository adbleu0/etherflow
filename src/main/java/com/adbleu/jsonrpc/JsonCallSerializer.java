package com.adbleu.jsonrpc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonCallSerializer extends JsonSerializer<JsonCall> {

	@Override
	public void serialize(JsonCall call, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeStartObject();
		gen.writeStringField("jsonrpc", call.getJsonrpc());
		gen.writeStringField("id", call.getId());
		gen.writeStringField("method", call.getMethod());
		gen.writeArrayFieldStart("params");
		Map<String, String> pmap = call.getObjectParams();
		if (!pmap.isEmpty()) {
			gen.writeStartObject();
			for (Map.Entry<String, String> e : pmap.entrySet()) {
				gen.writeStringField(e.getKey(), e.getValue());
			}
			gen.writeEndObject();
		}
		List<String> plist = call.getStringParams();
		if (!plist.isEmpty()) {
			for (String p : plist) {
				gen.writeString(p);
			}
		}
		List<Integer> ilist = call.getIntegerParams();
		if (!ilist.isEmpty()) {
			for (Integer i : ilist) {
				gen.writeNumber(i);
			}
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}

}