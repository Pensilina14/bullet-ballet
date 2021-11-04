package it.unibo.pensilina14.bullet.ballet.graphics.map;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CoinSprite extends Pane {

    public enum Coins {//TODO: mettere l'enum a parte?
        GOLD_COIN("res/assets/sprites/coins/MonedaD.png"),
        SILVER_COIN("res/assets/sprites/coins/MonedaP.png"),
        RED_COIN("res/assets/sprites/coins/MonedaR.png"),
        EMERALD_COIN("res/assets/sprites/coins/spr_coin_strip4.png"),
        LIGHT_BLUE_COIN("res/assets/sprites/coins/spr_coin_azu.png"),
        YELLOW_COIN("res/assets/sprites/coins/spr_coin_ama.png"),
        GREY_COIN("res/assets/sprites/coins/spr_coin_gri.png"),
        RED_COIN2("res/assets/sprites/coins/spr_coin_roj.png"); //TODO: rename it differently

        private final String path;

        Coins(final String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }
    }

    private ImageView coinView;

    private final Coins coinType;

    private final static Coins DEFAULT_COIN = Coins.GOLD_COIN;

    public CoinSprite(){
        this.coinType = DEFAULT_COIN;
    }

    public CoinSprite(final Coins coinType, final int x, final int y) throws IOException {

        this.coinType = coinType;

        setCoin(x, y);

        getChildren().add(this.coinView);
    }

    private void setCoin(final int x, final int y) throws IOException {
        final int minX;
        final int minY;
        final int coinWidth;
        final int coinHeight;

        switch(this.coinType){
            case GOLD_COIN:
            case SILVER_COIN:
            case RED_COIN:
                minX = 0;
                minY = 0;
                coinWidth = 14;
                coinHeight = 16;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(this.coinType.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.getProperties().put("alive", true);
                this.coinView.setViewport(new Rectangle2D(minX, minY, coinWidth, coinHeight));
                break;
            case EMERALD_COIN:
            case LIGHT_BLUE_COIN:
            case YELLOW_COIN:
            case GREY_COIN:
            case RED_COIN2:
                minX = 3;
                minY = 1;
                coinWidth = 10;
                coinHeight = 15;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(this.coinType.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.getProperties().put("alive", true);
                this.coinView.setViewport(new Rectangle2D(minX, minY, coinWidth, coinHeight));
                break;
            default:
                break; //TODO: default case
        }
    }

    public final Coins coinChooser(){
        final Random rand = new Random();
        final int max = CoinSprite.Coins.values().length;
        final int min = 0;
        final int randomMap = rand.nextInt(((max - min)) + min); // nextInt : 0 incluso, max escluso.
        for (final Coins c : Coins.values()) {
            if (c.ordinal() == randomMap) {
                return c;
            }
        }
        return CoinSprite.DEFAULT_COIN;
    }

}