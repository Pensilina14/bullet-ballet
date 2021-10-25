package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public interface ObstacleFactory {

    DynamicObstacle createDynamicObstacle(GameState gameState, SpeedVector2D vector);
    StaticObstacle createStaticObstacle(GameState gameState, MutablePosition2D position);
    
}
