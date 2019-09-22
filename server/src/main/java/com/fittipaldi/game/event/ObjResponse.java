package com.fittipaldi.game.event;

import com.google.gson.JsonObject;

public class ObjResponse {

	private boolean status;
	private String message;
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JsonObject toJsonObj() {
		JsonObject obj = new JsonObject();

		obj.addProperty("status", status);
		obj.addProperty("message", message);
		obj.addProperty("data", data);

		return obj;
	}

	public String toJson() {
		return this.toJsonObj().toString();
	}

}
