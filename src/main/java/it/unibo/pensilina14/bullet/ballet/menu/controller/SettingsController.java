package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SettingsController implements Initializable {

    @FXML
    private Slider audio;
    @FXML
    private ComboBox<String> resolution;
    @FXML
    private ComboBox<String> difficulty;
    @FXML
    private ComboBox<String> language;
    private final PageLoader loader = new PageLoaderImpl();

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) { // Questo serve semplicemente per caricare le impostazioni

        final Map<String,String> settingsMap = Save.loadSettings();

        if(!settingsMap.isEmpty()){
            final String res = "[ " + settingsMap.get(Save.RESOLUTION_WIDTH_STRING) + " ], [ " + settingsMap.get(Save.RESOLUTION_HEIGHT_STRING) + " ]";
            this.resolution.getSelectionModel().select(res);
            this.difficulty.getSelectionModel().select(settingsMap.get(Save.DIFFICULTY_STRING));
            this.audio.setValue(Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)));
            final String loadLanguage = settingsMap.get(Save.LANGUAGE_STRING);
            if(Objects.equals(loadLanguage, "en_UK")){
                this.language.getSelectionModel().select(Languages.ENGLISH.getLanguage());
            } else if(Objects.equals(loadLanguage, "it_IT")){
                this.language.getSelectionModel().select(Languages.ITALIANO.getLanguage());
            }
        }
    }
    
    @FXML
    void goBackOnMouseClick(final MouseEvent event) throws IOException {
        loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }

    @FXML
    void showDifficultiesOnMouseClick(final MouseEvent event) {
        final ObservableList<String> difficulties = FXCollections.observableArrayList(Difficulties.EASY.toString(),
        		Difficulties.MEDIUM.toString(), Difficulties.HARD.toString());
        this.difficulty.setItems(difficulties);
    }

    @FXML
    void showResolutionsOnMouseClicked(final MouseEvent event) {
        final ObservableList<String> resolutions = FXCollections.observableArrayList(Resolutions.FULLHD.toString(),
                Resolutions.HD.toString());
        this.resolution.setItems(resolutions);
        System.out.println(this.resolution.getSelectionModel());
        
    }

    @FXML
    void showLanguagesOnMouseClick(final MouseEvent event) {
        final ObservableList<String> languages = FXCollections.observableArrayList(Languages.ENGLISH.getLanguage(), Languages.ITALIANO.getLanguage());
        this.language.setItems(languages);
    }

    @FXML
    void submitSaveSettings(final MouseEvent event) {

        // Faccio un parsing molto semplice perch√® mi serve salvare solo la width e la height e non tutta la stringa.
        final List<String> resList = Arrays.asList(this.resolution.getSelectionModel().getSelectedItem().split("[ ]"));
        //Il 4 Ë un numero magico, va sostituito con una costante o enum
        final boolean hasSaved = Save.saveSettings(Integer.parseInt(resList.get(1)), Integer.parseInt(resList.get(4)),
                this.difficulty.getSelectionModel().getSelectedItem(), this.audio.getValue(),
                Languages.valueOf(this.language.getSelectionModel().getSelectedItem().toUpperCase()).getCountryCode()); //Sistemare sto warning
        if(hasSaved){
            generateSaveSettingsAlert(Alert.AlertType.INFORMATION);
        } else {
        	generateSaveSettingsAlert(Alert.AlertType.ERROR);
        }
    }
    
    private Alert generateSaveSettingsAlert(final AlertType alertType) {
    	final Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    	return alert;
    }
    
}
