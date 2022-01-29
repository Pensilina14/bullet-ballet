package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import java.util.List;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.collision.Collision;
import it.unibo.pensilina14.bullet.ballet.model.collision.CollisionSides;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

public class CollisionEventChecker implements EventChecker {
	
	private final EventBuffer eventBuffer;
	private final List<? extends PhysicalObject> objects;
	private final List<? extends PhysicalObject> otherObjects;
	
	public CollisionEventChecker(final List<? extends PhysicalObject> objs, final List<? extends PhysicalObject> otherObjs) {
		this.eventBuffer = new CollisionEventBuffer();
		this.objects = objs;
		this.otherObjects = otherObjs;
	}
	
	@Override
	public final void check() {
		final boolean isSingleElemList = (this.otherObjects.size() == 1) ? true : false;
		for (final PhysicalObject a : this.objects) {
			checkAllObjects(isSingleElemList, a);
		}
	}

	private void checkAllObjects(final boolean isSingleElemList, final PhysicalObject a) {
		for (final PhysicalObject b : this.otherObjects) {
			if (Collision.areColliding(a, b)) {
				checkPlayerAndItem(a, b);
				checkPlayerAndEnemy(a, b);
				checkPlayerAndObstacle(a, b);
				checkPlayerAndWeapon(a, b);
				checkBulletAndEnemy(a, b);
				checkPlayerAndPlatform(a, b);
				checkEnemyAndPlatform(a, b);
				checkBulletAndPlatform(b, a);
				checkBulletAndObstacle(a, b);
			}
			if (isSingleElemList) {
				break;
			}
		}
	}

	private void checkPlayerAndObstacle(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof ObstacleImpl && b instanceof Player) {
			final Player player = (Player) b;
			final ObstacleImpl obstacle = (ObstacleImpl) a;
			this.eventBuffer.addEvent(new PlayerHitsObstacleEvent(player, obstacle));
		}
	}

	private void checkPlayerAndEnemy(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Enemy && b instanceof Player) {
			final Player player = (Player) b;
			final Enemy enemy = (Enemy) a;
			this.eventBuffer.addEvent(new PlayerHitsEnemyEvent(player, enemy));
		}
	}

	private void checkPlayerAndItem(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof PickupItem && b instanceof Player) {
			final Player player = (Player) b;
			final PickupItem item = (PickupItem) a;
			this.eventBuffer.addEvent(new PlayerHitsItemEvent(player, item));
		}
	}
	
	private void checkPlayerAndWeapon(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof WeaponImpl && b instanceof Player) {
			final Player player = (Player) b;
			final WeaponImpl weapon = (WeaponImpl) a;
			this.eventBuffer.addEvent(new PlayerHitsWeaponEvent(player, weapon));
		}
	}
	
	private void checkBulletAndEnemy(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof BulletImpl && b instanceof Enemy) {
			final Enemy enemy = (Enemy) b;
			final BulletImpl bullet = (BulletImpl) a;
			this.eventBuffer.addEvent(new BulletHitsEnemyEvent(bullet, enemy));
		}
	}
	
	private void checkPlayerAndPlatform(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Platform && b instanceof Player) {
			final Player player = (Player) b;
			final Platform platform = (Platform) a;
			final Optional<CollisionSides> side = Collision.getCollisionSide(b, a);
			this.eventBuffer.addEvent(new PlayerHitsPlatformEvent(player, platform, side.get()));
		}
	}
	
	private void checkEnemyAndPlatform(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Platform && b instanceof Enemy) {
			final Enemy enemy = (Enemy) b;
			final Platform platform = (Platform) a;
			this.eventBuffer.addEvent(new EnemyHitsPlatformEvent(enemy, platform));
		}
	}
	
	private void checkBulletAndPlatform(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Platform && b instanceof Bullet) {
			final Platform platform = (Platform) a;
			final Bullet bullet = (Bullet) b;
			this.eventBuffer.addEvent(new BulletHitsPlatformEvent(bullet, platform));
		}
	}	
	
	private void checkBulletAndObstacle(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Bullet && b instanceof Obstacle) {
			final BulletImpl bullet = (BulletImpl) a;
			final Obstacle obstacle = (Obstacle) b;
			this.eventBuffer.addEvent(new BulletHitsObstacleEvent(bullet, obstacle));
		}
	}
	
	@Override
	public final EventBuffer getBuffer() {
		return this.eventBuffer;
	}
}
