package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class ObstacleFactoryImpl implements ObstacleFactory{

    @Override
    public DynamicObstacle createDynamicObstacle(final GameState gameState
            ,final SpeedVector2D vector) {
        return new DynamicObstacle(new Dimension2Dimpl(OBSTACLES.WIDTH.getValue(), OBSTACLES.HEIGHT.getValue())
                , gameState.getGameEnvironment(), OBSTACLES.MASS.getValue(), vector);
    }

    @Override
    public StaticObstacle createStaticObstacle(final GameState gameState
            , final MutablePosition2D position) {
        return new StaticObstacle(new Dimension2Dimpl(OBSTACLES.WIDTH.getValue(), OBSTACLES.HEIGHT.getValue()) 
                , position, gameState.getGameEnvironment());
    }

}
