package it.unibo.pensilina14.bullet.ballet.core.controller;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public interface ViewController {
	void render(GameState game); 
	GameView getGameView();
}
