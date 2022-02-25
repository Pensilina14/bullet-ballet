package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface ObstacleFactory {

    Obstacle createStandardObstacle(Environment environment, SpeedVector2D vector);

}
