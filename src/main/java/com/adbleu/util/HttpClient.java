package com.adbleu.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.jsonrpc.JsonCallSerializer;
import com.adbleu.jsonrpc.JsonParser;

public final class HttpClient {

	private static final Logger LOG = Logger.getLogger(HttpClient.class);

	private HttpClient() {
	}

	public static String execute(final JsonCall call) throws Exception {
		return execute("http://localhost:8545", call);
	}

	public static String execute(final String url, final JsonCall call) throws Exception {
		String request = null;
		String response = null;
		try {
			request = JsonParser.toJson(call, JsonCall.class, new JsonCallSerializer());
			LOG.info(JsonParser.prettyPrintJson(request));
			response = HttpClient.executePostJson(url, new StringEntity(request), new ResponseHandlerImpl());
			response = JsonParser.prettyPrintJson(response);
			LOG.info(response);
			return response;
		} catch (Exception e) {
			LOG.error(request, e);
			throw new Exception(String.join("\n", request, response, e.toString()), e);
		}
	}

	private static final <T> T executePostJson(String url, HttpEntity entity, ResponseHandler<T> responseHandler)
			throws IOException {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			return httpclient.execute(
					RequestBuilder.post().setUri(url).setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
							.setHeader(HttpHeaders.ACCEPT, "application/json").setEntity(entity).build(),
					responseHandler);
		}
	}

	static class ResponseHandlerImpl implements ResponseHandler<String> {
		@Override
		public String handleResponse(HttpResponse response) throws IOException {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		}
	}
}
