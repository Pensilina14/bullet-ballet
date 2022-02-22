package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
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
	/**
	 * Generates a bullet sprite based on the position of parameter bullet({@link Bullet}).
	 * @param bullet is the model component to render in the view.
	 * @throws IOException if file is not available.
	 */
	void generateBullet(PhysicalObject bullet) throws IOException;
	void deleteEnemySpriteImage(MutablePosition2D position);
	void deleteBulletSpriteImage(MutablePosition2D position);
	void deleteWeaponSpriteImage(MutablePosition2D position);
	void deleteObstacleSpriteImage(MutablePosition2D position);
	void deleteItemSprite(MutablePosition2D position);
	void stopPlayerAnimation();
	void autoKill();
}
