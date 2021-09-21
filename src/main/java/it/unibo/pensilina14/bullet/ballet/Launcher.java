package it.unibo.pensilina14.bullet.ballet;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;

public final class Launcher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Bullet Ballet";
		cfg.useGL30 = false;
		cfg.width = 1280;
		cfg.height = 720;

		new LwjglApplication(new Map(), cfg);

		/*final GameEngine game = new GameEngine();
		game.setup();
		game.mainLoop();*/
	}

}
