package com.fittipaldi.game.event;

public class ObjResponse {

	private boolean status;
	private String message;

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

	public String toJson() {
		return String.format("{status:%s,message:%s}", status, message);
	}

}
