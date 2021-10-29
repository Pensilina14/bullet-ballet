package it.unibo.pensilina14.bullet.ballet.save;

import org.apache.commons.configuration2.JSONConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public final class Save { //TODO: rename in Data?

    private static final String SAVE_PATH = "data/saves/save_file.json"; //TODO: aggiungere paths
    private static final String OLD_SAVE_FILE_PATH = "data/saves/save_file.txt";
    private static final String LEVEL_PATH = "data/levels/"; //TODO: data/levels/
    private static final String SETTINGS_PATH = "data/settings/"; //TODO: data/settings/...

    private static final String PLAYER_STRING = "Player"; //TODO: rename it better
    private static final String SCORE_STRING = "Score"; //TODO: rename it better

    private Save(){

    }

    /**
     *
     * @param playerName: the name of the player that you want to save.
     * @param playerScore: the score of the player that you want to save.
     * The data is saved in a .txt file named save_file in the project directory
     * If it is the first time that you call it, it will create the file, otherwise it will append the data to the file without deleting
     * previous stored data.
     */
    public static void save(String playerName, int playerScore){ //TODO: remove

        try {
            FileWriter file = new FileWriter(Save.OLD_SAVE_FILE_PATH, true); // true sta a significare di appendere se il file esiste
            BufferedWriter bufferedWriter = new BufferedWriter(file);

            bufferedWriter.write(playerName);
            bufferedWriter.newLine();

            bufferedWriter.write(String.valueOf(playerScore));
            bufferedWriter.newLine();

            bufferedWriter.flush();

            bufferedWriter.close();
            file.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    //TODO: add javadoc
    public static void saveJSON(final String playerName, final int playerScore){ //TODO: rename in just save
        JSONParser jsonParser = new JSONParser();

        JSONArray jsonArray;
        FileWriter fileWriter;

        try{
            //fileWriter = new FileWriter(Save.SAVE_PATH);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Save.SAVE_PATH));

            if(bufferedReader.readLine() == null){
                jsonArray = new JSONArray();
                LinkedHashMap<String, String> map = new LinkedHashMap<>(); //TODO: String, Integer
                map.put(Save.PLAYER_STRING, playerName);
                map.put(Save.SCORE_STRING, String.valueOf(playerScore));
                jsonArray.add(map);

                fileWriter = new FileWriter(Save.SAVE_PATH);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();

                fileWriter.close();
            } else {
                Object obj = jsonParser.parse(new FileReader(Save.SAVE_PATH));

                jsonArray = (JSONArray) obj;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Save.PLAYER_STRING, playerName);
                jsonObject.put(Save.SCORE_STRING, String.valueOf(playerScore));

                jsonArray.add(jsonObject);

                fileWriter = new FileWriter(Save.SAVE_PATH);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();

                fileWriter.close();
            }
        }catch(Exception e){ //TODO: FileNotFoundException, IOInput
            e.printStackTrace();
        }
    }

    //TODO: add javadoc
    public static final LinkedHashMap<String, Integer> loadJSON(){  //TODO: rename in loadData oppure semplicemente load oppure loadSaveFile
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(Save.SAVE_PATH));

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
     *
     * @return HashMap<String, Integer>: an HashMap containing all the players saved in the save_file.txt and their relative score.
     */
    public static HashMap<String, Integer> load() { //TODO: remove

        HashMap<String, Integer> data = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(OLD_SAVE_FILE_PATH));

            String line;
            String playerName;
            int playerScore;

            while((line = bufferedReader.readLine()) != null && line.length() != 0){
                playerName = line;
                playerScore = Integer.parseInt(bufferedReader.readLine());

                data.put(playerName, playerScore);
            }

            bufferedReader.close();

        } catch(Exception e){
            e.printStackTrace();
        }


        return data;
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
    public static void saveLevel(ArrayList<String> newLevel, int levelNumber){ //TODO: modify and test it.
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
    }

    /**
     *
     * @param levelNumber: the number of the level that we want to load.
     * @return String[]: an array of strings with the data of the level.
     */
    public static String[] loadLevel(int levelNumber){

        String[] level;
        ArrayList<String> levelList = new ArrayList<>();
        String line;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Save.LEVEL_PATH + "level" + levelNumber + ".txt"));

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
     * @param levelNumber: the level that we want to delete.
     * It will delete all the data about the specified level.
     */
    public static void resetLevelFile(int levelNumber){
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
    public static int getNumberOfLevels(){
        return Objects.requireNonNull(new File(Save.LEVEL_PATH).listFiles()).length;
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
