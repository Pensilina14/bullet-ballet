package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public interface PageLoader {
    
    /**
     * 
     * This will allow you to change scene after mouse click input.
     * 
     * @param frame
     * @param event
     * @throws IOException
     */
	void goToSelectedPageOnInput(final Frames frame, final MouseEvent event, final Languages language) throws IOException;
	
    void goToSelectedPageOnInput(final Frames frame, final MouseEvent event) throws IOException;
    
    void goToSelectedPageOnInput(final Frames frame) throws IOException;
    /**
     * 
     * This will load the software's first scene.
     * 
     * @param primaryStage
     * @throws IOException
     */
    void loadFirstScene (final Stage primaryStage) throws IOException;

    void loadFirstScene (final Stage primaryStage, Languages language) throws IOException;
    
}
