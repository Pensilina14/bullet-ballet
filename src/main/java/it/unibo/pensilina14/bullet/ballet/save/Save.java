package it.unibo.pensilina14.bullet.ballet.save;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    private static final String OLD_FILE_EXTENSION = ".txt";
    private static final String ENCRYPTED_FILES_EXTENSION = ".dat";

    public static final String PLAYER_STRING = "Player"; //TODO: rename it better
    public static final String SCORE_STRING = "Score"; //TODO: rename it better

    public static final String RESOLUTION_WIDTH_STRING = "Width";
    public static final String RESOLUTION_HEIGHT_STRING = "Height";
    public static final String DIFFICULTY_STRING = "Difficulty";
    public static final String AUDIO_STRING = "Audio";

    /**
     * private constructor because I don't want the class to be instantiated.
     */
    private Save(){}

    /**
     *
     * @param playerName:  the name of the player that you want to save.
     * @param playerScore: the score of the player that you want to save.
     * The data is encrypted and saved in .dat file.
     * If the files didn't exist it will be created.
     * If the file already had data in it, the new data will be encrypted and appended so nothing will be lost.
     */
    public static void saveGameStatistics(final String playerName, final int playerScore){ //TODO: opzionale : salvare il tempo.
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        File file = new File(Save.SAVE_PATH);

        try{
            if(file.length() == 0){ // FILE VUOTO
                jsonArray = new JSONArray();
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put(Save.PLAYER_STRING, playerName);
                map.put(Save.SCORE_STRING, String.valueOf(playerScore));
                jsonArray.add(map);

                byte[] encryptedMessage = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

                // Usiamo FileOutputStream al posto di FileWriter perchè questo ci permette di scrivere bytes, mentre FileWriter prende stringhe.
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(encryptedMessage);

                stream.close();

            } else{
                byte[] decryptedMessage = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD);
                String decryptedMessageString = new String(decryptedMessage, StandardCharsets.UTF_8);
                Object obj = jsonParser.parse(decryptedMessageString);

                jsonArray = (JSONArray) obj;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Save.PLAYER_STRING, playerName);
                jsonObject.put(Save.SCORE_STRING, String.valueOf(playerScore));

                jsonArray.add(jsonObject);

                byte[] encryptedMessage = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

                FileOutputStream stream = new FileOutputStream(file);
                stream.write(encryptedMessage);

                stream.close();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return HashMap<String, Integer>: an HashMap containing all the players saved in the save file and their relative score.
     */
    public static LinkedHashMap<String, String> loadGameStatistics(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        JSONParser jsonParser = new JSONParser();


        try {
            byte[] decryptedMessage = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD); // mettere save_path se voglio direttamente salvare i dati criptati

            String clearMessage = new String(decryptedMessage, StandardCharsets.UTF_8);

            System.out.println("decryptedMessage: " + Arrays.toString(decryptedMessage)); //TODO: remove
            System.out.println("clearMessage: " + clearMessage); //TODO: remove
            JSONArray jsonArray = (JSONArray) jsonParser.parse(clearMessage);

            //JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(Save.SAVE_PATH));

            for(Object o : jsonArray){

                JSONObject player = (JSONObject)o;

                String name = (String)player.get(Save.PLAYER_STRING);
                System.out.println("name: " + name);

                String score = (String)player.get(Save.SCORE_STRING);
                System.out.println("score: " + score);

                map.put(name, String.valueOf(score));

            }

        }catch(Exception e){
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
     * @return : a boolean whether the operation has been executed successfully
     */
    public static boolean updateGameStatistics(final String oldPlayer, final int oldScore, final String newPlayer, final int newScore) {
        final JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        final File file = new File(Save.SAVE_PATH);

        try {
            final byte[] decryptGameStatistics = SecureData.decryptFile(Save.SAVE_PATH, SecureData.PASSWORD);
            final String clearGameStatistics = new String(decryptGameStatistics, StandardCharsets.UTF_8);

            Object obj = jsonParser.parse(clearGameStatistics);
            jsonArray = (JSONArray) obj;

            for(Object o : jsonArray){

                JSONObject player = (JSONObject) o;

                player.replace(Save.PLAYER_STRING, oldPlayer, newPlayer);
                player.replace(Save.SCORE_STRING, String.valueOf(oldScore), String.valueOf(newScore));
            }

            System.out.println("jsonArray: " + jsonArray); //TODO: remove

            byte[] encryptedStatistics = SecureData.encrypt(jsonArray.toJSONString().getBytes(), SecureData.PASSWORD);

            FileOutputStream stream = new FileOutputStream(file);
            stream.write(encryptedStatistics);

            stream.close();

        } catch(Exception e){
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
        int numberOfLevels = getNumberOfLevels(Save.OLD_FILE_EXTENSION);

        for(int i = 0; i < numberOfLevels; i++){
            String levelToEncrypt = Save.LEVEL_PATH + "level" + i + ".txt";
            String levelEncrypted = Save.LEVEL_PATH + "level" + i + ".dat";
            SecureData.encryptFile(levelToEncrypt, levelEncrypted, SecureData.PASSWORD );

            //File levelFile = new File(levelToEncrypt); //TODO: uncomment quando avremo finito di testare i livelli.
            //levelFile.delete(); //TODO: uncomment quando avremo finito di testare i livelli.
        }
    }

    /**
     *
     * @param levelNumber: the number of the level that we want to load.
     * @return String[]: an array of strings with the data of the level.
     */
    public static String[] oldLoadLevel(int levelNumber){ //TODO: remove when we finished to test levels.

        String[] level;
        ArrayList<String> levelList = new ArrayList<>();
        String line;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Save.LEVEL_PATH + "level" + levelNumber + ".txt")); //TODO: cambiare .txt in .dat

            while((line = bufferedReader.readLine()) != null && line.length() != 0){
                levelList.add(String.valueOf(line));
            }

            bufferedReader.close();

        }catch(Exception e){
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
    public static String[] loadLevel(int levelNumber) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IOException, InvalidKeyException {
        String encryptedLevelPath = Save.LEVEL_PATH + "level" + levelNumber + Save.ENCRYPTED_FILES_EXTENSION;
        byte[] decryptedLevel = SecureData.decryptFile(encryptedLevelPath, SecureData.PASSWORD);
        String clearLevel = new String(decryptedLevel, StandardCharsets.UTF_8);
        //String[] level = clearLevel.split("\\n+"); //TODO: remove

        return clearLevel.split("[\\r\\n]+");
    }

    /**
     *
     * @param levelNumber: the level that we want to delete.
     * It will delete all the data about the specified level.
     */
    public static void resetLevelFile(int levelNumber){ //TODO: forse non serve.
        try {
            FileWriter fileWriter = new FileWriter(Save.LEVEL_PATH + "level" + levelNumber + ".txt", false);
            fileWriter.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param extension : the extension of the level files.
     * @return : an int with the number of the levels present in the levels' directory.
     */
    public static int getNumberOfLevels(String extension){
        File levelsDirectory = new File(Save.LEVEL_PATH);
        return Objects.requireNonNull(levelsDirectory.listFiles((dir, filter) -> filter.toLowerCase().endsWith(extension))).length;
    }

    /**
     *
     * @param resWidth : the resolution's width of the game.
     * @param resHeight : the resolution's height of the game.
     * @param difficulty : the difficulty of the game.
     * @param audioVolume : the in-game audio volume.
     * @return : a boolean whether the file has been saved successfully or not.
     */
    public static boolean saveSettings(final int resWidth, final int resHeight, final String difficulty, final double audioVolume){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        File file = new File(Save.SETTINGS_PATH);

        try {
            if(file.length() == 0){
                jsonObject.put(Save.RESOLUTION_WIDTH_STRING, resWidth);
                jsonObject.put(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                jsonObject.put(Save.DIFFICULTY_STRING, difficulty);
                jsonObject.put(Save.AUDIO_STRING, audioVolume);

                byte[] encryptedSettings = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedSettings);

                fileOutputStream.close();
            } else {
                byte[] decryptedSettings = SecureData.decryptFile(Save.SETTINGS_PATH, SecureData.PASSWORD);
                String decryptedSettingsString = new String(decryptedSettings, StandardCharsets.UTF_8);
                Object obj = jsonParser.parse(decryptedSettingsString);

                jsonObject = (JSONObject) obj;

                jsonObject.replace(Save.RESOLUTION_WIDTH_STRING, resWidth);
                jsonObject.replace(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                jsonObject.replace(Save.DIFFICULTY_STRING, difficulty);
                jsonObject.replace(Save.AUDIO_STRING, audioVolume);

                byte[] encryptedMessage = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedMessage);

                fileOutputStream.close();
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @return an HashMap<String, String> with the settings' data.
     */
    public static HashMap<String, String> loadSettings(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        JSONParser jsonParser = new JSONParser();

        try {
            byte[] decryptMessage = SecureData.decryptFile(Save.SETTINGS_PATH, SecureData.PASSWORD);

            String clearMessage = new String(decryptMessage, StandardCharsets.UTF_8);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(clearMessage);

            for(int i = 0; i < jsonObject.size(); i++){

                String resWidth = jsonObject.get(Save.RESOLUTION_WIDTH_STRING).toString();

                String resHeight = jsonObject.get(Save.RESOLUTION_HEIGHT_STRING).toString();

                String difficulty = jsonObject.get(Save.DIFFICULTY_STRING).toString();

                String audio = jsonObject.get(Save.AUDIO_STRING).toString();

                map.put(Save.RESOLUTION_WIDTH_STRING, resWidth);
                map.put(Save.RESOLUTION_HEIGHT_STRING, resHeight);
                map.put(Save.DIFFICULTY_STRING, difficulty);
                map.put(Save.AUDIO_STRING, audio);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     *
     * @param filePath : the path of the file that you want to reset all the data.
     */
    public static void resetFile(final String filePath){
        try {
            FileWriter fileWriter = new FileWriter(filePath, false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
