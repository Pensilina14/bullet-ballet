package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapScene extends AbstractScene{

    private Image background;

    public MapScene() throws IOException {
        super();

        this.background = new Image(Files.newInputStream(Paths.get(Map.Maps.HALLOWEEN.getPath())));

        gfx.drawImage(this.background, 0, 0);
    }

    public void showMap(){
        BackgroundImage background;
    }

    @Override
    public void draw() {
        showMap();
    }
}
