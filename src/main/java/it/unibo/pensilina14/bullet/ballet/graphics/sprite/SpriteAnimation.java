package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public class SpriteAnimation extends Sprite{

    public enum Direction{
        RIGHT(0),
        LEFT(1),
        UP(2),
        DOWN(3);

        int index;

        Direction(int index){
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }
    }

    private static final int SPRITE_FRAMES = 4; //TODO: Direction.values().length;
    private final static int SPRITE_STEP = 5;

    /*private final int totalFrames;
    private final int cols;
    private final int rows;

    private final int frameWidth;
    private final int frameHeight;*/

    protected int[][] spriteX = new int[SpriteAnimation.SPRITE_FRAMES][];
    protected int[][] spriteY = new int[SpriteAnimation.SPRITE_FRAMES][];

    private Direction currentDirection = Direction.RIGHT;
    private int currentSprite = 0;
    private int currentSpriteStep = 0;

    public SpriteAnimation(int width, int height) {
        super(width, height);
    }

    public void animate(Direction direction){
        if(direction == this.currentDirection){
            this.currentSpriteStep++;
            if(this.currentSpriteStep >= SpriteAnimation.SPRITE_STEP){
                this.currentSprite = ((this.currentSprite + 1) % this.spriteX[this.currentDirection.getIndex()].length);
            }
        } else {
            this.currentDirection = direction;
            this.currentSprite = 0;
            this.currentSpriteStep = 0;
        }

        updateSprite();
    }

    protected void updateSprite(){
        x = this.spriteX[this.currentDirection.getIndex()][this.currentSprite];
        y = this.spriteY[this.currentDirection.getIndex()][this.currentSprite];
    }
}
