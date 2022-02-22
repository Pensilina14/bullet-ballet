package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;

public interface PhysicalObjectSpriteFactory {

    PhysicalObjectSprite generatePlatformSprite(Images.Platforms platformTheme, MutablePosition2D position, Platform platform) throws IOException;
    PhysicalObjectSprite generateBulletSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateEnemySprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateDynamicObstacleSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateBunnySprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateHealingItemSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateDamagingItemSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generatePoisoningItemSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateCoinItemSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateGunWeaponSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateShotgunWeaponSprite(MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateAutogunWeaponSprite(MutablePosition2D position) throws IOException;
}
