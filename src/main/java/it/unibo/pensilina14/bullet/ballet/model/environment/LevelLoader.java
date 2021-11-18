package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Random;

public class LevelLoader {

    //private final Save levelData; //TODO: remove
    private final String[] level;
    private final double levelHeight;

    private final double levelWidth;

    public LevelLoader(){
       //this.levelData = //new Save();
       this.level = Save.loadLevel(getRandomLevel()); //levelData.loadLevel(getRandomLevel()); //TODO: remove
       this.levelWidth = this.level[0].length();
       this.levelHeight = this.level.length;
    }

    private int getRandomLevel(){
        final Random rand = new Random();
        final int maxLevels = Save.getNumberOfLevels(); //this.levelData.getNumberOfLevels(); //TODO: remove
        return rand.nextInt(maxLevels);
    }

    public double getLevelWidth(){
        return this.levelWidth;
    }
    
    public double getLevelHeight() {
    	return this.levelHeight;
    }

    public String[] getLevel(){
        return this.level; // Non va bene esporre una stringa così, bisogna fare un wrapper
    }

}
