package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhysicalObjectSprite extends Pane {

    private final ImageView imageView;
    private MutablePosition2D position;
    private final Dimension2D physicalObjectDimension;

    public PhysicalObjectSprite(final Images img, final MutablePosition2D position,
        final PhysicalObject physicalObject) throws IOException {
        this.imageView = new ImageView(new Image(Files.newInputStream(Paths.get(img.getFileName()))));
        AppLogger.getAppLogger().info(img.toString());
        this.physicalObjectDimension = physicalObject.getDimension().get();
        final double physicalObjectWidth = physicalObject.getDimension().get().getWidth();
        final double physicalObjectHeight = physicalObject.getDimension().get().getHeight();
        this.position = position;
        this.renderPosition(position.getX(), position.getY());
        this.imageView.setFitWidth(physicalObjectWidth);
        this.imageView.setFitHeight(physicalObjectHeight);
        this.imageView.setViewport(new Rectangle2D(0, 0, physicalObjectWidth, physicalObjectHeight));
        this.getChildren().add(this.imageView);
    }

    public PhysicalObjectSprite(final Images.Platforms img, final MutablePosition2D position,
            final PhysicalObject physicalObject) throws IOException {
        this.imageView = new ImageView(new Image(Files.newInputStream(Paths.get(img.getPath()))));
        AppLogger.getAppLogger().info(img.toString());
        this.physicalObjectDimension = physicalObject.getDimension().get();
        final double physicalObjectWidth = physicalObject.getDimension().get().getWidth();
        final double physicalObjectHeight = physicalObject.getDimension().get().getHeight();
        this.position = position;
        this.renderPosition(position.getX(), position.getY());
        this.imageView.setFitWidth(physicalObjectWidth);
        this.imageView.setFitHeight(physicalObjectHeight);
        this.imageView.setViewport(new Rectangle2D(0, 0, physicalObjectWidth, physicalObjectHeight));
        this.getChildren().add(this.imageView);
    }

    public PhysicalObjectSprite(final Images.Coins img, final MutablePosition2D position,
            final PhysicalObject physicalObject) throws IOException {
        this.imageView = new ImageView(new Image(Files.newInputStream(Paths.get(img.getPath()))));
        AppLogger.getAppLogger().info(img.toString());
        this.physicalObjectDimension = physicalObject.getDimension().get();
        final double physicalObjectWidth = physicalObject.getDimension().get().getWidth();
        final double physicalObjectHeight = physicalObject.getDimension().get().getHeight();
        this.position = position;
        this.renderPosition(position.getX(), position.getY());
        this.imageView.setFitWidth(physicalObjectWidth);
        this.imageView.setFitHeight(physicalObjectHeight);
        this.imageView.setViewport(new Rectangle2D(0, 0, physicalObjectWidth, physicalObjectHeight));
        this.getChildren().add(this.imageView);
    }

    public final void renderPosition(final double x, final double y) {
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.position = new MutablePosition2Dimpl(x, y);
    }

    public final void renderMovingPosition() {
        final double nextX = this.position.getX() - 1;
        this.position.setPosition(nextX, this.position.getY());
        this.setTranslateX(this.position.getX());
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final Dimension2D getDimension() {
        return this.physicalObjectDimension;
    }
}
