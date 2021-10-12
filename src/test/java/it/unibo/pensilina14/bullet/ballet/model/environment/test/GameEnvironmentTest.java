package it.unibo.pensilina14.bullet.ballet.model.environment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment.GravityConstants;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class GameEnvironmentTest {
  private static final int DEFAULT_DIM = 50;
  private static final double DELTA = 0.01;
  private static final double DEFAULT_MASS = 10.0;
  private static final double DEFAULT_SPEED = 15.0;
  
  private final FactoryCharacters characterFactory = new FactoryCharactersImpl();
  private final ObstacleFactory obstacleFactory = new ObstacleFactoryImpl();
  private final ItemFactory itemFactory = new ItemFactoryImpl();
  private final GameEventListener evListener = new GameEngine();

  @Test
  public void testGameEnvironment() {
    final Environment gameEnv = new GameEnvironment();
    
    assertEquals(gameEnv.getGravity(), GravityConstants.EARTH.getValue(), DELTA);
    assertTrue(gameEnv.getPlayer().isEmpty());
    assertEquals(gameEnv.getEnemies().get(), new ArrayList<>());
    assertEquals(gameEnv.getObstacles().get(), new ArrayList<>());
    assertEquals(gameEnv.getItems().get(), new ArrayList<>());
  }

  @Test
  public void testGameEnvironmentWithArgs() {
	final Player player = this.characterFactory.createPlayer(EntityList.Characters.Player.PLAYER1);
	final Environment gameEnv = new GameEnvironment(GravityConstants.MOON.getValue(), GameEnvironment.DEFAULT_DIM, GameEnvironment.DEFAULT_DIM, Optional.of(player), this.evListener);

    assertEquals(gameEnv.getGravity(), GravityConstants.MOON.getValue(), DELTA);
    assertEquals(gameEnv.getPlayer(), Optional.of(player));
    assertEquals(gameEnv.getEnemies().get(), new ArrayList<>());
    assertEquals(gameEnv.getObstacles().get(), new ArrayList<>());
    assertEquals(gameEnv.getItems().get(), new ArrayList<>());
  }

  @Test
  public void testDeleteObjByPosition() {
	/*
	 * DECLARATION
	 */
	final Player player = this.characterFactory.createPlayer(EntityList.Characters.Player.PLAYER1);
	final Environment gameEnv = new GameEnvironment(GravityConstants.MOON.getValue(), GameEnvironment.DEFAULT_DIM, GameEnvironment.DEFAULT_DIM, Optional.of(player), this.evListener);
	/*
	 * ELABORATION
	 * #subtest1 -- player
	 */
	final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);
	gameEnv.deleteObjByPosition(pos);
	// #subtest2 -- obstacles
	final ImmutablePosition2D pos2 = new ImmutablePosition2Dimpl(100, 0);
	final DynamicObstacle obstacle = this.obstacleFactory.createDynamicObstacle(gameEnv, 
			new SpeedVector2DImpl(new MutablePosition2Dimpl(100, 0), DEFAULT_SPEED));
	gameEnv.addObstacle(obstacle);
	final Optional<List<PhysicalObject>> obstaclesBefore = gameEnv.getObstacles();
	gameEnv.deleteObjByPosition(pos2);
	final Optional<List<PhysicalObject>> obstaclesAfter = gameEnv.getObstacles();
	// #subtest3 -- items
	final ImmutablePosition2D pos3 = new ImmutablePosition2Dimpl(0, 100);
	final Item item = this.itemFactory.createDamagingItem(gameEnv, 
			new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 100), DEFAULT_SPEED));
	gameEnv.addItem(item);
	final Optional<List<Item>> itemsBefore = gameEnv.getItems();
	gameEnv.deleteObjByPosition(pos3);
	final Optional<List<Item>> itemsAfter = gameEnv.getItems();
	/*
	 * ASSERTIONS
	 * #subtestassert1 -- player
	 */
	assertTrue(gameEnv.getPlayer().isEmpty());
	// #subtestassert2 -- obstacles
	assertEquals(obstaclesBefore, Optional.of(List.of(obstacle)));
	assertTrue(obstaclesAfter.isPresent());
	// #subtestassert3 -- items
	assertEquals(itemsBefore, Optional.of(List.of(item)));
	assertTrue(itemsAfter.isPresent());
  }
}
