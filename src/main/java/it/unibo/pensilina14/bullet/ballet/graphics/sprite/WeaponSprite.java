package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;
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
	private final Image weaponImg;
	
	private final ImageView weaponView;
    private int columns = 2;
    private int minY;
    private int minX;
    private int weaponWidth;
    private int weaponHeight;
    private final Weapon weapon;

    /*
    public WeaponSprite(final WeaponsImg img, final int x, final int y, final Environment gameEnv) throws  IOException{
    	if (img.equals(WeaponsImg.GUN)) {
    		this.weapon = new WeaponFactoryImpl().createGun(gameEnv);
    	}else if (img.equals(WeaponsImg.SHOTGUN)) {
    		this.weapon = new WeaponFactoryImpl().createShotGun(gameEnv);
    	}else if (img.equals(WeaponsImg.AUTO)) {
    		this.weapon = new WeaponFactoryImpl().createAuto(gameEnv);
    	}	
    }*/

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
    	getChildren().add(this.weaponView);
    }
}
    
    
    
 