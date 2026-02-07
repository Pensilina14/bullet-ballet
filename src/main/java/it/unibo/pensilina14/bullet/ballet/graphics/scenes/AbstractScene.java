package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

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

  private StackPane viewport;
  private Group scaledContent;
  private final Scale scaleTransform = new Scale(1.0, 1.0);

  protected final Set<KeyCode> keysPressed;
  protected final Set<KeyCode> keysReleased;

  public AbstractScene() {
    super(new Group(), AbstractScene.SCENE_WIDTH, AbstractScene.SCENE_HEIGHT);
    this.keysPressed = new HashSet<>();
    this.keysReleased = new HashSet<>();
  }

  public final void initScene() {
    // Content root: uses a fixed logical size. The whole content is scaled to fit the window.
    this.root = new AnchorPane();
    this.root.setMinSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.root.setPrefSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.root.setMaxSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());

    // Keep a canvas for legacy drawing code (even if most rendering uses nodes).
    final Canvas canvas = new Canvas(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.root.getChildren().add(canvas);
    this.gfx = canvas.getGraphicsContext2D();

    this.scaledContent = new Group(this.root);
    this.scaledContent.getTransforms().add(this.scaleTransform);

    this.viewport = new StackPane(this.scaledContent);
    // StackPane centers its children by default. We do our own centering via translate in updateScale(),
    // so anchor content to the top-left to avoid double-centering (which can push the player off-screen).
    this.viewport.setAlignment(Pos.TOP_LEFT);
    this.viewport.setStyle("-fx-background-color: black;");
    this.setRoot(this.viewport);

    this.widthProperty().addListener((obs, oldV, newV) -> updateScale());
    this.heightProperty().addListener((obs, oldV, newV) -> updateScale());
    updateScale();

    this.setOnKeyPressed(
        k -> {
          this.keysPressed.add(k.getCode());
        });
    this.setOnKeyReleased(
        k -> {
          this.keysPressed.remove(k.getCode());
          this.keysReleased.add(k.getCode());
        });
  }

  private void updateScale() {
    if (this.scaledContent == null) {
      return;
    }
    final double baseW = Resolutions.FULLHD.getWidth();
    final double baseH = Resolutions.FULLHD.getHeight();
    final double viewportW = Math.max(1.0, this.getWidth());
    final double viewportH = Math.max(1.0, this.getHeight());

    final double scale = Math.min(viewportW / baseW, viewportH / baseH);
    this.scaleTransform.setX(scale);
    this.scaleTransform.setY(scale);

    // Center scaled content and avoid white bands on resize.
    this.scaledContent.setTranslateX((viewportW - (baseW * scale)) / 2.0);
    this.scaledContent.setTranslateY((viewportH - (baseH * scale)) / 2.0);
  }

  public abstract void draw() throws IOException;

  public void setHeight(final double heigth) {
    // Kept for backward compatibility: stage size changes automatically trigger scale updates.
    updateScale();
  }

  public void setWidth(final double width) {
    // Kept for backward compatibility: stage size changes automatically trigger scale updates.
    updateScale();
  }

  public final int getScreenWidth() {
    return !Save.loadSettings().isEmpty()
        ? Integer.parseInt(Save.loadSettings().get(Save.RESOLUTION_WIDTH_STRING))
        : Resolutions.getDefaultResolution().getWidth();
  }

  public final int getScreenHeight() {
    return !Save.loadSettings().isEmpty()
        ? Integer.parseInt(Save.loadSettings().get(Save.RESOLUTION_HEIGHT_STRING))
        : Resolutions.getDefaultResolution().getHeight();
  }
}
