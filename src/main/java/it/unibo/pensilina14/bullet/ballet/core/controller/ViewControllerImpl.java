package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

	@Override
	public void changeScene(final Frames frame) throws IOException {
		final PageLoader pageLoader = new PageLoaderImpl();
		pageLoader.goToSelectedPageOnInput(Frames.HOMEPAGE);
	}
	
}
