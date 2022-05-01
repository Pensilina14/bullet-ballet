package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeaponImpl extends GameEntity implements Weapon {

  private static final double DEAFAULT_DAMAGE_FACTOR = 1.0;
  private static final double DAMAGE_FACTOR_SHOTGUN = 1.5;
  private static final double DAMAGE_FACTOR_AUTO = 1.5;

  // These values are used to set the correct position of weapon
  private static final int DISTANCE_X = 15;
  private static final int DISTANCE_Y = 6;

  // set for each weapon the total number of available bullets
  private final int limitBullets;
  private final int limitChargers;
  private final String name;
  private final BulletFactory bulletFactory;
  private boolean mode;

  /// it keeps the index of ammo left in the current charger
  private int currentAmmo;

  /// it keeps the index of charger in the bandolier
  private int indexCharger;

  private List<List<Bullet>> bandolier;
  private final EntityList.Weapons weaponType;
  private double damageFactor;

  public WeaponImpl(
      final EntityList.Weapons weaponType,
      final Dimension2D dimension,
      final Environment gameEnvironment,
      final double mass,
      final SpeedVector2D vector) {
    super(vector, gameEnvironment, mass, dimension);
    this.name = weaponType.getName();
    this.limitBullets = weaponType.getLimBullets();
    this.limitChargers = weaponType.getLimChargers();
    this.currentAmmo = limitBullets;
    this.weaponType = weaponType;
    this.mode = false;
    this.indexCharger = 0;
    this.bulletFactory = new BulletFactoryImpl();
    this.initializeWeapon();
  }

  private void initializeWeapon() {
    switch (this.weaponType) {
      case GUN:
        this.damageFactor = DEAFAULT_DAMAGE_FACTOR;
        break;
      case SHOTGUN:
        this.damageFactor = DAMAGE_FACTOR_SHOTGUN;
        break;
      case AUTO:
        this.damageFactor = DAMAGE_FACTOR_AUTO;
        break;
      default:
        this.damageFactor = DEAFAULT_DAMAGE_FACTOR;
        break;
    }
    final List<Bullet> charger = new ArrayList<>();
    // ((ArrayList<Bullet>) this.spareCharger).ensureCapacity(this.limitBullets);
    for (int i = 0; i < this.limitBullets; i++) {
      charger.add(
          bulletFactory.createClassicBullet(
              this.getGameEnvironment().get(), this.getSpeedVector().get()));
    }
    charger.stream().forEach(x -> x.setDamage(this.damageFactor));
    // this.spareCharger.stream().map(i -> new BulletImpl(EntityList.BulletType.CLASSICAL));
    this.bandolier = new ArrayList<>();
    // ((ArrayList<List<Bullet>>) this.bandolier).ensureCapacity(this.limitChargers);
    this.bandolier.add(charger);
    for (int y = 1; y < this.limitChargers; y++) {
      this.bandolier.add(new ArrayList<>());
    }
  }

  @Override
  public int getAmmoLeft() {
    int sum = 0;
    for (final List<Bullet> x : this.bandolier) {
      sum += x.size();
    }
    return sum;
    // return this.bandolier.stream().map(x -> x.size()).distinct().reduce((x, y) -> x+y).get();
  }

  @Override
  public int getTotalAmmo() {
    return this.limitBullets * this.limitChargers;
  }

  @Override
  public Optional<EntityList.BulletType> getTypeOfBulletInUse() {
    if (!this.hasAmmo()) {
      this.switchCharger();
      if (!this.hasAmmo()) {
        System.out.println("ERROR: finished bullets");
        return Optional.empty();
      }
    }
    /// Index of current ammo in the list charger(start from 0 to limitBullets)
    int indexAmmo = this.currentAmmo;
    indexAmmo--;

    return Optional.of(this.bandolier.get(this.indexCharger).get(indexAmmo).getBulletType());
  }

  @Override
  public int getLimitBullets() {
    return this.limitBullets;
  }

  @Override
  public int getLimitChargers() {
    return this.limitChargers;
  }

  @Override
  public void decreaseAmmo() {
    if (isOn()) {
      if (this.hasAmmo()) {
        this.currentAmmo--;
      } else {
        this.switchCharger();
        // System.out.println(this.bandolier);
        this.currentAmmo--;
      }
      // this.bandolier.get(this.indexCharger).get(this.currentAmmo).fire();
      this.bandolier.get(this.indexCharger).remove(this.currentAmmo);
    }
  }

  @Override
  public boolean hasAmmo() {
    if (this.bandolier.get(this.indexCharger).isEmpty()) {
      this.switchCharger();
      this.currentAmmo = this.bandolier.get(this.indexCharger).size();
      // this.currentAmmo = this.spareCharger.size();
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
  public void recharge(final List<Bullet> charger) {
    charger.stream().forEach(x -> x.setDamage(this.damageFactor));
    this.switchCharger().addAll(charger);
    if (this.indexCharger == 0) {
      this.indexCharger = this.limitChargers;
    }
    this.indexCharger--;
    this.currentAmmo = this.bandolier.get(this.indexCharger).size();
    // this.bandolier.stream().filter(x ->
    // x.isEmpty()).distinct().findFirst().get().addAll(charger);
  }

  @Override
  public void recharge() {
    final List<Bullet> charger = new ArrayList<>(this.limitBullets);
    for (int i = 0; i < this.limitBullets; i++) {
      charger.add(
          bulletFactory.createClassicBullet(
              this.getGameEnvironment().get(), this.getSpeedVector().get()));
    }
    charger.stream().forEach(x -> x.setDamage(this.damageFactor));
    this.switchCharger().addAll(charger);
    if (this.indexCharger == 0) {
      this.indexCharger = this.limitChargers;
    }
    this.indexCharger--;
    this.currentAmmo = this.bandolier.get(this.indexCharger).size();
  }

  private List<Bullet> switchCharger() {
    if (this.indexCharger == this.limitChargers - 1) {
      this.indexCharger = 0;
    } else {
      this.indexCharger++;
    }
    this.currentAmmo = this.bandolier.get(this.indexCharger).size();
    return this.bandolier.get(this.indexCharger);
  }

  @Override
  public EntityList.Weapons getTypeOfWeapon() {
    return this.weaponType;
  }

  @Override
  public boolean isOn() {
    return this.mode;
  }

  @Override
  public void setOn() {
    this.mode = true;
  }

  @Override
  public void setOff() {
    this.mode = false;
  }

  @Override
  public void setPosition(final MutablePosition2D newPos) {
    this.getPosition().get().setPosition(newPos.getX() + DISTANCE_X, newPos.getY() + DISTANCE_Y);
  }

  @Override
  public int getIndexCharger() {
    return this.indexCharger;
  }
}
