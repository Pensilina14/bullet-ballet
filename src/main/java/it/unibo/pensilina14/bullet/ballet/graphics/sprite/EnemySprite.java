package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Enemy sprite that also shows a gun overlay. */
public final class EnemySprite extends PhysicalObjectSprite {

  private final ImageView gunView;

  public EnemySprite(final MutablePosition2D position, final PhysicalObject enemy) {
    super(Images.ENEMY, position, enemy);

    this.gunView =
        new ImageView(
            new Image(String.valueOf(getClass().getClassLoader().getResource(Images.GUN.getFileName()))));

    this.gunView.setSmooth(false);
    this.gunView.setPreserveRatio(true);

    final double enemyW = this.getDimension().getWidth();
    final double enemyH = this.getDimension().getHeight();

    this.gunView.setFitWidth(enemyW * 0.65);
    this.gunView.setFitHeight(enemyH * 0.35);

    // Place the gun roughly at the enemy's hands.
    this.gunView.setTranslateX(enemyW * 0.1);
    this.gunView.setTranslateY(enemyH * 0.55);
    this.gunView.setMouseTransparent(true);

    this.getChildren().add(this.gunView);
  }
}
