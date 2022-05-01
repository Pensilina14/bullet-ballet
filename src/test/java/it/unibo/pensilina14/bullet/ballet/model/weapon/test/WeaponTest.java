package it.unibo.pensilina14.bullet.ballet.model.weapon.test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeaponTest {

  private static final int POSITION = -5;
  private static final int SPEED = 1;
  private static final int BULLET_SPEED = 4;
  private final Environment gameEnv = new GameEnvironment();
  private final SpeedVector2D speedVector =
      new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED);
  final WeaponFactory factory = new WeaponFactoryImpl();

  @Test
  public void ammoLeftTest() {
    final Weapon Gun = factory.createGun(this.gameEnv, this.speedVector);
    Gun.setOn();
    assertEquals(Gun.getAmmoLeft(), 10);
    for (int i = 2; i < Gun.getLimitBullets(); i++) {
      Gun.decreaseAmmo();
      // System.out.println(Gun.getAmmoLeft());
    }
    assertTrue(Gun.hasAmmo());
  }

  @Test
  public void rechargeWeaponTest() {
    final Weapon simplyGun = factory.createGun(this.gameEnv, this.speedVector);
    simplyGun.setOn();
    final ArrayList<Bullet> charger = new ArrayList<>();
    for (int i = 0; i < simplyGun.getLimitBullets(); i++) {
      charger.add(
          new BulletFactoryImpl()
              .createClassicBullet(
                  this.gameEnv,
                  new SpeedVector2DImpl(
                      new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
    }
    simplyGun.recharge(charger);
    simplyGun.recharge(charger);

    assertEquals(simplyGun.getAmmoLeft(), 30);
  }

  @Test
  public void simulateGameAction() {
    final Weapon shothun = factory.createShotGun(this.gameEnv, this.speedVector);
    shothun.setOn();
    final ArrayList<Bullet> charger_1 = new ArrayList<>();
    for (int i = 0; i < shothun.getLimitBullets(); i++) {
      charger_1.add(
          new BulletFactoryImpl()
              .createClassicBullet(
                  this.gameEnv,
                  new SpeedVector2DImpl(
                      new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
    }
    final ArrayList<Bullet> charger_2 = new ArrayList<>();
    for (int i = 0; i < shothun.getLimitBullets(); i++) {
      charger_2.add(
          new BulletFactoryImpl()
              .createPoisonBullet(
                  this.gameEnv,
                  new SpeedVector2DImpl(
                      new MutablePosition2Dimpl(POSITION, POSITION), BULLET_SPEED)));
    }
    assertEquals(shothun.getAmmoLeft(), 5);
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.recharge(charger_1);
    assertEquals(shothun.getAmmoLeft(), 6);
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.recharge(charger_2);
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    shothun.decreaseAmmo();
    assertEquals(shothun.getTypeOfBulletInUse(), Optional.empty());
    shothun.recharge(charger_1);
    assertEquals(shothun.getAmmoLeft(), 5);
    assertEquals(shothun.getTypeOfBulletInUse(), Optional.of(EntityList.BulletType.CLASSICAL));
  }

  @Test
  public void rechargeTest() {
    final Weapon weapon = factory.createShotGun(gameEnv, speedVector);
    weapon.setOn();
    for (int i = 1; i < weapon.getLimitBullets(); i++) {
      weapon.decreaseAmmo();
    }
    assertEquals(weapon.getAmmoLeft(), 1);
    weapon.recharge();
    assertEquals(weapon.getAmmoLeft(), 6);
    assertEquals(weapon.getIndexCharger(), 0);
    weapon.decreaseAmmo();
    weapon.decreaseAmmo();
    assertEquals(weapon.getAmmoLeft(), 4);
    assertEquals(weapon.getIndexCharger(), 1);
  }
}
