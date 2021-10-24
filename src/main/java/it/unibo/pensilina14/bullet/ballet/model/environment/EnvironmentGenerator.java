package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

import java.util.logging.Level;

public class EnvironmentGenerator implements LevelGenerator {

    //private final int level; //TODO: remove

    private static final int PLATFORM_SIZE = 60;
    private final LevelLoader levelLoader; //= new LevelLoader();
    //private static final int LEVEL_WIDTH = LevelLoader.LEVEL_WIDTH;

    private final Environment env;
	private final FactoryCharacters charactersFactory;
	private final ObstacleFactory obstacleFactory;
	private final ItemFactory itemFactory;
	
	public EnvironmentGenerator(final Environment environment) {
		//this.level = LevelData.getRandomLevel(); //TODO: remove
        this.levelLoader = new LevelLoader();
		this.env = environment;
		this.charactersFactory = new FactoryCharactersImpl();
		this.obstacleFactory = new ObstacleFactoryImpl();
		this.itemFactory = new ItemFactoryImpl();
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
        for (int i = 0; i < this.levelLoader.getLevel().length; i++) { //LevelLoader.level.length
            final String line = this.levelLoader.getLevel()[i]; //LevelLoader.level[i]
            for (int j = 0; j < line.length(); j++){
                switch(line.charAt(j)) {
                    case '0': //TODO: use an enum?
                    	break;
                    case '1':
                        this.env.addPlatform(new Platform(new Dimension2Dimpl(j, i), new MutablePosition2Dimpl(j, i), env));
                        break;
                    case '2':
                        // TODO: add coin.
                        break;
                    case '3':
                        this.env.addObstacle(this.obstacleFactory.createStaticObstacle(this.env, new MutablePosition2Dimpl(j, i)));
                        break;
                    case '4':
                        //TODO: add weapon
                        break;
                    case 'P':
                    	//final MutablePosition2D platPos = this.env.getPlatforms().get().get(0).getPosition();
                        this.env.setPlayer(this.charactersFactory.createRandomPlayer(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0), this.env));
                    	break;
                    case '*':
                        this.env.addItem(this.itemFactory.createDamagingItem(this.env, new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0)));
                        break;
                    case '!':
                        this.env.addEnemy(this.charactersFactory.createRandomEnemy(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 1.0), this.env));
                        break;
                }
            }
        }
	}

	@Override
	public int getLevelWidth() {
		//return EnvironmentGenerator.LEVEL_WIDTH;
        return levelLoader.getLevelWidth();
	}

	@Override
	public int getPlatformSize() {
		return EnvironmentGenerator.PLATFORM_SIZE;
	}
}
