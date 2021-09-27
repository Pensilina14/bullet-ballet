package it.unibo.pensilina14.bullet.ballet.graphics.map;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Map {

    private final static int WIDTH = 1280;
    private final static int HEIGHT = 720;

    private final Set<Pair<Integer,Integer>> tiles = new HashSet<>();

    public enum Maps {
        HALLOWEEN("res/assets/maps/Backgrounds/spooky_background.jpg"),
        JUNGLE("res/assets/maps/Backgrounds/jungle_background.jpg"),
        JUNGLE2("res/assets/maps/Backgrounds/jungle_background2.jpg"),
        JUNGLE3("res/assets/maps/Backgrounds/jungle_background3.png"),
        //FOREST("res/assets/maps/Backgrounds/"),
        CAVE("res/assets/maps/Backgrounds/cave_background.png"),
        CAVE2("res/assets/maps/Backgrounds/cave_background2.jpg"),
        CAVE3("res/assets/maps/Backgrounds/cave_background3.jpg"),
        LAVA("res/assets/maps/Backgrounds/lava_background.png"),
        DESERT("res/assets/maps/Backgrounds/desert_background.jpg"),
        DESERT2("res/assets/maps/Backgrounds/desert_background2.jpg"),
        FUTURISTIC("res/assets/maps/Backgrounds/futuristic_background.jpg");

        String path;

        Maps(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }
    }

    //TODO: metodo da tenere qui o da mettere in MapScene
    public void generate(){
        //TODO: posizionare a y : 0 (ovvero, HEIGHT : 0 + qualcosa) le piastrelle, capire quante piastrelle ci stanno nella larghezza della mappa
        //TODO: quindi fare HEIGHT / larghezza_di_una_piastrella = numero_piastrelle che ci stanno nello schermo.
        //TODO: aggiungere le coordinate di queste piastrelle in un Unmodifiable Set.
        //TODO: però quando il player arriva a metà della mappa, la mappa si deve aggiornare mostrando nuove piastrelle (tiles) in nuove posizioni.
        //TODO: Le posizioni delle piastrelle dovranno essere aggiunte alle entità fisiche e quindi ci sarà una collisione/fisica che fa stare il player sopra esse.
    }
}
