package it.unibo.pensilina14.bullet.ballet.model.environment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.DynamicPickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

public class GameEnvironmentTest {
  private static final double EARTH_GRAVITY = 9.81;
  private static final double MOON_GRAVITY = 6.673;
  private static final int DEFAULT_DIM = 500;
  private static final double DELTA = 0.01;
  private static final double DEFAULT_MASS = 10.0;
  private static final double DEFAULT_SPEED = 15.0;

  @Test
  public void testGameEnvironment() {
    final Environment gameEnv = new GameEnvironment();

    assertEquals(gameEnv.getGravity(), EARTH_GRAVITY, DELTA);
    assertEquals(gameEnv.getDimension(), new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM));

    final Map<ImmutablePosition2D, Optional<PhysicalObject>> expectedMap = new HashMap<>();
    for (int x = 0; x < gameEnv.getDimension().getWidth(); x++) {
      for (int y = 0; y < gameEnv.getDimension().getHeight(); y++) {
        expectedMap.put(new ImmutablePosition2Dimpl(x, y), Optional.empty());
      }
    }

    assertEquals(gameEnv.getMap(), expectedMap);
  }

  @Test
  public void testGameEnvironmentWithArgs() {
    final Environment gameEnv = new GameEnvironment(MOON_GRAVITY, 
        new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM));

    assertEquals(gameEnv.getGravity(), MOON_GRAVITY, DELTA);
    assertEquals(gameEnv.getDimension(), new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM));

    final Map<ImmutablePosition2D, Optional<PhysicalObject>> expectedMap = new HashMap<>();
    for (int x = 0; x < gameEnv.getDimension().getWidth(); x++) {
      for (int y = 0; y < gameEnv.getDimension().getHeight(); y++) {
        expectedMap.put(new ImmutablePosition2Dimpl(x, y), Optional.empty());
      }
    }

    assertEquals(gameEnv.getMap(), expectedMap);
  }

  @Test
  public void testAddObjToMap() {
    final Environment gameEnv = new GameEnvironment();
    final PhysicalObject obj = new DynamicPickupItem(
            new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
            gameEnv, DEFAULT_MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), DEFAULT_SPEED),
            ITEM_ID.HEART, null); // TODO: substitution of null!!!
    final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);

    final boolean hasBeenAdded = gameEnv.addObjToMap(obj, pos);

    assertTrue(hasBeenAdded);
    assertEquals(gameEnv.findObjInMap(obj).get(), pos);
  }

  @Test
  public void testDeleteObjByPosition() {
    final Environment gameEnv = new GameEnvironment();
    final PhysicalObject obj = new DynamicPickupItem(
            new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
            gameEnv, DEFAULT_MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), DEFAULT_SPEED),
            ITEM_ID.HEART, null); // TODO: substitution of null!!!
    final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);

    final boolean hasBeenAdded = gameEnv.addObjToMap(obj, pos);
    gameEnv.deleteObjByPosition(pos);

    assertTrue(hasBeenAdded);
    assertTrue(gameEnv.findObjInMap(obj).isEmpty());
  }

  @Test
  public void testGetObjListInMap() {
      final Environment gameEnv = new GameEnvironment();
      final PhysicalObject obj = new DynamicPickupItem(
              new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
              gameEnv, DEFAULT_MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), DEFAULT_SPEED),
              ITEM_ID.HEART, null); // TODO: substitution of null!!!
      final PhysicalObject obj2 = new DynamicPickupItem(
              new Dimension2Dimpl(DEFAULT_DIM + 1, DEFAULT_DIM + 1),
              gameEnv, DEFAULT_MASS + 1, new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 1), DEFAULT_SPEED),
              ITEM_ID.HEART, null); // TODO: substitution of null!!!
      final PhysicalObject obj3 = new DynamicPickupItem(
              new Dimension2Dimpl(DEFAULT_DIM + 2, DEFAULT_DIM + 2),
              gameEnv, DEFAULT_MASS + 2, new SpeedVector2DImpl(new MutablePosition2Dimpl(1, 1), DEFAULT_SPEED),
              ITEM_ID.HEART, null); // TODO: substitution of null!!!

      final boolean firstIsAdded = gameEnv.addObjToMap(obj, 
              new ImmutablePosition2Dimpl(obj.getPosition().getX(), obj.getPosition().getY()));
      final boolean secondIsAdded = gameEnv.addObjToMap(obj2,
              new ImmutablePosition2Dimpl(obj2.getPosition().getX(), obj2.getPosition().getY()));
      final boolean thirdIsAdded = gameEnv.addObjToMap(obj3,
              new ImmutablePosition2Dimpl(obj3.getPosition().getX(), obj3.getPosition().getY()));

      assertTrue(firstIsAdded);
      assertTrue(secondIsAdded);
      assertTrue(thirdIsAdded);
      assertTrue(gameEnv.getObjListInMap().isPresent());
      assertEquals(gameEnv.getObjListInMap().get(), List.of(obj, obj2, obj3));
  }
}
