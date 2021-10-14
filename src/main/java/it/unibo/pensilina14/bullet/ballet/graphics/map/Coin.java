package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Coin extends Pane {

    public enum Coins {
        GOLD_COIN("res/assets/sprites/coins/MonedaD.png"),
        SILVER_COIN("res/assets/sprites/coins/MonedaP.png"),
        RED_COIN("res/assets/sprites/coins/MonedaR.png"),
        EMERALD_COIN("res/assets/sprites/coins/spr_coin_strip4.png"),
        LIGHT_BLUE_COIN("res/assets/sprites/coins/spr_coin_azu.png"),
        YELLOW_COIN("res/assets/sprites/coins/spr_coin_ama.png"),
        GREY_COIN("res/assets/sprites/coins/spr_coin_gri.png"),
        RED_COIN2("res/assets/sprites/coins/spr_coin_roj.png"); //TODO: rename it differently

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

    public static Coins DEFAULT_COIN = Coins.GOLD_COIN;

    public Coin(){
        this.coinType = DEFAULT_COIN;
    }

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
            case SILVER_COIN:
            case RED_COIN:
                minX = 0;
                minY = 0;
                coinWidth = 14;
                coinHeight = 16;
                this.coinView = new ImageView(new Image(Files.newInputStream(Paths.get(this.coinType.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);
                this.getProperties().put("alive",true);
                this.coinView.setViewport(new Rectangle2D(minX,minY,coinWidth,coinHeight));
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
                this.getProperties().put("alive",true);
                this.coinView.setViewport(new Rectangle2D(minX,minY,coinWidth,coinHeight));
                break;
            default:
                break;
        }
    }

    public Coins coinChooser(){
        final Random rand = new Random();
        final int max = Map.Maps.values().length;
        final int min = 0;
        final int randomMap = rand.nextInt(((max - min) + 1 ) + min);
        for(Coins c : Coins.values()){
            if(c.ordinal() == randomMap){
                return c;
            }
        }
        return Coin.DEFAULT_COIN;
    }

}
