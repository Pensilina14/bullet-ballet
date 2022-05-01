package it.unibo.pensilina14.bullet.ballet.common.test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.common.SpriteContainer;
import it.unibo.pensilina14.bullet.ballet.common.SpriteManager;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.*;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpriteContainerTest {
  /**
   * The following X values indicate X axis position of the different entities. This is useful in
   * order to differentiate them.
   */
  enum TestXs {
    PLAYER_X(0),
    ENEMY_X(1),
    ITEM_X(2),
    OBSTACLE_X(3),
    WEAPON_X(4),
    BULLET_X(5),
    PLATFORM_X(6);

    private final int value;

    TestXs(final int value) {
      this.value = value;
    }

    public final int getValue() {
      return this.value;
    }
  }

  private final LevelGenerator lvlGenerator = new EnvironmentGenerator();

  private final SpriteManager sprites = new SpriteContainer();
  private final PhysicalObjectSpriteFactory spriteFactory = new PhysicalObjectSpriteFactoryImpl();

  private final FactoryCharacters characterFactory = new FactoryCharactersImpl();
  private final ItemFactory itemFactory = new ItemFactoryImpl();
  private final ObstacleFactory obstacleFactory = new ObstacleFactoryImpl();
  private final WeaponFactory weaponFactory = new WeaponFactoryImpl();
  private final BulletFactory bulletFactory = new BulletFactoryImpl();
  private final PlatformFactory platformFactory = new PlatformFactoryImpl(this.lvlGenerator);

  private final Environment testEnvironment = new GameEnvironment();

  @Test
  public void testAddSprites() throws IOException {
    /*
     * DECLARATION
     */
    // -- PLAYER --
    final PhysicalObject player =
        characterFactory.createPlayer(
            EntityList.Characters.Player.PLAYER1,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.PLAYER_X.getValue(), 0), 0.0),
            this.testEnvironment);
    final PhysicalObjectSprite playerSprite = spriteFactory.generatePlayerSprite(player);
    // -- ENEMY --
    final PhysicalObject enemy =
        characterFactory.createEnemy(
            EntityList.Characters.Enemy.ENEMY1,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.ENEMY_X.getValue(), 0), 0.0),
            this.testEnvironment);
    final PhysicalObjectSprite enemySprite = spriteFactory.generateEnemySprite(enemy);
    // -- ITEM --
    final PhysicalObject item =
        itemFactory.createHealingItem(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.ITEM_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite itemSprite = spriteFactory.generateHealingItemSprite(item);
    // -- OBSTACLE --
    final PhysicalObject obstacle =
        obstacleFactory.createStandardObstacle(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.OBSTACLE_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite obstacleSprite =
        spriteFactory.generateDynamicObstacleSprite(obstacle);
    // -- WEAPON --
    final PhysicalObject weapon =
        weaponFactory.createGun(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.WEAPON_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite weaponSprite = spriteFactory.generateGunWeaponSprite(weapon);
    // -- BULLET --
    final PhysicalObject bullet =
        bulletFactory.createClassicBullet(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.BULLET_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite bulletSprite = spriteFactory.generateBulletSprite(bullet);
    // -- PLATFORM --
    final PhysicalObject platform =
        platformFactory.createPlatform(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.PLATFORM_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite platformSprite =
        spriteFactory.generateDefaultPlatformSprite(platform);
    /*
     * ELABORATION
     */
    // -- PLAYER --
    final boolean addPlayerSpriteOutput =
        this.sprites.addPlayerSprite(playerSprite, player.getPosition().get());
    // -- ENEMY --
    final boolean addEnemySpriteOutput =
        this.sprites.addEnemySprite(enemySprite, enemy.getPosition().get());
    // -- ITEM --
    final boolean addItemSpriteOutput =
        this.sprites.addItemSprite(itemSprite, item.getPosition().get());
    // -- OBSTACLE --
    final boolean addObstacleSpriteOutput =
        this.sprites.addObstacleSprite(obstacleSprite, obstacle.getPosition().get());
    // -- WEAPON --
    final boolean addWeaponSpriteOutput =
        this.sprites.addWeaponSprite(weaponSprite, weapon.getPosition().get());
    // -- BULLET --
    final boolean addBulletSpriteOutput =
        this.sprites.addBulletSprite(bulletSprite, bullet.getPosition().get());
    // -- PLATFORM --
    final boolean addPlatformSpriteOutput =
        this.sprites.addPlatformSprite(platformSprite, platform.getPosition().get());
    /*
     * ASSERTIONS
     */
    // -- PLAYER --
    assertTrue(addPlayerSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(playerSprite, player.getPosition().get()))),
        this.sprites.getPlayerSprite());
    // -- ENEMY --
    assertTrue(addEnemySpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(enemySprite, enemy.getPosition().get()))),
        this.sprites.getEnemiesSprites());
    // -- ITEM --
    assertTrue(addItemSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(itemSprite, item.getPosition().get()))),
        this.sprites.getItemsSprites());
    // -- OBSTACLE --
    assertTrue(addObstacleSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(obstacleSprite, obstacle.getPosition().get()))),
        this.sprites.getObstaclesSprites());
    // -- WEAPON --
    assertTrue(addWeaponSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(weaponSprite, weapon.getPosition().get()))),
        this.sprites.getWeaponsSprites());
    // -- BULLET --
    assertTrue(addBulletSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(bulletSprite, bullet.getPosition().get()))),
        this.sprites.getBulletsSprites());
    // -- PLATFORM --
    assertTrue(addPlatformSpriteOutput);
    assertEquals(
        Optional.of(List.of(new ImmutablePair<>(platformSprite, platform.getPosition().get()))),
        this.sprites.getPlatformsSprites());
  }

  @Test
  public void testDeleteSprites() throws IOException {
    /*
     * DECLARATION
     */
    // -- PLAYER --
    final PhysicalObject player =
        characterFactory.createPlayer(
            EntityList.Characters.Player.PLAYER1,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.PLAYER_X.getValue(), 0), 0.0),
            this.testEnvironment);
    final PhysicalObjectSprite playerSprite = spriteFactory.generatePlayerSprite(player);
    // -- ENEMY --
    final PhysicalObject enemy =
        characterFactory.createEnemy(
            EntityList.Characters.Enemy.ENEMY1,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.ENEMY_X.getValue(), 0), 0.0),
            this.testEnvironment);
    final PhysicalObjectSprite enemySprite = spriteFactory.generateEnemySprite(enemy);
    // -- ITEM --
    final PhysicalObject item =
        itemFactory.createHealingItem(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.ITEM_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite itemSprite = spriteFactory.generateHealingItemSprite(item);
    // -- OBSTACLE --
    final PhysicalObject obstacle =
        obstacleFactory.createStandardObstacle(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.OBSTACLE_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite obstacleSprite =
        spriteFactory.generateDynamicObstacleSprite(obstacle);
    // -- WEAPON --
    final PhysicalObject weapon =
        weaponFactory.createGun(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.WEAPON_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite weaponSprite = spriteFactory.generateGunWeaponSprite(weapon);
    // -- BULLET --
    final PhysicalObject bullet =
        bulletFactory.createClassicBullet(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.BULLET_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite bulletSprite = spriteFactory.generateBulletSprite(bullet);
    // -- PLATFORM --
    final PhysicalObject platform =
        platformFactory.createPlatform(
            this.testEnvironment,
            new SpeedVector2DImpl(new MutablePosition2Dimpl(TestXs.PLATFORM_X.getValue(), 0), 0.0));
    final PhysicalObjectSprite platformSprite =
        spriteFactory.generateDefaultPlatformSprite(platform);
    /*
     * ELABORATION
     */
    // -- PLAYER --
    this.sprites.addPlayerSprite(playerSprite, player.getPosition().get());
    this.sprites.getPlayerSprite();
    final Optional<PhysicalObjectSprite> playerDeletionOutput =
        this.sprites.deleteSprite(player.getPosition().get());
    this.sprites.getPlayerSprite();
    // -- ENEMY --
    this.sprites.addEnemySprite(enemySprite, enemy.getPosition().get());
    this.sprites.getEnemiesSprites();
    final Optional<PhysicalObjectSprite> enemyDeletionOutput =
        this.sprites.deleteSprite(enemy.getPosition().get());
    this.sprites.getEnemiesSprites();
    // -- ITEM --
    this.sprites.addItemSprite(itemSprite, item.getPosition().get());
    this.sprites.getItemsSprites();
    final Optional<PhysicalObjectSprite> itemDeletionOutput =
        this.sprites.deleteSprite(item.getPosition().get());
    this.sprites.getItemsSprites();
    // -- OBSTACLE --
    this.sprites.addObstacleSprite(obstacleSprite, obstacle.getPosition().get());
    this.sprites.getObstaclesSprites();
    final Optional<PhysicalObjectSprite> obstacleDeletionOutput =
        this.sprites.deleteSprite(obstacle.getPosition().get());
    this.sprites.getObstaclesSprites();
    // -- WEAPON --
    this.sprites.addWeaponSprite(weaponSprite, weapon.getPosition().get());
    this.sprites.getWeaponsSprites();
    final Optional<PhysicalObjectSprite> weaponDeletionOutput =
        this.sprites.deleteSprite(weapon.getPosition().get());
    this.sprites.getWeaponsSprites();
    // -- BULLET --
    this.sprites.addBulletSprite(bulletSprite, bullet.getPosition().get());
    this.sprites.getBulletsSprites();
    final Optional<PhysicalObjectSprite> bulletDeletionOutput =
        this.sprites.deleteSprite(bullet.getPosition().get());
    this.sprites.getBulletsSprites();
    // -- PLATFORM --
    this.sprites.addPlatformSprite(platformSprite, platform.getPosition().get());
    this.sprites.getPlatformsSprites();
    final Optional<PhysicalObjectSprite> platformDeletionOutput =
        this.sprites.deleteSprite(platform.getPosition().get());
    this.sprites.getPlatformsSprites();
    /*
     * ASSERTIONS
     */
    // -- PLAYER --
    assertEquals(playerSprite, playerDeletionOutput.get());
    assertTrue(this.sprites.getPlayerSprite().isEmpty());
    // -- ENEMY --
    assertEquals(enemySprite, enemyDeletionOutput.get());
    assertTrue(this.sprites.getEnemiesSprites().isEmpty());
    // -- ITEM --
    assertEquals(itemSprite, itemDeletionOutput.get());
    assertTrue(this.sprites.getItemsSprites().isEmpty());
    // -- OBSTACLE --
    assertEquals(obstacleSprite, obstacleDeletionOutput.get());
    assertTrue(this.sprites.getObstaclesSprites().isEmpty());
    // -- WEAPON --
    assertEquals(weaponSprite, weaponDeletionOutput.get());
    assertTrue(this.sprites.getWeaponsSprites().isEmpty());
    // -- BULLET --
    assertEquals(bulletSprite, bulletDeletionOutput.get());
    assertTrue(this.sprites.getBulletsSprites().isEmpty());
    // -- PLATFORM --
    assertEquals(platformSprite, platformDeletionOutput.get());
    assertTrue(this.sprites.getPlatformsSprites().isEmpty());
  }
}
