package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface ObstacleFactory {

    DynamicObstacle createDynamicObstacle(Environment gameEnvironment, SpeedVector2D vector);
    StaticObstacle createStaticObstacle(Environment gameEnvironment, MutablePosition2D position);
    
}
