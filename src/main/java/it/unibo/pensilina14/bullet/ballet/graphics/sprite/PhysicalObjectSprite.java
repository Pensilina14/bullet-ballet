package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhysicalObjectSprite extends Pane{
    
	private static final int ITEM_SIZE = 30;
    private final ImageView objectView;
    private MutablePosition2D position;
    
    public PhysicalObjectSprite(final Images img, final double x, final double y, final PhysicalObject physicalObject, final MapScene scene) throws IOException {
        this.objectView = new ImageView(new Image(Files.newInputStream(Paths.get(img.toString()))));
        this.renderPosition(x, y);
        this.objectView.setFitWidth(PhysicalObjectSprite.ITEM_SIZE);
        this.objectView.setFitHeight(PhysicalObjectSprite.ITEM_SIZE);
        final int enemyWidth = 30;
        final int enemyHeight = 45;
        this.objectView.setViewport(new Rectangle2D(0, 0, enemyWidth,
                enemyHeight));
        this.getChildren().add(this);
    }

    
    public void renderPosition(final double x, final double y) {
    	this.setTranslateX(x);
    	this.setTranslateY(y);
    	this.position = new MutablePosition2Dimpl(x, y);
    }
    
    public void renderMovingPosition() {
    	final double netX = this.position.getX() - 1;
    	this.position.setPosition(netX, this.position.getY());
    	this.setTranslateX(this.position.getX());
    }
    
}
