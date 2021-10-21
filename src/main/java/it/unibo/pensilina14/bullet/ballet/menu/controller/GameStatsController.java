package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class GameStatsController {

    private final PageLoader loader = new PageLoaderImpl();
    @FXML
    private TableColumn<Statistics, String> playerName;
    @FXML
    private TableColumn<Statistics, Integer> points;
    @FXML
    private TableColumn<Statistics, Double> time;

    @FXML
    void goBackOnMouseClick(final MouseEvent event) throws IOException {
        loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }

}