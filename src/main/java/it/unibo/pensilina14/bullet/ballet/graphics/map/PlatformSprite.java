package it.unibo.pensilina14.bullet.ballet.graphics.map;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;

public class PlatformSprite extends Pane {

    public enum Platforms { //TODO: mettere l'enum a parte
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
        ICE_PLATFORM("res/assets/maps/Tiles/ice_tile3.png"),
        FUTURISTIC_PLATFORM("res/assets/maps/Tiles/scifi_tile.jpg"),
        SCIFI_PLATFORM("res/assets/maps/Tiles/scifi_tile3.jpg"),
        SCIFI_PLATFORM2("res/assets/maps/Tiles/scifi_tile2.jpg"),
        SCIFI_PLATFORM3("res/assets/maps/Tiles/scifi_tile.jpg"),
        SPACE_PLATFORM("res/assets/maps/Tiles/space_platform.jpg"),
        CRATE_PLATFORM("res/assets/maps/Tiles/crates_tile.png");

        private final String path;

        Platforms(final String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }
    }

    private MutablePosition2D position;
    /*
     * NOTE:
     * Temporary solution because this class could be 
     * put under PhysicalObjectSprite implementations.
     */
    public PlatformSprite(final Platforms platformType, final MutablePosition2D pos, final Platform platform) throws IOException {
        final ImageView platformView = new ImageView(new Image(Files.newInputStream(Paths.get(platformType.getPath()))));
        final double platformWidth = platform.getDimension().get().getWidth();
        final double platformHeight = platform.getDimension().get().getHeight();
        this.position = pos;
        this.renderPosition(this.position.getX(), this.position.getY());
        platformView.setFitWidth(platformWidth);
        platformView.setFitHeight(platformHeight);
        platformView.setViewport(new Rectangle2D(0, 0, platformWidth, platformHeight));
        this.getChildren().add(platformView);
    }

    public final void renderPosition(final double x, final double y) {
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.position = new MutablePosition2Dimpl(x, y);
    }
    
    public final void renderMovingPosition() {
    	final double nextX = this.position.getX() - 1.0;
    	this.position.setPosition(nextX, this.position.getY());
    	this.setTranslateX(this.position.getX());
    }

    public final MutablePosition2D getPosition() {
    	return this.position;
    }
}
