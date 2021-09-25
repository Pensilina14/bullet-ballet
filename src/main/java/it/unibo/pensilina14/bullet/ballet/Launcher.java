package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Launcher extends Application{

        @Override
        public void start(final Stage primaryStage) throws Exception {
            primaryStage.setTitle("PROVA PROVA PROVA!");
            final Button btn = new Button();
            btn.setText("PROVA PROVA PROVA");
            btn.setOnAction(new EventHandler<ActionEvent>() {
     
                @Override
                public void handle(final ActionEvent event) {
                    System.out.println("PROVA PROVA PROVA!");
                }
            });
            
            final StackPane root = new StackPane();
            root.getChildren().add(btn);
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        }

        public static void main(final String[] args) {
            launch(args);
            /*
                final GameEngine game = new GameEngine();
                game.setup();
                game.mainLoop();
                
            */
        }
        
}
