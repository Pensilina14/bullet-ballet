package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class PhysicalObjectSpriteFactoryImpl implements PhysicalObjectSpriteFactory {

    @Override
    public final PhysicalObjectSprite generatePlatformSprite(final Images.Platforms platformTheme, final PhysicalObject platform) throws IOException {
        return new PhysicalObjectSprite(platformTheme, platform.getPosition().get(), platform);
    }

    @Override
    public final PhysicalObjectSprite generateBulletSprite(final PhysicalObject bullet) throws IOException {
        return new PhysicalObjectSprite(Images.BULLET, bullet.getPosition().get(), bullet);
    }

    @Override
    public final PhysicalObjectSprite generateEnemySprite(final PhysicalObject enemy) throws IOException {
        return new PhysicalObjectSprite(Images.ENEMY, enemy.getPosition().get(), enemy);
    }

    @Override
    public final PhysicalObjectSprite generateDynamicObstacleSprite(final PhysicalObject dynamicObstacle) throws IOException {
        return new PhysicalObjectSprite(Images.WUT, dynamicObstacle.getPosition().get(), dynamicObstacle);
    }

    @Override
    public final PhysicalObjectSprite generateBunnySprite(final PhysicalObject bunny) throws IOException {
        return new PhysicalObjectSprite(Images.BUNNY, bunny.getPosition().get(), bunny);
    }

    @Override
    public final PhysicalObjectSprite generateHealingItemSprite(final PhysicalObject healingItem) throws IOException {
        return new PhysicalObjectSprite(Images.HEALING_ITEM, healingItem.getPosition().get(), healingItem);
    }

    @Override
    public final PhysicalObjectSprite generateDamagingItemSprite(final PhysicalObject damagingItem) throws IOException {
        return new PhysicalObjectSprite(Images.DAMAGING_ITEM, damagingItem.getPosition().get(), damagingItem);
    }

    @Override
    public final PhysicalObjectSprite generatePoisoningItemSprite(final PhysicalObject poisoningItem) throws IOException {
        return new PhysicalObjectSprite(Images.POISONING_ITEM, poisoningItem.getPosition().get(), poisoningItem);
    }

    @Override
    public final PhysicalObjectSprite generateCoinItemSprite(final Images.Coins coinTheme, final PhysicalObject coin) throws IOException {
        return new PhysicalObjectSprite(coinTheme, coin.getPosition().get(), coin);
    }

	@Override
	public final PhysicalObjectSprite generateGunWeaponSprite(final PhysicalObject gun) throws IOException {
		return new PhysicalObjectSprite(Images.GUN, gun.getPosition().get(), gun);
	}

	@Override
	public final PhysicalObjectSprite generateShotgunWeaponSprite(final PhysicalObject shotgun) throws IOException {
		return new PhysicalObjectSprite(Images.SHOTGUN, shotgun.getPosition().get(), shotgun);
	}

	@Override
	public final PhysicalObjectSprite generateAutogunWeaponSprite(final PhysicalObject autogun) throws IOException {
		return new PhysicalObjectSprite(Images.AUTO, autogun.getPosition().get(), autogun);
	}
	
	@Override
	public final PhysicalObjectSprite generateAmmoSprite(final PhysicalObject ammo) throws IOException {
		return new PhysicalObjectSprite(Images.AMMO, ammo.getPosition().get(), ammo);
	}

    @Override
    public PhysicalObjectSprite generateFlagSprite(final PhysicalObject flag) throws IOException {
        return new PhysicalObjectSprite(Images.FLAG, flag.getPosition().get(), flag);
    }
}
