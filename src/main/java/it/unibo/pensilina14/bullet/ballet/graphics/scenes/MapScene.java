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
    
    private Map map = new Map();

    private ImageView backgroundView;

    private MainPlayer mainPlayer;

    public static ArrayList<Platform> platforms = new ArrayList<>();
    public static ArrayList<WeaponSprite> weapons = new ArrayList<>();
    public static ArrayList<Coin> coins = new ArrayList<>();

    private final GameState gameState;
    private Controller controller; 

    private Pair<Integer,Integer> lastPos;

    public MapScene(){

        this.appPane.setMaxWidth(AbstractScene.SCENE_WIDTH); // casomai la mappa fosse più grande o anche più piccola.
        this.appPane.setMaxHeight(AbstractScene.SCENE_HEIGHT);
    }

    public void setup() throws IOException {

        this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));

        this.backgroundView.fitWidthProperty().bind(this.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(this.appPane.heightProperty());


        this.mainPlayer = new MainPlayer();

        this.mainPlayer.setTranslateX(0); // spawn coordinates
        this.mainPlayer.setTranslateY(400);

        this.mainPlayer.translateXProperty().addListener((obs, oldPosition, newPosition) -> {
            int playerPosition = newPosition.intValue();

            // this.map.getWidth() / 2 = metà della mappa.
            if(playerPosition > (this.map.getMapWidth() / 2) && playerPosition < this.levelWidth - (this.map.getMapWidth() / 2)){
                this.gamePane.setLayoutX(-(playerPosition - (int)(this.map.getMapWidth() / 2)));
            }
        });

        this.gamePane.getChildren().add(this.mainPlayer);
        this.appPane.getChildren().addAll(this.backgroundView, this.gamePane, this.uiPane);
    }

    private void update() {
        if (this.keysPressed.contains(KeyCode.UP)) { 
        	this.mainPlayer.getSpriteAnimation().play();
            this.controller.notifyCommand(new Up());
        }

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
            this.mainPlayer.getSpriteAnimation().play();
            this.controller.notifyCommand(new Right());
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	this.mainPlayer.getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	this.mainPlayer.getSpriteAnimation().stop();
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
