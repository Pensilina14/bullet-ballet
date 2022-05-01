package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

import java.io.IOException;

public class PlayerSprite extends PhysicalObjectSprite {
  /** Duration of the sprite animation cycle. */
  private static final int ANIMATION_TIME = 200;

  private static final int COUNT = 3;
  private static final int COLUMNS = 16;
  private static final double H_FACTOR = 2.95;
  private static final double W_FACTOR = 2.675;

  private final SpriteAnimation animation;

  public PlayerSprite(
      final Images img, final MutablePosition2D position, final PhysicalObject physicalObject)
      throws IOException {
    super(img, position, physicalObject);
    final int playerViewHeight = (int) (this.getDimension().getHeight() * H_FACTOR);
    final int playerViewWidth = (int) (this.getDimension().getWidth() * W_FACTOR);
    this.getImageView().setViewport(new Rectangle2D(0, 0, playerViewWidth, playerViewHeight));
    this.animation =
        new SpriteAnimation(
            this.getImageView(),
            Duration.millis(ANIMATION_TIME),
            COUNT,
            COLUMNS,
            0,
            0,
            playerViewWidth,
            playerViewHeight);
  }

  public final SpriteAnimation getSpriteAnimation() {
    return this.animation;
  }

  @Override
  public final void renderMovingPosition() {
    extracted();
  }

  private void extracted() {}
}
