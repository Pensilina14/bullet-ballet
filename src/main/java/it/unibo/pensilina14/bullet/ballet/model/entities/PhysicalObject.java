package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

import java.util.Optional;

/** */
public interface PhysicalObject {
  /**
   * @return object's coordinates
   */
  Optional<MutablePosition2D> getPosition();
  /**
   * @return object's speedVector, it gives access to its position
   */
  Optional<SpeedVector2D> getSpeedVector();

  /**
   * Moves the entity up.
   *
   * @param y
   * @return true if object is within map boundaries, false otherwise
   */
  boolean moveUp(double y);

  /**
   * moves the entity down.
   *
   * @param y
   */
  void moveDown(double y);

  /**
   * moves the entity left.
   *
   * @param x
   * @return true if object is within map boundaries, false otherwise
   */
  boolean moveLeft(double x);

  /**
   * moves the entity right
   *
   * @param x
   * @return true if object is within map boundaries, false otherwise
   */
  boolean moveRight(double x);

  /** updates object's state */
  void updateState();

  /**
   * @return object's dimension
   */
  Optional<Dimension2D> getDimension();

  /**
   * @return gameEnvironment istance
   */
  Optional<Environment> getGameEnvironment();

  /**
   * @return object's mass
   */
  double getMass();

  /**
   * @return true if GameEntity object has landed, else otherwise
   */
  boolean hasLanded();

  /** let's the object land */
  void land();

  /** avoid's landing */
  void resetLanding();
}
