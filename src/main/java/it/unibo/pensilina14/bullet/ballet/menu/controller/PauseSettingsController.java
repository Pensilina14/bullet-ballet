package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Rectangle2D;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PauseSettingsController implements Initializable {

  private static final int WIDTH_INDEX = 1;
  private static final int HEIGHT_INDEX = 4;
  @FXML private Slider audio;
  @FXML private ComboBox<String> resolution;
  @FXML private ComboBox<String> difficulty;
  @FXML private ComboBox<String> language;
  private final PageLoader loader = new PageLoaderImpl();
  private final SoundsFactory soundsFactory = new SoundsFactoryImpl();

  @Override
  public void initialize(
      final URL url,
      final ResourceBundle
          resourceBundle) { // Questo serve semplicemente per caricare le impostazioni
    final Map<String, String> settingsMap = Save.loadSettings();
    this.audio.setShowTickLabels(true);
    this.audio.setMax(1.0);

    if (!settingsMap.isEmpty()) {
      final String res =
          "[ "
              + settingsMap.get(Save.RESOLUTION_WIDTH_STRING)
              + " ], [ "
              + settingsMap.get(Save.RESOLUTION_HEIGHT_STRING)
              + " ]";
      this.resolution.getSelectionModel().select(res);
      this.audio.setValue(Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)));
      this.language
          .getSelectionModel()
          .select(Languages.getLanguagesMap().get(settingsMap.get(Save.LANGUAGE_STRING)));
    } else {
      this.resolution.getSelectionModel().select(Resolutions.getDefaultResolution().toString());
      this.language.getSelectionModel().select(Languages.getDefaultLanguage().getLanguage());
    }
  }

  @FXML
  void goBackOnMouseClick(final MouseEvent event) throws IOException {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    loader.goToSelectedPageOnInput(Frames.PAUSEMENU, event);
  }

  @FXML
  void showResolutionsOnMouseClicked(final MouseEvent event) {
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final ObservableList<String> resolutions = FXCollections.observableArrayList();
    // Teoricamente non servirebbero neanche sti metodi, basterebbe settare 1 volta in Initalizable
    for (final var r : Resolutions.values()) {
      resolutions.add(r.toString());
    }
    this.resolution.setItems(resolutions);
  }

  @FXML
  void showLanguagesOnMouseClick(final MouseEvent event) {
    this.soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final ObservableList<String> languages = FXCollections.observableArrayList();
    // Teoricamente non servirebbero neanche sti metodi, basterebbe settare 1 volta in Initalizable
    for (final var l : Languages.values()) {
      languages.add(l.getLanguage());
    }
    this.language.setItems(languages);
  }

  @FXML
  void submitSaveSettings(final MouseEvent event) {
    this.soundsFactory.createSound(Sounds.MENU_SOUND).play();

    // L'audio non è vuoto, è di default come 0.0 quindi non penso servi controllarlo.
    // Questa parentesi serve così evito di scrivere tre volte il !.

    if (!(this.resolution.getSelectionModel().getSelectedItem().isBlank()
        || this.language.getSelectionModel().getSelectedItem().isBlank())) {

      final List<String> resList =
          Arrays.asList(this.resolution.getSelectionModel().getSelectedItem().split("[ ]"));

      final boolean hasSaved =
          Save.saveSettings(
              Integer.parseInt(resList.get(PauseSettingsController.WIDTH_INDEX)),
              Integer.parseInt(resList.get(PauseSettingsController.HEIGHT_INDEX)),
              Difficulties.getDefaultDifficulty().toString(),
              this.audio.getValue(),
              Languages.valueOf(
                      this.language
                          .getSelectionModel()
                          .getSelectedItem()
                          .toUpperCase(Locale.getDefault()))
                  .getCountryCode()); // Sistemare sto warning

      if (hasSaved) {
        generateSaveSettingsAlert(Alert.AlertType.INFORMATION);
        applyResolutionToGameWindow(event);
      } else {
        generateSaveSettingsAlert(Alert.AlertType.ERROR);
      }

    } else {
      generateSaveSettingsAlert(AlertType.ERROR);
    }
  }

  @FXML
  void toggleFullScreenOnMouseClicked(final MouseEvent event) {
    this.soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Stage gameStage = getGameStage(event);
    if (gameStage != null) {
        final boolean next = !gameStage.isFullScreen();
        if (next) {
          gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
          gameStage.setFullScreenExitHint("");
        }
        gameStage.setFullScreen(next);
    }
  }

  @FXML
  void fitAndCenterOnMouseClicked(final MouseEvent event) {
    this.soundsFactory.createSound(Sounds.MENU_SOUND).play();
    final Stage gameStage = getGameStage(event);
    if (gameStage == null) {
      return;
    }
    if (gameStage.isFullScreen()) {
      gameStage.setFullScreen(false);
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
                gameStage.getX(), gameStage.getY(), gameStage.getWidth(), gameStage.getHeight())
            .stream()
            .findFirst()
            .orElse(Screen.getPrimary());
    final Rectangle2D bounds = currentScreen.getVisualBounds();

    Resolutions chosen = Resolutions.fromDimensions(requestedW, requestedH);
    if (chosen.getWidth() > bounds.getWidth() || chosen.getHeight() > bounds.getHeight()) {
      chosen = Resolutions.bestFit(bounds.getWidth(), bounds.getHeight());
    }

    gameStage.setResizable(false);
    gameStage.setWidth(chosen.getWidth());
    gameStage.setHeight(chosen.getHeight());
    if (gameStage.getScene() instanceof AbstractScene) {
      final AbstractScene scene = (AbstractScene) gameStage.getScene();
      scene.setWidth(gameStage.getWidth());
      scene.setHeight(gameStage.getHeight());
    }
    gameStage.centerOnScreen();
  }

  private void applyResolutionToGameWindow(final MouseEvent event) {
    final Stage settingsStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
    final Window owner = settingsStage.getOwner();
    if (!(owner instanceof Stage)) {
      return;
    }
    final Stage gameStage = (Stage) owner;

    final String selected = this.resolution.getSelectionModel().getSelectedItem();
    if (selected == null || selected.isBlank()) {
      return;
    }

    final List<String> resList = Arrays.asList(selected.split("[ ]"));
    final int requestedW = Integer.parseInt(resList.get(PauseSettingsController.WIDTH_INDEX));
    final int requestedH = Integer.parseInt(resList.get(PauseSettingsController.HEIGHT_INDEX));

    final Screen currentScreen =
        Screen.getScreensForRectangle(
                gameStage.getX(), gameStage.getY(), gameStage.getWidth(), gameStage.getHeight())
            .stream()
            .findFirst()
            .orElse(Screen.getPrimary());
    final Rectangle2D bounds = currentScreen.getVisualBounds();

    Resolutions chosen = Resolutions.fromDimensions(requestedW, requestedH);
    if (chosen.getWidth() > bounds.getWidth() || chosen.getHeight() > bounds.getHeight()) {
      chosen = Resolutions.bestFit(bounds.getWidth(), bounds.getHeight());
    }

    if (gameStage.isFullScreen()) {
      gameStage.setFullScreen(false);
    }
    gameStage.setResizable(false);
    gameStage.setWidth(chosen.getWidth());
    gameStage.setHeight(chosen.getHeight());
    if (gameStage.getScene() instanceof AbstractScene) {
      final AbstractScene scene = (AbstractScene) gameStage.getScene();
      scene.setWidth(gameStage.getWidth());
      scene.setHeight(gameStage.getHeight());
    }
    gameStage.centerOnScreen();
  }

  private Stage getGameStage(final MouseEvent event) {
    final Stage settingsStage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
    final Window owner = settingsStage.getOwner();
    return owner instanceof Stage ? (Stage) owner : null;
  }

  private void generateSaveSettingsAlert(final AlertType alertType) {
    final Alert alert = new Alert(alertType);
    if (alertType.equals(AlertType.INFORMATION)) {
      alert.setTitle("Save Settings");
      alert.setHeaderText("Save");
      alert.setContentText("Operation executed successfully!");
      alert.show();
    } else if (alertType.equals(AlertType.ERROR)) {
      alert.setTitle("Save Settings Error");
      alert.setHeaderText("Error");
      alert.setContentText("Save has not been executed.");
      alert.show();
    }
  }
}
