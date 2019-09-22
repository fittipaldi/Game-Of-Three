package com.fittipaldi.game.event;

import java.util.Random;

import com.fittipaldi.game.domain.Player;

public class Match {

	public Player run(Player p1, Player p2, double start) {
		double nStart = (double) start / 3;

		if (nStart < 2) {
			p1.setScore(nStart);
			return p1;
		} else if (nStart <= 3 && nStart > 1) {
			p2.setScore(nStart);
			return p2;
		} else {
			Random rand = new Random();
			int randomNumber = rand.nextInt(2 + 1 - 0) + 0;
			nStart = nStart + randomNumber;
			return this.run(p1, p2, (nStart + randomNumber));
		}

	}

}
