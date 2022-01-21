package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;

public class MainPlayer extends Pane {

    private final Image playerImg ;
    private final ImageView playerView;

    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int playerViewWidth;
    private final int playerViewHeight;
    private MutablePosition2D position;

    private final SpriteAnimation animation;

    public static final int PLAYER_SIZE = 40;

    public MainPlayer(final double x, final double y) throws IOException {
        this.playerImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/player/player_sprite.png")));
        this.playerView = new ImageView(this.playerImg);

        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);

        this.count = 3; // 4
        this.columns = 16;
        this.offsetX = 0;
        this.offsetY = 0;
        this.playerViewWidth = 107; // 105
        this.playerViewHeight = 118; // 120

        this.playerView.setViewport(new Rectangle2D(offsetX, offsetY, playerViewWidth, playerViewHeight));

        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), count, columns, offsetX, offsetY, playerViewWidth, playerViewHeight);
        
        renderPosition(x, y);

        getChildren().addAll(this.playerView);
    }

    public MainPlayer(final Image playerImg, final double x, final double y, final int count
    		, final int columns, final int offsetX, final int offsetY, final int playerViewWidth
    		, final int playerViewHeight){
        this.playerImg = playerImg;
        this.playerView = new ImageView(this.playerImg);

        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);

        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.playerViewWidth = playerViewWidth;
        this.playerViewHeight = playerViewHeight;

        this.playerView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight));

        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight);
        
        renderPosition(x, y);

        getChildren().addAll(this.playerView);
    }
    
    public void renderPosition(final double x, final double y) {
    	this.playerView.setTranslateX(x);
    	this.playerView.setTranslateY(y);
    	this.position = new MutablePosition2Dimpl(x, y);
    	//System.out.println("Player View: \t" + this.position.getX());
    }

    public final SpriteAnimation getSpriteAnimation() {
        return this.animation;
    }
}
