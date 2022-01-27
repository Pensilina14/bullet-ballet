package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class LevelLoader {

    private String[] level;
    private final double levelHeight;
    private final double levelWidth;

    // Se dovete provare i livelli e quindi dovete cambiarli, modificate i .txt
    // poi qui nel costruttore di LevelLoader, in this.level al posto di chiamare Save.LoadLevel, chiamate Save.oldLoadLevel(getRandomLevel());
    // Save.oldLoadLevel non ha nemmeno bisogno del try/catch.
    // e nel metodo getRandomLevel qua sotto, al posto di Save.getNumberOfLevels(".dat") mettete (".txt");

    public LevelLoader(){

        //this.level = Save.oldLoadLevel(getRandomLevel()); // decommentate questa se dovete testare i livelli con i .txt
        this.level = Save.oldLoadLevel(1); 
//        try { // commentate questo blocco try/catch se dovete usare i .txt
//            this.level = Save.loadLevel(getRandomLevel());
//        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IOException | InvalidKeyException e) { //TODO: volendo abbreviare in Exception e
//            e.printStackTrace();
//        }
        this.levelWidth = this.level[0].length();
        this.levelHeight = this.level.length;
    }

    private int getRandomLevel(){
        final Random rand = new Random();
        final int maxLevels = Save.getNumberOfLevels(".txt"); // mettete qui .txt se dovete testare i livelli con i .txt
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
