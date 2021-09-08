package it.unibo.pensilina14.bullet.ballet.weapon;

public interface Bullet extends Item {
	
	enum BulletType{
		Classical, Toxic, Paralyzing
		}
	
	BulletType getBulletType();
	
	double getDamage();
	
	void inflictDamage();
}
