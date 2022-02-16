package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import it.unibo.pensilina14.bullet.ballet.save.Save;
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
    	new AudioClip(Objects.requireNonNull(this.getClass().getResource("/menu_sound.mp4")).toExternalForm()).play();
        loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.setCells();
        final ObservableList<Statistics> list = FXCollections.observableArrayList();

        final Map<String, String> statsMap = Save.loadGameStatistics();

        if(!statsMap.isEmpty()){
            for(final var s : statsMap.keySet()){
                list.add(new Statistics(s, Double.parseDouble(statsMap.get(s)), -1)); //TODO: il tempo ho messo -1.0 perchè non l'abbiamo, se non lo facciamo è da togliere.
            }
        }

        this.tableView.setItems(list);
    }
    
    private void setCells() {
        this.playerName.setCellValueFactory(new PropertyValueFactory<Statistics, String>("playerName"));
        this.points.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("points"));
        this.time.setCellValueFactory(new PropertyValueFactory<Statistics, Double>("time"));
    }

}