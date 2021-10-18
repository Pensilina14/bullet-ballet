package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

public class EnvironmentGenerator implements LevelGenerator {

    private final int level;

    private static final int PLATFORM_SIZE = 60;
    private static final int LEVEL_WIDTH = LevelData.LEVEL_WIDTH;

    private final Environment env;
	private final FactoryCharacters charactersFactory;
	private final ObstacleFactory obstacleFactory;
	private final ItemFactory itemFactory;
	
	public EnvironmentGenerator(final Environment environment) {
		this.level = LevelData.getRandomLevel();
		this.env = environment;
		this.charactersFactory = new FactoryCharactersImpl();
		this.obstacleFactory = new ObstacleFactoryImpl();
		this.itemFactory = new ItemFactoryImpl();
	}
	
	@Override
	public final void generate() {
        for (int i = 0; i < LevelData.levels[this.level].length; i++) {
            final String line = LevelData.levels[this.level][i];
            for (int j = 0; j < line.length(); j++){
                switch(line.charAt(j)) {
                    case '0':
                    	break;
                    case '1':
                        //this.env.addPlatform(new Platform(new Dimension2Dimpl(j, i), new MutablePosition2Dimpl(j, i), env));
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
                    case '5':
                        //TODO:
                    	break;
                    case '*':
                        this.env.addItem(this.itemFactory.createDamagingItem(env, new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 0.0)));
                        break;
                    case '!':
                        this.env.addEnemy(this.charactersFactory.createRandomEnemy(new SpeedVector2DImpl(new MutablePosition2Dimpl(j, i), 0.0)));
                        break;
                }
            }
        }
	}

	@Override
	public int getLevelWidth() {
		return EnvironmentGenerator.LEVEL_WIDTH;
	}

	@Override
	public int getPlatformSize() {
		return EnvironmentGenerator.PLATFORM_SIZE;
	}
}
