package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

/**
 * {@inheritDoc}
 */
public class ViewControllerImpl implements ViewController {
	/**
	 * Main field of this class.
	 */
    private final Optional<GameView> gameView;

    /**
     * Useful to initialize instance of this class.
     * 
     * @param gameView which is a {@link Optional}<{@link GameView}>
     */
	public ViewControllerImpl(final Optional<GameView> gameView) {
		this.gameView = gameView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render() {
		this.gameView.get().draw();
	}

	/**
	 * {@InheritDoc}
	 */
	@Override
	public GameView getGameView() {
		return this.gameView.get();
	}
}
