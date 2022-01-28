package it.unibo.pensilina14.bullet.ballet.core.controller;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;

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
	
	/**
	 * Un po' di documentazione che se no pare spoglio 
	 */
	void stopPlayerAnimation();
	
	/**
	 * Un po' di documentazione che se no pare spoglio 
	 */
	void changeScene(Frames frame) throws IOException;
	
	
}
