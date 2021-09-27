package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    private final static int MAX_SCENES = 2;

    public static final AbstractScene[] scenes = new AbstractScene[MAX_SCENES];

    private static Stage stage;

    public enum Scenes {
        MENU_SCENE(0),
        MAP_SCENE(1);

        int index;

        Scenes(int index){
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Launcher.stage = primaryStage;

        //Launcher.scenes[Scenes.MENU_SCENE.getIndex()] = new Menu();
        Launcher.scenes[Scenes.MAP_SCENE.getIndex()] = new MapScene();

        Launcher.stage.setTitle("Bullet Ballet");
        setScene(Scenes.MAP_SCENE.getIndex());
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
        /*
                final GameEngine game = new GameEngine();
                game.setup();
                game.mainLoop();

           */
    }
}