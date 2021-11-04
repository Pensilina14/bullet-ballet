package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.input.Controller;
import javafx.scene.layout.Pane;

/**
 * Describes a contract for every game view
 * class that has to work as the game frame.
 *
 */
public interface GameView {
	/**
	 * 
	 */
	void setup();
	void draw();
	void setInputController(Controller controller);
	Pane getAppPane();
	Pane getGamePane();
	Pane getUiPane();
}
