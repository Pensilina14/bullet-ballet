package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainEnemy;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite.WeaponsImg;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class MapScene extends AbstractScene implements GameView{

    private final Pane appPane = new Pane();
    private final Pane gamePane = new Pane();
    private final Pane uiPane = new Pane(); 

    private Map map = new Map();

    private ImageView backgroundView;

    private MainPlayer mainPlayer;

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
                try {
					render();
				} catch (IOException e) {
					e.printStackTrace();
				} 
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
    
    private void render() throws IOException {
    	final Environment world = this.gameState.getGameEnvironment();
    	final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();
    	
    	final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);

    	for (final Weapon x : world.getWeapons().get()) {
    		for (final WeaponsImg y : WeaponsImg.values()) {
    			if (x.getName().equals(y.getName())) {
    				new WeaponSprite(y, (int) x.getPosition().getX(), (int) x.getPosition().getY(), 
    						x);
    			}
    		}
    	}

    	for (final Enemy x : world.getEnemies().get()) {
    		new MainEnemy((int) x.getPosition().getX(), (int) x.getPosition().getY());
    	}

    	for (final PhysicalObject x : world.getObstacles().get()) {
    		final MutablePosition2D xPos = x.getPosition();
    		if (x instanceof StaticObstacle) {
    			final PhysicalObjectSprite staticObstacle = physObjSpriteFactory.generateStaticObstacleSprite((int) (xPos.getX() * platformSize), (int) (xPos.getY() * platformSize));
    		} 
    		if (x instanceof DynamicObstacle) {
    			final PhysicalObjectSprite dynamicObstacle = physObjSpriteFactory.generateDynamicObstacleSprite((int) (xPos.getX() * platformSize), (int) (xPos.getY() * platformSize));
    		}
    	}

    	for (final Item x : world.getItems().get()) {
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
