package it.unibo.pensilina14.bullet.ballet.save;

import java.io.*;
import java.util.HashMap;

public class Save {

    // Salvare il nome del player e il suo relativo punteggio e appendarlo al file.
    public void save(String playerName, int playerScore){

        try {
            FileWriter file = new FileWriter("save_file.txt", true); // true sta a significare di appendere se il file esiste
            BufferedWriter bufferedWriter = new BufferedWriter(file);

            bufferedWriter.write(playerName);
            bufferedWriter.newLine();

            bufferedWriter.write(String.valueOf(playerScore));
            bufferedWriter.newLine();

            bufferedWriter.close();
            file.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    // Caricare il file del salvataggio e recuperare le informazioni riguardanti tutti i giocatori e i loro punteggi.
    // Restituire un HashMap con Nome del Player e relativo punteggio.
    public HashMap<String, Integer> load() {

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

    public void resetSaveFile(){

        try {

            FileWriter fileWriter = new FileWriter("save_file.txt", false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
