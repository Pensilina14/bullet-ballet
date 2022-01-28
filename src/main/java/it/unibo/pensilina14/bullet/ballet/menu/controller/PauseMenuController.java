package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

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
	}
	
	@FXML
    void settingsOnMouseClick(final MouseEvent event) throws IOException {
        loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
    }
	
	@FXML
	void backToHomepage(final MouseEvent event) {
		try {
			loader.goToSelectedPageOnInput(Frames.HOMEPAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
