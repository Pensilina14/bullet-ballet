package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Platform extends Pane { //TODO: Lasciare tutto qui o mettere tutto in Map. (tranne le enum che metterei apparte)

    public enum Platforms { //TODO: Add paths of other platform images.
        HALLOWEEN_PLATFORM(""),
        CAVE_PLATFORM(""),
        JUNGLE_PLATFORM(""),
        LAVA_PLATFORM(""),
        DESERT_PLATFORM("res/assets/maps/Tiles/tmw_desert_spacing.png"),
        FUTURISTIC_PLATFORM("");

        String path;

        Platforms(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }
    }

    private ImageView platformView;

    private Platforms platformType;

    public Platform(Platforms platformType, int x, int y) throws IOException {

        this.platformType = platformType;

        setPlatform(this.platformType, x, y);

        getChildren().add(this.platformView);
        MapScene.gamePane.getChildren().add(this.platformView);
    }

    private void setPlatform(Platforms platformType, int x, int y) throws IOException { //TODO: non serve passarlo come parametro.
        switch(platformType){
            case DESERT_PLATFORM:
                int minX = 20;
                int minY = 20;
                int width = 60;
                int height = 60;
                this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(Platforms.DESERT_PLATFORM.getPath()))));
                this.platformView.setViewport(new Rectangle2D(minX,minY,width,height));
                this.platformView.setTranslateX(x);
                this.platformView.setTranslateY(y);

                this.platformView.fitWidthProperty();
                this.platformView.fitHeightProperty();
                break;
            case HALLOWEEN_PLATFORM:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case FUTURISTIC_PLATFORM:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case JUNGLE_PLATFORM:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case LAVA_PLATFORM:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            case CAVE_PLATFORM:
                //this.platformSprite.getSpriteView().setViewport(new Rectangle2D());
                break;
            default:
                break;
        }
    }

}
