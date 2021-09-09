package it.unibo.pensilina14.bullet.ballet.weapon;

public class WeaponImpl implements Weapon {

	//set for each weapon the total number of available bullets
	private final static int MAX_AMMO = 10;
	private final String name;
	private int currentAmmo;
	private Bullet[] spareCharger = new BulletImpl[MAX_AMMO];
	
	public WeaponImpl(String nameOfWeapon) {
		this.name = nameOfWeapon;
		this.currentAmmo = MAX_AMMO;
		for(int i=0; i<MAX_AMMO; i++) {
			spareCharger[i] = new BulletImpl(BulletType.CLASSICAL);
		}
	}
	
	@Override
	public int getAmmoLeft() {
		return currentAmmo;
	}

	@Override
	public int getTotalAmmo() {
		return MAX_AMMO;
	}

	@Override
	public void decreaseAmmo() {
		if(hasAmmo() == true) {
			currentAmmo--;
			spareCharger[currentAmmo--] = null;
		}
	}

	@Override
	public boolean shoot() {
		return false;
	}

	@Override
	public boolean hasAmmo() {
		
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Effect getEffect() {
		return null;
	}

}
