package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractScene extends Scene implements GameView {

    public static final int SCENE_WIDTH = 1280;
    public static final int SCENE_HEIGHT = 720;

    protected int width = 1280; // TODO: 1920 o scelto dall'utente dal menu
    protected int height = 720; // TODO: 1080 o scelto dall'utente dal menu

    protected StackPane root = new StackPane();
    protected GraphicsContext gfx;

    protected Set<KeyCode> keysPressed;
    protected Set<KeyCode> keysReleased;

    public AbstractScene() {

        super(new StackPane(), AbstractScene.SCENE_WIDTH, AbstractScene.SCENE_HEIGHT);

        this.root = new StackPane();
        this.setRoot(this.root);

        Canvas canvas = new Canvas(this.width, this.height);
        this.root.getChildren().add(canvas);
        this.gfx = canvas.getGraphicsContext2D();

        this.keysPressed = new HashSet<>();
        this.keysReleased = new HashSet<>();
        this.setOnKeyPressed( k -> {
            this.keysPressed.add(k.getCode());
        });

        this.setOnKeyReleased( k -> {
            this.keysPressed.remove(k.getCode());
            this.keysReleased.add(k.getCode());
        });
    }

    public int setWidth(int width){
        return this.width = width;
    }

    public int setHeight(int height){
        return this.height = height;
    }

    public abstract void draw();
}
