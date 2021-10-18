package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

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
        
        final GameEngine engine = new GameEngine(view, state);
        engine.setup();
        engine.mainLoop();

        

        Launcher.stage.setTitle("Bullet Ballet");
        Launcher.stage.setScene(scene);
        //setScene(Scenes.MAP_SCENE.getIndex()); //TODO: uncomment

        //this.mapScene.draw();

        stage.show();
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
}