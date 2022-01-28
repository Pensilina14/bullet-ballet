package it.unibo.pensilina14.bullet.ballet.core.controller;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;

/**
 * This is the contract for classes that 
 * want to be referred as view controllers.
 * 
 * This means they act as an intermediary between caller
 * and the view itself.
 *
 */
public interface ViewController {
	/**
	 * Recalls the render method of the view, 
	 * in our case that's the {@link GameView#draw} method 
	 * inside {@link GameView} class.
	 */
	void render(); 
	/**
	 * Simple getters that returns a {@link GameView}.
	 * 
	 * @return {@link GameView} anchored to this controller.
	 */
	GameView getGameView();
}
