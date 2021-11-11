package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import java.util.List;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.collision.CollisionImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;

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
			if (new CollisionImpl().isCollidingWith(a, b)) {
				if (isSingleElemList) {
					break;
				}
				checkPlayerAndItem(a, b);
				checkPlayerAndItem(b, a);
				checkPlayerAndEnemy(a, b);
				checkObstacleAndPlayer(a, b);
				checkObstacles(a, b);
			}
		}
	}
	
	private void checkObstacles(final PhysicalObject a, final PhysicalObject b) {
		if (a instanceof Enemy && (b instanceof DynamicObstacle || b instanceof StaticObstacle)) {
			final Enemy enemy = (Enemy) a;
			final PhysicalObject obstacle = b;
			this.eventBuffer.addEvent(new EnemyHitsObstacleEvent(enemy, obstacle));
		}
	}

	private void checkObstacleAndPlayer(final PhysicalObject a, final PhysicalObject b) {
		if ((a instanceof DynamicObstacle || a instanceof StaticObstacle) && b instanceof Player) {
			final Player player = (Player) b;
			final PhysicalObject obstacle = a;
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
		if (a instanceof Enemy && (b instanceof DynamicObstacle || b instanceof StaticObstacle)) {
			final Characters player = (Characters) b;
			final Item item = (Item) a;
			this.eventBuffer.addEvent(new CharacterHitsPickupObjEvent(player, item));
		}
	}
	
	@Override
	public final EventBuffer getBuffer() {
		return this.eventBuffer;
	}
}
