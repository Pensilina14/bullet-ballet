package it.unibo.pensilina14.bullet.ballet.model.weapon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

public class WeaponTest {

	private static final int POSITION = -5;
    private static final int SPEED = 1;
	private static final int BULLET_SPEED = 4;
	private final Environment gameEnv = new GameEnvironment();
	private final SpeedVector2D speedVector = new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED);
	
	@Test
	public void ammoLeftTest() {
		final WeaponImpl weapon_second = new WeaponFactoryImpl().createGun(this.gameEnv, this.speedVector);
		weapon_second.setOn();
		assertEquals(weapon_second.getAmmoLeft(), 10);
		for (int i=2; i < weapon_second.getLimitBullets(); i++) {
			weapon_second.decreaseAmmo();
			//System.out.println(weapon_second.getAmmoLeft());
		}
		assertTrue(weapon_second.hasAmmo());
	}
	
	@Test
	public void rechargeWeaponTest() {
		final WeaponImpl weapon_third = new WeaponFactoryImpl().createGun(this.gameEnv, this.speedVector);
		weapon_third.setOn();
		final ArrayList<Bullet> charger = new ArrayList<>();
		for(int i = 0; i < weapon_third.getLimitBullets(); i++) {
			charger.add(new BulletFactoryImpl().createClassicBullet(this.gameEnv, new SpeedVector2DImpl
					(new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
		}
		weapon_third.recharge(charger);
		weapon_third.recharge(charger);

		assertEquals(weapon_third.getAmmoLeft(), 30);
	}
	       
	@Test
	public void simulateGameAction() {
		final WeaponImpl weapon_fourth = new  WeaponFactoryImpl().createShotGun(this.gameEnv, this.speedVector);
		weapon_fourth.setOn();
		final ArrayList<Bullet> charger_1 = new ArrayList<>();
		for(int i = 0; i < weapon_fourth.getLimitBullets(); i++) {
			charger_1.add(new BulletFactoryImpl().createClassicBullet(this.gameEnv, new SpeedVector2DImpl
					(new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
		}
		final ArrayList<Bullet> charger_2 = new ArrayList<>();
		for(int i = 0; i < weapon_fourth.getLimitBullets(); i++) {
			charger_2.add(new BulletFactoryImpl().createPoisonBullet(this.gameEnv, new SpeedVector2DImpl
					(new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
		}
		assertEquals(weapon_fourth.getAmmoLeft(), 5);
		weapon_fourth.decreaseAmmo();										// 4
		weapon_fourth.decreaseAmmo();										// 3
		weapon_fourth.decreaseAmmo();										// 2
		weapon_fourth.decreaseAmmo();										// 1
		weapon_fourth.recharge(charger_1);									// 6?
		assertEquals(weapon_fourth.getAmmoLeft(), 6);
		weapon_fourth.decreaseAmmo();										// 5
		weapon_fourth.decreaseAmmo();										// 4
		weapon_fourth.decreaseAmmo();										// 3		
		weapon_fourth.decreaseAmmo();										// 2
		weapon_fourth.decreaseAmmo();										// 1	
		weapon_fourth.recharge(charger_2);									// 6?	
		weapon_fourth.decreaseAmmo();										// 5
		weapon_fourth.decreaseAmmo();										// 4
		weapon_fourth.decreaseAmmo();										// 3
		weapon_fourth.decreaseAmmo();										// 2
		weapon_fourth.decreaseAmmo();										// 1
		weapon_fourth.decreaseAmmo();										// 0
		assertEquals(weapon_fourth.getTypeOfBulletInUse(), null);			// 
		weapon_fourth.recharge(charger_1);
		assertEquals(weapon_fourth.getAmmoLeft(), 5);
		assertEquals(weapon_fourth.getTypeOfBulletInUse(), EntityList.BulletType.CLASSICAL);
		
	} 
	
	@Test
	public void rechargeTest() {
		final WeaponImpl weapon_fourth = new  WeaponFactoryImpl().createShotGun(this.gameEnv, this.speedVector);
		weapon_fourth.setOn();
		for (int i = 1; i < weapon_fourth.getLimitBullets(); i++) {
			weapon_fourth.decreaseAmmo();
		}
		assertEquals(weapon_fourth.getAmmoLeft(), 1);
		weapon_fourth.recharge();
		weapon_fourth.decreaseAmmo();
		weapon_fourth.decreaseAmmo();

		//weapon_fourth.recharge();

		assertEquals(weapon_fourth.getAmmoLeft(), 4);
		assertEquals(weapon_fourth.getIndexCharger(), 1);

	}
	
}
