package it.unibo.pensilina14.bullet.ballet.save;

import org.apache.commons.lang3.tuple.MutablePair;
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
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class SaveTest {

    @Test
    public void saveAndLoadStatisticsTest(){

        // Before the test I delete all the previously stored data in the save file.
        Save.resetFile(Save.SAVE_PATH);

        final String playerName = "Paolo";
        final double playerScore = 7.0;
        final String date = "16/02/2022 16:54";

        final String playerName2 = "Giorgio";
        final double playerScore2 = 14.0;
        final String date2 = "16/02/2022 17:00";

        Save.saveGameStatistics(playerName, playerScore, date);
        Save.saveGameStatistics(playerName2, playerScore2, date2);

        final Map<String, MutablePair<String, String>> map;

        map = Save.loadGameStatistics();

        assertNotNull(map);
        assertFalse(map.isEmpty());

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, playerName2));
        final ArrayList<String> playersScoreList = new ArrayList<>(Arrays.asList(String.valueOf(playerScore), String.valueOf(playerScore2)));
        final ArrayList<String> playersDateList = new ArrayList<>(Arrays.asList(date, date2));

        assertTrue(map.keySet().containsAll(playersNameList));

        for(final var v : map.values()){
            assertTrue(playersScoreList.contains(v.getLeft()));
            assertTrue(playersDateList.contains(v.getRight()));
        }
    }

    @Test
    public void updateStatisticsTest(){
        Save.resetFile(Save.SAVE_PATH);

        final String playerName = "Alessio";
        final double playerScore = 7.0;
        final String date = "16/02/2022 16:56";

        final String playerName2 = "Gigi";
        final double playerScore2 = 14.0;
        final String date2 = "16/02/2022 16:57";

        final String playerName3 = "Pluto";
        final double playerScore3 = 5.0;
        final String date3 = "16/02/2022 18:00";

        Save.saveGameStatistics(playerName, playerScore, date);
        Save.saveGameStatistics(playerName2, playerScore2, date2);
        Save.saveGameStatistics(playerName3, playerScore3, date3);

        final String newPlayerName = "Mario";
        final double newPlayerScore = 18.0;
        final String newPlayerDate = "16/02/2022 17:00";

        // CHECK THAT DATA HAS BEEN UPDATED

        final boolean hasUpdated = Save.updateGameStatistics(playerName2, playerScore2, newPlayerName, newPlayerScore, date2, newPlayerDate);

        assertTrue(hasUpdated);

        // CHECK THAT DATA HAS BEEN UPDATED IN THE FILE AS WELL

        final Map<String, MutablePair<String, String>> statisticsMap = Save.loadGameStatistics();

        final ArrayList<String> playersNameList = new ArrayList<>(Arrays.asList(playerName, newPlayerName, playerName3));
        final ArrayList<String> playersScoreList = new ArrayList<>(Arrays.asList(String.valueOf(playerScore), String.valueOf(newPlayerScore), String.valueOf(playerScore3)));
        final ArrayList<String> playerDateList = new ArrayList<>(Arrays.asList(date, date2, date3, newPlayerDate));

        // CHECK THAT ALL THE UPDATED DATA IS PRESENT

        assertTrue(statisticsMap.keySet().containsAll(playersNameList));

        for(final var v : statisticsMap.values()){
            assertTrue(playersScoreList.contains(v.getLeft()));
            assertTrue(playerDateList.contains(v.getRight()));
        }

        // CHECK THAT THE DATA THAT HAS BEEN REPLACED ARE NOT PRESENT

        assertFalse(statisticsMap.containsKey(playerName2));
        assertFalse(statisticsMap.containsValue(new MutablePair<>(String.valueOf(playerScore2), date2)));
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
    public void saveAndLoadSettingsTest(){

        // Before the test I delete all the previously stored data in the save file.
        Save.resetFile(Save.SETTINGS_PATH);

        final int resWidth = 1920;
        final int resHeight = 1080;
        final String difficulty = "hard";
        final double audioVolume = 30.0;
        final String language = "it_IT";

        final boolean hasSavedSettings = Save.saveSettings(resWidth, resHeight, difficulty, audioVolume, language);

        assertTrue(hasSavedSettings);

        final HashMap<String, String> settingsMap = new HashMap<>();

        settingsMap.put(Save.RESOLUTION_WIDTH_STRING, String.valueOf(resWidth));
        settingsMap.put(Save.RESOLUTION_HEIGHT_STRING, String.valueOf(resHeight));
        settingsMap.put(Save.DIFFICULTY_STRING, difficulty);
        settingsMap.put(Save.AUDIO_STRING, String.valueOf(audioVolume));
        settingsMap.put(Save.LANGUAGE_STRING, language);

        Map<String, String> loadedSettings = Save.loadSettings();

        assertFalse(loadedSettings.isEmpty());
        assertEquals(settingsMap, loadedSettings);

        // UPDATE THE DATA
        final int resWidth2 = 1280;
        final int resHeight2 = 720;
        final String difficulty2 = "easy";
        final double audioVolume2 = 20.0;
        final String language2 = "en_UK";

        final boolean hasUpdatedSettings = Save.saveSettings(resWidth2, resHeight2, difficulty2, audioVolume2, language2);

        assertTrue(hasUpdatedSettings);

        settingsMap.clear();

        settingsMap.put(Save.RESOLUTION_WIDTH_STRING, String.valueOf(resWidth2));
        settingsMap.put(Save.RESOLUTION_HEIGHT_STRING, String.valueOf(resHeight2));
        settingsMap.put(Save.DIFFICULTY_STRING, difficulty2);
        settingsMap.put(Save.AUDIO_STRING, String.valueOf(audioVolume2));
        settingsMap.put(Save.LANGUAGE_STRING, language2);

        loadedSettings = Save.loadSettings();

        assertFalse(loadedSettings.isEmpty());
        assertEquals(settingsMap, loadedSettings);
    }

    @Test
    public void loadLevelTest() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IOException, InvalidKeyException {

        final int numberOfLevels = 4;

        final int txtLevels = Save.getNumberOfLevels(Extensions.TXT);
        final int datLevels = Save.getNumberOfLevels(Extensions.DAT);

        // If there are no levels in the .txt extension then we check it, otherwise we encrypt the files in the .dat extension and we remove the .txt
        // If there are no .txt then there is no need to encrypt the levels.
        // We need all 4 .txt to execute the encryptLevels() method.
        if(txtLevels == 0){
            assertEquals(0, txtLevels);
            assertEquals(numberOfLevels, datLevels);
        } else {
            Save.encryptLevels();
            final int txtAfterEncryption = Save.getNumberOfLevels(Extensions.TXT);
            final int datAfterEncryption = Save.getNumberOfLevels(Extensions.DAT);
            assertEquals(0, txtAfterEncryption);
            assertEquals(numberOfLevels, datAfterEncryption);
        }
    }

}
