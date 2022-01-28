package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public enum BulletImg {
	CLASSICAL("Normal", ""),
	TOXIC("Toxic", ""),
	SOPORIFIC("Soporofic", "");
	
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

