package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;

public enum GameEntities {
  /** User-controlled player. */
  PLAYER,
  /** Enemies of the player. */
  ENEMY,
  /** Collectable items that have an effect on the entity who picks it up. */
  PICKUP_ITEM,
  /** Platform-like obstacles that inflicted damage. */
  OBSTACLE,
  /** Weapons the player can pick up and use. */
  WEAPON,
  /** Bullet shoot out of the weapon on user input. */
  BULLET,
  /** Squared objects that float onto the background. */
  PLATFORM;

  /** The number of entity types present in the game. */
  private static final int ENTITY_TYPES = 7;

  /**
   * Simple getter method for {@link ENTITY_TYPES}.
   *
   * @return the number of entity types.
   */
  public static int count() {
    return GameEntities.ENTITY_TYPES;
  }

  public static final List<GameEntities> getList() {
    return List.of(PLAYER, ENEMY, PICKUP_ITEM, OBSTACLE, WEAPON, BULLET, PLATFORM);
  }
}
