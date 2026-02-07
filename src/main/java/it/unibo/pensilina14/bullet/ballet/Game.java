package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.Engine;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.core.controller.ModelController;
import it.unibo.pensilina14.bullet.ballet.core.controller.ModelControllerImpl;
import it.unibo.pensilina14.bullet.ballet.core.controller.ViewController;
import it.unibo.pensilina14.bullet.ballet.core.controller.ViewControllerImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Difficulties;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

import java.util.Map;
import java.util.Optional;

public class Game {
  private final ModelController model;
  private final ViewController view;
  private final Engine engine;
  private final GameInfo settings;

  public enum Scenes {
    /** */
    MENU_SCENE,
    /** */
    MAP_SCENE
  }

  public Game() {
    this.model = new ModelControllerImpl(new GameState());
    this.view = new ViewControllerImpl(Optional.of(new MapScene(this.model.getGameState().get())));
    this.engine = new GameEngine(this.view, this.model);
    this.settings = loadSettings();
  }

  public Game(final String playerName) {
    this.model = new ModelControllerImpl(new GameState(playerName));
    this.view = new ViewControllerImpl(Optional.of(new MapScene(this.model.getGameState().get())));
    this.engine = new GameEngine(this.view, this.model);
    this.settings = loadSettings();
  }

  private static GameInfo loadSettings() {
    final Map<String, String> settingsMap = Save.loadSettings();
    if (settingsMap.isEmpty()) {
      return new GameInfoImpl(Resolutions.getDefaultResolution(), Difficulties.getDefaultDifficulty());
    }

    final int width = Integer.parseInt(settingsMap.get(Save.RESOLUTION_WIDTH_STRING));
    final int height = Integer.parseInt(settingsMap.get(Save.RESOLUTION_HEIGHT_STRING));
    final Resolutions resolution = Resolutions.fromDimensions(width, height);

    final Difficulties difficulty =
        settingsMap.containsKey(Save.DIFFICULTY_STRING)
            ? Difficulties.fromString(settingsMap.get(Save.DIFFICULTY_STRING))
            : Difficulties.getDefaultDifficulty();

    return new GameInfoImpl(resolution, difficulty);
  }

  public final void start() {
    AppLogger.getAppLogger().debug("Inside Game start() method.");
    this.engine.start();
  }

  public final AbstractScene getView() {
    return (AbstractScene) this.view.getGameView();
  }

  public final GameState getModel() {
    return this.model.getGameState().get();
  }

  public final GameInfo getSettings() {
    return this.settings;
  }
}
