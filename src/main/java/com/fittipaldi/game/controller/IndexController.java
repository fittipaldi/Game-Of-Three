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

@Controller
public class IndexController {

	@RequestMapping("index")
	@ResponseBody
	public ObjResponse index(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String paramScore = req.getParameter("score");
		if (paramScore == null) {
			paramScore = "0";
		}
		Float score = Float.parseFloat(paramScore);

		HttpSession session = req.getSession();

		session.setAttribute("name", name);

		Player pla = new Player();
		ObjResponse jRes = new ObjResponse();
		StorePlayer store = new StorePlayer();

		pla.setId(session.getId());
		pla.setName(name);
		pla.setScore(score);

		store.appendPalyer(pla);

		jRes.setStatus(true);
		jRes.setMessage("It Works [name: " + name + "]");

		System.out.println(store.getData());

		return jRes;
	}
}