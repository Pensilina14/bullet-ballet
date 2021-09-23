package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public class SpriteAnimation extends Sprite{

    public enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }

    private static final int SPRITE_FRAMES = 3;
    private final static int SPRITE_STEP = 5;

    /*private final int totalFrames;
    private final int cols;
    private final int rows;

    private final int frameWidth;
    private final int frameHeight;*/

    private int[][] spriteX = new int[SpriteAnimation.SPRITE_FRAMES][];
    private int[][] spriteY = new int[SpriteAnimation.SPRITE_FRAMES][];

    private Direction currentDirection = Direction.RIGHT;
    private int currentSprite = 0;
    private int currentSpriteStep = 0;

    public SpriteAnimation(int width, int height) {
        super(width, height);
    }

    public void move(Direction direction){
        if(direction == this.currentDirection){
            // TODO: animate sprite in the same direction
            this.currentSpriteStep++;
            if(this.currentSpriteStep >= SpriteAnimation.SPRITE_STEP){
                //this.currentSprite = ((this.currentSprite + 1) % this.spriteX[this.currentDirection].length);
            }
        } else {
            this.currentDirection = direction;
            this.currentSprite = 0;
            this.currentSpriteStep = 0;
        }

        updateSprite();
    }

    private void updateSprite(){
        // TODO:
        // = this.spriteX[this.currentDirection][this.currentSprite];
        //y = this.spriteY[this.currentDirection][this.currentSprite];
    }
}
