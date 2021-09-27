package it.unibo.pensilina14.bullet.ballet;

<<<<<<< HEAD
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
=======
>>>>>>> main
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

<<<<<<< HEAD
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
=======
public final class Launcher extends Application{

        @Override
        public void start(final Stage stage) throws Exception {
            final Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
            final Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
>>>>>>> main
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

<<<<<<< HEAD
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
=======
        public static void main(final String[] args) {
            launch(args);
        }
        
>>>>>>> main
}
