package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.Game;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;

import java.io.IOException;

public class FormController {

  @FXML private TextField insertionForm;

  @FXML
  void goBackOnMouseClicked(final MouseEvent event) throws IOException {
    final SoundsFactory soundsFactory = new SoundsFactoryImpl();
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final PageLoader pageLoader = new PageLoaderImpl();
    pageLoader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
  }

  @FXML
  void submitOnMouseClicked(final MouseEvent event) {
    if (this.insertionForm.getText().isBlank()) {
      final Alert alert = new Alert(AlertType.ERROR, "Blank form!");
      alert.show();
    } else {
      newGame(event);
    }
  }

  private void newGame(final MouseEvent event) {
    final SoundsFactory soundsFactory = new SoundsFactoryImpl();
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Game game = new Game(this.insertionForm.getText());
    game.getSettings().setDifficulty(game.getSettings().getCurrentDifficulty());
    game.getSettings().setResolution(game.getSettings().getCurrentResolution());
    final AbstractScene gameScene = game.getView();
    final Stage stage = (Stage) (((Node) (event.getSource())).getScene().getWindow());

    final Screen currentScreen =
      Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight())
        .stream()
        .findFirst()
        .orElse(Screen.getPrimary());
    final Rectangle2D bounds = currentScreen.getVisualBounds();

    // Prefer the largest supported resolution that fits the current monitor.
    Resolutions chosen = game.getSettings().getCurrentResolution();
    if (chosen.getWidth() > bounds.getWidth() || chosen.getHeight() > bounds.getHeight()) {
      chosen = Resolutions.bestFit(bounds.getWidth(), bounds.getHeight());
    }
    game.getSettings().setResolution(chosen);

    stage.setResizable(false);
    stage.setWidth(chosen.getWidth());
    stage.setHeight(chosen.getHeight());
    gameScene.setHeight(stage.getHeight());
    gameScene.setWidth(stage.getWidth());
    stage.setScene(gameScene);
    stage.show();
    stage.centerOnScreen();
    game.start();
  }
}
