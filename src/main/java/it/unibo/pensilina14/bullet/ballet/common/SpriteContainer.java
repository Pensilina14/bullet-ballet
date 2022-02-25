package it.unibo.pensilina14.bullet.ballet.common;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;

public class SpriteContainer extends AbstractContainer<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>> implements SpriteManager {

    @Override
    public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlayerSprite() {
        return this.getSpritesFromContainer(GameEntities.PLAYER);
    }

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlatformsSprites() {
		return this.getSpritesFromContainer(GameEntities.PLATFORM); 
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getEnemiesSprites() {
		return this.getSpritesFromContainer(GameEntities.ENEMY);
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getItemsSprites() {
		return this.getSpritesFromContainer(GameEntities.PICKUP_ITEM);
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getObstaclesSprites() {
		return this.getSpritesFromContainer(GameEntities.OBSTACLE);
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getWeaponsSprites() {
		return this.getSpritesFromContainer(GameEntities.WEAPON);
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getBulletsSprites() {
		return this.getSpritesFromContainer(GameEntities.BULLET);
	}
	
	private Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getSpritesFromContainer(final GameEntities entityType) {
        final List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>> sprites = this.getContainer().get(entityType).get();
        if (!sprites.isEmpty()) {
            return Optional.ofNullable(sprites);
        }
        return Optional.empty();
    }
	
	@Override
	public final boolean addPlayerSprite(final PhysicalObjectSprite playerSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.PLAYER, playerSprite, position);
	}

	@Override
	public final boolean addPlatformSprite(final PhysicalObjectSprite platformSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.PLATFORM, platformSprite, position);
	}

	@Override
	public final boolean addEnemySprite(final PhysicalObjectSprite enemySprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.ENEMY, enemySprite, position);
	}

	@Override
	public final boolean addItemSprite(final PhysicalObjectSprite itemSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.PICKUP_ITEM, itemSprite, position);
	}

	@Override
	public final boolean addObstacleSprite(final PhysicalObjectSprite obstacleSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.OBSTACLE, obstacleSprite, position);
	}

	@Override
	public final boolean addWeaponSprite(final PhysicalObjectSprite weaponSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.WEAPON, weaponSprite, position);
	}

	@Override
	public final boolean addBulletSprite(final PhysicalObjectSprite bulletSprite, final MutablePosition2D position) {
		return this.addSpriteToContainer(GameEntities.BULLET, bulletSprite, position);
	}
	
	private boolean addSpriteToContainer(final GameEntities entityType, final PhysicalObjectSprite sprite, final MutablePosition2D spritePosition) {
		final ImmutablePair<PhysicalObjectSprite, MutablePosition2D> spriteAndItsPosition = new ImmutablePair<>(sprite, spritePosition);
		if (this.getContainer().get(entityType).get().contains(spriteAndItsPosition)) {
			return false;
		} else {
			this.getContainer().get(entityType).get().add(spriteAndItsPosition);
			return true;
		}
	}

	@Override
	public final boolean deleteSprite(final MutablePosition2D targetPosition) {
		for (final Entry<GameEntities, Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>>> entry : this.getContainer().entrySet()) {
			if (entry.getValue().isPresent()) {
				final Iterator<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>> iter = entry.getValue().get().iterator();
				while (iter.hasNext()) {
					final ImmutablePair<PhysicalObjectSprite, MutablePosition2D> spriteAndItsPosition = iter.next();
					if (spriteAndItsPosition.getRight().equals(targetPosition)) {
						iter.remove();
						return true;
					}
				}
			} else {
				throw new NoSuchElementException();
			}
		}
		return false;
	}
}
