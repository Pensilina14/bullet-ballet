package it.unibo.pensilina14.bullet.ballet.save;

import java.io.*;
import java.util.*;

public final class Save {

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
    public static void save(String playerName, int playerScore){

        try {
            FileWriter file = new FileWriter("save_file.txt", true); // true sta a significare di appendere se il file esiste
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

    /**
     *
     * @return HashMap<String, Integer>: an HashMap containing all the players saved in the save_file.txt and their relative score.
     */
    public static HashMap<String, Integer> load() {

        HashMap<String, Integer> data = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("save_file.txt"));

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

            FileWriter fileWriter = new FileWriter("save_file.txt", false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //TODO: saveLevel not ready.
    public static void saveLevel(ArrayList<String> newLevel, int levelNumber){ //TODO: modify and test it.
        try {
            FileWriter fileWriter = new FileWriter("levels/level" + levelNumber + ".txt");
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader("levels/level" + levelNumber + ".txt"));

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
            FileWriter fileWriter = new FileWriter("level" + levelNumber + ".txt", false);
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
        return Objects.requireNonNull(new File("levels/").listFiles()).length;
    }

    /**
     *
     * @param oldPlayer: the name of the Player that we want to find and replace.
     * @param oldScore: the score of the player that we want to find and replace.
     * @param newPlayer: the name with which we want to replace it.
     * @param newScore: the score with which we want to replace it.
     */
    public static void modifySaveFile(String oldPlayer, int oldScore, String newPlayer, int newScore){ //TODO: rename it in playerToSearch ?
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
