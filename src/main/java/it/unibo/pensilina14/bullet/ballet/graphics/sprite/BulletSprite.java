package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BulletSprite extends Pane {
	
	/*
	private final Image bulletImg;
	private final ImageView bulletView;
	private final int minX;
	private final int minY
	*/
	private final static int DEFAULT_VALUE = 0;
	
	public BulletSprite(final BulletImg img, final Bullet bullet) throws IOException {
		final Image bulletImg = new Image(Files.newInputStream(Paths.get(img.getPath())));
		final ImageView bulletView = new ImageView(bulletImg);
		this.setTranslateX(bullet.getPosition().get().getX());
		this.setTranslateY(bullet.getPosition().get().getX());
		bulletView.setViewport(new Rectangle2D(DEFAULT_VALUE, DEFAULT_VALUE, 
				bullet.getDimension().get().getWidth(), bullet.getDimension().get().getHeight()));
		getChildren().add(bulletView);
	}
}
