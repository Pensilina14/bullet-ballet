package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import java.util.Random;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ObstacleImpl extends GameEntity implements Obstacle{

	//private Random random = new Random();
	public int dumbCounter;
	
	public ObstacleImpl(final SpeedVector2D speedVector, final Environment gameEnvironment
			, final double mass, final Dimension2D dimension) {
		super(speedVector, gameEnvironment, mass, dimension);
	}

	@Override
	public void rotate() {
		if (this.dumbCounter < 50) {
    		this.moveUp(10);
    		this.dumbCounter++;
    	} else if (this.dumbCounter >= 50) {
    		this.moveDown(8);
    		this.dumbCounter++;
    		if (this.dumbCounter == 100) {
    			this.dumbCounter = 0;
    		}
    	}
	}

	@Override
	public void updateState() {
		this.getSpeedVector().get().noSpeedVectorSum(-GameEntity.MS_TO_S, 0);
    	rotate();
	}

}
