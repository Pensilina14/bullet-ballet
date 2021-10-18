package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapScene extends AbstractScene{

    private final Pane appPane = new Pane();
    private final Pane gamePane = new Pane();
    private final Pane uiPane = new Pane(); 

    private Map map = new Map();

    private ImageView backgroundView;

    private MainPlayer mainPlayer;
    private final List<Platform> platforms = new ArrayList<>();
    private final List<WeaponSprite> weapons = new ArrayList<>();

    private final GameState gameState;
    private Optional<Controller> controller; 

    public MapScene(final GameState gameState) {
        this.gameState = gameState;
        this.controller = Optional.empty();
        this.appPane.setMaxWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMaxHeight(AbstractScene.SCENE_HEIGHT);
    }

    public MapScene(final GameState gameState, final Controller ctrlr) {
        this.gameState = gameState;
        this.controller = Optional.of(ctrlr);
        this.appPane.setMaxWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMaxHeight(AbstractScene.SCENE_HEIGHT);
    }

    public final void setup() {
        try {
			this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.backgroundView.fitWidthProperty().bind(this.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(this.appPane.heightProperty());

        this.appPane.getChildren().addAll(this.backgroundView, this.gamePane, this.uiPane);
    }

    private void addCameraListenerToPlayer() {
        this.mainPlayer.translateXProperty().addListener((obs, oldPosition, newPosition) -> {
            final int playerPosition = newPosition.intValue();

            // this.map.getWidth() / 2 = metà della mappa.
            if (playerPosition > (this.map.getMapWidth() / 2) && playerPosition < (this.gameState.getEnvGenerator().getLevelWidth()) - (this.map.getMapWidth() / 2)) {
                this.gamePane.setLayoutX(-(playerPosition - (int) (this.map.getMapWidth() / 2)));
            }
        });
    }

    @Override
    public final void draw() {
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                update();
                render();
            }
        };
        timer.start();
    }

    private void update() {
        if (this.keysPressed.contains(KeyCode.UP)) { 
        	this.mainPlayer.getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Up());
        }

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
            this.mainPlayer.getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Right());
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	this.mainPlayer.getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	this.mainPlayer.getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.RIGHT);
        }
    }
    
    private void render() {
    	/* Wait branch feature-graphics merge with main
    	for (final Weapon x : this.gs.getGameEnvironment().getWeapons().get()) {
    		for (WeaponsImg y : WeaponsImg.values()) {
    			new WeaponSprite(y, (int) x.getPosition().getX(),(int) x.getPosition().getY(), 
    					this.gs.getGameEnvironment());
    		}
    	}
    	*/
    	
    	for (final Enemy x : this.gameState.getGameEnvironment().getEnemies().get()) {
    		// Wait branch feature-graphics merge with main
    		//new MainEnemy((int) x.getPosition().getX(), x.getPosition().getY());
    	}
    	
    	for (final PhysicalObject x : this.gameState.getGameEnvironment().getObstacles().get()) {
    		// TODO add implementation Obstacle view
    	}
    	
    	for (final Item x : this.gameState.getGameEnvironment().getItems().get()) {
    		// TODO add implementation Item view
    	}
    }

    public final void setMap(final Map.Maps map) {
        this.map.setMap(map);
    }

    public final Pane getAppPane() {
    	return this.appPane;
    }

    public final Pane getGamePane() {
    	return this.gamePane;
    }

    public final Pane getUiPane() {
    	return this.uiPane;
    }

	@Override
	public void setInputController(final Controller controller) {
		this.controller = Optional.of(controller);
	}
}
