package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import javafx.scene.Scene;

public class Game {
    private final GameState state;
    private final GameView view;
    private final GameEngine engine;
    private Scene scene;

    public enum Scenes {
    	/**
    	 * 
    	 */
        MENU_SCENE,
        /**
         * 
         */
        MAP_SCENE;
    }

    public Game() {
    	this.state = new GameState();
        this.view = new MapScene(this.state);
        this.engine = new GameEngine(this.view, this.state);
        this.createScene();
    }

    public Game(final GameState gameState, final GameView gameView, final GameEngine gameEngine) {
    	this.state = gameState;
    	this.view = gameView;
    	this.engine = gameEngine;
    	this.createScene();
    }

    private void createScene() {
    	this.scene = new Scene(this.view.getAppPane());
    }

    public final void start() {
    	AppLogger.getAppLogger().debug("Inside Game start() method.");
        this.engine.setup();
        AppLogger.getAppLogger().debug("Engine setup done.. Starting main loop.");
        this.engine.mainLoop();
    }

    public final Scene getScene() {
    	return this.scene;
    }
}
