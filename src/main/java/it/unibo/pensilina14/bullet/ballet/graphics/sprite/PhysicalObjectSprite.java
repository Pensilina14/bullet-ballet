package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhysicalObjectSprite extends Pane{
    
    private  MutablePosition2D position;
    
    public PhysicalObjectSprite(final Images img, final MutablePosition2D position
            , final PhysicalObject physicalObject) throws IOException {
        final ImageView imageView = new ImageView(new Image(Files.newInputStream(Paths.get(img.getFileName()))));
        AppLogger.getAppLogger().info(img.toString());
        final double physicalObjectWidth = physicalObject.getDimension().getWidth();
        final double physicalObjectHeight = physicalObject.getDimension().getHeight();
        this.position = position;
        this.renderPosition(position.getX(), position.getY());
        imageView.setFitWidth(physicalObjectWidth);
        imageView.setFitHeight(physicalObjectHeight);
        imageView.setViewport(new Rectangle2D(0, 0, physicalObjectWidth, physicalObjectHeight));
        this.getChildren().add(imageView);
    }

    public void renderPosition(final double x, final double y) {
    	this.setTranslateX(x);
    	this.setTranslateY(y);
    	this.position = new MutablePosition2Dimpl(x, y);
    }
}
