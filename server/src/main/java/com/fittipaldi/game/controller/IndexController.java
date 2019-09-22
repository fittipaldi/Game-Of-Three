package com.fittipaldi.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fittipaldi.game.domain.Player;
import com.fittipaldi.game.domain.StorePlayer;
import com.fittipaldi.game.event.ObjResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/")
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
				name = session.getId();
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

			jRes.setStatus(true);
			jRes.setMessage("All palyers");
			jRes.setData(store.getData().toString());
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
		} catch (Exception e) {
			jRes.setStatus(false);
			jRes.setMessage(e.getMessage());
		}
		return jRes;
	}

}