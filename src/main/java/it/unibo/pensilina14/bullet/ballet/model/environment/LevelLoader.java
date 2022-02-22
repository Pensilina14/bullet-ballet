package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Extensions;
import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Random;

public class LevelLoader {

    private final String[] level;
    private final double levelHeight;
    private final double levelWidth;
    
    private static final Random RAND = new Random();

    // Se dovete provare i livelli e quindi dovete cambiarli, modificate i .txt
    // poi qui nel costruttore di LevelLoader, in this.level al posto di chiamare Save.LoadLevel, chiamate Save.loadLevelForTesting(getRandomLevel());
    // Save.loadLevelForTesting non ha nemmeno bisogno del try/catch.
    // e nel metodo getRandomLevel qua sotto, al posto di Save.getNumberOfLevels(".dat") mettete (".txt") oppure Extensions.TXT;

    public LevelLoader(){

        this.level = Save.loadLevelForTesting(getRandomLevel()); // decommentate questa se dovete testare i livelli con i .txt
        this.levelWidth = this.level[0].length();
        this.levelHeight = this.level.length;
    }

    private int getRandomLevel(){
        final int maxLevels = Save.getNumberOfLevels(Extensions.TXT); // mettete qui Extensions.TXT se dovete testare i livelli con i .txt
        return LevelLoader.RAND.nextInt(maxLevels);
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
