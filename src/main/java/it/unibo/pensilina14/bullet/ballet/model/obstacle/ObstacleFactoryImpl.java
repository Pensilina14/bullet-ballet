package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleFactoryImpl implements ObstacleFactory{

	@Override
	public Obstacle createStandardObstacle(final Environment environment, final SpeedVector2D vector) {
		return new ObstacleImpl(vector, environment, Obstacles.MASS.getValue()
				, new Dimension2Dimpl(Obstacles.WIDTH.getValue(), Obstacles.HEIGHT.getValue()));
	}

}
