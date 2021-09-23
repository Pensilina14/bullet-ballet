package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    private final int width;
    private final int height;

    private Image image;
    private int spriteX;
    private int spriteY;
    protected int x;
    protected int y;

    public Sprite(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(GraphicsContext gc){
        //gc.drawImage(this.image, this.width, this.height);
        gc.drawImage(this.image, this.spriteX, this.spriteY, this.width, this.height, this.x, this.y, this.width, this.height);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
