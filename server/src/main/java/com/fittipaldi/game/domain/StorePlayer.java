package com.fittipaldi.game.domain;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StorePlayer {

	private String keyPlayer = "palyer";

	public void addPalyer(Player player) {
		JsonArray jArr = new JsonArray();

		String plyers = System.getProperty(keyPlayer);
		if (plyers != null) {

			Gson gJson = new Gson();
			ArrayList listPla = gJson.fromJson(plyers, ArrayList.class);
			JsonParser jsonParser = new JsonParser();

			listPla.forEach(item -> {
				if (item != null) {
					JsonObject objectFromString = jsonParser.parse(item.toString()).getAsJsonObject();

					if (!objectFromString.get("id").getAsString().toString().equals(player.getId().toString())) {
						Player pla = new Player();

						pla.setId(objectFromString.get("id").getAsString());
						pla.setName(objectFromString.get("name").getAsString());
						pla.setScore(objectFromString.get("score").getAsFloat());

						jArr.add(pla.toJsonObj());
					}
				}
			});

		}
		jArr.add(player.toJsonObj());

		System.setProperty(keyPlayer, jArr.toString());
	}

	public void removePlayer(Player player) {
		JsonArray jArr = new JsonArray();

		String plyers = System.getProperty(keyPlayer);
		if (plyers != null) {

			Gson gJson = new Gson();
			ArrayList listPla = gJson.fromJson(plyers, ArrayList.class);
			JsonParser jsonParser = new JsonParser();

			listPla.forEach(item -> {
				if (item != null) {
					JsonObject objectFromString = jsonParser.parse(item.toString()).getAsJsonObject();

					if (!objectFromString.get("id").getAsString().toString().equals(player.getId().toString())) {
						Player pla = new Player();

						pla.setId(objectFromString.get("id").getAsString());
						pla.setName(objectFromString.get("name").getAsString());
						pla.setScore(objectFromString.get("score").getAsFloat());

						jArr.add(pla.toJsonObj());
					}
				}
			});

		}
		System.setProperty(keyPlayer, jArr.toString());

	}

	public ArrayList getDataObj() {
		JsonArray jArr = new JsonArray();
		ArrayList listPla = new ArrayList();

		String plyers = System.getProperty(keyPlayer);
		if (plyers != null) {
			Gson gJson = new Gson();
			listPla = gJson.fromJson(plyers, ArrayList.class);
		}
		return listPla;
	}

	public String getData() {
		return System.getProperty(keyPlayer);
	}

}
