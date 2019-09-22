package com.fittipaldi.game.event;

import com.fittipaldi.game.domain.Player;

public class Match {

	public Player run(Player p1, Player p2, double start) {
		double p1s = (double) start;
		double p2s = (double) start / 3;

		if (p1s <= 1) {
			p1.setScore(p1s);
			return p1;
		} else if (p2s <= 1) {
			p2.setScore(p2s);
			return p2;
		} else {
			return this.run(p1, p2, p2s);
		}

	}

}
