package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.Engine;
import javafx.animation.AnimationTimer;

public class AnimationTimerImpl extends AnimationTimer {
	
    private final Engine engine;

	public AnimationTimerImpl(final Engine gameEngine) {
		this.engine = gameEngine;
		this.engine.setup();
	}

	@Override
	public void handle(final long now) {
		this.engine.processInput();
		this.engine.updateGame();
		this.engine.render();
	}
	
}
