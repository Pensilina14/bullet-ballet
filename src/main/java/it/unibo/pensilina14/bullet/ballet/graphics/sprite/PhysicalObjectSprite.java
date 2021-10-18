package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhysicalObjectSprite extends Pane{
    
    private final ImageView weaponView;
    
    public PhysicalObjectSprite(final Images img, final int x, final int y, final PhysicalObject physicalObject, final MapScene scene) throws IOException {
        this.weaponView = new ImageView(new Image(Files.newInputStream(Paths.get(img.toString()))));
        this.weaponView.setViewport(new Rectangle2D(0, 0, physicalObject.getDimension().getWidth(),
                physicalObject.getDimension().getHeight()));
        this.setTranslateX(x);
        this.setTranslateY(y);
        getChildren().addAll(this);
        scene.getGamePane().getChildren().add(this);
    }

    public ImageView getWeaponView() {
        return this.weaponView;
    }
    
}
