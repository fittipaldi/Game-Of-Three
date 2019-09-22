package com.fittipaldi.game.domain;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StorePlayer {

	private String keyPlayer = "palyer";

	public void appendPalyer(Player player) {
		JsonArray jArr = new JsonArray();

		String datas = System.getProperty(keyPlayer);
		if (datas != null) {

			Gson gJson = new Gson();
			ArrayList listPla = gJson.fromJson(datas, ArrayList.class);
			JsonParser jsonParser = new JsonParser();

			listPla.forEach(item -> {
				JsonObject objectFromString = jsonParser.parse(item.toString()).getAsJsonObject();

				if (!objectFromString.get("id").getAsString().toString().equals(player.getId().toString())) {
					Player pla = new Player();

					pla.setId(objectFromString.get("id").getAsString());
					pla.setName(objectFromString.get("name").getAsString());
					pla.setScore(objectFromString.get("score").getAsFloat());

					jArr.add(pla.toJsonObj());
				}
			});

		}
		jArr.add(player.toJsonObj());

		System.setProperty(keyPlayer, jArr.toString());
	}

	public String getData() {
		return System.getProperty(keyPlayer);
	}

}
