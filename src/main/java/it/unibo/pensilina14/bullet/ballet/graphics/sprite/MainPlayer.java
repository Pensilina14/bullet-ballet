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

    private Image playerImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/player/player_sprite.png")));
    private ImageView playerView = new ImageView(this.playerImg);

    private int count = 3; // 4
    private int columns = 16;
    private int offsetX = 0;
    private int offsetY = 0;
    private int playerViewWidth = 107; // 105
    private int playerViewHeight = 118; // 120

    private SpriteAnimation animation;

    public static final int PLAYER_SIZE = 40;

    public MainPlayer() throws IOException {
        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);
        this.playerView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight));
        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight);

        getChildren().addAll(this.playerView);
    }

    

    public final SpriteAnimation getSpriteAnimation() {
        return animation;
    }
}
