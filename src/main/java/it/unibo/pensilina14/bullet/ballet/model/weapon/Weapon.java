package it.unibo.pensilina14.bullet.ballet.model.weapon;

import java.util.ArrayList;
import java.util.List;

public interface Weapon extends Item {
	
	/*
	 * @return the number of bullets remaining
	 */
	int getAmmoLeft();
	
	/*
	 * @return the maximum number of bullets of a weapon
	 */
	int getTotalAmmo();
	
	/*
	 * decrease by one the available bullets
	 */
	void decreaseAmmo();
		
	/*
	 * @return True if there is at least one bullet
	 */
	boolean hasAmmo();
	
	/*
	 * @return name of weapon
	 */
	String getName();
	
	/*
	 * @return True if recharging takes place correctly
	 */
	void recharge(List<Bullet> charger);
}
