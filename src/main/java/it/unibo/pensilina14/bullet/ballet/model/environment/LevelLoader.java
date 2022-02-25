package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Extensions;
import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LevelLoader {

    private final String[] level;
    private final double levelHeight;
    private final double levelWidth;
    
    private static final Random RAND = new Random();

    public LevelLoader() {
        this.level = Save.loadLevel(getRandomLevel());
        this.levelWidth = this.level[0].length();
        this.levelHeight = this.level.length;
    }

    private int getRandomLevel(){
        final int maxLevels = Save.getNumberOfLevels(Extensions.DAT);
        return LevelLoader.RAND.nextInt(maxLevels);
    }

    public double getLevelWidth(){
        return this.levelWidth;
    }
    
    public double getLevelHeight() {
    	return this.levelHeight;
    }

    public List<String> getLevel(){
        return Arrays.asList(this.level); 
    }

}
