package com.fittipaldi.game.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fittipaldi.game.domain.Player;
import com.fittipaldi.game.domain.StorePlayer;
import com.fittipaldi.game.event.ObjResponse;
import com.fittipaldi.game.event.Match;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class IndexController {

	@RequestMapping("index")
	@ResponseBody
	public ObjResponse index(HttpServletRequest req, HttpServletResponse res) {
		ObjResponse jRes = new ObjResponse();
		try {
			String name = req.getParameter("name");
			String paramScore = req.getParameter("score");
			if (paramScore == null) {
				paramScore = "0";
			}
			Float score = Float.parseFloat(paramScore);

			HttpSession session = req.getSession();

			if (name.isEmpty()) {
				name = session.getId().toString();
			}

			Player pla = new Player();
			StorePlayer store = new StorePlayer();

			pla.setId(session.getId());
			pla.setName(name);
			pla.setScore(score);

			session.setAttribute("player", pla.toJson());

			store.addPalyer(pla);

			jRes.setStatus(true);
			jRes.setMessage("Welcame [name: " + name + "]");
			jRes.setData(store.getData().toString());
			jRes.setSessionId(session.getId().toString());
		} catch (Exception e) {
			jRes.setStatus(false);
			jRes.setMessage(e.getMessage());
		}

		return jRes;
	}

	@RequestMapping("match")
	@ResponseBody
	public ObjResponse match(HttpServletRequest req, HttpServletResponse res) {
		ObjResponse jRes = new ObjResponse();
		try {
			StorePlayer store = new StorePlayer();
			HttpSession session = req.getSession();
			JsonParser jsonParser = new JsonParser();
			Random rand = new Random();

			String p1 = req.getParameter("p1");
			String p2 = req.getParameter("p2");
			Player play1 = new Player();
			Player play2 = new Player();

			double start = rand.nextInt(96) + 3;

			System.out.println(start);

			String winner = "";
			store.getDataObj().forEach(item -> {
				if (item != null) {
					JsonObject objectFromString = jsonParser.parse(item.toString()).getAsJsonObject();

					if (objectFromString.get("name").getAsString().toString().equals(p1.toString())) {
						play1.setId(objectFromString.get("id").getAsString());
						play1.setName(objectFromString.get("name").getAsString());
						play1.setScore(objectFromString.get("score").getAsFloat());
					}

					if (objectFromString.get("name").getAsString().toString().equals(p2.toString())) {
						play2.setId(objectFromString.get("id").getAsString());
						play2.setName(objectFromString.get("name").getAsString());
						play2.setScore(objectFromString.get("score").getAsFloat());
					}

					System.out.println(objectFromString.toString());

				}
			});

			Match match = new Match();
			Player pResult = new Player();

			pResult = match.run(play1, play2, start);

			jRes.setStatus(true);
			jRes.setMessage("Winner: " + pResult.getName());
			jRes.setData(store.getData().toString());
			jRes.setSessionId(session.getId().toString());
		} catch (Exception e) {
			jRes.setStatus(false);
			jRes.setMessage(e.getMessage());
		}
		return jRes;
	}

	@RequestMapping("players")
	@ResponseBody
	public ObjResponse players(HttpServletRequest req, HttpServletResponse res) {
		ObjResponse jRes = new ObjResponse();
		try {
			StorePlayer store = new StorePlayer();
			HttpSession session = req.getSession();

			jRes.setStatus(true);
			jRes.setMessage("All palyers");
			jRes.setData(store.getData().toString());
			jRes.setSessionId(session.getId().toString());
		} catch (Exception e) {
			jRes.setStatus(false);
			jRes.setMessage(e.getMessage());
		}
		return jRes;
	}

	@RequestMapping("logout")
	@ResponseBody
	public ObjResponse logout(HttpServletRequest req, HttpServletResponse res) {
		ObjResponse jRes = new ObjResponse();
		try {
			HttpSession session = req.getSession();
			StorePlayer store = new StorePlayer();

			String p = session.getAttribute("player").toString();

			System.out.println(p);

			if (p != null) {
				JsonParser jsonParser = new JsonParser();
				Player pla = new Player();

				JsonObject objectFromString = jsonParser.parse(p).getAsJsonObject();
				pla.setId(objectFromString.get("id").getAsString());

				store.removePlayer(pla);
			}
			jRes.setStatus(true);
			jRes.setMessage("Session Updated");
			jRes.setData(store.getData().toString());
			jRes.setSessionId(session.getId().toString());
		} catch (Exception e) {
			jRes.setStatus(false);
			jRes.setMessage(e.getMessage());
		}
		return jRes;
	}

}