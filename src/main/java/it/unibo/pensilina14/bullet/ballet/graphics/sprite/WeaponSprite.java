package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WeaponSprite extends Pane {
	
	public enum WeaponsImg {
		
		GUN("Gun", "res/assets/sprites/weapons/gun.jpeg"),
		SHOTGUN("shotgun", "res/assets/sprites/weapons/shotgun.jpeg"),
		AUTO("Auto", "res/assets/sprites/weapons/auto.jpeg");
		
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

    public WeaponSprite(final WeaponsImg img, final Weapon weapon, final int platformSize) throws  IOException{
		final Image weaponImg = new Image(Files.newInputStream(Paths.get(img.getPath())));
    	final ImageView weaponView= new ImageView(weaponImg);
    	final double weaponWidth = weapon.getDimension().getWidth();
    	final double weaponHeight = weapon.getDimension().getHeight();
    	this.setTranslateX(weapon.getPosition().getX() * platformSize);
    	this.setTranslateY(weapon.getPosition().getY() * platformSize);
    	weaponView.setViewport(new Rectangle2D(DEFAULT_VALUE, DEFAULT_VALUE, weaponWidth, weaponHeight));
    	getChildren().add(weaponView);
    }
}
    
    
    
 