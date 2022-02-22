package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;

public class SpriteContainer extends AbstractContainer<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>> implements SpriteManager {

    @Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlayerSprite() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getPlatformsSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getEnemiesSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getItemsSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getObstaclesSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getWeaponsSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final Optional<List<ImmutablePair<PhysicalObjectSprite, MutablePosition2D>>> getBulletsSprites() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final void addPlayerSprite(final PhysicalObjectSprite playerSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addPlatformSprite(final PhysicalObjectSprite platformSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addEnemySprite(final PhysicalObjectSprite enemySprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addItemSprite(final PhysicalObjectSprite itemSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addObstacleSprite(final PhysicalObjectSprite obstacleSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addWeaponSprite(final PhysicalObjectSprite weaponSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void addBulletSprite(final PhysicalObjectSprite bulletSprite, final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deletePlayerSprite() {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deletePlatformSprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deleteEnemySprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deleteItemSprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deleteObstacleSprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deleteWeaponSprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}

	@Override
	public final void deleteBulletSprite(final MutablePosition2D position) {
		// TODO Auto-generated method stub
	}
}
