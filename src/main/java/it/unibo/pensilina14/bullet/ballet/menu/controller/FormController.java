package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.Game;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.AbstractScene;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class FormController {
	@FXML
    private TextField insertionForm;

	@FXML
    void goBackOnMouseClicked(final MouseEvent event) throws IOException {
		final PageLoader pageLoader = new PageLoaderImpl();
		pageLoader.goToSelectedPageOnInput(Frames.HOMEPAGE, event);
    }
	
    @FXML
    void submitOnMouseClicked(final MouseEvent event) {
    	System.out.println(this.insertionForm.getText());
    	if (this.insertionForm.getText().isBlank()) {
    		final Alert alert = new Alert(AlertType.ERROR
    				, "Blank form!");
    		alert.show();
    	} else {
    		newGame(event);
    	}
    }
    
    private void newGame(final MouseEvent event) {
    	new AudioClip(this.getClass().getResource("/menu_sound.mp4").toExternalForm()).play();
    	final Game game = new Game();
    	game.getSettings().setDifficulty(Difficulties.EASY);
    	game.getSettings().setResolution(Resolutions.FULLHD);
    	final AbstractScene gameScene = game.getView();
    	final Stage stage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        stage.setWidth(game.getSettings().getCurrentResolution().getWidth());
        stage.setHeight(game.getSettings().getCurrentResolution().getHeight());
        gameScene.setHeight(stage.getHeight());
        gameScene.setWidth(stage.getWidth());
        stage.setScene(gameScene);

        stage.show();
        game.start();
    }
}
