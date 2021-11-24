package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SaveTest {

    @Test
    public void saveAndLoad(){

        // Prima di eseguire il test cancello tutti i dati precedentemente salvati nel file.
        Save.resetSaveFile();

        final String playerName = "Paolo";
        final int playerScore = 7;

        final String playerName2 = "Giorgio";
        final int playerScore2 = 14;

        Save.saveData(playerName, playerScore);
        Save.saveData(playerName2, playerScore2);

        final LinkedHashMap<String, Integer> map;

        map = Save.loadSaveFile();

        assertNotNull(map);
        assertFalse(map.isEmpty());

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<Integer> playersScoreList = new ArrayList<>(Arrays.asList(playerScore, playerScore2));

        assertTrue(map.keySet().containsAll(playersNameList));
        assertTrue(map.values().containsAll(playersScoreList));
    }

    @Test
    public void resetTest(){
        Save.resetSaveFile();

        HashMap<String, Integer> resetResults;

        resetResults = Save.loadSaveFile();

        assertTrue(resetResults.isEmpty());
    }

    @Test
    public void loadLevelTest(){

        final int currentLevel = 0;

        final String[] level = Save.loadLevel(currentLevel);

        assertTrue(level.length != 0);

        final int max_levels = 3;
        final int numberOfLevels = Save.getNumberOfLevels();

        assertEquals(max_levels, numberOfLevels);
    }

    /*@Test
    public void modifyDataTest(){ //TODO: uncomment when the method will be fixed
        //data.resetSaveFile();
        Save.resetSaveFile();

        final String player = "Pippo";
        final int playerScore = 14;

        final String player2 = "Giorgio";
        final int playerScore2 = 8;

        //data.save(player, playerScore);
        //data.save(player2, playerScore2);
        Save.save(player, playerScore);
        Save.save(player2, playerScore2);

        HashMap<String, Integer> results = new HashMap<>();
        results = Save.load(); //data.load();

        assertFalse(results.isEmpty());

        final String playerRename = "Marco";
        final int newPlayerScore = 5;

        //data.modifySaveFile(player, playerScore, playerRename, newPlayerScore);
        Save.modifySaveFile(player, playerScore, playerRename, newPlayerScore);

        results.clear();
        results = Save.load(); //data.load();

        assertTrue(results.containsKey(playerRename));
        assertTrue(results.containsValue(newPlayerScore));
    }*/

}
