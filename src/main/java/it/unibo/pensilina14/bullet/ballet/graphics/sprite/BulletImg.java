package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public enum BulletImg {
	CLASSICAL("Normal", "res/assets/sprites/bullets/Classic.png"),
	TOXIC("Toxic", "res/assets/sprites/bullets/Classic.png"),
	SOPORIFIC("Soporofic", "res/assets/sprites/bullets/Classic.png");
	
	private final String name;
	private final String path;
	
	BulletImg(final String name, final String path){
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

