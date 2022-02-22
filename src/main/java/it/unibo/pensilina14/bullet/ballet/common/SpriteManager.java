package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;

public interface SpriteManager {
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlayerSprite();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlatformsSprites();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getEnemiesSprites();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getItemsSprites();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getObstaclesSprites();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getWeaponsSprites();
    Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getBulletsSprites();

    void addPlayerSprite(PhysicalObjectSprite playerSprite, MutablePosition2D position);
    void addPlatformSprite(PhysicalObjectSprite platformSprite, MutablePosition2D position);
    void addEnemySprite(PhysicalObjectSprite enemySprite, MutablePosition2D position);
    void addItemSprite(PhysicalObjectSprite itemSprite, MutablePosition2D position);
    void addObstacleSprite(PhysicalObjectSprite obstacleSprite, MutablePosition2D position);
    void addWeaponSprite(PhysicalObjectSprite weaponSprite, MutablePosition2D position);
    void addBulletSprite(PhysicalObjectSprite bulletSprite, MutablePosition2D position);

    void deletePlayerSprite();
    void deletePlatformSprite(MutablePosition2D position);
    void deleteEnemySprite(MutablePosition2D position);
    void deleteItemSprite(MutablePosition2D position);
    void deleteObstacleSprite(MutablePosition2D position);
    void deleteWeaponSprite(MutablePosition2D position);
    void deleteBulletSprite(MutablePosition2D position);
}
