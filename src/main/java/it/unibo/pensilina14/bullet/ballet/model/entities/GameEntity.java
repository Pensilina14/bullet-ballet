package it.unibo.pensilina14.bullet.ballet.model.entities;

import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class GameEntity implements PhysicalObject{

  private enum Bounds implements Predicate<ImmutablePair<GameEntity, MutablePosition2D>> {
    UP_CHECK(
        p ->
            p.left.getPosition().get().getY()
                    - p.right.getY()
                    - p.left.getDimension().get().getHeight()
                >= p.left.getGameEnvironment().get().getDimension().getHeight()),
    RIGHT_CHECK(
        p ->
            p.left.getPosition().get().getX()
                    + p.right.getX()
                    + p.left.getDimension().get().getWidth()
                <= p.left.getGameEnvironment().get().getDimension().getWidth()),
    LEFT_CHECK(
        p ->
            p.left.getPosition().get().getX()
                    - p.right.getX()
                    - p.left.getDimension().get().getWidth()
                >= 0),
    ;

    private final Predicate<ImmutablePair<GameEntity, MutablePosition2D>> predicate;

    Bounds(final Predicate<ImmutablePair<GameEntity, MutablePosition2D>> predicate) {
      this.predicate = predicate;
    }

    @Override
    public boolean test(final ImmutablePair<GameEntity, MutablePosition2D> t) {
      return predicate.test(t);
    }
  }

  private static final double MS_TO_S = 1;
  private final SpeedVector2D speedVector;
  private final Environment gameEnvironment;
  private final Dimension2D dimension;
  private final double mass;
  private boolean landed;

  public GameEntity(
      final SpeedVector2D speedVector,
      final Environment gameEnvironment,
      final double mass,
      final Dimension2D dimension) {
    super();
    this.speedVector = speedVector;
    this.gameEnvironment = gameEnvironment;
    this.dimension = dimension;
    this.mass = mass;
    this.landed = false;
  }

  @Override
  public Optional<MutablePosition2D> getPosition() {
    return this.speedVector.getPosition();
  }

  @Override
  public Optional<SpeedVector2D> getSpeedVector() {
    return Optional.ofNullable(this.speedVector);
  }

  @Override
  public void updateState() {
    this.speedVector.noSpeedVectorSum(-MS_TO_S, 0);
  }

  @Override
  public Optional<Dimension2D> getDimension() {
    return Optional.of(this.dimension);
  }

  @Override
  public Optional<Environment> getGameEnvironment() {
    return Optional.of(this.gameEnvironment);
  }

  @Override
  public boolean moveUp(final double y) {
    return move(Bounds.UP_CHECK, 0, -y);
  }

  @Override
  public void moveDown(final double y) {
    this.speedVector.vectorSum(0, y);
  }

  @Override
  public boolean moveRight(final double x) {
    return move(Bounds.RIGHT_CHECK, x, 0);
  }

  @Override
  public boolean moveLeft(final double x) {
    return move(Bounds.LEFT_CHECK, -x, 0);
  }

  private boolean move(
      final Predicate<ImmutablePair<GameEntity, MutablePosition2D>> predicate,
      final double x,
      final double y) {
    if (predicate.test(
        new ImmutablePair<GameEntity, MutablePosition2D>(this, new MutablePosition2Dimpl(x, y)))) {
      this.speedVector.vectorSum(x, y);
      return true;
    }
    return false;
  }

  @Override
  public double getMass() {
    return this.mass;
  }

  @Override
  public boolean hasLanded() {
    return this.landed;
  }

  @Override
  public void land() {
    this.landed = true;
  }

  @Override
  public void resetLanding() {
    this.landed = false;
  }
}
