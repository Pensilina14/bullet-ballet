package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class PauseMenuController {

  private static final String PAUSE_ACTION_KEY = "pauseAction";
  private static final String ACTION_RETRY = "retry";
  private static final String ACTION_MENU = "menu";

  @FXML private Button resumeButton;
  private final PageLoader loader = new PageLoaderImpl();
  private final SoundsFactory soundsFactory = new SoundsFactoryImpl();

  @FXML
  void resumeGame(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Window window = resumeButton.getScene().getWindow();
    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
  }

  @FXML
  void retryOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    closeWithAction(ACTION_RETRY);
  }

  @FXML
  void mainMenuOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    closeWithAction(ACTION_MENU);
  }

  @FXML
  void settingsOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    loader.goToSelectedPageOnInput(Frames.PSETTINGS, event);
  }

  private void closeWithAction(final String action) {
    final Window window = resumeButton.getScene().getWindow();
    window.getProperties().put(PAUSE_ACTION_KEY, action);
    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
  }
}
