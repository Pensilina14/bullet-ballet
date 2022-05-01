package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleImpl extends GameEntity implements Obstacle {

  private static final double MS_TO_S = 1;
  private int counter;

  public ObstacleImpl(
      final SpeedVector2D speedVector,
      final Environment gameEnvironment,
      final double mass,
      final Dimension2D dimension) {
    super(speedVector, gameEnvironment, mass, dimension);
    this.counter = 0;
  }

  @Override
  public void spin() {
    if (hasUpMovement()) {
      this.moveUp(Obstacles.MOVE_UP_DELTA.getValue());
      increaseCounter();
    } else if (hasDownMovement()) {
      this.moveDown(Obstacles.MOVE_DOWN_DELTA.getValue());
      checkChanges();
    }
  }

  public boolean hasDownMovement() {
    return this.counter >= Obstacles.MAX_ITERATIONS_DELTA.getValue() / 2;
  }

  public boolean hasUpMovement() {
    return this.counter < Obstacles.MAX_ITERATIONS_DELTA.getValue() / 2;
  }

  private void checkChanges() {
    increaseCounter();
    if (this.counter == Obstacles.MAX_ITERATIONS_DELTA.getValue()) {
      this.counter = 0;
    }
  }

  private void increaseCounter() {
    this.counter++;
  }

  @Override
  public void updateState() {
    this.getSpeedVector().get().noSpeedVectorSum(-MS_TO_S, 0);
    spin();
  }
}
