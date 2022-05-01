package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList.BulletType;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

import java.util.List;
import java.util.Optional;

public interface Weapon extends PhysicalObject {

  /*
   * @return the number of bullets remaining
   */
  int getAmmoLeft();

  /*
   * @return the maximum number of bullets of a weapon
   */
  int getTotalAmmo();

  /*
   * decrease by one the available bullets
   */
  void decreaseAmmo();

  /*
   * @return True if there is at least one bullet
   */
  boolean hasAmmo();

  /*
   * @return name of weapon
   */
  String getName();

  /*
   * @return True if recharging takes place correctly
   */
  void recharge(List<Bullet> charger);

  /*
   * @return max value of bullets in the charger
   */
  int getLimitBullets();

  /*
   * @return max value of chargers in the weapon
   */
  int getLimitChargers();

  /*
   * @return type of bullet in use
   */
  Optional<BulletType> getTypeOfBulletInUse();

  /*
   * @return type of weapon.
   */
  EntityList.Weapons getTypeOfWeapon();

  boolean isOn();

  void setOn();

  void setOff();

  void setPosition(MutablePosition2D newPos);

  void recharge();

  int getIndexCharger();
}
