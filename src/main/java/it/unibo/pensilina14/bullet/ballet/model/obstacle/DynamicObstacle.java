package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class DynamicObstacle extends AbstractDynamicComponent{

    public DynamicObstacle(final Dimension2D dimension, final Environment gameEnvironment, 
            final double mass, final SpeedVector2D vector) {
        super(dimension, gameEnvironment, mass, vector);
    }

}
