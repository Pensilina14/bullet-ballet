package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class HomePageController {

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
  void newGameOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    this.loader.goToSelectedPageOnInput(Frames.FORM, event);
  }

  @FXML
  void settingsOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    this.loader.goToSelectedPageOnInput(Frames.SETTINGS, event);
  }

  @FXML
  void statsOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    this.loader.goToSelectedPageOnInput(Frames.GAMESTATS, event);
  }
}
