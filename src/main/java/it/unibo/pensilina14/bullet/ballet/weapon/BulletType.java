package it.unibo.pensilina14.bullet.ballet.weapon;

public enum BulletType {

	CLASSICAL("Normal bullet", 10),
	TOXIC("Toxic bullet", 10),
	SOPORIFIC("Soporific bullet", 10);
	
	private final String description;
	private final double damage;
	
	BulletType(String name, double damage) {
		this.description = name;
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}
