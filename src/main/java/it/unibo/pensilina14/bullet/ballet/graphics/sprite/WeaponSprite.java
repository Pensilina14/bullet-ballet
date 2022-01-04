package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WeaponSprite extends Pane {
	
	public enum WeaponsImg {
		
		GUN("Gun", "res/assets/sprites/weapons/gun.png"),
		SHOTGUN("Shotgun", "res/assets/sprites/weapons/shotgun.png"),
		AUTO("Auto", "res/assets/sprites/weapons/auto.png");
		
		private final String path;
		private final String name;
		
		WeaponsImg(final String name,final String path){
			this.name = name;
			this.path = path;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getPath() {
			return this.path;
		}
	}
	/// Add image for weapon
	private final Image weaponImage;
	private final ImageView weaponView;
	private final int offsetX;
	private final int offsetY;
	private final WeaponsImg typeOfImage;
	private MutablePosition2D position;
	private final static int WEAPON_SIZE = 35;
	private final static int WEAPON_VIEW_WIDTH = 30;
	private final static int WEAPON_VIEW_HEIGHT = 42;



    public WeaponSprite(final WeaponsImg img, final double x, final double y) throws  IOException{
		this.weaponImage = new Image(Files.newInputStream(Paths.get(img.getPath())));
    	this.weaponView = new ImageView(this.weaponImage);
    	this.typeOfImage = img;
    	this.renderPosition(x, y);
    	this.offsetX = 0;
    	this.offsetY = 0;
    	this.weaponView.setFitWidth(WeaponSprite.WEAPON_SIZE);
    	this.weaponView.setFitHeight(WeaponSprite.WEAPON_SIZE);
    	this.weaponView.setViewport(new Rectangle2D(this.offsetX, this.offsetY
    			, WeaponSprite.WEAPON_VIEW_WIDTH, WeaponSprite.WEAPON_VIEW_HEIGHT));
    	this.getChildren().add(this.weaponView);
    }
    
    public void renderPosition(final double x, final double y) {
    	this.setTranslateX(x);
    	this.setTranslateY(y);
    	this.position = new MutablePosition2Dimpl(x, y);
    }
    
    public final void renderMovingPosition() {
    	final double netx = this.position.getX() - 1.0;
    	this.position.setPosition(netx, this.position.getY());
    	this.setTranslateX(this.position.getX());
    }
    
    public WeaponsImg getTypeOfImage() {
    	return this.typeOfImage;
    }
}
    
    
    
 