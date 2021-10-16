package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Coin;
import it.unibo.pensilina14.bullet.ballet.graphics.map.LevelData;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapScene extends AbstractScene{

    private final Pane appPane = new Pane();
    private final Pane gamePane = new Pane();
    private final Pane uiPane = new Pane(); 

    private int levelWidth;
    private int currentLevel = 0;

    private static final int MAX_LEVELS = LevelData.levels.length;

    private Map map = new Map();

    private ImageView backgroundView;

    private MainPlayer mainPlayer;

    public static ArrayList<Platform> platforms = new ArrayList<>();
    public static ArrayList<WeaponSprite> weapons = new ArrayList<>();
    public static ArrayList<Coin> coins = new ArrayList<>();
    
    public final static int PLATFORM_SIZE = 60;

    private GameState gameState;
    private Controller controller; 

    private Pair<Integer,Integer> lastPos;

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
        while (this.currentLevel < MapScene.MAX_LEVELS){
            for (int i = 0; i < LevelData.levels[this.currentLevel].length; i++){
                String line = LevelData.levels[this.currentLevel][i];
                //int previousLevel = ((this.currentLevel - 1) < 0 ? this.currentLevel : (this.currentLevel - 1)); //TODO: unccoment
                //this.lastPos = new Pair<>(line.length() - 1,LevelData.levels[this.currentLevel].length - 1); //TODO: - 1
                for (int j = 0; j < line.length(); j++){
                    switch(line.charAt(j)) {
                        case '0':
                            break;
                        case '1':
                            //int coordinatesAdjustment = LevelData.levels[previousLevel][i].charAt(j) * previousLevel; //TODO: da fixare.
                            //int coordsAppend = previousLevel * lastPos.getKey().intValue(); //TODO: uncomment.

                            // Model
                            /*List<PhysicalObject> platforms = gs.getGameEnvironment().getObstacles().get(); //TODO: uncomment
                            MutablePosition2D platformPosition;
                            for(PhysicalObject pf : platforms){
                                if(pf.getPosition().getX() == j && pf.getPosition().getY() == i){
                                    platformPosition = pf.getPosition();
                                }
                            }*/

                            // Platform Sprite
                            Platform platform = new Platform(this.map.getPlatformType(), (j * MapScene.PLATFORM_SIZE), i * MapScene.PLATFORM_SIZE); //TODO: nella j + qualcosa
                            break;
                        case '2':
                            /*List<PhysicalObject> coins = gs.getGameEnvironment().getObstacles().get(); //TODO: uncomment
                            MutablePosition2D coinPosition;
                            for(PhysicalObject c : coins){
                                if(c.getPosition().getX() == j && c.getPosition().getY() == i){
                                    coinPosition = c.getPosition();
                                }
                            }*/

                            Coin coin = new Coin(this.map.getCoinType(), j * MapScene.PLATFORM_SIZE, i * MapScene.PLATFORM_SIZE); //TODO: nella j + qualcosa
                            break;
                        case '3':
                            //TODO: obstacles
                            /*List<PhysicalObject> obstacles = gs.getGameEnvironment().getObstacles().get(); //TODO: uncomment
                            MutablePosition2D obstacle;
                            for(PhysicalObject obs : obstacles){
                                if(obs.getPosition().getX() == j && obs.getPosition().getY() == i){
                                    obstacle = obs.getPosition();
                                }
                            }*/
                            // Sprite dell'obstacle

                        	final List<PhysicalObject> obstacles = this.gameState.getGameEnvironment().getObstacles().get();
                        	PhysicalObject appropriateObstacle;
                        	for (final PhysicalObject obs : obstacles) {
                        		if (obs.getPosition().getX() == j && obs.getPosition().getY() == i) {
                        			appropriateObstacle = obs;
                        		}
                        	}
                        	//TODO instantiate obstacle sprite..
                            break;
                        case '4':
                            //TODO: weapons
                            break;
                        case '5':
                            //TODO:
                            break;
                        case '*':
                            //TODO: items
                            /*List<PhysicalObject> items = gs.getGameEnvironment().getObstacles().get(); //TODO: uncomment
                            MutablePosition2D itemPosition;
                            for(PhysicalObject it : items){
                                if(it.getPosition().getX() == j && it.getPosition().getY() == i){
                                    itemPosition = it.getPosition();
                                }
                            }*/

                            // Sprite dell'item
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
                MapScene.gamePane.setLayoutX(-(playerPosition - (int)(this.map.getMapWidth() / 2)));
            }
        });

        MapScene.gamePane.getChildren().add(this.mainPlayer);
        MapScene.appPane.getChildren().addAll(this.backgroundView, MapScene.gamePane, MapScene.uiPane);
    }

    private void update() {
        if (this.keysPressed.contains(KeyCode.UP)) { 
            this.controller.notifyCommand(new Up());
        }

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
            this.mainPlayer.getSpriteAnimation().play();
            this.controller.notifyCommand(new Right());
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	this.keysReleased.remove(KeyCode.RIGHT);
        }
    }

    @Override
    public final void draw() {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                update();
            }
        };
        timer.start();
    }

    public final void setMap(final Map.Maps map) {
        this.map.setMap(map);
    }

    public final Pane getAppPane() {
    	return this.appPane;
    }

    public final Pane getGamePane() {
    	return this.gamePane;
    }

    public final Pane getUiPane() {
    	return this.uiPane;
    }
}
