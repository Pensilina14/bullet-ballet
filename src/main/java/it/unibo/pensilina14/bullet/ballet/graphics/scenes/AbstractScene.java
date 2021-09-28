package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public abstract class AbstractScene extends Scene {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    protected int width = 1280; // TODO: 1920 o scelto dall'utente dal menu
    protected int height = 720; // TODO: 1080 o scelto dall'utente dal menu

    private StackPane root = new StackPane();
    protected GraphicsContext gfx;

    public AbstractScene() {

        super(new StackPane(), AbstractScene.WIDTH, AbstractScene.HEIGHT); // TODO: add width, height

        this.root = new StackPane();
        this.setRoot(this.root);

        Canvas canvas = new Canvas(this.width, this.height);
        this.root.getChildren().add(canvas);
        this.gfx = canvas.getGraphicsContext2D();
    }

    public int setWidth(int width){
        return this.width = width;
    }

    public int setHeight(int height){
        return this.height = height;
    }

    public abstract void draw();
}
