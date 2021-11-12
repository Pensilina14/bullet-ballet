package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sprite {

    private int spriteWidth;
    private int spriteHeight;

    private Image image;
    private int spriteX;
    private int spriteY;
    protected int x;
    protected int y;

    public Sprite(final int width, final int height){
        this.spriteWidth = width;
        this.spriteHeight = height;
    }

    public void draw(final GraphicsContext gc){
        //gc.drawImage(this.image, this.width, this.height);
        gc.drawImage(this.image, this.spriteX, this.spriteY, this.spriteWidth, this.spriteHeight, this.x, this.y, this.spriteWidth, this.spriteHeight);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setSpriteImage(final String img) {
        try {
            this.image = new Image(Files.newInputStream(Paths.get(img)));
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void move(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public Image getSpriteImage(){
        return this.image;
    }

    public int getSpriteWidth(){
        return this.spriteWidth;
    }

    public int getSpriteHeight(){
        return this.spriteHeight;
    }

    public void setSpriteWidth(final int width){
        this.spriteWidth = width;
    }

    public void setSpriteHeight(final int height){
        this.spriteHeight = height;
    }
}
