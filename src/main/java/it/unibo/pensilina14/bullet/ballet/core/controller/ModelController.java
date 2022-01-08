package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public interface ModelController {
	
	Optional<GameState> getGameState();
	
}
