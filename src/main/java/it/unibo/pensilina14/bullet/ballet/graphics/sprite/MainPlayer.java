package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPlayer extends SpriteAnimation {

    /*private final static int WIDTH = 64;
    private final static int HEIGHT = 64;*/

    private final static int MAIN_PLAYER_WIDTH = 36; //TODO: modify values based on Sprite Width
    private final static int MAIN_PLAYER_HEIGHT = 36; //TODO: modify values based on Sprite Width

    private final static int STEP = 3;

    private static final String MAIN_PLAYER_IMAGE = "res/assets/sprites/characters/enemies/EnemyCharacterTest.png";

    private final static Map map = new Map();

    private Image mainPlayerImage; //TODO: final

    private final FactoryCharactersImpl characters = new FactoryCharactersImpl();

    public MainPlayer(EntityList.Characters.Player playerType) {

        super(MainPlayer.MAIN_PLAYER_WIDTH, MainPlayer.MAIN_PLAYER_HEIGHT);

        //super(width, height);

        /*this.MainPlayerWidth = width;
        this.MainPlayerHeight = height;*/

        Player player = characters.createPlayer(playerType);

        try {
            this.mainPlayerImage = new Image(Files.newInputStream(Paths.get(MAIN_PLAYER_IMAGE)));
        } catch(Exception e){
            e.printStackTrace();
        }


        //TODO: Add sprites coords of spritesheet.
        spriteXCoords[Direction.RIGHT.ordinal()] = new int[] {};
        spriteXCoords[Direction.LEFT.ordinal()] = new int[] {};
        spriteXCoords[Direction.UP.ordinal()] = new int[] {};
        spriteXCoords[Direction.DOWN.ordinal()] = new int[] {};

        updateSprite();

    }

    public void move(Direction direction){
        int newX = x;
        if(direction == Direction.LEFT){
            newX -= Math.min(MainPlayer.STEP, x);
        }
        else if(direction == Direction.RIGHT){
            newX += Math.min(MainPlayer.STEP, map.getMapWidth() - MainPlayer.MAIN_PLAYER_WIDTH - x);
        }
        move(newX, y);
        animate(direction);

        //TODO: add up and down movements.
    }
}
