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
    private final PageLoader loader = new PageLoaderImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // Questo serve semplicemente per caricare le impostazioni

        final Map<String,String> settingsMap = Save.loadSettings();

        if(!settingsMap.isEmpty()){
            final String res = "[ " + settingsMap.get(Save.RESOLUTION_WIDTH_STRING) + " ], [ " + settingsMap.get(Save.RESOLUTION_HEIGHT_STRING) + " ]";
            this.resolution.getSelectionModel().select(res);

            this.difficulty.getSelectionModel().select(settingsMap.get(Save.DIFFICULTY_STRING));

            this.audio.setValue(Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)));
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
    void submitSaveSettings(final MouseEvent event) {

        // Faccio un parsing molto semplice perchè mi serve salvare solo la width e la height e non tutta la stringa.
        final List<String> resList = Arrays.asList(this.resolution.getSelectionModel().getSelectedItem().split("[ ]"));

        final boolean hasSaved = Save.saveSettings(Integer.parseInt(resList.get(1)), Integer.parseInt(resList.get(4)), this.difficulty.getSelectionModel().getSelectedItem(), this.audio.getValue());

        final Alert saveAlertSuccess = new Alert(Alert.AlertType.INFORMATION);
        final Alert saveAlertError = new Alert(Alert.AlertType.ERROR);

        if(hasSaved){
            saveAlertSuccess.setTitle("Salvataggio Impostazioni");
            saveAlertSuccess.setHeaderText("Salva");
            saveAlertSuccess.setContentText("Operazione eseguita con successo!");
            saveAlertSuccess.show();
        } else {
            saveAlertError.setTitle("Errore Salvataggio Impostazioni");
            saveAlertError.setHeaderText("Errore");
            saveAlertError.setContentText("Il salvataggio non è stato eseguito.");
            saveAlertError.show();
        }
    }
}
