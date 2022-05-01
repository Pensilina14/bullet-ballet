package it.unibo.pensilina14.bullet.ballet.common;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Optional;

public interface SpriteManager {
  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlayerSprite();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlatformsSprites();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getEnemiesSprites();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getItemsSprites();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getObstaclesSprites();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getWeaponsSprites();

  Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getBulletsSprites();

  boolean addPlayerSprite(PhysicalObjectSprite playerSprite, MutablePosition2D position);

  boolean addPlatformSprite(PhysicalObjectSprite platformSprite, MutablePosition2D position);

  boolean addEnemySprite(PhysicalObjectSprite enemySprite, MutablePosition2D position);

  boolean addItemSprite(PhysicalObjectSprite itemSprite, MutablePosition2D position);

  boolean addObstacleSprite(PhysicalObjectSprite obstacleSprite, MutablePosition2D position);

  boolean addWeaponSprite(PhysicalObjectSprite weaponSprite, MutablePosition2D position);

  boolean addBulletSprite(PhysicalObjectSprite bulletSprite, MutablePosition2D position);

  Optional<PhysicalObjectSprite> deleteSprite(MutablePosition2D targetPosition);
}
