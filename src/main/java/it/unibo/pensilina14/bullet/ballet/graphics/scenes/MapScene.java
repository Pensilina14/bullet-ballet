package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.PlatformSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;
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
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
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
    	AppLogger.getAppLogger().debug("Inside MapScene setup() method."); 
        try {
			this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));
		} catch (IOException e) {
			e.printStackTrace();
			AppLogger.getAppLogger().error("Failed to load background image.");
		}
        this.backgroundView.fitWidthProperty().bind(this.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(this.appPane.heightProperty());

        this.appPane.getChildren().addAll(this.backgroundView, this.gamePane, this.uiPane);
        AppLogger.getAppLogger().debug("appPane children: " + this.appPane.getChildren().toString());
    }

    private void addCameraListenerToPlayer() {
        this.mainPlayer.translateXProperty().addListener((obs, oldPosition, newPosition) -> {
            final int playerPosition = newPosition.intValue();

            // this.map.getWidth() / 2 = metà della mappa.
            if (playerPosition > (this.map.getMapWidth() / 2) && 
            		playerPosition < (this.gameState.getEnvGenerator().getLevelWidth()) - (this.map.getMapWidth() / 2)) {
                this.gamePane.setLayoutX(-(playerPosition - (int) (this.map.getMapWidth() / 2)));
            }
        });
    }

    @Override
    public final void draw() {
            update();
            try {
                render();
			} catch (IOException e) {
				e.printStackTrace();
				AppLogger.getAppLogger().error("Couldn't load sprite images.");
			} 
    }

    private void update() {
    	AppLogger.getAppLogger().debug("Inside update() method, checks input keys.");
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
    	AppLogger.getAppLogger().debug("Inside render() method.");
    	final Environment world = this.gameState.getGameEnvironment();
    	final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();
    	
    	final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);

    	if (world.getPlayer().isPresent()) {
    		final MutablePosition2D playerPos = world.getPlayer().get().getPosition();
    		this.mainPlayer = new MainPlayer((int) (playerPos.getX() * platformSize), 
    				(int) (playerPos.getY() * platformSize));
    		this.gamePane.getChildren().add(this.mainPlayer);
    		AppLogger.getAppLogger().debug(String.format("Player %s rendered.", world.getPlayer().get()));
    	}
    	
    	for (final Platform x : world.getPlatforms().get()) {
    		final MutablePosition2D xPos = x.getPosition();
    		final PlatformSprite sprite = new PlatformSprite(this.map.getPlatformType(), 
    				(int) (xPos.getX() * platformSize), (int) (xPos.getY() * platformSize));
    		this.gamePane.getChildren().add(sprite);
    	}
    	AppLogger.getAppLogger().debug("Platforms rendered.");
    	
    	for (final Weapon x : world.getWeapons().get()) {
    		for (final WeaponsImg y : WeaponsImg.values()) {
    			if (x.getName().equals(y.getName())) {
    				this.gamePane.getChildren().add(new WeaponSprite(y, x, platformSize));
    				AppLogger.getAppLogger().debug("Weapon rendered");
    			}
    		}
    	}

    	for (final Enemy x : world.getEnemies().get()) {
    		this.gamePane.getChildren().add(new MainEnemy((int) (x.getPosition().getX() * platformSize), 
    				(int) (x.getPosition().getY() * platformSize)));
    		AppLogger.getAppLogger().debug("Enemy rendered");
    	}

    	for (final PhysicalObject x : world.getObstacles().get()) {
    		final MutablePosition2D xPos = x.getPosition();
    		if (x instanceof StaticObstacle) {
    			this.gamePane.getChildren().add(physObjSpriteFactory.generateStaticObstacleSprite((int) (xPos.getX() * platformSize), 
    					(int) (xPos.getY() * platformSize)));
    			AppLogger.getAppLogger().debug("Static Obstacle rendered");
    		} 
    		if (x instanceof DynamicObstacle) {
    			this.gamePane.getChildren().add(physObjSpriteFactory.generateDynamicObstacleSprite((int) (xPos.getX() * platformSize), 
    					(int) (xPos.getY() * platformSize)));
    			AppLogger.getAppLogger().debug("Dynamic Obstacle rendered");
    		}
    	}

    	for (final Item x : world.getItems().get()) {
    		for (final Images y : Images.values()) {
    			if (x.getItemId().toString().equals(y.getObjectName())) {
    				this.gamePane.getChildren().add(new PhysicalObjectSprite(y, (int) (x.getPosition().getX() * platformSize),
    						(int) (x.getPosition().getY() * platformSize), x, this));
    				AppLogger.getAppLogger().debug("Item rendered");
    			}
    		}
    	}
    	/*
    	for (final Bullet x : this.gameState.getGameEnvironment().getBullets().get().subList()) {
    		// TODO add algorithm for bullets
    	}
    	 */
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
