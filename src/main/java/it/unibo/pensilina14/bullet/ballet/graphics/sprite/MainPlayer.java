package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPlayer extends Pane {

    Image playerImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/player/player_sprite.png")));
    ImageView playerView = new ImageView(this.playerImg);

    int count = 3; // 4
    int columns = 16;
    int offsetX = 0;
    int offsetY = 0;
    int playerViewWidth = 107; // 105
    int playerViewHeight = 118; // 120

    public SpriteAnimation animation;
    public Point2D playerVelocity = new Point2D(0,0);
    private boolean canJump = true;

    public static final int PLAYER_SIZE = 40;

    private final FactoryCharactersImpl characters = new FactoryCharactersImpl();

    private Player player;


    public MainPlayer() throws IOException {
        this.player = this.characters.createPlayer(EntityList.Characters.Player.PLAYER1); //TODO: chiamare il costruttore e creare un player casuale senza dover passare il tipo.

        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);
        this.playerView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight));
        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight);

        getChildren().addAll(this.playerView);
    }

    public void moveX(int value){
        boolean movingRight = value > 0;

        for(int i = 0; i < Math.abs(value); i++){
            for(Platform platform : MapScene.platforms){
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingRight){
                        if(this.getTranslateX() + PLAYER_SIZE == platform.getTranslateX()){
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if(this.getTranslateX() == platform.getTranslateX() + MapScene.PLATFORM_SIZE){
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void moveY(int value){
        boolean movingDown = value > 0;

        for(int i = 0; i < Math.abs(value); i++){
            for(Platform platform : MapScene.platforms){
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(this.getTranslateY() + PLAYER_SIZE == platform.getTranslateY()){
                            this.setTranslateY(this.getTranslateY() - 1);
                            this.canJump = true;
                            return;
                        }
                    }
                    else {
                        if(this.getTranslateY() == platform.getTranslateY() + MapScene.PLATFORM_SIZE){
                            this.setTranslateY(this.getTranslateY() + 1);
                            this.playerVelocity = new Point2D(0, 10);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
            //TODO: se il player cade in uno dei buchi della mappa, riportarlo all'inizio della mappa.
        }
    }

    public void jumpPlayer(){
        if(this.canJump){
            this.playerVelocity = this.playerVelocity.add(0,-30);
            this.canJump = false;
        }
    }

    public Player getPlayer(){
        return this.player;
    }
}
