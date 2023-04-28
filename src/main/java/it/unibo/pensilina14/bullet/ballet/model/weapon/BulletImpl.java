package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList.BulletType;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class BulletImpl extends GameEntity implements Bullet {

  private static final double MS_TO_S = 5;
  private final String name;
  private double damage;
  private boolean fired;
  private final Items id;
  private final Effect effect;
  private final EntityList.BulletType bulletType;

  @Override
  public String getName() {
    return this.name;
  }

  public BulletImpl(
      final SpeedVector2D speedVector,
      final Environment gameEnvironment,
      final double mass,
      final Dimension2D dimension,
      final Items id,
      final Effect effect,
      final BulletType bulletType) {
    super(speedVector, gameEnvironment, mass, dimension);
    this.name = bulletType.description();
    this.damage = bulletType.damage();
    this.fired = false;
    this.id = id;
    this.effect = effect;
    this.bulletType = bulletType;
  }

  @Override
  public double getDamage() {
    return this.damage;
  }

  @Override
  public Effect getEffect() {
    return this.effect;
  }

  @Override
  public Items getItemId() {
    return this.id;
  }

  @Override
  public void setDamage(final double factor) {
    this.damage *= factor;
  }

  @Override
  public void fire() {
    this.fired = true;
  }

  @Override
  public boolean isShot() {
    return this.fired;
  }

  @Override
  public EntityList.BulletType getBulletType() {
    return this.bulletType;
  }

  @Override
  public void updateState() {
    this.getSpeedVector().get().noSpeedVectorSum(MS_TO_S, 0);
  }
}
