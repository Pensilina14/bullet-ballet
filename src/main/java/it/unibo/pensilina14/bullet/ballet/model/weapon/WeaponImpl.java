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
	
	///it keeps the index of ammo left in the current charger
	private int currentAmmo;
	
	/// it keeps the index of charger in the bandolier
	private int indexCharger = 0;
	
	/// This is the first charger in the bandolier
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
		this.currentAmmo = limitBullets;
		this.initializeWeapon();
	}	
	
	private void initializeWeapon() {
		this.spareCharger = new ArrayList<>();
		//((ArrayList<Bullet>) this.spareCharger).ensureCapacity(this.limitBullets);
		for(int i = 0; i < this.limitBullets; i++) {
			this.spareCharger.add(new BulletImpl(EntityList.BulletType.CLASSICAL));
		}
		//this.spareCharger.stream().map(i -> new BulletImpl(EntityList.BulletType.CLASSICAL));
		this.bandolier = new ArrayList<>();
		//((ArrayList<List<Bullet>>) this.bandolier).ensureCapacity(this.limitChargers);
		this.bandolier.add(this.spareCharger);
		for(int y = 1; y < this.limitChargers; y++) {
			this.bandolier.add(new ArrayList<>());
		}
	}
	
	@Override
	public int getAmmoLeft() {
		int sum = 0;
		for(final List<Bullet> x : this.bandolier) {
			sum += x.size();
		}
		return sum;
		//return this.bandolier.stream().map(x -> x.size()).distinct().reduce((x, y) -> x+y).get();
	}
	
	@Override
	public int getTotalAmmo() {
		return this.limitBullets*this.limitChargers;
	}
	
	public EntityList.BulletType getTypeOfBulletInUse(){
		if(!this.hasAmmo()) {
			this.switchCharger();
			if(!this.hasAmmo()) {
				System.out.println("ERROR: finished bullets");
				return null;
			}
		}
		/// Index of current ammo in the list charger(start from 0 to limitBullets)
		int indexAmmo = this.currentAmmo;
		indexAmmo--;

		if(this.bandolier.get(this.indexCharger).get(indexAmmo).getName() == 
				new BulletImpl(EntityList.BulletType.CLASSICAL).getName()) {
			 return EntityList.BulletType.CLASSICAL;
		}else if(this.bandolier.get(this.indexCharger).get(indexAmmo).getName() == 
				new BulletImpl(EntityList.BulletType.SOPORIFIC).getName()){
			return EntityList.BulletType.SOPORIFIC;
		}
		else if(this.bandolier.get(this.indexCharger).get(indexAmmo).getName() == 
				new BulletImpl(EntityList.BulletType.TOXIC).getName()){
			return EntityList.BulletType.TOXIC;
		}
		return null;
	}
	
	public int getLimitBullets() {
		return this.limitBullets;
	}
	
	public int getLimitChargers() {
		return this.limitChargers;
	}
	
	@Override
	public void decreaseAmmo() {
		if(this.hasAmmo()) {
			this.currentAmmo--;
		}else {
			this.switchCharger();
			System.out.println(this.bandolier);
			this.currentAmmo--;
		}
		this.bandolier.get(this.indexCharger).remove(this.currentAmmo);
		//this.bandolier.stream().filter(x -> x.size()>0).findFirst().get().remove(this.currentAmmo--);
	}
	
	@Override
	public boolean hasAmmo() {
		if(this.bandolier.get(this.indexCharger).size() == 0){
			this.switchCharger();
			this.currentAmmo = this.bandolier.get(this.indexCharger).size();
			//this.currentAmmo = this.spareCharger.size();
		}
		return !this.bandolier.get(this.indexCharger).isEmpty();
	}
	
	public boolean hasAmmo(final List<Bullet> charger) {
		return !charger.isEmpty();
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public Effect getEffect() {
		return null;
	}
	
	@Override
	public void recharge(final ArrayList<Bullet> charger) {
		this.switchCharger().addAll(charger);
		if(this.indexCharger == 0) {
			this.indexCharger=this.limitChargers;
		}
		this.indexCharger--;
		this.currentAmmo = this.bandolier.get(this.indexCharger).size();
		//this.bandolier.stream().filter(x -> x.isEmpty()).distinct().findFirst().get().addAll(charger);
	}

    @Override
    public ITEM_ID getItemId() {
    	return ITEM_ID.WEAPON;
    }
    
    private List<Bullet> switchCharger() {
    	if(this.indexCharger==this.limitChargers-1) {
    		this.indexCharger = 0;
    	}
    	else{
    		this.indexCharger++;
    	}
    	this.currentAmmo = this.bandolier.get(this.indexCharger).size();
    	return this.bandolier.get(this.indexCharger);
    }
    
    @Override
    public String toString() {
    	return "name: " + this.name + "\t num of charger: ";
    }

}
