package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainEnemy extends Pane {

    private final Image enemyImage;
    private final ImageView enemyView;

    private final int offsetX;
    private final int offsetY;
    private final int enemyViewWidth;
    private final int enemyViewHeight;

    private final static int ENEMY_SIZE = 40;

    public MainEnemy(double x, double y) throws IOException {

        this.enemyImage = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/enemies/enemy_idle.png")));
        this.enemyView = new ImageView(this.enemyImage);
        this.enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        this.enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        this.offsetX = 0;
        this.offsetY = 0;
        this.enemyViewWidth = 36;
        this.enemyViewHeight = 51;

        this.enemyView.setViewport(new Rectangle2D(offsetX, offsetY, enemyViewWidth, enemyViewHeight));

        this.enemyView.setTranslateX(x);
        this.enemyView.setTranslateY(y);

        getChildren().addAll(this.enemyView);
        //MapScene.gamePane.getChildren().add(this);
    }

    public MainEnemy(Image enemyImage, double x, double y, int offsetX, int offsetY, int enemyViewWidth, int enemyViewHeight){
        this.enemyImage = enemyImage;
        this.enemyView = new ImageView(this.enemyImage);

        this.enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        this.enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.enemyViewWidth = enemyViewWidth;
        this.enemyViewHeight = enemyViewHeight;

        this.enemyView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.enemyViewWidth, this.enemyViewHeight));

        this.enemyView.setTranslateX(x);
        this.enemyView.setTranslateY(y);

        getChildren().addAll(this.enemyView);
    }

}
