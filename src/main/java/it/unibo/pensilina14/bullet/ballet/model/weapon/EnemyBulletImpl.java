package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

/** Bullet shot by enemies, capable of moving both left or right. */
public class EnemyBulletImpl extends BulletImpl {

  private final double horizontalStep;

  public EnemyBulletImpl(
      final SpeedVector2D speedVector,
      final Environment gameEnvironment,
      final double mass,
      final Dimension2D dimension,
      final Items id,
      final Effect effect,
      final EntityList.BulletType bulletType,
      final double horizontalStep) {
    super(speedVector, gameEnvironment, mass, dimension, id, effect, bulletType);
    this.horizontalStep = horizontalStep;
  }

  @Override
  public void updateState() {
    this.getSpeedVector().get().noSpeedVectorSum(this.horizontalStep, 0);
  }
}
