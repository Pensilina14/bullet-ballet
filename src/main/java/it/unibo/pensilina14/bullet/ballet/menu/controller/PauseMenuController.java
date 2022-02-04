package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class PauseMenuController {
	
	@FXML
    private Button resumeButton;
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

	@FXML void resumeGame(final MouseEvent event) {
		new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
		final Window window = resumeButton.getScene().getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	
	@FXML
    void settingsOnMouseClick(final MouseEvent event) throws IOException {
		new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
        loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
    }
	
	@FXML
	void backToHomepage(final MouseEvent event) {
		new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
		try {
			final Window window = resumeButton.getScene().getWindow();			
			window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_HIDDEN));
			window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
			loader.goToSelectedPageOnInput(Frames.HOMEPAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
