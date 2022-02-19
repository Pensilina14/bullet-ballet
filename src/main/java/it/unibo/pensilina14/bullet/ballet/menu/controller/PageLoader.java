package it.unibo.pensilina14.bullet.ballet.menu.controller;

import java.io.IOException;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public interface PageLoader { //TODO: volendo si possono rimuovere quelli con lingua e unirli a quelli con la width e la height
    
    /**
     * 
     * This will allow you to change scene after mouse click input.
     * 
     * @param frame
     * @param event
     * @throws IOException: fail or interrupted I/O operations.
     */
	Window goToSelectedPageOnInput(final Frames frame, final MouseEvent event, final Languages language) throws IOException;

    /**
     *
     * @param frame: the selected menu.
     * @param event: an event.
     * @param language: the language of the menu.
     * @param width: the width of the screen.
     * @param height: the height of the screen.
     * @throws IOException: fail or interrupted I/O operations.
     */
    Window goToSelectedPageOnInput(final Frames frame, final MouseEvent event, final Languages language, final int width, final int height) throws IOException;

    /**
     *
     * @param frame: the selected menu.
     * @param event: an event.
     * @throws IOException: fail or interrupted I/O operations.
     */
    Window goToSelectedPageOnInput(final Frames frame, final MouseEvent event) throws IOException;

    /**
     *
     * @param frame: the selected menu.
     * @throws IOException: fail or interrupted I/O operations.
     */
   	Window goToSelectedPageOnInput(final Frames frame) throws IOException;
    /**
     * 
     * This will load the software's first scene.
     * 
     * @param primaryStage: the stage.
     * @throws IOException: fail or interrupted I/O operations.
     */
    void loadFirstScene (final Stage primaryStage) throws IOException;

    /**
     *
     * @param primaryStage : the stage.
     * @param language : the language of the menu.
     * @throws IOException: fail or interrupted I/O operations.
     */
    void loadFirstScene (final Stage primaryStage, final Languages language) throws IOException;

    /**
     *
     * @param primaryStage : the stage.
     * @param language : the language of the scene.
     * @param width : the width of the screen.
     * @param height : the height of the screen.
     * @throws IOException: fail or interrupted I/O operations.
     */
    void loadFirstScene (final Stage primaryStage, final Languages language, final int width, final int height) throws IOException;
    
    void setWindow(final Window window);
    
    Window getWindow();
    
}
