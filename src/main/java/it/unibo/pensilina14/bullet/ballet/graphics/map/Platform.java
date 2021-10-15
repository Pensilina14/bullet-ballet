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
        CAVE_PLATFORM("res/assets/maps/Tiles/rock_tile2.png"),
        CAVE_PLATFORM2("res/assets/maps/Tiles/rock_tile4.png"),
        CAVE_PLATFORM3("res/assets/maps/Tiles/rock_tile.jpg"),
        JUNGLE_PLATFORM("res/assets/maps/Tiles/jungle_rock_tile.png"),
        FOREST_PLATFORM("res/assets/maps/Tiles/grass_tile.png"),
        SWAMP_PLATFORM("res/assets/maps/Tiles/Swamp-tile.jpg"),
        LAVA_PLATFORM("res/assets/maps/Tiles/brick_wall-red.png"),
        DESERT_PLATFORM("res/assets/maps/Tiles/desert_platform2.png"),
        DESERT_PLATFORM2("res/assets/maps/Tiles/desert-tile.png"),
        DESERT_PLATFORM3("res/assets/maps/Tiles/desert_platform3.png"),
        DESERT_PLATFORM4("res/assets/maps/Tiles/desert_platform4.png"),
        FUTURISTIC_PLATFORM("res/assets/maps/Tiles/crates_tile.png"), //TODO: change it
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
        minX = 0;
        minY = 0;
        platformWidth = 60;
        platformHeight = 60;
        this.platformView = new ImageView(new Image(Files.newInputStream(Paths.get(this.platformType.getPath()))));
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.platformView.setFitWidth(MapScene.PLATFORM_SIZE);
        this.platformView.setFitHeight(MapScene.PLATFORM_SIZE);
    }

}
