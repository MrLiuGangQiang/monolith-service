package org.monolith.core.api;

public class JsonApi {
	private int code;
	private Object data;
	private String msg;

	public JsonApi() {
	}

	public JsonApi(ApiCode code) {
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	public JsonApi(ApiCode code, Object data) {
		this.data = data;
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data == null ? "" : data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public JsonApi setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
