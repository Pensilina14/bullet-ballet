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
	private final static int DEFAULT_VALUE = 0;
	private MutablePosition2D position;

    public WeaponSprite(final WeaponsImg img, final Weapon weapon, final double x, final double y) throws  IOException{
		final Image weaponImg = new Image(Files.newInputStream(Paths.get(img.getPath())));
    	final ImageView weaponView= new ImageView(weaponImg);
    	final double weaponWidth = weapon.getDimension().get().getWidth();
    	final double weaponHeight = weapon.getDimension().get().getHeight();
    	this.renderPosition(x, y);
    	weaponView.setViewport(new Rectangle2D(DEFAULT_VALUE, DEFAULT_VALUE, weaponWidth, weaponHeight));
    	this.getChildren().add(weaponView);
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
}
    
    
    
 