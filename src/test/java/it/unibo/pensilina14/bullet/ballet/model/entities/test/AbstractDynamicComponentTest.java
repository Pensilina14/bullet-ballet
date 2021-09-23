package it.unibo.pensilina14.bullet.ballet.model.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;

public class AbstractDynamicComponentTest {
    
    private final static double APPROXIMATION = 0.001;
    private final static double SPEED = 2.0;
    private final static int SIZE = 1;
    private final static double MASS = 5;
    private final static double POSITION = -5;
    private final static double TIME = 10;
    private final static double MS_TO_S = 0.001;
    
    
    private class AbstractDynamicComponentImpl extends AbstractDynamicComponent {

        public AbstractDynamicComponentImpl() {
            super(new Dimension2Dimpl(SIZE, SIZE), new GameEnvironment(), MASS
                    , new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED));
        }
        
    }
    
    @Test
    public void testMutablePosition2D() {
        final PhysicalObject component = new AbstractDynamicComponentImpl();
        final MutablePosition2D position = component.getPosition();
        assertEquals(position.getCoordinates(), MutablePair.of(-5.0, -5.0));
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
        isMoving = !component.move(-506, -506);
        assertTrue(isMoving);
    }
    
    @Test
    public void testMoveUP() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = !component.moveUP(10);
        final double yAxisPositionBeforeMovement = component.getPosition().getY();
        assertTrue(isMoving);
        isMoving = component.moveUP(2);
        assertTrue(isMoving);
        assertEquals(yAxisPositionBeforeMovement + (2 * component.getSpeedVector().getSpeed())
                , component.getPosition().getY(), APPROXIMATION);
    }
    
    
    @Test
    public void testMoveDOWN() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        final double yAxisPositionBeforeMovement = component.getPosition().getY();
        boolean isMoving = component.moveDOWN(10);
        assertTrue(isMoving);
        isMoving = !component.moveDOWN(245);
        assertTrue(isMoving);
        assertEquals(yAxisPositionBeforeMovement - (10 * component.getSpeedVector().getSpeed())
                , component.getPosition().getY(), APPROXIMATION);
        
    }
    
    @Test
    public void testMoveRIGHT() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        boolean isMoving = !component.moveRIGHT(10);
        assertTrue(isMoving);
        component.getPosition().setPosition(-100, -10);
        final double xAxisPositionBeforeMovement = component.getPosition().getX();
        isMoving = component.moveRIGHT(10);
        assertTrue(isMoving);
        assertEquals(xAxisPositionBeforeMovement + (10 * component.getSpeedVector().getSpeed())
                , component.getPosition().getX(), APPROXIMATION);
    }
    
    @Test
    public void testMoveLEFT() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        final double xAxisPositionBeforeMovement = component.getPosition().getX();
        boolean isMoving = component.moveLEFT(10);
        assertTrue(isMoving);
        isMoving = !component.moveLEFT(243);
        assertTrue(isMoving);
        assertEquals(xAxisPositionBeforeMovement - (10 * component.getSpeedVector().getSpeed())
                , component.getPosition().getX(), APPROXIMATION);
    }
    
    @Test
    public void testUpdateState() {
        final AbstractDynamicComponent component = new AbstractDynamicComponentImpl();
        final SpeedVector2D vector = component.getSpeedVector();
        final MutablePosition2D prevPosition = vector.getPosition();
        prevPosition.setPosition(component.getPosition().getX() + TIME * MS_TO_S, 
                component.getPosition().getY() + TIME * MS_TO_S);
        component.updateState(TIME);
        assertEquals(component.getPosition(), prevPosition); 
    }
}
