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
        HALLOWEEN_PLATFORM("res/assets/maps/Tiles/halloween_tile.png"),
        CAVE_PLATFORM(""),
        JUNGLE_PLATFORM(""),
        LAVA_PLATFORM(""),
        DESERT_PLATFORM("res/assets/maps/Tiles/tmw_desert_spacing.png"),
        FUTURISTIC_PLATFORM(""),
        ICE_PLATFORM("res/assets/maps/Tiles/ice_tile3.png");

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
        MapScene.platforms.add(this);
        MapScene.gamePane.getChildren().add(this);
    }

    private void setPlatform(int x, int y) throws IOException {
        switch(this.platformType){
            case DESERT_PLATFORM:
                minX = 20;
                minY = 20;
                platformWidth = 60;
                platformHeight = 60;
                this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(Platforms.DESERT_PLATFORM.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);

                /*this.platformView.setFitWidth(MapScene.MAP_LENGTH);
                this.platformView.setFitHeight(MapScene.MAP_LENGTH);*/

                this.platformView.setViewport(new Rectangle2D(minX,minY,platformWidth,platformHeight));
                break;
                //TODO: Add other platform types
            case HALLOWEEN_PLATFORM:
                minX = 60; // 60
                minY = 40; // 40
                platformWidth = 60; // 15
                platformHeight = 60; // 16
                this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(Platforms.HALLOWEEN_PLATFORM.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);

                break;
            case FUTURISTIC_PLATFORM:
                break;
            case JUNGLE_PLATFORM:
                break;
            case LAVA_PLATFORM:
                break;
            case CAVE_PLATFORM:
                break;
            case ICE_PLATFORM:
                minX = 0;
                minY = 0;
                platformWidth = 60;
                platformHeight = 60;
                this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(Platforms.ICE_PLATFORM.getPath()))));
                this.setTranslateX(x);
                this.setTranslateY(y);

                break;
            default:
                break;
        }
    }

}
