package it.unibo.pensilina14.bullet.ballet.core.controller;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;

import java.io.IOException;

/**
 * This is the contract for classes that want to be referred as view controllers.
 *
 * <p>This means they act as an intermediary between caller and the view itself.
 */
public interface ViewController {
  /**
   * Recalls the render method of the view, in our case that's the {@link GameView#draw} method
   * inside {@link GameView} class.
   */
  void render();
  /**
   * Simple getters that returns a {@link GameView}.
   *
   * @return {@link GameView} anchored to this controller.
   */
  GameView getGameView();

  /** Stops the player animation. */
  void stopPlayerAnimation();

  /**
   * Changes scene from current to another frame.
   *
   * @param frame where you wanna go.
   */
  void changeScene(Frames frame) throws IOException;
}
