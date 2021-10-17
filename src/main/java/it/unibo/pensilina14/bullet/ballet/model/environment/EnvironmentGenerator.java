package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.map.LevelData;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharacters;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

public class EnvironmentGenerator implements Generator {

	private int levelWidth;
    private int currentLevel = 0;

    private static final int MAX_LEVELS = LevelData.levels.length;
    private final static int PLATFORM_SIZE = 60;

	private final Environment env;
	private final FactoryCharacters charactersFactory;
	private final ObstacleFactory obstacleFactory;
	private final ItemFactory itemFactory;
	
	public EnvironmentGenerator(final Environment environment) {
		this.env = environment;
		this.charactersFactory = new FactoryCharactersImpl();
		this.obstacleFactory = new ObstacleFactoryImpl();
		this.itemFactory = new ItemFactoryImpl();
	}
	
	@Override
	public void generate() {
		//TODO: al momento mostra soltanto l'ultimo livello (ovvero il secondo). (perchè si sovrappongono)
        //TODO: da fixare aggiungendo alla coordinate del secondo livello la distanza dall'ultima piattaforma del primo livello (per appendare il livello 2 al livello 1)
        //TODO: però, se sta generando il primo livello non serve aggiungere le coordinate dall'ultima piattaforma del livello precedente
        //TODO: quindi alle coordinate da aggiungere, metto un int previousLevel = ((this.currentLevel - 1) < 0 ? this.currentLevel : (this.currentLevel - 1));
        this.levelWidth = LevelData.levels[this.currentLevel][0].length() * PLATFORM_SIZE;
        while (this.currentLevel < MAX_LEVELS){
            for (int i = 0; i < LevelData.levels[this.currentLevel].length; i++){
                String line = LevelData.levels[this.currentLevel][i];
                //int previousLevel = ((this.currentLevel - 1) < 0 ? this.currentLevel : (this.currentLevel - 1)); //TODO: unccoment
                //this.lastPos = new Pair<>(line.length() - 1,LevelData.levels[this.currentLevel].length - 1); //TODO: - 1
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
                            this.env.addEnemy(this.charactersFactory.createRandomEnemy());
                            break;
                    }
                }
            }
            this.currentLevel++;
            //TODO: salvare le coordinate dell'ultima piattaforma del livello precedente.
        }
	}

}
