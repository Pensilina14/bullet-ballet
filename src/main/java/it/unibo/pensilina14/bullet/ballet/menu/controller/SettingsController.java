package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    @FXML
    private Slider audio;
    @FXML
    private ComboBox<String> resolution;
    @FXML
    private ComboBox<String> difficulty;
    private final PageLoader loader = new PageLoaderImpl();
    
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
    
}
