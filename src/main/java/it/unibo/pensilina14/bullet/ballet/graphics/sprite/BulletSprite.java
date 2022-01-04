package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BulletSprite extends Pane {
	

	private final Image bulletImg;
	private final ImageView bulletView;
	private final int offsetX;
	private final int offsetY;
	private final int bulletViewWidth;
    private final int bulletViewHeight;
	private MutablePosition2D position;
	
	private final static int BULLET_SIZE = 20;
	
	public BulletSprite(final double x, final double y) throws IOException {
		this.bulletImg = new Image(Files.newInputStream(Paths.get("res/assets/sprites/bullets/Classic.png")));
		this.bulletView = new ImageView(bulletImg);
		this.renderPosition(x, y);
		this.bulletView.setFitHeight(BULLET_SIZE);
		this.bulletView.setFitWidth(BULLET_SIZE);
		this.offsetX = 0;
        this.offsetY = -0;
        this.bulletViewWidth = 24;
        this.bulletViewHeight = 12;
		bulletView.setViewport(new Rectangle2D(offsetX, offsetY, bulletViewWidth, bulletViewHeight));
		getChildren().add(bulletView);
	}
	
	public void renderPosition(final double x, final double y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.position = new MutablePosition2Dimpl(x, y);
	}
	
	public final void renderMovingPosition() {
    	final double netx = this.position.getX() + 3.0;
    	this.position.setPosition(netx, this.position.getY());
    	this.setTranslateX(this.position.getX());
    	//System.out.println("Enemy View: \t" + this.position.getX());
    }
}
