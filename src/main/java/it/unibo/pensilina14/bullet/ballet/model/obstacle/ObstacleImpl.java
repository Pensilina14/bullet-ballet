package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleImpl extends GameEntity implements Obstacle{

	public ObstacleImpl(final SpeedVector2D speedVector, final Environment gameEnvironment
			, final double mass, final Dimension2D dimension) {
		super(speedVector, gameEnvironment, mass, dimension);
	}

	@Override
	public void rotate() {
		// TODO implement this cool method
	}
	
}
