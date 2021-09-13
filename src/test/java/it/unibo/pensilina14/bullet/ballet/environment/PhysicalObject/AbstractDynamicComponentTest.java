package it.unibo.pensilina14.bullet.ballet.environment.PhysicalObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.game.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.game.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2Dimpl;

public class AbstractDynamicComponentTest {
    
    private final static double APPROXIMATION = 0.001;
    
    private class AbstractDynamicComponentImpl extends AbstractDynamicComponent {

        public AbstractDynamicComponentImpl() {
            super(new Dimension2Dimpl(10, 10), new MutablePosition2Dimpl(5, 5)
                    , new GameEnvironment(), 10);
        }
        
    }
    
    @Test
    public void testMutablePosition2D() {
        final PhysicalObject component = new AbstractDynamicComponentImpl();
        final MutablePosition2D position = component.getPosition();
        assertEquals(position.getCoordinates(), MutablePair.of(5, 5));
    }
    
    @Test
    public void testGravityForce() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        final double gravity = component.getGameEnvironment().getGravity();
        final double mass = component.getMass();
        assertEquals(gravity * mass, component.getGravityForce(), APPROXIMATION);
    }
    
    @Test
    public void testMove() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = component.move(-5, -5);
        assertTrue(isMoving);
        isMoving = component.move(506, 506);
        assertTrue(!isMoving);
    }
    
    
    
}
