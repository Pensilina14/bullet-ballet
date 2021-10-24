package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPlayer extends Pane {

    private final Image playerImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/player/player_sprite.png")));
    private final ImageView playerView = new ImageView(this.playerImg);

    private final SpriteAnimation animation;

    public static final int PLAYER_SIZE = 40;

    public MainPlayer(final double x, final double y) throws IOException {
        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);

        int count = 3; // 4
        int columns = 16;
        int offsetX = 0;
        int offsetY = 0;
        int playerViewWidth = 107; // 105
        int playerViewHeight = 118; // 120

        this.playerView.setViewport(new Rectangle2D(offsetX, offsetY, playerViewWidth, playerViewHeight));

        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), count, columns, offsetX, offsetY, playerViewWidth, playerViewHeight);

        this.playerView.setTranslateX(x);
        this.playerView.setTranslateY(y);

        getChildren().addAll(this.playerView);
    }
    
    public void renderPosition(final double x, final double y) {
    	this.playerView.setTranslateX(x);
    	this.playerView.setTranslateY(y);
    }

    public final SpriteAnimation getSpriteAnimation() {
        return animation;
    }
}
