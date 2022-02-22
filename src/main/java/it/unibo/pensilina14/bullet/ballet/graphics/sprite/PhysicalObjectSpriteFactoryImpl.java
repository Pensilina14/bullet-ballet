package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;

public class PhysicalObjectSpriteFactoryImpl implements PhysicalObjectSpriteFactory{

    private static final double SPEED = 1.5;
    private final BulletFactory bulletFact = new BulletFactoryImpl();
    private final ObstacleFactory obstacleFact = new ObstacleFactoryImpl();
    private final ItemFactory itemFact = new ItemFactoryImpl();
    private final WeaponFactory weaponFact = new WeaponFactoryImpl();
    private final GameState gameState;

    public PhysicalObjectSpriteFactoryImpl(final GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public PhysicalObjectSprite generateBulletSprite(final MutablePosition2D position) throws IOException {
        final PhysicalObject bullet = bulletFact
                .createClassicBullet(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.BULLET, position, bullet);
    }

    @Override
    public PhysicalObjectSprite generateDynamicObstacleSprite(final MutablePosition2D position) throws IOException {
        final PhysicalObject dynamicObstacle = obstacleFact
                .createStandardObstacle(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.WUT, position, dynamicObstacle);
    }

    @Override
    public PhysicalObjectSprite generateBunnySprite(final MutablePosition2D position) throws IOException {
        final PhysicalObject staticObstacle = obstacleFact
                .createStandardObstacle(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.BUNNY, position, staticObstacle);
    }

    @Override
    public PhysicalObjectSprite generateHealingItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createHealingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.HEALING_ITEM, position, item);
    }

    @Override
    public PhysicalObjectSprite generateDamagingItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createDamagingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.DAMAGING_ITEM, position, item);
    }

    @Override
    public PhysicalObjectSprite generatePoisoningItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createPoisoningItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.POISONING_ITEM, position, item);
    }
    
    @Override
    public PhysicalObjectSprite generateCoinItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createPoisoningItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.COIN, position, item);
    }

	@Override
	public PhysicalObjectSprite generateGunWeaponSprite(final MutablePosition2D position) throws IOException {
		final PhysicalObject gun = weaponFact
				.createGun(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
		return new PhysicalObjectSprite(Images.GUN, position, gun);
	}

	@Override
	public PhysicalObjectSprite generateShotgunWeaponSprite(final MutablePosition2D position) throws IOException {
		final PhysicalObject shotgun = weaponFact
				.createShotGun(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
		return new PhysicalObjectSprite(Images.SHOTGUN, position, shotgun);
	}

	@Override
	public PhysicalObjectSprite generateAutogunWeaponSprite(final MutablePosition2D position) throws IOException {
		final PhysicalObject autogun = weaponFact
				.createAuto(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
		return new PhysicalObjectSprite(Images.AUTO, position, autogun);
	}
}
