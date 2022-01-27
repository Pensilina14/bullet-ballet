package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;

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
		try {
			this.gameView.get().draw();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@InheritDoc}
	 */
	@Override
	public GameView getGameView() {
		return this.gameView.get();
	}

	@Override
	public void stopPlayerAnimation() {
		this.gameView.get().stopPlayerAnimation();
	}
}
