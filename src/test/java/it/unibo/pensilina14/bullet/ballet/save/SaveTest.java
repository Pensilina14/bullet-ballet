package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
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
    public void saveAndLoadStatisticsTest(){

        // Prima di eseguire il test cancello tutti i dati precedentemente salvati nel file.
        Save.resetFile(Save.SAVE_PATH);

        final String playerName = "Paolo";
        final int playerScore = 7;

        final String playerName2 = "Giorgio";
        final int playerScore2 = 14;

        Save.saveGameStatistics(playerName, playerScore);
        Save.saveGameStatistics(playerName2, playerScore2);

        final LinkedHashMap<String, String> map;

        map = Save.loadGameStatistics();

        assertNotNull(map);
        assertFalse(map.isEmpty());

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<String> playersScoreList = new ArrayList<>(Arrays.asList(String.valueOf(playerScore), String.valueOf(playerScore2)));

        assertTrue(map.keySet().containsAll(playersNameList));
        assertTrue(map.values().containsAll(playersScoreList));
    }

    @Test
    public void updateStatisticsTest(){
        Save.resetFile(Save.SAVE_PATH);

        final String playerName = "Alessio";
        final int playerScore = 7;

        final String playerName2 = "Gigi";
        final int playerScore2 = 14;

        final String playerName3 = "Pluto";
        final int playerScore3 = 5;

        Save.saveGameStatistics(playerName, playerScore);
        Save.saveGameStatistics(playerName2, playerScore2);
        Save.saveGameStatistics(playerName3, playerScore3);

        final String newPlayerName = "Mario";
        final int newPlayerScore = 18;

        // CONTROLLO CHE I DATI SIANO STATI AGGIORNATI

        final boolean hasUpdated = Save.updateGameStatistics(playerName2, playerScore2, newPlayerName, newPlayerScore);

        assertTrue(hasUpdated);

        // CONTROLLO CHE I DATI SIANO STATI AGGIORNATI ANCHE NEL FILE

        final HashMap<String, String> statisticsMap = Save.loadGameStatistics();

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, newPlayerName, playerName3));
        final ArrayList<String> playersScoreList = new ArrayList<>(Arrays.asList(String.valueOf(playerScore), String.valueOf(newPlayerScore), String.valueOf(playerScore3)));

        // CONTROLLO CHE CI SIANO TUTTI I DATI AGGIORNATI

        assertTrue(statisticsMap.keySet().containsAll(playersNameList));
        assertTrue(statisticsMap.values().containsAll(playersScoreList));

        // CONTROLLO CHE I DATI CHE SON STATI RIMPIAZZATI NON SIANO PRESENTI

        assertFalse(statisticsMap.containsValue(playerName2));
        assertFalse(statisticsMap.containsValue(String.valueOf(playerScore2)));
    }

    @Test
    public void resetTest(){
        final File statisticsFile = new File(Save.SAVE_PATH);

        Save.resetFile(statisticsFile.getPath());

        assertEquals(0, statisticsFile.length());

        final File settingsFile = new File(Save.SETTINGS_PATH);

        Save.resetFile(settingsFile.getPath());

        assertEquals(0, settingsFile.length());
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
        final double audioVolume = 30.0;

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
        final double audioVolume2 = 20.0;

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
