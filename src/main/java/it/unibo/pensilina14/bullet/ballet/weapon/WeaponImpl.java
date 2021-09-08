package it.unibo.pensilina14.bullet.ballet.weapon;

public class WeaponImpl implements Weapon {

	//set for each weapon the total number of available bullets
	private static int MAX_AMMO;
	private final String name;
	private int currentAmmo;
	private Bullet[] spareCharger;
	
	public WeaponImpl(int max_ammo, String nameOfWeapon) {
		this.MAX_AMMO = max_ammo;
		this.name = nameOfWeapon;
		this.currentAmmo = this.MAX_AMMO;
	}
	
	@Override
	public int getAmmoLeft() {
		return this.currentAmmo;
	}

	@Override
	public int getTotalAmmo() {
		return this.MAX_AMMO;
	}

	@Override
	public void decreaseAmmo() {
		this.currentAmmo--;
	}

	@Override
	public boolean shoot() {
		return false;
	}

	@Override
	public boolean hasAmmo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Effect getEffect() {
		
	}

}
