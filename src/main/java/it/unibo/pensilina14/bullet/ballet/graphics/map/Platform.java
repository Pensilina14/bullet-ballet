package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
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

    private int minY;
    private int minX;
    private int platformWidth;
    private int platformHeight;

    public Platform(Platforms platformType, int x, int y) throws IOException {

        this.platformType = platformType;

        setPlatform(x, y);

        getChildren().add(this.platformView);
        MapScene.gamePane.getChildren().add(this.platformView);
    }

    private void setPlatform(int x, int y) throws IOException {
        switch(this.platformType){
            case DESERT_PLATFORM:
                minX = 20;
                minY = 20;
                platformWidth = 60;
                platformHeight = 60;
                this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(Platforms.DESERT_PLATFORM.getPath()))));
                this.platformView.setViewport(new Rectangle2D(minX,minY,platformWidth,platformHeight));
                this.platformView.setTranslateX(x);
                this.platformView.setTranslateY(y);

                this.platformView.fitWidthProperty();
                this.platformView.fitHeightProperty();
                break;
                //TODO: Add other platform types
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
