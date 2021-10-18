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
		
		GUN("Gun", "res/assets/sprites/weapons/...png"),
		SHOTGUN("shotgun", "res/assets/sprites/weapons/...png"),
		AUTO("Auto", "res/assets/sprites/weapons/...png\"");
		
		String path;
		String name;
		
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
	final Image weaponImg;
	
	final ImageView weaponView;
    int columns = 2;
    int minY;
    int minX;
    int weaponWidth;
    int weaponHeight;
    Weapon weapon;
    
    public WeaponSprite(final WeaponsImg img, final int x, final int y, final Environment gameEnv) throws  IOException{
    	if (img.equals(WeaponsImg.GUN)) {
    		this.weapon = new WeaponFactoryImpl().createGun(gameEnv);
    	}else if (img.equals(WeaponsImg.SHOTGUN)) {
    		this.weapon = new WeaponFactoryImpl().createShotGun(gameEnv);
    	}else if (img.equals(WeaponsImg.AUTO)) {
    		this.weapon = new WeaponFactoryImpl().createAuto(gameEnv);
    	}
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
    	MapScene.weapons.add(this);
    	MapScene.gamePane.getChildren().add(this);
    }
    
    
    
 /*    
    public Point2D weaponVelocity = new Point2D(0,0);
    
    public static final int WEAPON_SIZE = 20;
    public static final int MASS = 10;
    
    final Weapon weapon;
    
    public WeaponSprite() {
    	this.weapon = new WeaponImpl(EntityList.Weapons.GUN,  new Dimension2Dimpl(WEAPON_SIZE, WEAPON_SIZE),
    			new GameEnvironment(), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(this.offsetX, this.offsetY), 0),
				ITEM_ID.WEAPON, null);
    	this.weaponView.setFitHeight(WeaponSprite.WEAPON_SIZE);
    	this.weaponView.setFitWidth(WeaponSprite.WEAPON_SIZE);
    	this.weaponView.setViewport(new Rectangle2D(this.offsetX, this.offsetY, this.weaponViewWidth, this.weaponViewHeight));
    	
    	getChildren().addAll(this.weaponView);
    }
   */
}
