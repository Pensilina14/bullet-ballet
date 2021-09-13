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
            super(new Dimension2Dimpl(2, 2), new MutablePosition2Dimpl(0, 0)
                    , new GameEnvironment(), 10);
        }
        
    }
    
    @Test
    public void testMutablePosition2D() {
        final PhysicalObject component = new AbstractDynamicComponentImpl();
        final MutablePosition2D position = component.getPosition();
        assertEquals(position.getCoordinates(), MutablePair.of(0, 0));
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
        isMoving = component.move(-506, -506);
        assertTrue(!isMoving);
    }
    
    @Test
    public void testMoveUP() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = component.moveUP(10);
        assertTrue(!isMoving);
        component.getPosition().setPosition(-20, -20);
        isMoving = component.moveUP(10);
        assertTrue(isMoving);
    }
    
    @Test
    public void testMoveDOWN() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = component.moveDOWN(10);
        assertTrue(!isMoving);
        isMoving = component.moveDOWN(1000);
        assertTrue(!isMoving);
    }
    
    @Test
    public void testMoveRIGHT() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = component.moveRIGHT(10);
        assertTrue(!isMoving);
        isMoving = component.moveRIGHT(2000);
        assertTrue(!isMoving);
    }
    
    @Test
    public void testMoveLEFT() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = component.moveLEFT(10);
        assertTrue(isMoving);
        isMoving = component.moveLEFT(2000);
        assertTrue(!isMoving);
    }
    
    
    
}
