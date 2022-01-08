package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;

public interface ModelController {
	
	Optional<GameState> getGameState();
	void setEventListener(GameEventListener e);
}
