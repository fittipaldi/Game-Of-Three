package com.fittipaldi.game.domain;

import com.google.gson.JsonObject;

public class Player {

	private String id;
	private String name;
	private double score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public JsonObject toJsonObj() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.addProperty("name", name);
		obj.addProperty("score", score);

		return obj;
	}

	public String toJson() {
		return this.toJsonObj().toString();
	}

}
