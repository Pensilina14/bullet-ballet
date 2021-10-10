package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.LevelData;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Character;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class MapScene extends AbstractScene{

    public static Pane appPane = new Pane();
    public static Pane gamePane = new Pane();
    public static Pane uiPane = new Pane(); //TODO: add UI.

    private int levelWidth;
    private int currentLevel = 0;

    private static final int MAX_LEVELS = LevelData.levels.length;

    private Map map = new Map();

    private ImageView backgroundView;

    private MainPlayer mainPlayer;

    public static ArrayList<Platform> platforms = new ArrayList<>();
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public final static int PLATFORM_SIZE = 60;

    public MapScene(){

        MapScene.appPane.setMaxWidth(AbstractScene.SCENE_WIDTH); // casomai la mappa fosse più grande o anche più piccola.
        MapScene.appPane.setMaxHeight(AbstractScene.SCENE_HEIGHT);
    }

    public void generateMap() throws IOException {

        this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));

        this.backgroundView.fitWidthProperty().bind(MapScene.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(MapScene.appPane.heightProperty());

        //TODO: al momento mostra soltanto l'ultimo livello (ovvero il secondo). (perchè si sovrappongono)
        //TODO: da fixare aggiungendo alla coordinate del secondo livello la distanza dall'ultima piattaforma del primo livello (per appendare il livello 2 al livello 1)
        //TODO: però, se sta generando il primo livello non serve aggiungere le coordinate dall'ultima piattaforma del livello precedente
        //TODO: quindi alle coordinate da aggiungere, metto un int previousLevel = ((this.currentLevel - 1) < 0 ? this.currentLevel : (this.currentLevel - 1));
        this.levelWidth = LevelData.levels[this.currentLevel][0].length() * MapScene.PLATFORM_SIZE;
        while(this.currentLevel < MapScene.MAX_LEVELS){
            for(int i = 0; i < LevelData.levels[this.currentLevel].length; i++){
                String line = LevelData.levels[this.currentLevel][i];
                //int previousLevel = ((this.currentLevel - 1) < 0 ? this.currentLevel : (this.currentLevel - 1)); //TODO: unccoment
                for(int j = 0; j < line.length(); j++){
                    switch(line.charAt(j)){
                        case '0':
                            break;
                        case '1':
                            //int coordinatesAdjustment = LevelData.levels[previousLevel][i].charAt(j) * previousLevel; //TODO: da fixare.
                            Platform platform = new Platform(this.map.getPlatformType(), (j * MapScene.PLATFORM_SIZE), i * MapScene.PLATFORM_SIZE);
                            break;
                        case '2':
                            //TODO: coins
                            break;
                        case '3':
                            //TODO: obstacles
                            break;
                        case '4':
                            //TODO:
                            break;
                        case '5':
                            //TODO:
                            break;
                        case '*':
                            //TODO: items
                            break;
                        case '!':
                            //TODO: nemici
                            break;
                    }
                }
            }
            this.currentLevel++;
            //TODO: salvare le coordinate dell'ultima piattaforma del livello precedente.
        }

        this.mainPlayer = new MainPlayer();

        this.mainPlayer.setTranslateX(0); // spawn coordinates
        this.mainPlayer.setTranslateY(400);

        this.mainPlayer.translateXProperty().addListener((obs, oldPosition, newPosition) -> {
            int playerPosition = newPosition.intValue();

            // this.map.getWidth() / 2 = metà della mappa.
            if(playerPosition > (this.map.getMapWidth() / 2) && playerPosition < this.levelWidth - (this.map.getMapWidth() / 2)){
                MapScene.gamePane.setLayoutX(-(playerPosition - (this.map.getMapWidth() / 2)));
                //this.backgroundView.setLayoutX(-(playerPosition- (this.map.getMapWidth() / 2)));
            }
        });

        MapScene.gamePane.getChildren().add(this.mainPlayer);
        MapScene.appPane.getChildren().addAll(this.backgroundView, MapScene.gamePane, MapScene.uiPane);
    }

    private boolean isPressed(KeyCode key){
        return this.keys.getOrDefault(key, false);
    }

    private void update(){
        if(isPressed(KeyCode.UP) && this.mainPlayer.getTranslateY() >= 5){
            this.mainPlayer.jumpPlayer();
        }

        if(isPressed(KeyCode.LEFT) && this.mainPlayer.getTranslateX() >= 5){
            this.mainPlayer.setScaleX(-1);
            this.mainPlayer.animation.play();
            this.mainPlayer.moveX(-5);
        }

        if(isPressed(KeyCode.RIGHT) && this.mainPlayer.getTranslateX() + 40 <= this.levelWidth - 5){
            this.mainPlayer.setScaleX(1);
            this.mainPlayer.animation.play();
            this.mainPlayer.moveX(5);
        }

        if(this.mainPlayer.playerVelocity.getY() < 10){
            this.mainPlayer.playerVelocity = this.mainPlayer.playerVelocity.add(0,1);
        }
        this.mainPlayer.moveY((int)this.mainPlayer.playerVelocity.getY());
    }

    @Override
    public void draw() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    public void setMap(Map.Maps map){
        this.map.setMap(map);
    }

    public void setKeys(KeyCode key, Boolean pressed){
        this.keys.put(key, pressed);
    }

    public MainPlayer getMainPlayer(){
        return this.mainPlayer;
    }
}
