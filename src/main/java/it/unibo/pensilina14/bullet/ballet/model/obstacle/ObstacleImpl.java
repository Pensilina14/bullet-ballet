package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import java.util.Random;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleImpl extends GameEntity implements Obstacle{

	//private Random random = new Random();
	
	public ObstacleImpl(final SpeedVector2D speedVector, final Environment gameEnvironment
			, final double mass, final Dimension2D dimension) {
		super(speedVector, gameEnvironment, mass, dimension);
	}

	@Override
	public void rotate() {
		/*
		final double rand = random.nextDouble();
		if (rand % 2 == 0) {
			this.moveRight(5);
			System.out.println("right");
		} else {
			this.moveLeft(5);
			System.out.println("left");
		}
		*/
	}

	@Override
	public void updateState() {
		//this.rotate();
	}

}
