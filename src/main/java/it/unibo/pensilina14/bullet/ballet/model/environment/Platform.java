package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractStaticComponent;

public class Platform extends AbstractStaticComponent {
	
	public Platform(final Dimension2D dimension, final MutablePosition2D position, final Environment gameEnvironment) {
		super(dimension, position, gameEnvironment);
	}

}
