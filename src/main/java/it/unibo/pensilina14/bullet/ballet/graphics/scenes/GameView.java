package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import javafx.scene.layout.Pane;

/**
 * Describes a contract for every game view
 * class that has to work as the game frame.
 *
 */
public interface GameView {
	/**
	 * @param controller 
	 * 
	 */
	void setup(GameEngine controller);
	void draw() throws IOException;
	void setInputController(GameEngine controller);
	Pane getAppPane();
	Pane getGamePane();
	Pane getUiPane();
	void deleteEnemySpriteImage(final MutablePosition2D position);
	void deleteBulletSpriteImage(final MutablePosition2D position);
	void deleteWeaponSpriteImage();
	void generateBullet() throws IOException;
}
