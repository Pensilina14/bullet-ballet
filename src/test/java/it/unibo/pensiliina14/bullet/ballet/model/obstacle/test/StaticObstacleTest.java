package it.unibo.pensiliina14.bullet.ballet.model.obstacle.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;

public class StaticObstacleTest {

    private static final double DIM = 1.0;
    private static final double POS = -5.0;
    
    private final StaticObstacle staticObstacle = new StaticObstacle(new Dimension2Dimpl(DIM, DIM)
            , new MutablePosition2Dimpl(POS, POS), new GameEnvironment());
    
    @Test
    public void testGetPosition() {
        final MutablePosition2D position = staticObstacle.getPosition();
        assertEquals(position.getCoordinates(), MutablePair.of(POS, POS));
    }
    
    @Test
    public void testGetDimension() {
        final Dimension2D dimension = staticObstacle.getDimension();
        assertEquals(dimension.getSize(), ImmutablePair.of(DIM, DIM));
    }
    
}
