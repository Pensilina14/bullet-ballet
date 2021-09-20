package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public class SpriteAnimation extends Sprite{

    public enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    /*private final int totalFrames;
    private final int cols;
    private final int rows;

    private final int frameWidth;
    private final int frameHeight;*/

    private Direction currentDirection = Direction.RIGHT;
    private int currentSprite = 0;

    public SpriteAnimation(int width, int height) {
        super(width, height);
    }

    public void move(Direction direction){
        if(direction == this.currentDirection){
            // TODO: animate sprite in the same direction
        } else {
            this.currentDirection = direction;
            this.currentSprite = 0;
        }

        updateSprite();
    }

    private void updateSprite(){
        // TODO:
    }
}
