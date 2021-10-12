package it.unibo.pensilina14.bullet.ballet.model.weapon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

public class WeaponTest {

	private static final int DIMENSION = 1;
	private static final int POSITION = -5;
	private static final int MASS = 10;
	private static final int SPEED = 1;
	
	private final WeaponImpl weapon_first = new WeaponImpl("Classic", 10, 4, new Dimension2Dimpl(DIMENSION, DIMENSION),
			new GameEnvironment(), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED),
					ITEM_ID.WEAPON, null);
	
	@Test
	public void ammoLeftTest() {
		final WeaponImpl weapon_second = this.weapon_first;
		assertEquals(weapon_second.getAmmoLeft(), 10);
		for (int i=2; i < weapon_second.getLimitBullets(); i++) {
			weapon_second.decreaseAmmo();
			//System.out.println(weapon_second.getAmmoLeft());
		}
		assertTrue(weapon_second.hasAmmo());
	}
	
	@Test
	public void rechargeWeaponTest() {
		final WeaponImpl weapon_third = new WeaponImpl(EntityList.Weapons.GUN, new Dimension2Dimpl(DIMENSION, DIMENSION),
				new GameEnvironment(), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED),
				ITEM_ID.WEAPON, null);
		final ArrayList<Bullet> charger = new ArrayList<>();
		for(int i = 0; i < weapon_third.getLimitBullets(); i++) {
			charger.add(new BulletImpl(EntityList.BulletType.CLASSICAL));
		}
		weapon_third.recharge(charger);
		weapon_third.recharge(charger);

		assertEquals(weapon_third.getAmmoLeft(), 30);
	}
	
	@Test
	public void simulateGameAction() {
		final WeaponImpl weapon_fourth = new WeaponImpl(EntityList.Weapons.SHOTGUN, new Dimension2Dimpl(DIMENSION, DIMENSION),
				new GameEnvironment(), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED),
				ITEM_ID.WEAPON, null);
		final ArrayList<Bullet> charger_1 = new ArrayList<>();
		for(int i = 0; i < weapon_fourth.getLimitBullets(); i++) {
			charger_1.add(new BulletImpl(EntityList.BulletType.CLASSICAL));
		}
		final ArrayList<Bullet> charger_2 = new ArrayList<>();
		for(int i = 0; i < weapon_fourth.getLimitBullets(); i++) {
			charger_2.add(new BulletImpl(EntityList.BulletType.TOXIC));
		}
		
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.recharge(charger_1);
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.recharge(charger_2);
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();
		assertEquals(weapon_fourth.getTypeOfBulletInUse(), null);
		weapon_fourth.recharge(charger_1);
		assertEquals(weapon_fourth.getAmmoLeft(), 5);
		assertEquals(weapon_fourth.getTypeOfBulletInUse(), EntityList.BulletType.CLASSICAL);
		
	} 
	
	
}
