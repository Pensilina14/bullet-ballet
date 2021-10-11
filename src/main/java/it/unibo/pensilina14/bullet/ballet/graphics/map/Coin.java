package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Coin extends Pane {

    public enum Coins { //TODO: Add paths of other coin images.
        GOLD_COIN("res/assets/sprites/coins/MonedaD.png"),
        SILVER_COIN("res/assets/sprites/coins/MonedaP.png"),
        RED_COIN("res/assets/sprites/coins/MonedaR.png"),
        EMERALD_COIN(""),
        LIGHT_BLUE_COIN(""),
        YELLOW_COIN("");

        String path;

        Coins(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }
    }

    private ImageView coinView;

    private Coins coinType;

    private int minY;
    private int minX;
    private int coinWidth;
    private int coinHeight;

    public Coin(Coins coinType, int x, int y) throws IOException {

        this.coinType = coinType;

        setCoin(x, y);

        getChildren().add(this.coinView);
        MapScene.coins.add(this);
        MapScene.gamePane.getChildren().add(this);
    }

    private void setCoin(int x, int y) throws IOException {
        switch(this.coinType){
            case GOLD_COIN:
                minX = 0;
                minY = 0;
                coinWidth = 14;
                coinHeight = 16;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(Coins.GOLD_COIN.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.coinView.setViewport(new Rectangle2D(minX,minY,coinWidth,coinHeight));
                break;
                //TODO: Add other platform types
            case SILVER_COIN:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case RED_COIN:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case EMERALD_COIN:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case LIGHT_BLUE_COIN:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case YELLOW_COIN:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            default:
                break;
        }
    }

}
