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

public final class Save { //TODO: rename in Data?

    private static final String SAVE_PATH = "data/saves/save_file.dat"; //TODO: aggiungere paths
    private static final String LEVEL_PATH = "data/levels/"; //TODO: data/levels/
    //private static final String ENCRYPTED_LEVEL_PATH = "data/levels/"; //TODO: aggiungere path, remove
    private static final String SETTINGS_PATH = "data/settings/"; //TODO: data/settings/...
    private static final String ENCRYPTED_SETTINGS_PATH = ""; //TODO: aggiungere path

    private static final String OLD_FILE_EXTENSION = ".txt";
    private static final String ENCRYPTED_FILES_EXTENSION = ".dat";

    private static final String PLAYER_STRING = "Player"; //TODO: rename it better
    private static final String SCORE_STRING = "Score"; //TODO: rename it better

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
    public static void saveData(final String playerName, final int playerScore){
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
    public static LinkedHashMap<String, Integer> loadSaveFile(){  //TODO: rename in loadData oppure semplicemente load oppure loadSaveFile
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
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

                map.put(name, Integer.valueOf(score));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * It will delete all the data stored in the save_file.txt, but it will keep the file.
     */
    public static void resetSaveFile(){

        try {

            FileWriter fileWriter = new FileWriter(Save.SAVE_PATH, false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //TODO: saveLevel not ready.
    /*public static void saveLevel(ArrayList<String> newLevel, int levelNumber){ //TODO: modify and test it.
        try {
            FileWriter fileWriter = new FileWriter(Save.LEVEL_PATH + "level" + levelNumber + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String s : newLevel){
                bufferedWriter.write(String.valueOf(s));
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();

            fileWriter.close();
            bufferedWriter.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }*/

    //TODO: add javadoc
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

    //TODO: add javadoc
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
     * @return int: the number of files in the directory "levels/", so all the levels stored.
     */
    public static int getNumberOfLevels(){ //TODO: remove
        return Objects.requireNonNull(new File(Save.LEVEL_PATH).listFiles()).length;
    }

    public static int getNumberOfLevels(String extension){
        File levelsDirectory = new File(Save.LEVEL_PATH);
        return Objects.requireNonNull(levelsDirectory.listFiles((dir, filter) -> filter.toLowerCase().endsWith(extension))).length;
    }

    /**
     *
     * @param oldPlayer: the name of the Player that we want to find and replace.
     * @param oldScore: the score of the player that we want to find and replace.
     * @param newPlayer: the name with which we want to replace it.
     * @param newScore: the score with which we want to replace it.
     */
    public static void modifySaveFile(String oldPlayer, int oldScore, String newPlayer, int newScore){ //TODO: rename it in playerToSearch ?, modify it to use it with JSON.
        try {
            File saveFile = new File("save_file.txt");
            FileReader fileReader = new FileReader(saveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            ArrayList<String> modifiedDataList = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null && line.length() != 0){
                if(line.contains(oldPlayer)){
                    line = line.replace(oldPlayer, newPlayer);
                }
                if(line.contains(String.valueOf(oldScore))){
                    line = line.replace(String.valueOf(oldScore), String.valueOf(newScore));
                }
                modifiedDataList.add(line);
            }

            fileReader.close();
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(saveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String s : modifiedDataList){
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            fileWriter.close();
            bufferedWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
