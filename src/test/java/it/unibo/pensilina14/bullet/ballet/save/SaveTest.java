package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SaveTest {

    private final Save data = new Save();

    /*@Test //TODO: remove
    public void saveTest(){
    }*/

    @Test
    public void saveAndLoadTest(){

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
}
