package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainEnemy extends Pane {

    private final Image enemyImage = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/enemies/enemy_idle.png")));

    private final static int ENEMY_SIZE = 40;

    public MainEnemy(int x, int y) throws IOException {

        ImageView enemyView = new ImageView(this.enemyImage);
        enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        int offsetX = 0;
        int offsetY = 0;
        int enemyViewWidth = 36;
        int enemyViewHeight = 51;
        enemyView.setViewport(new Rectangle2D(offsetX, offsetY, enemyViewWidth, enemyViewHeight));

        enemyView.setTranslateX(x);
        enemyView.setTranslateY(y);

        getChildren().addAll(enemyView);
        //MapScene.gamePane.getChildren().add(this);
    }

}
