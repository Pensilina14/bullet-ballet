package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Random;


import java.util.Random;
import java.util.logging.Level;

public class LevelLoader {

    private final Save levelData;
    private final String[] level;

    private final int LEVEL_WIDTH;
    //private final int MAX_LEVELS;

    public LevelLoader(){
       this.levelData = new Save();
       this.level = levelData.loadLevel(getRandomLevel());
       this.LEVEL_WIDTH = this.level[0].length();
       //this.MAX_LEVELS = this.levelData.getNumberOfLevels(); // da 0, non so perchè
    }

    private int getRandomLevel(){
        final Random rand = new Random();
        final int maxLevels = this.levelData.getNumberOfLevels();
        return rand.nextInt(maxLevels);
    }

    public int getLevelWidth(){
        return this.LEVEL_WIDTH;
    }

    public String[] getLevel(){
        return this.level;
    }

}
