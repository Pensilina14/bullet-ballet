package it.unibo.pensilina14.bullet.ballet.weapon;

public class BulletImpl implements Bullet {

	private final String name;
	private final double damage;
	
	public BulletImpl(BulletType bulletType) {
		this.name = bulletType.toString();
		this.damage = bulletType.getDamage();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDamage() {
		return damage;
	}

	@Override
	public Effect getEffect() {
		return null;
	}

}
