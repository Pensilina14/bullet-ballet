package it.unibo.pensilina14.bullet.ballet.common;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

/**
 * This interface gives to its implementors a set of methods that permit to build an {@link
 * EntityManager}.
 */
public interface EntityManagerBuilder {
  /**
   * Sets a given player({@link Player}).
   *
   * @param player (to be set)
   * @return the builder.
   */
  EntityManagerBuilder addPlayer(Player player);
  /**
   * Adds an enemy({@link Enemy}) to the game.
   *
   * @param enemy
   * @return the builder.
   */
  EntityManagerBuilder addEnemy(Enemy enemy);
  /**
   * Adds a pickup item({@link PickupItem}) to the game.
   *
   * @param item
   * @return the builder.
   */
  EntityManagerBuilder addItem(Item item);
  /**
   * Adds an obstacle({@link Obstacle}) to the game.
   *
   * @param obstacle
   * @return the builder.
   */
  EntityManagerBuilder addObstacle(Obstacle obstacle);
  /**
   * @param platform indicates the {@link Platform} to be added.
   * @return the builder.
   */
  EntityManagerBuilder addPlatform(Platform platform);
  /**
   * Adds a weapon({@link Weapon}) to the game.
   *
   * @param weapon
   * @return the builder.
   */
  EntityManagerBuilder addWeapon(Weapon weapon);
  /**
   * Adds a bullet({@link Bullet}) to the game.
   *
   * @param bullet
   * @return the builder.
   */
  EntityManagerBuilder addBullet(Bullet bullet);
  /**
   * Builds the entire {@link EntityManager} constructed along the way.
   *
   * @return the resulting {@link EntityManager}.
   */
  EntityManager build();
}
