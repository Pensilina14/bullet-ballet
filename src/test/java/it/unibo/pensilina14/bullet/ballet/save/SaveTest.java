package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SaveTest {

    private final Save data = new Save();

    @Test
    public void saveAndLoadTest(){

        data.resetSaveFile();

        final String playerName = "player1";
        final int playerScore = 18;

        final String playerName2 = "player2";
        final int playerScore2 = 14;

        data.save(playerName, playerScore);
        data.save(playerName2, playerScore2);

        final ArrayList<String> namesList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<Integer> scoresList = new ArrayList<>(Arrays.asList(playerScore, playerScore2));

        HashMap<String, Integer> results;

        results = data.load();

        assertNotNull(results);
        assertFalse(results.isEmpty());

        assertTrue(results.keySet().containsAll(namesList));
        assertTrue(results.values().containsAll(scoresList));


    }

    @Test
    public void resetTest(){
        data.resetSaveFile();

        HashMap<String, Integer> resetResults;

        resetResults = data.load();

        assertTrue(resetResults.isEmpty());
    }

    @Test
    public void loadLevelTest(){
        //TODO:

        final int currentLevel = 0;

        final String[] level = data.loadLevel(currentLevel);

        assertTrue(level.length != 0);

        final int max_levels = 3;
        final int numberOfLevels = data.getNumberOfLevels();

        assertEquals(max_levels, numberOfLevels);
    }

    @Test
    public void modifyDataTest(){
        data.resetSaveFile();

        final String player = "Pippo";
        final int playerScore = 14;

        final String player2 = "Giorgio";
        final int playerScore2 = 8;

        data.save(player, playerScore);
        data.save(player2, playerScore2);

        HashMap<String, Integer> results = new HashMap<>();
        results = data.load();

        assertFalse(results.isEmpty());

        final String playerRename = "Marco";
        final int newPlayerScore = 5;

        data.modifySaveFile(player, playerScore, playerRename, newPlayerScore);

        results.clear();
        results = data.load();

        assertTrue(results.containsKey(playerRename));
        assertTrue(results.containsValue(newPlayerScore));
    }

}
