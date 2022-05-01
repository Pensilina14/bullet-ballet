package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

  private final PageLoader loader = new PageLoaderImpl();

  @Override
  public final void start(final Stage primaryStage) throws Exception {
    AppLogger.getAppLogger().debug("Inside Launcher start() method, it's been called.");
    loader.loadFirstScene(primaryStage);
    Save.createGameDirectories();
  }

  public static void main(final String[] args) {
    AppLogger.getAppLogger().info("Application has started.");
    launch(args);
  }
}
