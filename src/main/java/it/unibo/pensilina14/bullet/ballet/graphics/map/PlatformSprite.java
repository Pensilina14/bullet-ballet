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

    private MutablePosition2D position;
    /*
     * NOTE:
     * Temporary solution because this class could be 
     * put under PhysicalObjectSprite implementations.
     */
    public PlatformSprite(final Platforms platformType, final Platform platform) throws IOException {
        final ImageView platformView = new ImageView(new Image(Files.newInputStream(Paths.get(platformType.getPath()))));
        final double platformWidth = platform.getDimension().get().getWidth();
        final double platformHeight = platform.getDimension().get().getHeight();
        this.position = platform.getPosition().get();
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
