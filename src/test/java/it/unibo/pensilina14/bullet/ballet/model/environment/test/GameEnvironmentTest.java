package it.unibo.pensilina14.bullet.ballet.model.environment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.EntityContainer;
import it.unibo.pensilina14.bullet.ballet.common.EntityManager;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment.GravityConstants;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;

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
  private final WeaponFactory weaponFactory = new WeaponFactoryImpl();
  private final BulletFactory bulletFactory = new BulletFactoryImpl();

  @Test
  public void testGameEnvironment() {
    final Environment gameEnv = new GameEnvironment();

    assertEquals(gameEnv.getGravity(), GravityConstants.TEST.getValue(), DELTA);
    assertTrue(gameEnv.getEntityManager().getPlayer().isEmpty());
    assertEquals(gameEnv.getEntityManager().getEnemies(), Optional.empty());
    assertEquals(gameEnv.getEntityManager().getObstacles(), Optional.empty());
    assertEquals(gameEnv.getEntityManager().getItems(), Optional.empty());
  }

  @Test
  public void testEntityManager() {
    final Environment gameEnv = new GameEnvironment();
    final Player player = this.characterFactory.createPlayer(EntityList.Characters.Player.PLAYER1, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0), gameEnv);
    final Enemy enemy = this.characterFactory.createEnemy(EntityList.Characters.Enemy.ENEMY1, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0), gameEnv);
    final Obstacle obstacle = this.obstacleFactory.createStandardObstacle(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0));
    final Item item = this.itemFactory.createHealingItem(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0));
    final Platform platform = new Platform(new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), 1.0), gameEnv, 1, new Dimension2Dimpl(0, 0));
    final Weapon weapon = this.weaponFactory.createGun(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), 0.0));
    final Bullet bullet = this.bulletFactory.createClassicBullet(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0));

    gameEnv.getEntityManager().setPlayer(player);
    final boolean addEnemyOutput = gameEnv.getEntityManager().addEnemy(enemy);
    final boolean addObstacleOutput = gameEnv.getEntityManager().addObstacle(obstacle);
    final boolean addItemOutput = gameEnv.getEntityManager().addItem(item);
    final boolean addPlatformOutput = gameEnv.getEntityManager().addPlatform(platform);
    final boolean addWeaponOutput = gameEnv.getEntityManager().addWeapon(weapon);
    final boolean addBulletOutput = gameEnv.getEntityManager().addBullet(bullet);

    assertTrue(addEnemyOutput);
    assertTrue(addObstacleOutput);
    assertTrue(addItemOutput);
    assertTrue(addPlatformOutput);
    assertTrue(addWeaponOutput);
    assertTrue(addBulletOutput);
    assertEquals(gameEnv.getGravity(), GravityConstants.TEST.getValue(), DELTA);
    assertEquals(gameEnv.getEntityManager().getPlayer(), Optional.of(player));
    assertEquals(gameEnv.getEntityManager().getEnemies(), Optional.of(List.of(enemy)));
    assertEquals(gameEnv.getEntityManager().getObstacles(), Optional.of(List.of(obstacle)));
    assertEquals(gameEnv.getEntityManager().getItems(), Optional.of(List.of(item)));
    assertEquals(gameEnv.getEntityManager().getPlatforms(), Optional.of(List.of(platform)));
    assertEquals(gameEnv.getEntityManager().getWeapons(), Optional.of(List.of(weapon)));
    assertEquals(gameEnv.getEntityManager().getBullets(), Optional.of(List.of(bullet)));
  }

  @Test
  public void testDeleteObjByPosition() {
	/*
	 * DECLARATION
	 */
	final Environment gameEnv = new GameEnvironment();
	final Player player = this.characterFactory.createPlayer(EntityList.Characters.Player.PLAYER1, new SpeedVector2DImpl(new MutablePosition2Dimpl(0.0, 0.0), 0.0), gameEnv);
	final Obstacle obstacle = this.obstacleFactory.createStandardObstacle(gameEnv, 
			new SpeedVector2DImpl(new MutablePosition2Dimpl(100, 0), DEFAULT_SPEED));
	final Item item = this.itemFactory.createDamagingItem(gameEnv, 
			new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 100), DEFAULT_SPEED));
	final Weapon weapon = this.weaponFactory.createAuto(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(100, 0), DEFAULT_SPEED));
	final Bullet bullet = new BulletFactoryImpl().createClassicBullet(gameEnv, new SpeedVector2DImpl(new MutablePosition2Dimpl(101, 100), DEFAULT_SPEED));
	/*
	 * ELABORATION
	 * #subtest1 -- player
	 */
	final ImmutablePosition2D pos = new ImmutablePosition2Dimpl(0, 0);
	gameEnv.deleteObjByPosition(pos);
	// #subtest2 -- obstacles
	final ImmutablePosition2D pos2 = new ImmutablePosition2Dimpl(100, 0);
	final boolean addObstacleOutput = gameEnv.getEntityManager().addObstacle(obstacle);
	final Optional<List<ObstacleImpl>> obstaclesBefore = gameEnv.getEntityManager().getObstacles();
	gameEnv.deleteObjByPosition(pos2);
	final Optional<List<ObstacleImpl>> obstaclesAfter = gameEnv.getEntityManager().getObstacles();
	// #subtest3 -- items
	final ImmutablePosition2D pos3 = new ImmutablePosition2Dimpl(0, 100);
	final boolean addItemOutput = gameEnv.getEntityManager().addItem(item);
	final Optional<List<PickupItem>> itemsBefore = gameEnv.getEntityManager().getItems();
	gameEnv.deleteObjByPosition(pos3);
	final Optional<List<PickupItem>> itemsAfter = gameEnv.getEntityManager().getItems();
	// #subtest4 -- weapons
	final ImmutablePosition2D pos4 = new ImmutablePosition2Dimpl(100, 100);
	final boolean addWeaponOutput = gameEnv.getEntityManager().addWeapon(weapon);
	final Optional<List<Weapon>> weaponsBefore = gameEnv.getEntityManager().getWeapons();
	gameEnv.deleteObjByPosition(pos4);
	final Optional<List<Weapon>> weaponsAfter = gameEnv.getEntityManager().getWeapons();
	// #subtest5 -- bullets
		final ImmutablePosition2D pos5 = new ImmutablePosition2Dimpl(101, 100);
		final boolean addBulletOutput = gameEnv.getEntityManager().addBullet(bullet);
		final Optional<List<Bullet>> bulletsBefore = gameEnv.getEntityManager().getBullets();
		gameEnv.deleteObjByPosition(pos5);
		final Optional<List<Bullet>> bulletsAfter = gameEnv.getEntityManager().getBullets();
	/*
	 * ASSERTIONS
	 * #subtestassert1 -- player
	 */
	assertTrue(gameEnv.getEntityManager().getPlayer().isEmpty());
	// #subtestassert2 -- obstacles
	assertTrue(addObstacleOutput);
	assertEquals(obstaclesBefore, Optional.of(List.of(obstacle)));
	assertTrue(obstaclesAfter.isPresent());
	// #subtestassert3 -- items
	assertTrue(addItemOutput);
	assertEquals(itemsBefore, Optional.of(List.of(item)));
	assertTrue(itemsAfter.isPresent());
	// #subtestassert4 -- weapons
	assertTrue(addWeaponOutput);
	assertEquals(weaponsBefore, Optional.of(List.of(weapon)));
	assertTrue(weaponsAfter.isPresent());
	// #subtestassert5 -- bullets
	assertTrue(addBulletOutput);
	assertEquals(bulletsBefore, Optional.of(List.of(bullet)));
	assertTrue(bulletsAfter.isPresent());
  }
}
