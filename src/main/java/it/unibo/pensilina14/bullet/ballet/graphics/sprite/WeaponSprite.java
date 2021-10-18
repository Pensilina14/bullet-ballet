package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WeaponSprite extends Pane {
	
	private enum WeaponsImg {

		GUN("res/assets/sprites/weapons/...png"),
		SHOTGUN("res/assets/sprites/weapons/...png"),
		AUTO("res/assets/sprites/weapons/...png\"");

		private final String path;

		WeaponsImg(final String path){
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}
	}
	/// Add image for weapon
	private final Image weaponImg;
	
	private final ImageView weaponView;
    private int columns = 2;
    private int minY;
    private int minX;
    private int weaponWidth;
    private int weaponHeight;
    private final Weapon weapon;

    public WeaponSprite(final WeaponsImg img, final int x, final int y, final Weapon weapon) throws  IOException{
    	this.weapon = weapon;
    	this.weaponImg = new Image(Files.newInputStream(Paths.get(img.getPath())));
    	this.weaponView = new ImageView(this.weaponImg);
    	this.minX = 0;
    	this.minY = 0;
    	this.weaponHeight = 25;
    	this.weaponWidth = 50;
    	this.setTranslateX(x);
    	this.setTranslateY(y);
    	this.weaponView.setViewport(new Rectangle2D(this.minX, this.minY, this.weaponWidth, this.weaponHeight));

    	getChildren().addAll(this);
    }
}
