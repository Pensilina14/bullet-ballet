package it.unibo.pensiliina14.bullet.ballet.model.obstacle.test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObstacleTest {

  private static final double FACTOR = 0.001;
  private static final double DELTA = 50;
  final ObstacleFactory obstacleFactory = new ObstacleFactoryImpl();

  @Test
  public void testSpin() {
    final Obstacle obstacle =
        obstacleFactory.createStandardObstacle(
            new GameEnvironment(), new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), 1));
    assertEquals(obstacle.getPosition().get().getX(), 0.0, FACTOR);
    obstacle.spin();
    assertTrue(obstacle.hasUpMovement());
    for (int i = 0; i < DELTA; i++) {
      obstacle.spin();
    }
    assertTrue(obstacle.hasDownMovement());
  }
}
