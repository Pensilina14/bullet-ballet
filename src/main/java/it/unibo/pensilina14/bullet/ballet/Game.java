package it.unibo.pensilina14.bullet.ballet;

import java.util.Optional;

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
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Game {
    private final ModelController model;
    private final ViewController view;
    private final Engine engine;
    private final GameInfo settings; 
    
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
    	this.model = new ModelControllerImpl(new GameState());
        this.view = new ViewControllerImpl(Optional.of(
        		new MapScene(this.model.getGameState().get())
        		));
        this.engine = new GameEngine(this.view, this.model);
        this.settings = new GameInfoImpl(Resolutions.FULLHD, Difficulties.EASY);
    }
    
    public Game(final String playerName) {
    	this.model = new ModelControllerImpl(new GameState(playerName));
        this.view = new ViewControllerImpl(Optional.of(
        		new MapScene(this.model.getGameState().get())
        		));
        this.engine = new GameEngine(this.view, this.model);
        this.settings = new GameInfoImpl(Resolutions.FULLHD, Difficulties.EASY);
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
