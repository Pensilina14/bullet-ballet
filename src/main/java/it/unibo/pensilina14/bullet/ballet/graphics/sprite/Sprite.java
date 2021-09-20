package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    private final int width;
    private final int height;

    private Image image;

    public Sprite(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(this.image, this.width, this.height);
    }
}
