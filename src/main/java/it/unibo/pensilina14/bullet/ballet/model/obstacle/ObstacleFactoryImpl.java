package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleFactoryImpl implements ObstacleFactory{

    @Override
    public DynamicObstacle createDynamicObstacle(final Environment gameEnvironment
            ,final SpeedVector2D vector) {
        return new DynamicObstacle(new Dimension2Dimpl(OBSTACLES.WIDTH.getValue(), OBSTACLES.HEIGHT.getValue())
                , gameEnvironment, OBSTACLES.MASS.getValue(), vector);
    }

    @Override
    public StaticObstacle createStaticObstacle(final Environment gameEnvironment
            , final MutablePosition2D position) {
        return new StaticObstacle(new Dimension2Dimpl(OBSTACLES.WIDTH.getValue(), OBSTACLES.HEIGHT.getValue()) 
                , position, gameEnvironment);
    }

}
