package it.unibo.pensilina14.bullet.ballet.model.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class WeaponImpl extends DynamicPickupItem implements Weapon {

	//set for each weapon the total number of available bullets
	private final int limitBullets;
	private final int limitChargers;
	private final String name;
	private int currentAmmo;
	private List<Bullet> spareCharger;
	private List<List<Bullet>> bandolier;
	
	public WeaponImpl(final String nameOfWeapon, final int limitBullets,
			final int limitChargers, final Dimension2D dimension, final Environment gameEnvironment, 
            final double mass, final SpeedVector2D vector, final ITEM_ID id, 
            final Effect effect) {
		super(dimension, gameEnvironment, mass, vector, id, effect);
		
		this.name = nameOfWeapon;
		this.limitBullets = limitBullets;
		this.limitChargers = limitChargers;
		this.currentAmmo = limitBullets;
		this.initializeWeapon();

		//for(int i=0; i<limitBullets; i++) {
		//	spareCharger.add(new BulletImpl(BulletType.CLASSICAL));
		//}
	}
	
	public WeaponImpl(final EntityList.Weapons weaponType, final Dimension2D dimension, final Environment gameEnvironment, 
            final double mass, final SpeedVector2D vector, final ITEM_ID id, 
            final Effect effect) {
		super(dimension, gameEnvironment, mass, vector, id, effect);
		this.name = weaponType.getName();
		this.limitBullets = weaponType.getLimBullets();
		this.limitChargers = weaponType.getLimChargers();
		this.initializeWeapon();
	}	
	
	private void initializeWeapon() {
		((ArrayList<Bullet>) this.spareCharger).ensureCapacity(this.limitBullets);
		this.spareCharger.stream().map(i -> new BulletImpl(BulletType.CLASSICAL));
		((ArrayList<List<Bullet>>) this.bandolier).ensureCapacity(this.limitChargers);
		this.bandolier.add(this.spareCharger);
		this.bandolier.add(new ArrayList<>());
		this.bandolier.add(new ArrayList<>());
	}
	
	@Override
	public int getAmmoLeft() {
		return this.bandolier.stream().map(x -> x.size()).reduce((x, y) -> x+y).get();
	}
	
	@Override
	public int getTotalAmmo() {
		return this.limitBullets*this.limitChargers;
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
    	this.currentAmmo = limitBullets;
    	this.spareCharger = this.bandolier.stream().filter(x -> x.size()>0).findFirst().get();
    }

}
