package it.unibo.pensilina14.bullet.ballet.menu.controller;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GameStatsController implements Initializable {

  private final PageLoader loader = new PageLoaderImpl();
  @FXML private TableView<Statistics> tableView;
  @FXML private TableColumn<Statistics, String> playerName;
  @FXML private TableColumn<Statistics, Integer> points;
  @FXML private TableColumn<Statistics, String> date;

  @FXML
  void goBackOnMouseClick(final MouseEvent event) throws IOException {
    final SoundsFactory soundsFactory = new SoundsFactoryImpl();
    soundsFactory.createSound(Sounds.MENU_SOUND).play();
    loader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    this.setCells();
    final ObservableList<Statistics> list = FXCollections.observableArrayList();

    final Map<String, MutablePair<String, String>> statsMap = Save.loadGameStatistics();

    if (!statsMap.isEmpty()) {
      for (final var s : statsMap.keySet()) {
        list.add(
            new Statistics(
                s,
                Double.parseDouble(String.valueOf(statsMap.get(s).getLeft())),
                statsMap.get(s).getRight()));
      }
    }

    this.tableView.setItems(list);
  }

  private void setCells() {
    this.playerName.setCellValueFactory(new PropertyValueFactory<Statistics, String>("playerName"));
    this.points.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("points"));
    this.date.setCellValueFactory(new PropertyValueFactory<Statistics, String>("date"));
  }
}
