package com.adbleu.jsonrpc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * JsonNodeTypes include <br/>
 * 
 * ARRAY BINARY BOOLEAN MISSING NULL NUMBER OBJECT POJO STRING
 *
 */
public class JsonParser {

	private JsonParser() {
	}

	public static void parse(InputStream jsonData) throws JsonProcessingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(jsonData);
		processNode(rootNode);
	}

	private static void processNode(JsonNode node) {
		Iterator<JsonNode> elements = node.elements();
		while (elements.hasNext()) {
			JsonNode n = elements.next();
			processNode(n);
		}
	}

	public static <T> String toJson(T object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public static <T> T fromJson(String json, Class<T> t) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, t);
	}

	public static <T> T fromJson(InputStream json, Class<T> t, JsonDeserializer<T> deserializer) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(t, deserializer);
		mapper.registerModule(module);
		return mapper.readValue(json, t);
	}

	public static <T> String toJson(T object, Class<T> t, JsonSerializer<T> serializer) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(t, serializer);
		mapper.registerModule(module);
		return mapper.writeValueAsString(object);
	}

	public static <T> String toJsonPretty(T object, Class<T> t, JsonSerializer<T> serializer)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(t, serializer);
		mapper.registerModule(module);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}

	public static String prettyPrintJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readValue(json, Object.class));
	}
}
