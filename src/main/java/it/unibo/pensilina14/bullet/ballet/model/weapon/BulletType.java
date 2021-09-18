package it.unibo.pensilina14.bullet.ballet.model.weapon;

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
	
	public double damage() {
		return this.damage;
	}
	
	public String description() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.description + "/t damage: " + this.damage;
	}
	
}
