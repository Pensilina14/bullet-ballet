package it.unibo.pensilina14.bullet.ballet.model.entities;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class GameEntity implements PhysicalObject{

    protected static final double MS_TO_S = 1;
	private final SpeedVector2D speedVector;
	private final Environment gameEnvironment;
	private final double mass;
	private final Dimension2D dimension;
	
	public GameEntity(final SpeedVector2D speedVector, final Environment gameEnvironment, final double mass, final Dimension2D dimension) {
		super();
		this.speedVector = speedVector;
		this.gameEnvironment = gameEnvironment;
		this.mass = mass;
		this.dimension = dimension;
	}

	@Override
	public Optional<MutablePosition2D> getPosition() {
		return this.speedVector.getPosition();
	}

	@Override
	public Optional<SpeedVector2D> getSpeedVector() {
		return Optional.ofNullable(this.speedVector);
	}

	@Override
	public double getMass() {
		return this.mass;
	}

	@Override
	public void updateState() {
		this.speedVector.noSpeedVectorSum(-MS_TO_S, 0);
	}

	@Override
	public Optional<Dimension2D> getDimension() {
		return Optional.of(this.dimension);
	}

	@Override
	public Optional<Environment> getGameEnvironment() {
		return Optional.of(this.gameEnvironment);
	}

	@Override
	public void moveUp(final double y) {
		if (this.getPosition().get().getY() -y - this.getDimension().get().getHeight()
				>= this.gameEnvironment.getDimension().getHeight()) {
	        this.move(0, -y);
		}
    }
    
	@Override
    public void moveDown(final double y) {
		this.move(0, y);
    }
    
	@Override
    public void moveRight(final double x) {
		if (this.getPosition().get().getX() + x + this.getDimension().get().getWidth() 
				<= this.gameEnvironment.getDimension().getWidth()){
			this.move(x, 0);
		} 
    }
    
	@Override
    public void moveLeft(final double x) {
		if (this.getPosition().get().getX() - x  - this.getDimension().get().getWidth() >= 0) {
			this.move(-x, 0);
		}
    }
    
    private void move(final double x, final double y) {
        this.speedVector.vectorSum(x, y);
    }
	
}
