package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class ViewControllerImpl implements ViewController {
	private Optional<GameView> gameView;

	public ViewControllerImpl(Optional<GameView> gameView) {
		this.gameView = gameView;
	}

	@Override
	public void render(final GameState game) {
		this.gameView.get().draw();
	}
}
