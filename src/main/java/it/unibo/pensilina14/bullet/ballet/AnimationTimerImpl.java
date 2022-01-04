package it.unibo.pensilina14.bullet.ballet;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import javafx.animation.AnimationTimer;

public class AnimationTimerImpl extends AnimationTimer {
	
    private final GameEngine engine;

	public AnimationTimerImpl(final GameEngine gameEngine) {
		this.engine = gameEngine;
		this.engine.setup();
	}

	@Override
	public void handle(final long now) {
		this.engine.processInput();
		this.engine.updateGame();
		try {
			this.engine.render();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	private final long toNano(final long ms) {
		return ms * (long) Math.pow(10.0, 6.0);
	}
	
	private final long toMs(final long nano) {
		return nano * (long) Math.pow(10.0, -6.0);
	}
	*/

}
