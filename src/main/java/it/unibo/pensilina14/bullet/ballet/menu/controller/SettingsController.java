package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class SettingsController implements Initializable {

    private static final int WIDTH_INDEX = 1;
    private static final int HEIGHT_INDEX = 4;
    @FXML
    private Slider audio;
    @FXML
    private ComboBox<String> resolution;
    @FXML
    private ComboBox<String> difficulty;
    @FXML
    private ComboBox<String> language;
    private final PageLoader loader = new PageLoaderImpl();
    private final SoundsFactory soundsFactory = new SoundsFactoryImpl();

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) { // Questo serve semplicemente per caricare le impostazioni
    	this.audio.setShowTickLabels(true);
    	this.audio.setMax(1.0);
        final Map<String,String> settingsMap = Save.loadSettings();
        if(!settingsMap.isEmpty()){
            final String res = "[ " + settingsMap.get(Save.RESOLUTION_WIDTH_STRING) + " ], [ " + settingsMap.get(Save.RESOLUTION_HEIGHT_STRING) + " ]";
            this.resolution.getSelectionModel().select(res);
            this.difficulty.getSelectionModel().select(settingsMap.get(Save.DIFFICULTY_STRING));
            this.audio.setValue(Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)));
            this.language.getSelectionModel().select(Languages.getLanguagesMap().get(settingsMap.get(Save.LANGUAGE_STRING)));
        } else {
            this.resolution.getSelectionModel().select(Resolutions.getDefaultResolution().toString());
            this.difficulty.getSelectionModel().select(Difficulties.getDefaultDifficulty().toString());
            this.language.getSelectionModel().select(Languages.getDefaultLanguage().getLanguage());
        }
    }
    
    @FXML
    void goBackOnMouseClick(final MouseEvent event) throws IOException {
    	soundsFactory.createSound(Sounds.MENU_SOUND).play();
        loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }

    @FXML
    void showDifficultiesOnMouseClick(final MouseEvent event) {
    	soundsFactory.createSound(Sounds.MENU_SOUND).play();
        final ObservableList<String> difficulties = FXCollections.observableArrayList();
        // Teoricamente non servirebbero neanche sti metodi, basterebbe settare 1 volta in Initalizable
        for(final var d : Difficulties.values()){
            difficulties.add(d.toString());
        }
        this.difficulty.setItems(difficulties);
    }

    @FXML
    void showResolutionsOnMouseClicked(final MouseEvent event) {
    	soundsFactory.createSound(Sounds.MENU_SOUND).play();
        final ObservableList<String> resolutions = FXCollections.observableArrayList();
        // Teoricamente non servirebbero neanche sti metodi, basterebbe settare 1 volta in Initalizable
        for(final var r : Resolutions.values()){
            resolutions.add(r.toString());
        }
        this.resolution.setItems(resolutions);
    }

    @FXML
    void showLanguagesOnMouseClick(final MouseEvent event) {
    	soundsFactory.createSound(Sounds.MENU_SOUND).play();
        final ObservableList<String> languages = FXCollections.observableArrayList();
        // Teoricamente non servirebbero neanche sti metodi, basterebbe settare 1 volta in Initalizable
        for(final var l : Languages.values()){
            languages.add(l.getLanguage());
        }
        this.language.setItems(languages);
    }

    @FXML
    void submitSaveSettings(final MouseEvent event) {
    	new AudioClip(Objects.requireNonNull(this.getClass().getResource("/menu_sound.mp4")).toExternalForm()).play();

        // L'audio non è vuoto, è di default come 0.0 quindi non penso servi controllarlo.
        // Questa parentesi serve così evito di scrivere tre volte il !.

        if(!(this.resolution.getSelectionModel().getSelectedItem().isBlank()
                || this.difficulty.getSelectionModel().getSelectedItem().isBlank()
                || this.language.getSelectionModel().getSelectedItem().isBlank())){

            final List<String> resList = Arrays.asList(this.resolution.getSelectionModel().getSelectedItem().split("[ ]"));

            final boolean hasSaved = Save.saveSettings(Integer.parseInt(resList.get(SettingsController.WIDTH_INDEX)), Integer.parseInt(resList.get(SettingsController.HEIGHT_INDEX)),
                    this.difficulty.getSelectionModel().getSelectedItem(), this.audio.getValue(),
                    Languages.valueOf(this.language.getSelectionModel().getSelectedItem().toUpperCase(Locale.getDefault())).getCountryCode());

            if(hasSaved){
                generateSaveSettingsAlert(Alert.AlertType.INFORMATION);
            } else {
                generateSaveSettingsAlert(Alert.AlertType.ERROR);
            }

        } else {
            generateSaveSettingsAlert(AlertType.ERROR);
        }
    }
    
    private void generateSaveSettingsAlert(final AlertType alertType) {
    	final Alert alert = new Alert(alertType);
    	if (alertType.equals(AlertType.INFORMATION)) {
    		alert.setTitle("Save Settings"); 
            alert.setHeaderText("Save"); 
            alert.setContentText("Operation executed successfully!"); 
            alert.show();
    	} else if (alertType.equals(AlertType.ERROR)){
    		alert.setTitle("Save Settings Error"); 
            alert.setHeaderText("Error"); 
            alert.setContentText("Save has not been executed.");
            alert.show();
    	}
    }
    
}
