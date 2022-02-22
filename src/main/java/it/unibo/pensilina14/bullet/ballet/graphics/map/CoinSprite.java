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

public class CoinSprite extends Pane {

    private ImageView coinView;
    private final Coins coinType;
    private final static Coins DEFAULT_COIN = Coins.GOLD_COIN;

    private static final int COUNT = 3;
    private static final int COLUMNS = 1;
    private static final int OFFSETX = 0;
    private static final int OFFSETY = 0;
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

                animation = new SpriteAnimation(this.coinView, Duration.millis(200), CoinSprite.COUNT, CoinSprite.COLUMNS, CoinSprite.OFFSETX, CoinSprite.OFFSETY, coinWidth, coinHeight);
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

                animation = new SpriteAnimation(this.coinView, Duration.millis(200), CoinSprite.COUNT, CoinSprite.COLUMNS, CoinSprite.OFFSETX, CoinSprite.OFFSETY, coinWidth, coinHeight);
                break;
        }
    }

}
