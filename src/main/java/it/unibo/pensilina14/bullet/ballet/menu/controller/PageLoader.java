package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PageLoader {

    public void goToSelectedPage(final String string, final MouseEvent event) throws IOException {
            
            Platform.runLater(() -> {
                    Parent page = null;
                    try {
                            page = FXMLLoader.load(getClass().getResource(string));
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
                final Scene scene = new Scene(page);
                final Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
                window.setScene(scene);
                window.show();
            });
            
    }
    
}
