package it.unibo.pensilina14.bullet.ballet.model.environment;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;

public class EnvironmentGenerator implements LevelGenerator {

    private static final int PLATFORM_SIZE = 60;
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
	
	public void setEnvironment(final Environment environment) {
		this.env = Optional.of(environment);
	}

    // i: riga.
    // j: elemento alla riga i.

    //TODO: magari usare un enum per questi.
    // 1: piattaforma
    // 2: Moneta
    // 3: Ostacolo
    // 4: Arma
    // P: Player
    // *: Oggetto (Item)
    // !: Nemico
	
	@Override
	public final void generate() {
        for (int i = 0; i < this.levelLoader.getLevel().length; i++) {
            final String line = this.levelLoader.getLevel()[i];
            for (int j = 0; j < line.length(); j++){
                switch(line.charAt(j)) {
					case LevelEntity.EMPTY: //TODO: use an enum?
                    	break;
					case LevelEntity.PLATFORM:
                        this.env.get().addPlatform(new Platform(new Dimension2Dimpl(j, i), new MutablePosition2Dimpl(j, i), this.env.get()));
                        break;
                    case LevelEntity.COIN:
                        // TODO: add coin.
                        break;
                    case LevelEntity.OBSTACLE:
                        this.env.get().addObstacle(this.obstacleFactory.createStaticObstacle(this.env.get(), new MutablePosition2Dimpl(j, i)));
                        break;
                    case LevelEntity.WEAPON:
                        this.env.get().addWeapon(this.weaponFactory.createGun(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0), this.env.get()));
                        break;
                    case LevelEntity.PLAYER:
                    	//final MutablePosition2D platPos = this.env.getPlatforms().get().get(0).getPosition();
                        this.env.get().setPlayer(this.charactersFactory.createRandomPlayer(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0), this.env.get()));
                    	break;
                    case LevelEntity.ITEM:
                        this.env.get().addItem(this.itemFactory.createHealingItem(this.env.get(), new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0)));
                        break;
                    case LevelEntity.ENEMY:
                        this.env.get().addEnemy(this.charactersFactory.createRandomEnemy(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0), this.env.get()));
                        break;
                }
            }
        }
	}

	@Override
	public int getLevelWidth() {
        return levelLoader.getLevelWidth();
	}
	
	@Override
	public int getLevelHeight() {
        return levelLoader.getLevelHeight();
	}

	@Override
	public int getPlatformSize() {
		return EnvironmentGenerator.PLATFORM_SIZE;
	}
}
