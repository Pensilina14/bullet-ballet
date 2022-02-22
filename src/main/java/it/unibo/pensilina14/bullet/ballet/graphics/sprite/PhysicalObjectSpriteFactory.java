package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public interface PhysicalObjectSpriteFactory {

    PhysicalObjectSprite generatePlatformSprite(Images.Platforms platformTheme, PhysicalObject platform) throws IOException;
    PhysicalObjectSprite generateBulletSprite(PhysicalObject bullet) throws IOException;
    PhysicalObjectSprite generateEnemySprite(PhysicalObject enemy) throws IOException;
    PhysicalObjectSprite generateDynamicObstacleSprite(PhysicalObject dynamicObstacle) throws IOException;
    PhysicalObjectSprite generateBunnySprite(PhysicalObject bunny) throws IOException;
    PhysicalObjectSprite generateHealingItemSprite(PhysicalObject healingItem) throws IOException;
    PhysicalObjectSprite generateDamagingItemSprite(PhysicalObject damagingItem) throws IOException;
    PhysicalObjectSprite generatePoisoningItemSprite(PhysicalObject poisoningItem) throws IOException;
    PhysicalObjectSprite generateCoinItemSprite(Images.Coins coins, PhysicalObject coin) throws IOException;
    PhysicalObjectSprite generateGunWeaponSprite(PhysicalObject gun) throws IOException;
    PhysicalObjectSprite generateShotgunWeaponSprite(PhysicalObject shotgun) throws IOException;
    PhysicalObjectSprite generateAutogunWeaponSprite(PhysicalObject autogun) throws IOException;
}
