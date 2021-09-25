package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;

public final class Launcher {

	public static void main(String[] args) {
		final GameEngine game = new GameEngine();
		game.setup();
		game.mainLoop();
	}

}
