package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PageLoaderImpl implements PageLoader{

    //final Locale currentLocale = Locale.getDefault(); // Questo serve per ottenere il paese in cui ci troviamo, se vogliamo basare la lingua sul paese di provenienza.
    
    private static final String LANGUAGES_PATH = "languages.bulletBallet";
    private static final String DEFAULT_LANGUAGE = "en_UK";

    private final String language = (!Save.loadSettings().isEmpty()) ? Save.loadSettings().get(Save.DIFFICULTY_STRING) : PageLoaderImpl.DEFAULT_LANGUAGE; //TODO: al posto di Save.DIFFICULTY mettere language

    private final Locale locale = new Locale("it_IT"); //TODO: qui devo mettere language nel parametro
    private final ResourceBundle bundle = ResourceBundle.getBundle(PageLoaderImpl.LANGUAGES_PATH, locale);

    @Override
    public void goToSelectedPageOnInput(final Frames frame, final MouseEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource(frame.toString()), this.bundle);
        final Scene scene = new Scene(root);
        final Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void goToSelectedPageOnInput(final Frames frame) throws IOException { 
    	final Parent root = FXMLLoader.load(getClass().getResource(frame.toString()), this.bundle);
    	final Scene scene = new Scene(root);
    	final Stage window = new Stage();
    	window.setScene(scene);
    	window.show();
    }
    
    @Override
    public void loadFirstScene (final Stage primaryStage) throws IOException{
        final Parent root = FXMLLoader.load(getClass().getResource(Frames.HOMEPAGE.toString()), this.bundle);
        final Scene scene = new Scene(root);
        primaryStage.setTitle("bullet-ballet");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
