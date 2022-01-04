package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SaveTest {

    @Test
    public void saveAndLoad(){

        // Prima di eseguire il test cancello tutti i dati precedentemente salvati nel file.
        Save.resetFile(Save.SAVE_PATH);

        final String playerName = "Paolo";
        final int playerScore = 7;

        final String playerName2 = "Giorgio";
        final int playerScore2 = 14;

        Save.saveGameStatistics(playerName, playerScore);
        Save.saveGameStatistics(playerName2, playerScore2);

        final LinkedHashMap<String, Integer> map;

        map = Save.loadGameStatistics();

        assertNotNull(map);
        assertFalse(map.isEmpty());

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<Integer> playersScoreList = new ArrayList<>(Arrays.asList(playerScore, playerScore2));

        assertTrue(map.keySet().containsAll(playersNameList));
        assertTrue(map.values().containsAll(playersScoreList));
    }

    @Test
    public void resetTest(){
        Save.resetFile(Save.SAVE_PATH);

        HashMap<String, Integer> resetResults;

        resetResults = Save.loadGameStatistics();

        assertTrue(resetResults.isEmpty());
    }

    @Test
    public void oldLoadLevelTest(){

        final int currentLevel = 0;

        final String[] level = Save.oldLoadLevel(currentLevel);

        assertTrue(level.length != 0);

        final int max_levels = 4; // Se aggiungete dei livelli, dovete aggiornare questa variabile
        final int numberOfLevels = Save.getNumberOfLevels(".txt");

        assertEquals(max_levels, numberOfLevels);
    }

    @Test
    public void loadLevelTest() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IOException, InvalidKeyException {
        final int NUMBER_OF_LEVELS = 3;

        if(Save.getNumberOfLevels(".txt") > 0){
            //Save.encryptLevels(); //TODO: uncomment when we finished to test the levels.
        }

        String[] s = Save.loadLevel(0);

        assertEquals(NUMBER_OF_LEVELS, Save.getNumberOfLevels(".dat"));

        assertNotNull(s);
        assertTrue(s.length != 0);
    }

    @Test
    public void saveAndLoadSettingsTest(){

        // Prima di eseguire il test cancello tutti i dati precedentemente salvati nel file.
        Save.resetFile(Save.SETTINGS_PATH);

        final int resWidth = 1920;
        final int resHeight = 1080;
        final String difficulty = "hard";
        final int audioVolume = 30;

        final boolean hasSavedSettings = Save.saveSettings(resWidth, resHeight, difficulty, audioVolume);

        assertTrue(hasSavedSettings);

        final HashMap<String, String> settingsMap = new HashMap<>();

        settingsMap.put(Save.RESOLUTION_WIDTH_STRING, String.valueOf(resWidth));
        settingsMap.put(Save.RESOLUTION_HEIGHT_STRING, String.valueOf(resHeight));
        settingsMap.put(Save.DIFFICULTY_STRING, difficulty);
        settingsMap.put(Save.AUDIO_STRING, String.valueOf(audioVolume));

        HashMap<String, String> loadedSettings = Save.loadSettings();

        assertFalse(loadedSettings.isEmpty());
        assertEquals(settingsMap, loadedSettings);

        // AGGIORNAMENTO DEI DATI
        final int resWidth2 = 1280;
        final int resHeight2 = 720;
        final String difficulty2 = "easy";
        final int audioVolume2 = 20;

        final boolean hasUpdatedSettings = Save.saveSettings(resWidth2, resHeight2, difficulty2, audioVolume2);

        assertTrue(hasUpdatedSettings);

        settingsMap.clear();

        settingsMap.put(Save.RESOLUTION_WIDTH_STRING, String.valueOf(resWidth2));
        settingsMap.put(Save.RESOLUTION_HEIGHT_STRING, String.valueOf(resHeight2));
        settingsMap.put(Save.DIFFICULTY_STRING, difficulty2);
        settingsMap.put(Save.AUDIO_STRING, String.valueOf(audioVolume2));

        loadedSettings = Save.loadSettings();

        assertFalse(loadedSettings.isEmpty());
        assertEquals(settingsMap, loadedSettings);
    }

}
