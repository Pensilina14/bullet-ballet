package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class HomePageController {
    
    private final PageLoader loader = new PageLoaderImpl();
    
    @FXML
    void exitOnMouseClicked(final MouseEvent event) {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
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
    void newGameOnMouseClick(final MouseEvent event) throws IOException {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
    	this.loader.goToSelectedPageOnInput(Frames.FORM, event);
    }

    @FXML
    void settingsOnMouseClick(final MouseEvent event) throws IOException {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
        this.loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
    }

    @FXML
    void statsOnMouseClick(final MouseEvent event) throws IOException {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
        this.loader.goToSelectedPageOnInput(Frames.GAMESTATS, event);
    }
}
