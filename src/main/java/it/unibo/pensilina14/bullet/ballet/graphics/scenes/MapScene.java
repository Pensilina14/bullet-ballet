package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.LevelData;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class MapScene extends AbstractScene{

    public static Pane appPane = new Pane();
    public static Pane gamePane = new Pane();
    public static Pane uiPane = new Pane();

    private int levelWidth;

    private Map map = new Map(); // da questa istanza della mappa prendo sia il background sia la piattaforma scelta.

    private ImageView backgroundView;

    //TODO: Add private MainPlayer mainPlayer

    public static ArrayList<Platform> platforms = new ArrayList<>();
    private HashMap<KeyCode,Boolean> keys = new HashMap<>();

    private final static int MAP_LENGTH = 60;

    public MapScene(){

        // serve per quando si cambia la risoluzione del gioco.
        appPane.setMaxWidth(AbstractScene.SCENE_WIDTH);
        appPane.setMaxHeight(AbstractScene.SCENE_HEIGHT); // Questi mi servono perchè ci sono alcuni background che sono più grandi della MAX WIDTH AND MAX HEIGHT
    }

    public void generateMap() throws IOException { //TODO: passare mappa e platformType

        this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));

        this.backgroundView.fitWidthProperty().bind(MapScene.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(MapScene.appPane.heightProperty());

        this.levelWidth = LevelData.LEVEL1[0].length() * MapScene.MAP_LENGTH;

        for(int i = 0; i < LevelData.LEVEL1.length; i++){
            String line = LevelData.LEVEL1[i];
            for(int j = 0; j < line.length(); j++){
                switch(line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Platform platform = new Platform(this.map.getPlatformType(), j * MapScene.MAP_LENGTH, i * MapScene.MAP_LENGTH);
                        MapScene.platforms.add(platform);
                        break;
                    case '2':
                        //TODO: coins
                        break;
                    case '3':
                        //TODO: obstacles
                        break;
                    case '4':
                        //TODO: items
                        break;
                    case '5':
                        //TODO: enemies
                        break;
                }
            }
        }

        //TODO: Add player here.

    }

    @Override
    public void draw() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
        timer.start();
    }

    // Questo serve per cambiare la mappa: quindi l'immagine di sfondo e delle piattaforme.
    public void setMap(Map.Maps map){
        this.map.setMap(map);

        //TODO: potrei anche chiamare direttamente generateMap() da qui.
    }

    public HashMap<KeyCode, Boolean> getKeys(){
        return this.keys;
    }

    public int getLevelWidth(){
        return this.levelWidth;
    }
}
