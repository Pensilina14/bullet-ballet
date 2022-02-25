package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;

public class PlatformFactoryImpl implements PlatformFactory {
	
	private static final double MASS = 200.0;
    private final double size;
	
    public PlatformFactoryImpl(final LevelGenerator level) {
    	this.size = level.getPlatformSize();
    }
    
    @Override
	public final Platform createPlatform(final Environment env, final SpeedVector2D speedVector) {
        return new Platform(speedVector, env, MASS, new Dimension2Dimpl(this.size, this.size));
	}
}
