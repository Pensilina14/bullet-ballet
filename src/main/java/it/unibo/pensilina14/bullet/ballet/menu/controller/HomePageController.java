package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class HomePageController {
    
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
        System.out.println("new game");
    }

    @FXML
    void settingsOnMouseClick(final MouseEvent event) {
        System.out.println("settings");
    }

    @FXML
    void statsOnMouseClick(final MouseEvent event) throws IOException {
        final PageLoader loader = new PageLoader();
        loader.goToSelectedPage("fxml/menu/settings.fxml", event);
    }
}
