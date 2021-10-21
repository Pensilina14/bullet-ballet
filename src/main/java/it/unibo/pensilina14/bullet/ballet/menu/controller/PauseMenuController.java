package it.unibo.pensilina14.bullet.ballet.menu.controller;
import java.io.IOException;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.Game;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PauseMenuController {
	
	private final PageLoader loader = new PageLoaderImpl();
	
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

	@FXML void resumeGame(final MouseEvent event) {
		AppLogger.getAppLogger().info("Resume.");
		System.out.println("Resume Game, wait for update");
		// TODO add implementation
	}
	
	@FXML
    void settingsOnMouseClick(final MouseEvent event) throws IOException {
        loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
    }
}
