package it.unibo.pensilina14.bullet.ballet.model.environment;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.EntityContainer;
import it.unibo.pensilina14.bullet.ballet.common.EntityManagerBuilder;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;

public class EnvironmentGenerator implements LevelGenerator {
	
    private static final int TILE_SIZE = 60;
    private final LevelLoader levelLoader;

    private Optional<Environment> env;
    private final FactoryCharacters charactersFactory;
	private final ObstacleFactory obstacleFactory;
	private final ItemFactory itemFactory;
	private final WeaponFactory weaponFactory;
	
	public EnvironmentGenerator() {
		this.levelLoader = new LevelLoader();
		this.env = Optional.empty();
		this.charactersFactory = new FactoryCharactersImpl();
		this.obstacleFactory = new ObstacleFactoryImpl();
		this.itemFactory = new ItemFactoryImpl();
		this.weaponFactory = new WeaponFactoryImpl();
	}
	
	public EnvironmentGenerator(final Environment environment) {
        this.levelLoader = new LevelLoader();
		this.env = Optional.of(environment);
		this.charactersFactory = new FactoryCharactersImpl();
		this.obstacleFactory = new ObstacleFactoryImpl();
		this.itemFactory = new ItemFactoryImpl();
		this.weaponFactory = new WeaponFactoryImpl();
	}
	
	public final void setEnvironment(final Environment environment) {
		this.env = Optional.of(environment);
	}
	
	@Override
	public final void generate() {
		final EntityManagerBuilder entityManagerBuilder = new EntityContainer.Builder(this.env.get().getEntityManager());
        for (int i = 0; i < this.levelLoader.getLevel().length; i++) {
            final String line = this.levelLoader.getLevel()[i];
            for (int j = 0; j < line.length(); j++) {
            	populate(entityManagerBuilder, i, line, j); 
            }
        }
		AppLogger.getAppLogger().debug(((EntityContainer) entityManagerBuilder.build()).getContainer().toString());
	}

	private void populate(final EntityManagerBuilder entityManagerBuilder, final int i, final String line, final int j) {
		final char symbol = line.charAt(j);
		if (symbol == LevelEntity.EMPTY.getValue()) {
			System.out.println("che bisogna mettere qui?");
		} else if (symbol == LevelEntity.PLATFORM.getValue()) {
			entityManagerBuilder.addPlatform(new Platform(
					new SpeedVector2DImpl(
							new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0), 
							this.env.get(), 
							0, 
							new Dimension2Dimpl(TILE_SIZE, TILE_SIZE)
					));
		} else if (symbol == LevelEntity.COIN.getValue()) {
			generateCoin(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.OBSTACLE.getValue()) {
			generateStandardObstacle(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.GUN.getValue()) {
			generateStandardGun(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.SHOTGUN.getValue()) {
			generateShotGun(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.AUTOGUN.getValue()) {
			generateAuto(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.PLAYER.getValue()) {
			generatePlayer(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.HEART.getValue()) {
			generateHealingItem(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.POISON.getValue()) {
			generatePoisoningItem(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.DAMAGE.getValue()) {
			generatePoison(entityManagerBuilder, i, j);
		} else if (symbol == LevelEntity.ENEMY.getValue()) {
			generateEnemy(entityManagerBuilder, i, j);
		}
	}

	private void generateEnemy(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addEnemy(this.charactersFactory.createRandomEnemy(new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0), this.env.get()));
	}

	private void generatePoison(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addItem(this.itemFactory.createDamagingItem(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generatePoisoningItem(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addItem(this.itemFactory.createPoisoningItem(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateHealingItem(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addItem(this.itemFactory.createHealingItem(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateAuto(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addWeapon(this.weaponFactory.createAuto(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateShotGun(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addWeapon(this.weaponFactory.createShotGun(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateStandardGun(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addWeapon(this.weaponFactory.createGun(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateStandardObstacle(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addObstacle(this.obstacleFactory.createStandardObstacle(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}

	private void generateCoin(final EntityManagerBuilder entityManagerBuilder, final int i, final int j) {
		entityManagerBuilder.addItem(this.itemFactory.createCoinItem(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 1.0)));
	}
	
	private void generatePlayer(final EntityManagerBuilder e, final int i, final int j) {
		e.addPlayer(this.charactersFactory.createRandomPlayer(new SpeedVector2DImpl(new MutablePosition2Dimpl(j * TILE_SIZE, i * TILE_SIZE), 2.0), this.env.get()));
	}
	
	@Override
	public final double getLevelWidth() {
        return levelLoader.getLevelWidth();
	}
	
	@Override
	public final double getLevelHeight() {
        return levelLoader.getLevelHeight();
	}

	@Override
	public final int getPlatformSize() {
		return EnvironmentGenerator.TILE_SIZE;
	}
}
