package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imgView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int imgViewWidth;
    private final int imgViewHeight;

    public SpriteAnimation(ImageView imgView, Duration duration, int count, int columns, int offsetX, int offsetY, int imgViewWidth, int imgViewHeight){
        this.imgView = imgView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.imgViewWidth = imgViewWidth;
        this.imgViewHeight = imgViewHeight;

        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);

        this.imgView.setViewport(new Rectangle2D(offsetX, offsetY, imgViewWidth, imgViewHeight));
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(this.count * frac), this.count - 1);
        final int x = (index % this.columns) * this.imgViewWidth + this.offsetX;
        final int y = (index / this.columns) * this.imgViewHeight + this.offsetY;

        this.imgView.setViewport(new Rectangle2D(x, y, this.imgViewWidth, this.imgViewHeight));
    }
}
