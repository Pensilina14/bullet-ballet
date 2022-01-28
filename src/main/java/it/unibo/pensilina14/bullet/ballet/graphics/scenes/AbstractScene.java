package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractScene extends Scene {

    public static final int SCENE_WIDTH = 1280;
    public static final int SCENE_HEIGHT = 720;

    protected int width = getScreenWidth();
    protected int height = getScreenHeight();

    protected Pane root;
    protected GraphicsContext gfx;

    protected final Set<KeyCode> keysPressed;
    protected final Set<KeyCode> keysReleased;

    public AbstractScene() {
        super(new Group(), AbstractScene.SCENE_WIDTH, AbstractScene.SCENE_HEIGHT);
        this.keysPressed = new HashSet<>();
        this.keysReleased = new HashSet<>();
    }

    public final void initScene() {
        this.root = new AnchorPane();
        final Canvas canvas = new Canvas(this.width, this.height);
        this.root.getChildren().add(canvas);
        this.setRoot(this.root);
        this.gfx = canvas.getGraphicsContext2D();

        this.setOnKeyPressed(k -> {
            this.keysPressed.add(k.getCode());
        });
        this.setOnKeyReleased(k -> {
            this.keysPressed.remove(k.getCode());
            this.keysReleased.add(k.getCode());
        });
    }

    public abstract void draw() throws IOException;

	public void setHeight(final double heigth) {
		this.root.setMaxHeight(heigth);
	}
	
	public void setWidth(final double width) {
		this.root.setMaxWidth(width);
	}

    //TODO: questo è un doppione che sta in PageLoaderImpl, ma verrà fixato.
    public final int getScreenWidth(){
        return (!Save.loadSettings().isEmpty()) ? Integer.parseInt(Save.loadSettings().get(Save.RESOLUTION_WIDTH_STRING)) : Resolutions.getDefaultResolution().getWidth();
    }

    public final int getScreenHeight(){
        return (!Save.loadSettings().isEmpty()) ? Integer.parseInt(Save.loadSettings().get(Save.RESOLUTION_HEIGHT_STRING)) : Resolutions.getDefaultResolution().getHeight();
    }
}
