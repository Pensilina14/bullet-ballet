package it.unibo.pensilina14.bullet.ballet.environment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.game.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.weapon.PickupItem;

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
    final PhysicalObject obj = new PickupItem(
            new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
            new MutablePosition2Dimpl(0, 0),
            gameEnv, DEFAULT_MASS, null); // TODO: substitution of null!!!
    final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);

    final boolean hasBeenAdded = gameEnv.addObjToMap(obj, pos);

    assertTrue(hasBeenAdded);
    assertEquals(gameEnv.findObjInMap(obj).get(), pos);
  }

  @Test
  public void testDeleteObjByPosition() {
    final Environment gameEnv = new GameEnvironment();
    final PhysicalObject obj = new PickupItem(
            new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
            new MutablePosition2Dimpl(0, 0),
            gameEnv, DEFAULT_MASS, null); // TODO: substitution of null!!!
    final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);

    final boolean hasBeenAdded = gameEnv.addObjToMap(obj, pos);
    gameEnv.deleteObjByPosition(pos);

    assertTrue(hasBeenAdded);
    assertEquals(gameEnv.findObjInMap(obj).get(), Optional.empty());
  }

  @Test
  public void testGetObjListInMap() {
      final Environment gameEnv = new GameEnvironment();
      final PhysicalObject obj = new PickupItem(
              new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM),
              new MutablePosition2Dimpl(0, 0),
              gameEnv, DEFAULT_MASS, null); // TODO: substitution of null!!!
      final PhysicalObject obj2 = new PickupItem(
              new Dimension2Dimpl(DEFAULT_DIM + 1, DEFAULT_DIM + 1),
              new MutablePosition2Dimpl(0, 1),
              gameEnv, DEFAULT_MASS + 1, null); // TODO: substitution of null!!!
      final PhysicalObject obj3 = new PickupItem(
              new Dimension2Dimpl(DEFAULT_DIM - 1, DEFAULT_DIM - 1),
              new MutablePosition2Dimpl(1, 1),
              gameEnv, DEFAULT_MASS - 1, null); // TODO: substitution of null!!!

      final boolean firstIsAdded = gameEnv.addObjToMap(obj, 
              new ImmutablePosition2Dimpl(obj.getPosition().getX(), obj.getPosition().getY()));
      final boolean secondIsAdded = gameEnv.addObjToMap(obj2,
              new ImmutablePosition2Dimpl(obj2.getPosition().getX(), obj2.getPosition().getY()));
      final boolean thirdIsAdded = gameEnv.addObjToMap(obj3,
              new ImmutablePosition2Dimpl(obj3.getPosition().getX(), obj3.getPosition().getY()));

      assertTrue(firstIsAdded);
      assertTrue(secondIsAdded);
      assertTrue(thirdIsAdded);
      assertEquals(gameEnv.getObjListInMap().get(), Optional.empty());
      assertEquals(gameEnv.getObjListInMap().get(), List.of(obj, obj2, obj3));
  }
}
