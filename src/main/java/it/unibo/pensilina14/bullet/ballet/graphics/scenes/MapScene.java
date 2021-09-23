package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapScene extends AbstractScene{

    private Image background;

    public MapScene() throws IOException {
        super();

        this.background = new Image(Files.newInputStream(Paths.get(Map.Maps.HALLOWEEN.getPath())));

    }

    @Override
    public void draw() {

        new AnimationTimer(){

            public void handle(long currentNanoTime){

                gfx.setFill(Color.BLACK);
                gfx.fillRect(0,0,width, height);
                gfx.drawImage(background, 0, 0);
            }
        }.start();


    }
}
