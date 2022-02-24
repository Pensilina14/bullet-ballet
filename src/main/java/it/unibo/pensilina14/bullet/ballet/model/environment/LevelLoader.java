package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Extensions;
import it.unibo.pensilina14.bullet.ballet.save.Save;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LevelLoader {

    private String[] level;
    private final double levelHeight;
    private final double levelWidth;
    
    private static final Random RAND = new Random();

    // Se dovete provare i livelli e quindi dovete cambiarli, modificate i .txt
    // poi qui nel costruttore di LevelLoader, in this.level al posto di chiamare Save.LoadLevel, chiamate Save.loadLevelForTesting(getRandomLevel());
    // Save.loadLevelForTesting non ha nemmeno bisogno del try/catch.
    // e nel metodo getRandomLevel qua sotto, al posto di Save.getNumberOfLevels(".dat") mettete (".txt") oppure Extensions.TXT;

    public LevelLoader() {
        // Ho fatto il try/catch soltanto perché se no boh ci portavamo dietro la throw in diverse classi, ma se non è un problema uso la throw.
        try {
            this.level = Save.loadLevel(getRandomLevel()); // decommentate questa se dovete testare i livelli con i .txt
        }catch(InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                InvalidKeySpecException | BadPaddingException | IOException | InvalidKeyException e){
            e.printStackTrace();
        }
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

    public List<String> getLevel(){
        return Arrays.asList(this.level); 
    }

}
