package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.PlatformSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainEnemy;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.input.Down;
import it.unibo.pensilina14.bullet.ballet.input.Left;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.MutablePair;

public class MapScene extends AbstractScene implements GameView{

    private final Pane appPane = new StackPane();
    private final Pane gamePane = new Pane();
    private final Pane uiPane = new Pane(); 
    
    private BackgroundMap map = new BackgroundMap();

    private ImageView backgroundView;

    private MutablePair<Optional<MainPlayer>, MutablePosition2D> mainPlayer;

    private final GameState gameState;
    private Optional<Controller> controller;
    private Map<MainEnemy, MutablePosition2D> enemySprites;
    private Map<PlatformSprite, MutablePosition2D> platformSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> itemSprites;
    
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
    	this.initScene();
        this.root.getChildren().add(this.appPane);
        AppLogger.getAppLogger().debug("Inside MapScene setup() method."); 
        try {
            this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));
        } catch (final IOException e) {
            e.printStackTrace();
			AppLogger.getAppLogger().error("Failed to load background image.");
		}
        this.backgroundView.fitWidthProperty().bind(this.root.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(this.root.heightProperty());
        
        this.mainPlayer = new MutablePair<>();
        this.enemySprites = new HashMap<>();
        this.platformSprites = new HashMap<>();
        this.itemSprites = new HashMap<>();

        this.appPane.getChildren().addAll(this.backgroundView, this.gamePane, this.uiPane);
        AppLogger.getAppLogger().debug("appPane children: " + this.appPane.getChildren().toString());
        try {
        	this.initialize();
        } catch (final IOException exc) {
        	exc.printStackTrace();
        	AppLogger.getAppLogger().error("IOException, probably caused by a problem with components sprite imgs.");
        }
        this.addCameraListenerToPlayer();
    }
    
    private void initialize() throws IOException {
    	final Environment world = this.gameState.getGameEnvironment();
    	final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();

    	//final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);
    	
    	if (world.getPlayer().isPresent()) {
    		final MutablePosition2D playerPos = world.getPlayer().get().getPosition();
    		this.mainPlayer.setLeft(Optional.of(new MainPlayer(playerPos.getX() * platformSize, 
    				playerPos.getY() * platformSize)));
    		this.mainPlayer.setRight(playerPos);
    		
        	this.gamePane.getChildren().add(this.mainPlayer.getLeft().get());
        	this.addCameraListenerToPlayer();
       		AppLogger.getAppLogger().debug(String.format("Player %s rendered.", world.getPlayer().get()));
    	}
    	
    	for (final Platform x : world.getPlatforms().get()) {
    		final MutablePosition2D xPos = x.getPosition();
    		final PlatformSprite newSprite = new PlatformSprite(this.map.getPlatformType()
    				, xPos.getX() * platformSize, xPos.getY() * platformSize);
    		this.platformSprites.put(newSprite, xPos);
    		this.gamePane.getChildren().add(newSprite);
    	}
    	AppLogger.getAppLogger().debug("Platforms rendered.");
    	
    	for (final Enemy x : world.getEnemies().get()) {
    		final MutablePosition2D xPos = x.getPosition();
    		final MainEnemy enemySprite = new MainEnemy(xPos.getX() * platformSize 
    				, xPos.getY() * platformSize);
    		this.enemySprites.put(enemySprite, xPos);
    		this.gamePane.getChildren().add(enemySprite);
    	}
		AppLogger.getAppLogger().debug("Enemies rendered");
    	/*
		for (final PhysicalObject x : world.getItems().get()) {
			AppLogger.getAppLogger().info(x.toString());
			final MutablePosition2D xPos = x.getPosition();
			final PhysicalObjectSprite itemSprite = new PhysicalObjectSpriteFactoryImpl(this
					, this.gameState.getGameEnvironment()).generateDamagingItemSprite((int) xPos.getX()
							* platformSize, (int) xPos.getY() * platformSize);
			this.itemSprites.put(itemSprite, xPos);
			this.gamePane.getChildren().add(itemSprite);
			
		}
    	*/
    	
    }

    private void addCameraListenerToPlayer() {
        this.mainPlayer.getLeft().get().translateXProperty().addListener((obs, oldPosition, newPosition) -> {
            final int playerPosition = newPosition.intValue();

            // this.map.getWidth() / 2 = metà della mappa.
            
            if (playerPosition > (this.map.getMapWidth() / 2) 
            		&&  playerPosition < (this.gameState.getEnvGenerator().getLevelWidth()) - (this.map.getMapWidth() / 2)) {
                this.root.setLayoutX(-(playerPosition - (int) (this.map.getMapWidth() / 2)));
            }
        });
    }

    @Override
    public final void draw() {
	    this.update();
	    try {
			this.render();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void update() {
    	AppLogger.getAppLogger().debug("Inside update() method, checks input keys.");
        if (this.keysPressed.contains(KeyCode.UP)) { 
        	AppLogger.getAppLogger().info("Key 'UP' pressed.");
        	this.mainPlayer.left.get().getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Up());
        }

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
        	AppLogger.getAppLogger().info("Key 'RIGHT' pressed.");
            this.mainPlayer.left.get().getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Right());
        }

        if (this.keysPressed.contains(KeyCode.DOWN)) { 
        	AppLogger.getAppLogger().info("Key 'DOWN' pressed.");
            this.controller.get().notifyCommand(new Down());
        }

        if (this.keysPressed.contains(KeyCode.LEFT)) {
        	AppLogger.getAppLogger().info("Key 'LEFT' pressed.");
            this.controller.get().notifyCommand(new Left());
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	AppLogger.getAppLogger().info("Key 'UP' released.");
        	this.mainPlayer.left.get().getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	AppLogger.getAppLogger().info("Key 'RIGHT' released.");
            this.mainPlayer.left.get().getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.RIGHT);
        }

        if (this.keysReleased.contains(KeyCode.DOWN)) { 
        	AppLogger.getAppLogger().info("Key 'DOWN' pressed.");
        	this.keysReleased.remove(KeyCode.DOWN);
        }

        if (this.keysReleased.contains(KeyCode.LEFT)) {
        	AppLogger.getAppLogger().info("Key 'LEFT' pressed.");
        	this.keysReleased.remove(KeyCode.LEFT); 
        }
    }
    
    

    private void render() throws IOException {
    	AppLogger.getAppLogger().debug("Inside render() method.");
    	//AppLogger.getAppLogger().debug("appPane: " + this.appPane.getChildren().toString());
    	//AppLogger.getAppLogger().debug("gamePane: " + this.gamePane.getChildren().toString());

    	final Environment world = this.gameState.getGameEnvironment();
    	final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();

    	//final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);

    	this.mainPlayer.left.get().renderPosition(this.mainPlayer.getRight().getX() * platformSize,
    			this.mainPlayer.getRight().getY() * platformSize);
    	AppLogger.getAppLogger().debug("Player sprite position updated");
    	
    	this.platformSprites.forEach((x, y) -> x.renderMovingPosition());
    	AppLogger.getAppLogger().debug("Platforms sprite position updated");
    	
    	this.enemySprites.forEach((x, y) -> x.renderMovingPosition());
		AppLogger.getAppLogger().debug("Enemies sprite position updated");

		this.itemSprites.forEach((x, y) -> x.renderMovingPosition());
//
////    	for (final Weapon x : world.getWeapons().get()) {
////    		for (final WeaponsImg y : WeaponsImg.values()) {
////    			if (x.getName().equals(y.getName())) {
////    				this.gamePane.getChildren().add(new WeaponSprite(y, x, platformSize));
////    				AppLogger.getAppLogger().debug("Weapon rendered");
////    			}
////    		}
////    	}
//
//    	for (final PhysicalObject x : world.getObstacles().get()) {
//    		final MutablePosition2D xPos = x.getPosition();
//    		if (x instanceof StaticObstacle) {
//    			this.gamePane.getChildren().add(physObjSpriteFactory.generateStaticObstacleSprite((int) (xPos.getX() * platformSize), 
//    					(int) (xPos.getY() * platformSize)));
//    			AppLogger.getAppLogger().debug("Static Obstacle rendered");
//    		} 
//    		if (x instanceof DynamicObstacle) {
//    			this.gamePane.getChildren().add(physObjSpriteFactory.generateDynamicObstacleSprite((int) (xPos.getX() * platformSize), 
//    					(int) (xPos.getY() * platformSize)));
//    			AppLogger.getAppLogger().debug("Dynamic Obstacle rendered");
//    		}
//    	}
//
//    	for (final Item x : world.getItems().get()) {
//    		for (final Images y : Images.values()) {
//    			if (x.getItemId().toString().equals(y.getObjectName())) {
//    				this.gamePane.getChildren().add(new PhysicalObjectSprite(y, (int) (x.getPosition().getX() * platformSize),
//    						(int) (x.getPosition().getY() * platformSize), x, this));
//    				AppLogger.getAppLogger().debug("Item rendered");
//    			}
//    		}
//    	}
    	/*
    	for (final Bullet x : this.gameState.getGameEnvironment().getBullets().get().subList()) {
    		// TODO add algorithm for bullets
    	}
    	 */
    	//DEBUG ONLY: throw new NullPointerException();

    }
    
    
    

    public final void setMap(final BackgroundMap.Maps map) {
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
	public final void setInputController(final Controller controller) {
		this.controller = Optional.of(controller);
	}
}
