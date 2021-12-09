package it.unibo.pensilina14.bullet.ballet.model.entities;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

/**
 * 
 *
 */
public interface PhysicalObject {
	/**
	 * 
	 * @return object's coordinates
	 */
	Optional<MutablePosition2D> getPosition();

	Optional<SpeedVector2D> getSpeedVector();
	
	double getMass();
	
	void moveUp(double y);
	
	void moveDown(double y);
	
	void moveLeft(double x);
	
	void moveRight(double x);
	
	void updateState();
	
	/**
	 * 
	 * @return object's dimension
	 */
	Optional<Dimension2D> getDimension();

	/**
	 * 
	 * @return gameEnvironment istance
	 */
	Optional<Environment> getGameEnvironment();
}
