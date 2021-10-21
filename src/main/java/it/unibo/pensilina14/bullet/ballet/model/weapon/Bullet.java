package it.unibo.pensilina14.bullet.ballet.model.weapon;

public interface Bullet extends Item {
	
	String getName();
	
	double getDamage();
	
	void setDamage(double factor);
	
	/*
	 * @return true if the bullet was shot
	 */
	boolean isShot();
	
	void fire();
	
}
