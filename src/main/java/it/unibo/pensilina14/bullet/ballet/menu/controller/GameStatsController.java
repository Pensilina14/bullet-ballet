package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class GameStatsController implements Initializable{

    private final PageLoader loader = new PageLoaderImpl();
    @FXML
    private TableView<Statistics> tableView;
    @FXML
    private TableColumn<Statistics, String> playerName;
    @FXML
    private TableColumn<Statistics, Integer> points;
    @FXML
    private TableColumn<Statistics, Double> time;

    @FXML
    void goBackOnMouseClick(final MouseEvent event) throws IOException {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
        loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.setCells();
        final ObservableList<Statistics> list = FXCollections.observableArrayList();
        list.add(new Statistics("Alessandro", 10, 0.5));
        tableView.setItems(list);
    }
    
    private void setCells() {
        this.playerName.setCellValueFactory(new PropertyValueFactory<Statistics, String>("playerName"));
        this.points.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("points"));
        this.time.setCellValueFactory(new PropertyValueFactory<Statistics, Double>("time"));
    }

}