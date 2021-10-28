package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
	
    private final PageLoader loader = new PageLoaderImpl();

    @Override
    public final void start(final Stage primaryStage) throws Exception {
    	final Game game = new Game();
    	final Scene gameScene = (Scene) game.getView();
        primaryStage.setScene(gameScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        final AnimationTimer timer = new AnimationTimerImpl(game);
        timer.start();
//    	AppLogger.getAppLogger().debug("Inside Launcher start() method, it's been called.");
//        loader.loadFirstScene(primaryStage);
    	
    }

    public static void main(final String[] args) {
    	AppLogger.getAppLogger().info("Application has started.");
        launch(args);
    }
    /*
    private final static int MAX_SCENES = 2;

    public static final AbstractScene[] scenes = new AbstractScene[MAX_SCENES];

    private static Stage stage;

    public enum Scenes {
        MENU_SCENE,
        MAP_SCENE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Launcher.stage = primaryStage;

        //Launcher.scenes[Scenes.MENU_SCENE.ordinal()] = new Menu();
        //Launcher.scenes[Scenes.MAP_SCENE.ordinal()] = this.mapScene; //TODO: uncomment

        final GameState state = new GameState();
        final GameView view = new MapScene(state);
        final Scene scene = new Scene(view.getAppPane());
        Launcher.stage.setTitle("Bullet Ballet");
        Launcher.stage.setScene(scene);
        stage.show();  
        
        
        final GameEngine engine = new GameEngine(view, state);
        engine.setup();
        engine.mainLoop();

        
        //setScene(Scenes.MAP_SCENE.getIndex()); //TODO: uncomment

        //this.mapScene.draw();

        
    }

    public static void setScene(int currentScene){
        Launcher.stage.setScene(scenes[currentScene]);
        scenes[currentScene].draw();
    }

    public static void exit(){
        stage.hide();
    }

    public static void main(String[] args) {

        launch(args);
    }
    */
}
