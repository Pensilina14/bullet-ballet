package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainEnemy extends Pane {

    private Image enemyImage = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/enemies/enemy_idle.png")));
    private ImageView enemyView = new ImageView(this.enemyImage);

    private final int offsetX = 0;
    private final int offsetY = 0;
    private final int enemyViewWidth = 36;
    private final int enemyViewHeight = 51;

    private final FactoryCharactersImpl characters = new FactoryCharactersImpl();
    //private final Enemy enemy;

    private final static int ENEMY_SIZE = 40;

    public MainEnemy(int x, int y) throws IOException {

        //this.enemy = this.characters.createRandomEnemy(); //TODO: da rimuovere perchè dovrà essere messo nel model.

        this.enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        this.enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        this.enemyView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.enemyViewWidth, this.enemyViewHeight));

        this.enemyView.setTranslateX(x);
        this.enemyView.setTranslateY(y);

        getChildren().addAll(this.enemyView);
        //MapScene.gamePane.getChildren().add(this);
    }

    /*public Enemy getEnemy(){
        return this.enemy;
    }*/
}
