package it.unibo.pensilina14.bullet.ballet.weapon;

import java.util.ArrayList;
import java.util.List;
import it.unibo.pensilina14.bullet.ballet.game.effects.Effect;

public class WeaponImpl implements Weapon {

	//set for each weapon the total number of available bullets
	private final static int MAX_AMMO = 10;
	private final static int MAX_CAPACITY = 3;
	private final String name;
	private int currentAmmo;
	private List<Bullet> spareCharger;
	private List<List<Bullet>> bandolier;
	
	public WeaponImpl(String nameOfWeapon) {
		this.name = nameOfWeapon;
		this.currentAmmo = MAX_AMMO;
		((ArrayList<Bullet>) this.spareCharger).ensureCapacity(MAX_AMMO);
		this.spareCharger.stream().map(i -> new BulletImpl(BulletType.CLASSICAL));
		((ArrayList<List<Bullet>>) this.bandolier).ensureCapacity(MAX_CAPACITY);
		this.bandolier.add(spareCharger);
		this.bandolier.add(new ArrayList<>());
		this.bandolier.add(new ArrayList<>());
		//for(int i=0; i<MAX_AMMO; i++) {
		//	spareCharger.add(new BulletImpl(BulletType.CLASSICAL));
		//}
	}
	
	@Override
	public int getAmmoLeft() {
		return this.bandolier.stream().map(x -> x.size()).reduce((x, y) -> x+y).get();
	}
	
	@Override
	public int getTotalAmmo() {
		return MAX_AMMO;
	}
	
	@Override
	public void decreaseAmmo() {
		if(hasAmmo()) {
			this.currentAmmo--;
			this.spareCharger.remove(this.currentAmmo--);
		}
		//this.bandolier.stream().filter(x -> x.size()>0).findFirst().get().remove(this.currentAmmo--);
	}
	
	@Override
	public boolean hasAmmo() {
		if(this.currentAmmo == 0){
			switchCharger();
		}
		return !this.spareCharger.isEmpty();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Effect getEffect() {
		return null;
	}
	
	@Override
	public void recharge(final ArrayList<Bullet> charger) {
		this.bandolier.stream().filter(x -> x.isEmpty()).findFirst().get().addAll(charger);
	}

    @Override
    public ITEM_ID getItemId() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private void switchCharger() {
    	this.currentAmmo = MAX_AMMO;
    	this.spareCharger = this.bandolier.stream().filter(x -> x.size()>0).findFirst().get();
    }

}
