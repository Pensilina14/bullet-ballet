package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Random;

public class LevelLoader {

    private final Save levelData;
    private final String[] level;

    private final int LEVEL_WIDTH;

    public LevelLoader(){
       this.levelData = new Save();
       this.level = levelData.loadLevel(getRandomLevel());
       this.LEVEL_WIDTH = this.level[0].length();
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
