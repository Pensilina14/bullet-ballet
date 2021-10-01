package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import com.sun.tools.javac.Main;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.SpriteAnimation;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapScene extends AbstractScene{

    private final Map map = new Map();

    private Image background;

    //private Node backgroundNode;

    //private final MainPlayer mainPlayer = new MainPlayer(EntityList.Characters.Player.PLAYER1);

    BackgroundImage bi = new BackgroundImage(new Image(Map.Maps.HALLOWEEN.getPath()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    // Background creation
    Background bg = new Background(bi);

    public MapScene() throws IOException {
        super();

        this.background = new Image(Files.newInputStream(Paths.get(Map.Maps.HALLOWEEN.getPath())));

        this.map.generate();

    }

    @Override
    public void draw() {

        keysPressed.clear();

        new AnimationTimer(){

            public void handle(long currentNanoTime){

                gfx.setFill(Color.BLACK);
                gfx.fillRect(0,0,width, height);
                //gfx.drawImage(background, 0, 0);
                // Dopo il background

                root.setBackground(bg);

                //TODO: aggiungere il personaggio principale ed i nemici.

                //TODO: HashMap platforms
                map.getPlatforms().forEach( (s, c) -> {
                    gfx.drawImage(s.getSpriteImage(), c.getKey(), c.getValue()); //TODO: non mi serve sempre settare la sprite, perchè è sempra la stessa (basta una volta)
                });

                if(keysPressed.contains(KeyCode.RIGHT)){
                    //TODO: move screen pane()
                    int offset = 100; //TODO: will be added based upon player movement
                    root.setLayoutX(-(offset - map.getMapWidth()));
                    //root.getChildren().addAll(backgroundNode, root, root);

                    // player movement and animation
                    //mainPlayer.move(SpriteAnimation.Direction.RIGHT);
                }
            }
        }.start();


    }
}
