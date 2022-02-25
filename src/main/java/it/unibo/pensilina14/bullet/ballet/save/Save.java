package it.unibo.pensilina14.bullet.ballet.save;

import org.apache.commons.lang3.tuple.MutablePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public final class Save {

    public static final String SAVE_PATH = "data/saves/save_file.dat";
    private static final String LEVEL_PATH = "data/levels/";
    public static final String SETTINGS_PATH = "data/settings/settings.dat";

    public static final String PLAYER_STRING = "Player";
    public static final String SCORE_STRING = "Score";
    public static final String DATE_STRING = "Date";

    public static final String RESOLUTION_WIDTH_STRING = "Width";
    public static final String RESOLUTION_HEIGHT_STRING = "Height";
    public static final String DIFFICULTY_STRING = "Difficulty";
    public static final String AUDIO_STRING = "Audio";
    public static final String LANGUAGE_STRING = "Language";

    /**
     * private constructor because I don't want the class to be instantiated.
     */
    private Save(){}

    /**
     *
     * @param playerName:  the name of the player that you want to save.
     * @param playerScore: the score of the player that you want to save.
     * @param date: the date of when the game has been played.
     * The data is encrypted and saved in .dat file.
     * If the files didn't exist it will be created.
     * If the file already had data in it, the new data will be encrypted and appended so nothing will be lost.
     */
    public static void saveGameStatistics(final String playerName, final double playerScore, final String date){
        final JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        final File file = new File(Save.SAVE_PATH);

        try{
            if(file.length() == 0){
                jsonArray = new JSONArray();
                final LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put(Save.PLAYER_STRING, playerName);
                map.put(Save.SCORE_STRING, String.valueOf(playerScore));
                map.put(Save.DATE_STRING, date);
                jsonArray.add(map);

                final byte[] encryptedMessage = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

                // We use FileOutputStream instead of FileWriter because this allows us to write in bytes, while FileWriter takes strings.
                final FileOutputStream stream = new FileOutputStream(file);
                stream.write(encryptedMessage);

                stream.close();

            } else{
            	final byte[] decryptedMessage = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD);
            	final String decryptedMessageString = new String(decryptedMessage, StandardCharsets.UTF_8);
            	final Object obj = jsonParser.parse(decryptedMessageString);

                jsonArray = (JSONArray) obj;

                final JSONObject jsonObject = new JSONObject();
                jsonObject.put(Save.PLAYER_STRING, playerName);
                jsonObject.put(Save.SCORE_STRING, String.valueOf(playerScore));
                jsonObject.put(Save.DATE_STRING, date);

                jsonArray.add(jsonObject);

                final byte[] encryptedMessage = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

                final FileOutputStream stream = new FileOutputStream(file);
                stream.write(encryptedMessage);

                stream.close();

            }
        }catch(NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | ParseException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return HashMap<String, Integer>: an HashMap containing all the players saved in the save file and their relative score.
     */
    public static Map<String, MutablePair<String, String>> loadGameStatistics(){
    	final LinkedHashMap<String, MutablePair<String, String>> map = new LinkedHashMap<>();
    	final JSONParser jsonParser = new JSONParser();

    	final File statsFile = new File(Save.SAVE_PATH);

        // I put the if in the try to keep in consideration the possibility that the file doesn't exist, even though it shouldn't be a problem.
        try {
            if(statsFile.length() != 0){
            	final byte[] decryptedMessage = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD);

            	final String clearMessage = new String(decryptedMessage, StandardCharsets.UTF_8);

                final JSONArray jsonArray = (JSONArray) jsonParser.parse(clearMessage);

                for (final Object o : jsonArray) {

                	final JSONObject player = (JSONObject) o;

                	final String name = (String) player.get(Save.PLAYER_STRING);

                    final String score = (String) player.get(Save.SCORE_STRING);

                    final String date = (String) player.get(Save.DATE_STRING);

                    map.put(name, new MutablePair<>(String.valueOf(score), date));
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | ParseException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     *
     * @param oldPlayer : the name of the player that you want to update
     * @param oldScore : the score of the player that you want to update
     * @param newPlayer : the new name of the player
     * @param newScore : the new score of the player
     * @param oldDate : the date when the game has been played.
     * @param newDate : the new date when the game has been played.
     * @return : a boolean whether the operation has been executed successfully
     */
    public static boolean updateGameStatistics(final String oldPlayer, final double oldScore, final String newPlayer, final double newScore,
                                               final String oldDate, final String newDate) {
        final JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        final File file = new File(Save.SAVE_PATH);

        // I put the if in the try to keep in consideration the possibility that the file doesn't exist, even though it shouldn't be a problem.
        try {
            if(file.length() != 0){
                final byte[] decryptGameStatistics = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD);
                final String clearGameStatistics = new String(decryptGameStatistics, StandardCharsets.UTF_8);

                final Object obj = jsonParser.parse(clearGameStatistics);
                jsonArray = (JSONArray) obj;

                for (final Object o : jsonArray) {

                	final JSONObject player = (JSONObject) o;

                    player.replace(Save.PLAYER_STRING, oldPlayer, newPlayer);
                    player.replace(Save.SCORE_STRING, String.valueOf(oldScore), String.valueOf(newScore));
                    player.replace(Save.DATE_STRING, oldDate, newDate);
                }

                final byte[] encryptedStatistics = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

                final FileOutputStream stream = new FileOutputStream(file);
                stream.write(encryptedStatistics);

                stream.close();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * It encrypts the .txt level files into .dat files and remove the old .txt
     * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
     * @throws NoSuchPaddingException: padding not available.
     * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
     * @throws NoSuchAlgorithmException: algorithm doesn't exist.
     * @throws InvalidKeySpecException: invalid key specifications.
     * @throws BadPaddingException: input data not properly padded.
     * @throws IOException: fail or interrupted I/O operations.
     * @throws InvalidKeyException: invalid key.
     */
    public static void encryptLevels() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IOException, InvalidKeyException {
    	final int numberOfLevels = getNumberOfLevels(Extensions.TXT.getExtension());
    	
    	final String level = "level";

        for(int i = 0; i < numberOfLevels; i++){
        	final String levelToEncrypt = Save.LEVEL_PATH + level + i + Extensions.TXT.getExtension();
        	final String levelEncrypted = Save.LEVEL_PATH + level + i + Extensions.DAT.getExtension();
            SecureData.encryptFile(levelToEncrypt, levelEncrypted, SecureData.PASSWORD );

            File levelFile = new File(levelToEncrypt);
            levelFile.delete();
        }
    }

    /**
     *
     * @param levelNumber: the number of the level that we want to load.
     * @return String[]: an array of strings with the data of the level.
     */
    public static String[] loadLevelForTesting(final int levelNumber){

        String[] level;
        final ArrayList<String> levelList = new ArrayList<>();
        String line;

        try{
        	final BufferedReader bufferedReader = new BufferedReader(new FileReader(Save.LEVEL_PATH + "level" + levelNumber + Extensions.TXT.getExtension()));
        	
        	line = bufferedReader.readLine();

            while(Objects.nonNull(line) && !line.isEmpty()){
                levelList.add(line);
				line = bufferedReader.readLine();
            }

            bufferedReader.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        level = levelList.toArray(String[]::new);

        return level;
    }

    /**
     *
     * @param levelNumber : the number of the level that you want to load
     * @return a String[] of the level
     * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
     * @throws NoSuchPaddingException: padding not available.
     * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
     * @throws NoSuchAlgorithmException: algorithm doesn't exist.
     * @throws InvalidKeySpecException: invalid key specifications.
     * @throws BadPaddingException: input data not properly padded.
     * @throws IOException: fail or interrupted I/O operations.
     * @throws InvalidKeyException: invalid key.
     */
    public static String[] loadLevel(final int levelNumber) {
        final String clearLevel;
        try {
            final String encryptedLevelPath = Save.LEVEL_PATH + "level" + levelNumber + Extensions.DAT.getExtension();
            final byte[] decryptedLevel = SecureData.decryptFile(encryptedLevelPath, SecureData.PASSWORD);
            clearLevel = new String(decryptedLevel, StandardCharsets.UTF_8);

            return clearLevel.split("[\\r\\n]+");

        }catch(InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                InvalidKeySpecException | BadPaddingException | IOException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return new String[0];
    }

    /**
     *
     * @param levelNumber: the level that we want to delete.
     * It will delete all the data about the specified level.
     */
    public static void resetLevelFile(final int levelNumber) {
        try {
        	final FileWriter fileWriter = new FileWriter(Save.LEVEL_PATH + "level" + levelNumber + Extensions.TXT.getExtension(), false);
            fileWriter.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param extension : the extension of the level files.
     * @return : an int with the number of the levels present in the levels' directory.
     */
	public static int getNumberOfLevels(final String extension){
    	final File levelsDirectory = new File(Save.LEVEL_PATH);
        return Objects.requireNonNull(levelsDirectory.listFiles((dir, filter) -> filter.toLowerCase(Locale.getDefault()).endsWith(extension))).length;
    }

	public static int getNumberOfLevels(final Extensions extension){
    	final File levelsDirectory = new File(Save.LEVEL_PATH);
        return Objects.requireNonNull(levelsDirectory.listFiles((dir, filter) -> filter.toLowerCase(Locale.getDefault()).endsWith(extension.getExtension()))).length;
    }

    /**
     *
     * @param resWidth : the resolution's width of the game.
     * @param resHeight : the resolution's height of the game.
     * @param difficulty : the difficulty of the game.
     * @param audioVolume : the in-game audio volume.
     * @param language  : the language
     * @return : a boolean whether the file has been saved successfully or not.
     */
    public static boolean saveSettings(final int resWidth, final int resHeight, final String difficulty, final double audioVolume, final String language){
    	final JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        final File file = new File(Save.SETTINGS_PATH);

        // I put the if in the try to keep in consideration the possibility that the file doesn't exist, even though it shouldn't be a problem.
        try {
            if(file.length() == 0){
                jsonObject.put(Save.RESOLUTION_WIDTH_STRING, resWidth);
                jsonObject.put(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                jsonObject.put(Save.DIFFICULTY_STRING, difficulty);
                jsonObject.put(Save.AUDIO_STRING, audioVolume);
                jsonObject.put(Save.LANGUAGE_STRING, language);

                final byte[] encryptedSettings = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedSettings);

                fileOutputStream.close();
            } else {
            	final byte[] decryptedSettings = SecureData.decryptFile(Save.SETTINGS_PATH, SecureData.PASSWORD);
            	final String decryptedSettingsString = new String(decryptedSettings, StandardCharsets.UTF_8);
            	final Object obj = jsonParser.parse(decryptedSettingsString);

                jsonObject = (JSONObject) obj;

                jsonObject.replace(Save.RESOLUTION_WIDTH_STRING, resWidth);
                jsonObject.replace(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                jsonObject.replace(Save.DIFFICULTY_STRING, difficulty);
                jsonObject.replace(Save.AUDIO_STRING, audioVolume);
                jsonObject.replace(Save.LANGUAGE_STRING, language);

                final byte[] encryptedMessage = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedMessage);

                fileOutputStream.close();
            }
        } catch(NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | ParseException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @return an HashMap<String, String> with the settings' data.
     */
    public static Map<String, String> loadSettings() {
    	final HashMap<String, String> map = new HashMap<>();
    	final JSONParser jsonParser = new JSONParser();

    	final File settingsFile = new File(Save.SETTINGS_PATH);

        try {
            if(settingsFile.length() != 0){
            	final byte[] decryptMessage = SecureData.decryptFile(Save.SETTINGS_PATH, SecureData.PASSWORD);

            	final String clearMessage = new String(decryptMessage, StandardCharsets.UTF_8);

            	final JSONObject jsonObject = (JSONObject) jsonParser.parse(clearMessage);

                for (int i = 0; i < jsonObject.size(); i++) {

                	final String resWidth = jsonObject.get(Save.RESOLUTION_WIDTH_STRING).toString();

                	final String resHeight = jsonObject.get(Save.RESOLUTION_HEIGHT_STRING).toString();

                	final String difficulty = jsonObject.get(Save.DIFFICULTY_STRING).toString();

                	final String audio = jsonObject.get(Save.AUDIO_STRING).toString();

                	final String language = jsonObject.get(Save.LANGUAGE_STRING).toString();

                    map.put(Save.RESOLUTION_WIDTH_STRING, resWidth);
                    map.put(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                    map.put(Save.DIFFICULTY_STRING, difficulty);
                    map.put(Save.AUDIO_STRING, audio);
                    map.put(Save.LANGUAGE_STRING, language);
                }
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | ParseException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     *
     * @param filePath : the path of the file that you want to reset all the data. 
     */
    public static void resetFile(final String filePath) {
        try {
        	final FileWriter fileWriter = new FileWriter(filePath, false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
