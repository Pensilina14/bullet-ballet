package it.unibo.pensilina14.bullet.ballet.save;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SaveTest {

    //private final Save data = new Save(); //TODO: remove

    @Test
    public void saveAndLoadJSON(){ //TODO: rename then in saveAndLoad()

        // Prima di eseguire il test cancello tutti i dati precedentemente salvati nel file.
        Save.resetSaveFile();

        final String playerName = "Paolo";
        final int playerScore = 7;

        final String playerName2 = "Giorgio";
        final int playerScore2 = 14;

        Save.saveJSON(playerName, playerScore);
        Save.saveJSON(playerName2, playerScore2);

        final LinkedHashMap<String, Integer> map;

        map = Save.loadJSON();

        assertNotNull(map);
        assertFalse(map.isEmpty());

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<Integer> playersScoreList = new ArrayList<>(Arrays.asList(playerScore, playerScore2));

        assertTrue(map.keySet().containsAll(playersNameList));
        assertTrue(map.values().containsAll(playersScoreList));
    }

    /*@Test
    public void saveAndLoadTest(){

        //data.resetSaveFile(); //TODO: remove
        Save.resetSaveFile();

        final String playerName = "player1";
        final int playerScore = 18;

        final String playerName2 = "player2";
        final int playerScore2 = 14;

        //data.save(playerName, playerScore); //TODO: remove
        //data.save(playerName2, playerScore2);

        Save.save(playerName, playerScore);
        Save.save(playerName2, playerScore2);

        final ArrayList<String> namesList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<Integer> scoresList = new ArrayList<>(Arrays.asList(playerScore, playerScore2));

        HashMap<String, Integer> results;

        //results = data.load();
        results = Save.load();

        assertNotNull(results);
        assertFalse(results.isEmpty());

        assertTrue(results.keySet().containsAll(namesList));
        assertTrue(results.values().containsAll(scoresList));


    }*/

    @Test
    public void resetTest(){
        //data.resetSaveFile(); //TODO: remove
        Save.resetSaveFile();

        HashMap<String, Integer> resetResults;

        //resetResults = data.load();
        resetResults = Save.loadJSON();

        assertTrue(resetResults.isEmpty());
    }

    @Test
    public void loadLevelTest(){
        //TODO:

        final int currentLevel = 0;

        final String[] level = Save.loadLevel(currentLevel); //data.loadLevel(currentLevel);

        assertTrue(level.length != 0);

        final int max_levels = 3;
        final int numberOfLevels = Save.getNumberOfLevels(); //data.getNumberOfLevels();

        assertEquals(max_levels, numberOfLevels);
    }

    /*@Test
    public void modifyDataTest(){
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
