package it.unibo.pensilina14.bullet.ballet;

import java.util.Optional;

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
    private final GameEngine engine;
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

//    public Game() {
//    	this.model = new ModelControllerImpl(new GameState());
//        this.view = new ViewControllerImpl(Optional.of(
//        		new MapScene(this.model.getGameState().get())
//        		));
//        this.engine = new GameEngine(this.view, this.model);
//        //this.settings = new GameInfoImpl(Resolutions.FULLHD, Difficulties.EASY);
//    }
    
    public Game(final String playerName) {
    	this.model = new ModelControllerImpl(new GameState(playerName));
        this.view = new ViewControllerImpl(Optional.of(
        		new MapScene(this.model.getGameState().get())
        		));
        this.engine = new GameEngine(this.view, this.model);
        this.settings = new GameInfoImpl(Resolutions.FULLHD, Difficulties.EASY);
    }

//    public Game(final GameState gameState, final GameView gameView, final GameEngine gameEngine) {
//    	this.state = gameState;
//    	this.view = (AbstractScene) gameView;
//    	this.engine = gameEngine;
//    	this.settings = new SettingsImpl(Resolutions.FULLHD, Difficulties.EASY);
//    }
//    
//    public Game(final GameState state, final AbstractScene view, final GameEngine engine
//    		, final Settings settings) {
//		this.state = state;
//		this.view = view;
//		this.engine = engine;
//		this.settings = settings;
//	}
    
	public final void start() {
    	AppLogger.getAppLogger().debug("Inside Game start() method.");
    	//this.engine.setup();
    	this.engine.start();
        //AppLogger.getAppLogger().debug("Engine setup done.. Starting main loop.");
        //this.engine.mainLoop();
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
