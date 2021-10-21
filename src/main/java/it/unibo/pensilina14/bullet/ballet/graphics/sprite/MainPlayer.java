package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;

public class MainPlayer extends Pane {

    private Image playerImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/player/player_sprite.png")));
    private ImageView playerView = new ImageView(this.playerImg);

    private final int count = 3; // 4
    private final int columns = 16;
    private final int offsetX = 0;
    private final int offsetY = 0;
    private final int playerViewWidth = 107; // 105
    private final int playerViewHeight = 118; // 120

    private SpriteAnimation animation;

    public static final int PLAYER_SIZE = 40;

    private final FactoryCharactersImpl characters = new FactoryCharactersImpl();

    //private final Player player;


    public MainPlayer(final int x, final int y) throws IOException {
        this.playerView.setFitHeight(MainPlayer.PLAYER_SIZE);
        this.playerView.setFitWidth(MainPlayer.PLAYER_SIZE);
        this.playerView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight));
        this.animation = new SpriteAnimation(this.playerView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, this.playerViewWidth, this.playerViewHeight);

        this.playerView.setTranslateX(x);
        this.playerView.setTranslateY(y);

        getChildren().addAll(this.playerView);
    }

    /*public Player getPlayer(){
        return this.player;
    }*/

    public final SpriteAnimation getSpriteAnimation() {
        return animation;
    }
}
