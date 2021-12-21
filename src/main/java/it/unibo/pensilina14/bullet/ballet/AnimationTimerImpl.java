package it.unibo.pensilina14.bullet.ballet;

import javafx.animation.AnimationTimer;

public class AnimationTimerImpl extends AnimationTimer {
	
    private final Game game;

	public AnimationTimerImpl(final Game game) {
		this.game = game;
		this.game.engine.setup();
	}

	@Override
	public void handle(final long now) {
		this.game.engine.processInput();
		this.game.engine.updateGame();
		this.game.engine.render();
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
