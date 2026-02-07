package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.geometry.Rectangle2D;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class PauseMenuController {

  @FXML private Button resumeButton;
  private final PageLoader loader = new PageLoaderImpl();
  private final SoundsFactory soundsFactory = new SoundsFactoryImpl();

  @FXML
  void exitOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Alert alert =
        new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
    createDialog(alert);
  }

  private void createDialog(final Alert alert) {
    final Optional<ButtonType> result = alert.showAndWait();
    if (result.get().equals(ButtonType.OK)) {
      Platform.exit();
    }
  }

  @FXML
  void resumeGame(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Window window = resumeButton.getScene().getWindow();
    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
  }

  @FXML
  void settingsOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    loader.goToSelectedPageOnInput(Frames.PSETTINGS, event);
  }

  @FXML
  void toggleFullScreenOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Stage targetStage = getGameStage();
    targetStage.setFullScreen(!targetStage.isFullScreen());
  }

  @FXML
  void fitAndCenterOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Stage targetStage = getGameStage();
    if (targetStage.isFullScreen()) {
      targetStage.setFullScreen(false);
    }

    final Map<String, String> settingsMap = Save.loadSettings();
    final int requestedW =
        settingsMap.isEmpty()
            ? Resolutions.getDefaultResolution().getWidth()
            : Integer.parseInt(settingsMap.get(Save.RESOLUTION_WIDTH_STRING));
    final int requestedH =
        settingsMap.isEmpty()
            ? Resolutions.getDefaultResolution().getHeight()
            : Integer.parseInt(settingsMap.get(Save.RESOLUTION_HEIGHT_STRING));

    final Screen currentScreen =
        Screen.getScreensForRectangle(
                targetStage.getX(), targetStage.getY(), targetStage.getWidth(), targetStage.getHeight())
            .stream()
            .findFirst()
            .orElse(Screen.getPrimary());
    final Rectangle2D bounds = currentScreen.getVisualBounds();

    Resolutions chosen = Resolutions.fromDimensions(requestedW, requestedH);
    if (chosen.getWidth() > bounds.getWidth() || chosen.getHeight() > bounds.getHeight()) {
      chosen = Resolutions.bestFit(bounds.getWidth(), bounds.getHeight());
    }

    targetStage.setResizable(false);
    targetStage.setWidth(chosen.getWidth());
    targetStage.setHeight(chosen.getHeight());

    if (targetStage.getScene() instanceof AbstractScene) {
      final AbstractScene scene = (AbstractScene) targetStage.getScene();
      scene.setWidth(targetStage.getWidth());
      scene.setHeight(targetStage.getHeight());
    }
    targetStage.centerOnScreen();
  }

  private Stage getGameStage() {
    final Stage pauseStage = (Stage) resumeButton.getScene().getWindow();
    final Window owner = pauseStage.getOwner();
    return owner instanceof Stage ? (Stage) owner : pauseStage;
  }
}
