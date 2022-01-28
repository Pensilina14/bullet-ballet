package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;

/**
 * Contract adhered by classes that need to 
 * control the model or bridge communications between another class
 * and the model.
 *
 */
public interface ModelController {
	/**
	 * Simple getter of the implementing class {@link GameState}.
	 * 
	 * @return a {@link GameState} wrapped in a {@link Optional}.
	 */
	Optional<GameState> getGameState();
	/**
	 * Simple setter for the {@link GameEventListener} 
	 * that is going to listen every incoming event from the model.
	 * 
	 * @param e the {@link GameEventListener}.
	 */
	void setEventListener(GameEventListener e);
	/**
	 * Checks if game is over.
	 * 
	 * @return false if game is still on.
	 * 		   true if game is over.
	 */
	boolean isGameOver();
	/**
	 * Recalls the implementing class' object's method {@link GameState#update}.
	 */
	void update();
	/**
	 * Retrieves and returns the model world({@link GameEnvironment}) within the {@link GameState}.
	 * 
	 * @return {@link Environment} instance that represents the model world.
	 */
	Environment getGameEnvironment();
	
}
