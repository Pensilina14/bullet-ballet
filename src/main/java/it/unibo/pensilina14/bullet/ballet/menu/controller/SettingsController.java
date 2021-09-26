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

    @FXML
    void goBackOnMouseClick(final MouseEvent event) throws IOException {
        final PageLoader loader = new PageLoader();
        loader.goToSelectedPage(FRAME.HOMEPAGE, event);
    }

    @FXML
    void showDifficultiesOnMouseClick(final MouseEvent event) {
        final ObservableList<String> difficulties = FXCollections.observableArrayList("dumb", "easy", 
                "medium", "hard", "godlike");
        this.difficulty.setItems(difficulties);
    }

    @FXML
    void showResolutionsOnMouseClicked(final MouseEvent event) {
        final ObservableList<String> resolutions = FXCollections.observableArrayList("1920x1080",
                "1024x768");
        this.resolution.setItems(resolutions);
    }
    
}
