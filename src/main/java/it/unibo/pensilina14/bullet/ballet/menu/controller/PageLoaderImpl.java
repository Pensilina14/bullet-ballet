package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PageLoaderImpl implements PageLoader{

    @Override
    public void goToSelectedPageOnInput(final Frames frame, final MouseEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource(frame.toString())); 
        final Scene scene = new Scene(root);
        final Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void goToSelectedPageOnInput(final Frames frame) throws IOException { 
    	final Parent root = FXMLLoader.load(getClass().getResource(frame.toString()));
    	final Scene scene = new Scene(root);
    	final Stage window = new Stage();
    	window.setScene(scene);
    	window.show();
    }
    
    @Override
    public void loadFirstScene (final Stage primaryStage) throws IOException{
        final Parent root = FXMLLoader.load(getClass().getResource(Frames.HOMEPAGE.toString()));
        final Scene scene = new Scene(root);
        primaryStage.setTitle("bullet-ballet");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
