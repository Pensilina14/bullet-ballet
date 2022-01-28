package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
/**
 * 
 * {@inheritDoc}
 */
public class ModelControllerImpl implements ModelController{
	/**
	 * Main field of this class.
	 */
	private final Optional<GameState> gameState;
	/**
	 * Simple constructor to set {@link GameState}.
	 * 
	 * @param gameState 
	 */
	public ModelControllerImpl(final GameState gameState) {
		this.gameState = Optional.of(gameState);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<GameState> getGameState() {
		return this.gameState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEventListener(final GameEventListener e) {
		this.gameState.get().setEventListener(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGameOver() {
		return this.gameState.get().isGameOver();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		this.gameState.get().update();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Environment getGameEnvironment() {
		return this.gameState.get().getGameEnvironment();
	}
	
}
