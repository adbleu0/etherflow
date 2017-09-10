package com.adbleu.jsonrpc;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonCallSerializerTest {

	@Test
	public void testSerializeJsonCallJsonGeneratorSerializerProvider() throws JsonProcessingException {

		String expected = "{\"jsonrpc\":\"2.0\",\"id\":\"6\",\"method\":\"trace_call\",\"params\":[{\"data\":\"encodedData\",\"from\":\"fromAddress\",\"to\":\"toAddress\"},[\"vmTrace\",\"trace\"]]}";
		JsonCall call = new JsonCall().setId(String.valueOf(6)).setMethod("trace_call")
				.addObjectParam("from", "fromAddress").addObjectParam("to", "toAddress")
				.addObjectParam("data", "encodedData").addArrayParam("vmTrace").addArrayParam("trace");

		String actual = JsonParser.toJson(call, JsonCall.class, new JsonCallSerializer());

		System.out.println(actual);

		Assert.assertEquals(expected, actual);
	}

}
