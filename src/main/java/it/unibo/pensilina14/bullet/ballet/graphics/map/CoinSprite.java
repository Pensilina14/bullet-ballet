package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CoinSprite extends Pane {

    private ImageView coinView;
    private final Coins coinType;
    private final static Coins DEFAULT_COIN = Coins.GOLD_COIN;

    private final int count = 3;
    private final int columns = 1;
    private final int offsetX = 0;
    private final int offsetY = 0;
    private static final Random RAND = new Random();
    private SpriteAnimation animation;

    public CoinSprite(){
        this.coinType = DEFAULT_COIN;
    }

    public CoinSprite(final Coins coinType, final double x, final double y) throws IOException {

        this.coinType = coinType;

        setCoin(x, y);

        getChildren().add(this.coinView);
    }

    private void setCoin(final double x, final double y) throws IOException {
        final int minX;
        final int minY;
        final int coinWidth;
        final int coinHeight;

        switch(this.coinType){
            case EMERALD_COIN:
            case LIGHT_BLUE_COIN:
            case YELLOW_COIN:
            case GREY_COIN:
            case RUBY_COIN:
                minX = 3;
                minY = 1;
                coinWidth = 10;
                coinHeight = 15;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(this.coinType.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.getProperties().put("alive", true);
                this.coinView.setViewport(new Rectangle2D(minX, minY, coinWidth, coinHeight));

                this.animation = new SpriteAnimation(this.coinView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, coinWidth, coinHeight);
                break;
            case RED_COIN:
            case SILVER_COIN:
            case GOLD_COIN:
            default:
                minX = 0;
                minY = 0;
                coinWidth = 14;
                coinHeight = 16;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(this.coinType.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.getProperties().put("alive", true);
                this.coinView.setViewport(new Rectangle2D(minX, minY, coinWidth, coinHeight));

                this.animation = new SpriteAnimation(this.coinView, Duration.millis(200), this.count, this.columns, this.offsetX, this.offsetY, coinWidth, coinHeight);
                break;
        }
    }

}
