package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractStaticComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class StaticObstacle extends AbstractStaticComponent{

    public StaticObstacle(final Dimension2D dimension, final MutablePosition2D position, 
            final Environment gameEnvironment) {
        super(dimension, position, gameEnvironment);
    }
    
}
