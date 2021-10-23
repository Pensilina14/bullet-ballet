package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Random;


import java.util.Random;
import java.util.logging.Level;

public class LevelLoader {
    //TODO: da encapsulare.

    private final static Save levelData = new Save();
    public static final String[] level = levelData.loadLevel(getRandomLevel());

    /**
     * 
     */
    /*public static String[][] levels= new String[][]{
            LEVEL0, LEVEL1, LEVEL2
    };*/

    /**
     * 
     */
    public static final int MAX_LEVELS = 3; //TODO: nella classe Save prendere quanti files ci sono nella directory levels per determinare il numero di MAX_LEVELS
    /**
     * 
     * 
     */
    //public static final int LEVEL_WIDTH = LevelData.levels[LevelData.getRandomLevel()][0].length();
    public static final int LEVEL_WIDTH = level[0].length();

    public static int getRandomLevel(){
        final Random rand = new Random();
        final int min = 0;
        return rand.nextInt(((MAX_LEVELS - min)) + min); //TODO: min ridondante in questo caso.
    }

    /*public String[] getLevel(){
        return LevelData.level;
    }*/

}
