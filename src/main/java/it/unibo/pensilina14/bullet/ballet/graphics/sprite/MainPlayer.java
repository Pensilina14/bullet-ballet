package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class MainPlayer extends SpriteAnimation {

    /*private final static int WIDTH = 64;
    private final static int HEIGHT = 64;*/

    private int width;
    private int heigth;

    private final static int STEP = 3;

    //private final static String path = "res/assets/sprites/characters/player/";

    private final Player player;

    public MainPlayer(int width, int height, EntityList.Characters.Player playerType, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass) {
        //super(MainPlayer.WIDTH, MainPlayer.HEIGHT);

        super(width, height);

        this.width = width;
        this.heigth = height;

        this.player = new Player(playerType, dimension, vector, environment, mass);

        //TODO: Add sprites coords of spritesheet.
        spriteX[Direction.RIGHT.ordinal()] = new int[] {};
        spriteX[Direction.LEFT.ordinal()] = new int[] {};
        spriteX[Direction.UP.ordinal()] = new int[] {};
        spriteX[Direction.DOWN.ordinal()] = new int[] {};

        updateSprite();

    }

    public void move(Direction direction){
        //TODO: add movement and its relative animation by calling animate().
    }
}
