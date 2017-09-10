package com.adbleu.jsonrpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonCall {

	private String jsonrpc = "2.0";

	private String id;

	private String method;

	private Map<String, String> objectParams = new HashMap<>();
	
	private List<String> arrayParams = new ArrayList<>();

	private List<String> stringParams = new ArrayList<>();

	private List<Integer> integerParams = new ArrayList<>();

	public String getJsonrpc() {
		return jsonrpc;
	}

	public JsonCall setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public JsonCall setMethod(String method) {
		this.method = method;
		return this;
	}

	public List<String> getStringParams() {
		return stringParams;
	}

	public void setStringParams(List<String> stringParams) {
		this.stringParams = stringParams;
	}

	public JsonCall addObjectParam(String key, String value) {
		this.objectParams.put(key, value);
		return this;
	}

	public JsonCall addStringParam(String p) {
		this.stringParams.add(p);
		return this;
	}

	public JsonCall addIntegerParam(int p) {
		this.integerParams.add(p);
		return this;
	}

	public Map<String, String> getObjectParams() {
		return objectParams;
	}

	public void setObjectParams(Map<String, String> objectParams) {
		this.objectParams = objectParams;
	}

	public String getId() {
		return id;
	}

	public JsonCall setId(String id) {
		this.id = id;
		return this;
	}

	public List<Integer> getIntegerParams() {
		return integerParams;
	}

	public void setIntegerParams(List<Integer> integerParams) {
		this.integerParams = integerParams;
	}

	public List<String> getArrayParams() {
		return arrayParams;
	}

	public void setArrayParams(List<String> arrayParams) {
		this.arrayParams = arrayParams;
	}

	public JsonCall addArrayParam(String value) {
		this.arrayParams.add(value);
		return this;
	}
}
