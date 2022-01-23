package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;

public class MainEnemy extends Pane {

    private final Image enemyImage;
    private final ImageView enemyView;
    private MutablePosition2D position;

    private final int offsetX;
    private final int offsetY;
    private final int enemyViewWidth;
    private final int enemyViewHeight;

    private final static int ENEMY_SIZE = 40;


    public MainEnemy(final double x, final double y) throws IOException {

        this.enemyImage = new Image(Files.newInputStream(Paths.get("res/assets/sprites/characters/enemies/enemy_idle.png")));
        this.enemyView = new ImageView(this.enemyImage);
        this.enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        this.enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        this.offsetX = 0;
        this.offsetY = -0;
        this.enemyViewWidth = 36;
        this.enemyViewHeight = 51;

        this.enemyView.setViewport(new Rectangle2D(offsetX, offsetY, enemyViewWidth, enemyViewHeight));

        this.renderPosition(x, y);

        this.getChildren().add(this.enemyView);
        //MapScene.gamePane.getChildren().add(this);
    }
    
    public MainEnemy(final Image enemyImage, final double x, final double y, final int offsetX, final int offsetY, 
    		final int enemyViewWidth, final int enemyViewHeight) {
        this.enemyImage = enemyImage;
        this.enemyView = new ImageView(this.enemyImage);
        this.enemyView.setFitWidth(MainEnemy.ENEMY_SIZE);
        this.enemyView.setFitHeight(MainEnemy.ENEMY_SIZE);

        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.enemyViewWidth = enemyViewWidth;
        this.enemyViewHeight = enemyViewHeight;

        this.enemyView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.enemyViewWidth, this.enemyViewHeight));
        this.renderPosition(x, y);

        getChildren().addAll(this.enemyView);
    }
    
    public void renderPosition(final double x, final double y) {
    	this.enemyView.setTranslateX(x);
    	this.enemyView.setTranslateY(y);
    	this.position = new MutablePosition2Dimpl(x, y);
    }
    
    public final void renderMovingPosition() {
    	final double netx = this.position.getX() - 1.0;
    	this.position.setPosition(netx, this.position.getY());
    	this.enemyView.setTranslateX(this.position.getX());
    }
}
