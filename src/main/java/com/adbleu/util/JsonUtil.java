package com.adbleu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class JsonUtil {

	private JsonUtil() {
	}

	public static String parse(InputStream is, String jsonPath) throws IOException {
		return JsonPath.read(is, jsonPath);
	}

	public static List<String> parseArray(InputStream is, String jsonPath) throws IOException {
		List<String> l = new ArrayList<>();
		JSONArray js = JsonPath.read(is, jsonPath);
		js.forEach(s -> {
			l.add(String.valueOf(s));
		});
		return l;
	}
}
