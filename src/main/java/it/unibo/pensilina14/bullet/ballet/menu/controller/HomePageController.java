package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.core.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController {
    
    private final PageLoader loader = new PageLoader();
    
    @FXML
    void exitOnMouseClicked(final MouseEvent event) {
        final Alert alert = new Alert(AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.OK, 
                ButtonType.CANCEL);
        createDialog(alert);
    }

    private void createDialog(final Alert alert) {
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            Platform.exit();
        }
    }
    
    @FXML
    void newGameOnMouseClick(final MouseEvent event) {
        final Game game = new Game();
        final Stage stage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        stage.setScene(game.getScene());
        game.start();
    }

    @FXML
    void settingsOnMouseClick(final MouseEvent event) throws IOException {
        loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
    }

    @FXML
    void statsOnMouseClick(final MouseEvent event) throws IOException {
        
    }
}
