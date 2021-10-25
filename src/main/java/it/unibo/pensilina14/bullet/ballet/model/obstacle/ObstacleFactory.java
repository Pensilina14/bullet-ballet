package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface ObstacleFactory {

    DynamicObstacle createDynamicObstacle(Environment environment, SpeedVector2D vector);
    StaticObstacle createStaticObstacle(Environment environment, MutablePosition2D position);
    
}
