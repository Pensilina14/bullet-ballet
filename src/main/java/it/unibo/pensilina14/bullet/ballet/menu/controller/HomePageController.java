package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class HomePageController {
    
    @FXML
    void exitOnMouseClicked(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.OK, 
                ButtonType.CANCEL);
        createDialog(alert);
    }

    private void createDialog(Alert alert) {
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }
    
    @FXML
    void newGameOnMouseClick(MouseEvent event) {
        System.out.println("new game");
    }

    @FXML
    void settingsOnMouseClick(MouseEvent event) {
        System.out.println("settings");
    }

    @FXML
    void statsOnMouseClick(MouseEvent event) {
        System.out.println("stats");
    }
}
