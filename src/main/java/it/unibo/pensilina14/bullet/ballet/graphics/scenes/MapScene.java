package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.PlatformSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.BulletSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainEnemy;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.WeaponSprite.WeaponsImg;
import it.unibo.pensilina14.bullet.ballet.input.Down;
import it.unibo.pensilina14.bullet.ballet.input.Esc;
import it.unibo.pensilina14.bullet.ballet.input.Left;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Space;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Items;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.MutablePair;

public class MapScene extends AbstractScene implements GameView{

    private final Pane appPane = new StackPane();
    private final Pane gamePane = new Pane();
    private final Pane uiPane = new StackPane(); 
    
    private final BackgroundMap map = new BackgroundMap();

    private ImageView backgroundView;

    private MutablePair<Optional<MainPlayer>, MutablePosition2D> mainPlayer;
    private Optional<MutablePair<Optional<WeaponSprite>, MutablePosition2D>> mainWeapon;

    private final GameState gameState;
    private Optional<GameEngine> controller;
    private Map<MainEnemy, MutablePosition2D> enemySprites;
    private Map<PlatformSprite, MutablePosition2D> platformSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> itemSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> obstacleSprites;
    private Map<WeaponSprite, MutablePosition2D> weaponSprites;
    private Map<BulletSprite, MutablePosition2D> bulletSprites;
	//private Map<CoinSprite, MutablePosition2D> coinSprites;
    private List<Hud> hudList;

    public MapScene(final GameState gameState) {
        this.gameState = gameState;
        this.controller = Optional.empty();
        this.appPane.setMinWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
    }	

    public MapScene(final GameState gameState, final GameEngine ctrlr) {
        this.gameState = gameState;
        this.controller = Optional.of(ctrlr);
        this.appPane.setMinWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
    }

    public final void setup(final GameEngine controller) {
    	setInputController(controller);
    	this.initScene();
        this.root.getChildren().add(this.appPane);
        AppLogger.getAppLogger().debug("Inside MapScene setup() method."); 
        try {
            this.backgroundView = new ImageView(new Image(Files.newInputStream(Paths.get(this.map.getMap().getPath()))));
            AppLogger.getAppLogger().debug("Load background image");
        } catch (final IOException e) {
            e.printStackTrace();
			AppLogger.getAppLogger().error("Failed to load background image.");
		}
        this.mainPlayer = new MutablePair<>();
        this.mainWeapon = Optional.empty();
        this.enemySprites = new HashMap<>();
        this.platformSprites = new HashMap<>();
        this.itemSprites = new HashMap<>();
        this.obstacleSprites = new HashMap<>();
        this.weaponSprites = new HashMap<>();
        this.bulletSprites = new HashMap<>();

        this.appPane.getChildren().addAll(this.backgroundView, this.gamePane, this.uiPane);
        this.backgroundView.fitWidthProperty().bind(this.appPane.widthProperty()); // per quando si cambia la risoluzione dello schermo.
        this.backgroundView.fitHeightProperty().bind(this.appPane.heightProperty());
        AppLogger.getAppLogger().debug("appPane children: " + this.appPane.getChildren().toString());
        try {
        	this.initialize();
        } catch (final IOException exc) {
        	exc.printStackTrace();
        	AppLogger.getAppLogger().error("IOException, probably caused by a problem with components sprite imgs.");
        }
        
        final Hud healthInfo = new Hud(HudLabels.HEALTH, Pos.TOP_LEFT, ContentDisplay.CENTER
				, this.uiPane, new Insets(20, 0, 0, 20));
		final Hud scoreInfo = new Hud(HudLabels.SCORE, Pos.TOP_CENTER, ContentDisplay.RIGHT
				, this.uiPane, new Insets(20, 0, 0, 50));	
		this.hudList = List.of(healthInfo, scoreInfo);
    }
    
    private void initialize() throws IOException {
    	final Environment world = this.gameState.getGameEnvironment();
    	// final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();
	    final PhysicalObjectSpriteFactory spriteFactory = new PhysicalObjectSpriteFactoryImpl(gameState);

    	//final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);
    	
    	if (world.getPlayer().isPresent()) {
    		final MutablePosition2D playerPos = world.getPlayer().get().getPosition().get();
    		this.mainPlayer.setLeft(Optional.of(new MainPlayer(playerPos.getX(), 
    				playerPos.getY())));
    		this.mainPlayer.setRight(playerPos);
        	this.gamePane.getChildren().add(this.mainPlayer.getLeft().get());
       		AppLogger.getAppLogger().debug(String.format("Player %s rendered.", world.getPlayer().get()));
    	}
    	
    	for (final Platform x : world.getPlatforms().get()) {
    		final MutablePosition2D xPos = x.getPosition().get();
    		final PlatformSprite newSprite = new PlatformSprite(this.map.getPlatformType(), x);
    		this.platformSprites.put(newSprite, xPos);
    		this.gamePane.getChildren().add(newSprite);
    	}
    	AppLogger.getAppLogger().debug("Platforms rendered.");
    	
    	for (final Enemy x : world.getEnemies().get()) {
    		final MutablePosition2D xPos = x.getPosition().get();
    		final MainEnemy enemySprite = new MainEnemy(xPos.getX() 
    				, xPos.getY());
    		this.enemySprites.put(enemySprite, xPos);
    		this.gamePane.getChildren().add(enemySprite);
    	}
    	AppLogger.getAppLogger().debug("Enemies rendered.");
		
    	
    	for (final Item x : world.getItems().get()) {
    	    final MutablePosition2D position = x.getPosition().get();
    		if (x.getItemId().equals(Items.DAMAGE)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generateDamagingItemSprite(position);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.HEART)) {
        	    final PhysicalObjectSprite itemSprite = spriteFactory.generateHealingItemSprite(position);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.POISON)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generatePoisoningItemSprite(position);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.COIN)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generateCoinItemSprite(position);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		}
    	}
    	AppLogger.getAppLogger().debug("Items rendered.");
    	

		for (final PhysicalObject x : world.getObstacles().get()) {
    		final MutablePosition2D xPos = x.getPosition().get();
    		if (x instanceof Obstacle) {
    			final PhysicalObjectSprite obstacleSprite = spriteFactory.generateStaticObstacleSprite(xPos);
    			obstacleSprite.renderPosition(xPos.getX(), xPos.getY());
    			this.obstacleSprites.put(obstacleSprite, xPos);
    			this.gamePane.getChildren().add(obstacleSprite);
    			AppLogger.getAppLogger().debug("Static Obstacle rendered");
    		} 
    	}
		
		for (final Weapon x : world.getWeapons().get()) {
			final MutablePosition2D xPos = x.getPosition().get();
			if (x.getTypeOfWeapon().equals(EntityList.Weapons.GUN)) {
				final WeaponSprite weaponSprite = new WeaponSprite(WeaponsImg.GUN
						, xPos.getX(), xPos.getY());
				this.weaponSprites.put(weaponSprite, xPos);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Gun rendered");
			} else if (x.getTypeOfWeapon().equals(EntityList.Weapons.SHOTGUN)) {
				final WeaponSprite weaponSprite = new WeaponSprite(WeaponsImg.SHOTGUN
						, xPos.getX(), xPos.getY());
				this.weaponSprites.put(weaponSprite, xPos);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Shotgun rendered");
			} else if (x.getTypeOfWeapon().equals(EntityList.Weapons.AUTO)) {
				final WeaponSprite weaponSprite = new WeaponSprite(WeaponsImg.AUTO
						, xPos.getX(), xPos.getY());
				this.weaponSprites.put(weaponSprite, xPos);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Automatic weapon rendered");
			}	
		}
		AppLogger.getAppLogger().debug("Weapons rendered");

		//TODO: uncomment when it will be fixed
		/*for(final Coin c : world.getCoins().get()){
			final MutablePosition2D xPos = c.getPosition().get();
			final CoinSprite coinSprite = new CoinSprite();
			this.coinSprites.put(coinSprite, xPos);
			this.gamePane.getChildren().add(coinSprite);
		}
		AppLogger.getAppLogger().debug("Coins rendered");*/

		/*
		 * Ui initializing
		 */
			
    }

    @Override
    public final void draw() throws IOException {
	    this.update();
	    try {
			this.render();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void update() throws IOException {
    	//AppLogger.getAppLogger().debug("Inside update() method, checks input keys.");
    	
//    	if (this.keysPressed.contains(KeyCode.UP)) {
//		//AppLogger.getAppLogger().info("Key 'UP' pressed.");
//		this.mainPlayer.left.get().getSpriteAnimation().play();
//		this.controller.get().notifyCommand(new Up(5));
//		final Timer t = new Timer();
//		t.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				MapScene.this.getController().get().notifyCommand(new Down(5));
//			}
//		}, 250L);
//	}
    	
    	
        if (this.keysPressed.contains(KeyCode.UP)) { 
        	//AppLogger.getAppLogger().info("Key 'UP' pressed.");
        	this.mainPlayer.left.get().getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Up());
        }
        

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
        	//AppLogger.getAppLogger().info("Key 'RIGHT' pressed.");
            this.mainPlayer.left.get().getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Right());
        }

        if (this.keysPressed.contains(KeyCode.DOWN)) { 
        	//AppLogger.getAppLogger().info("Key 'DOWN' pressed.");
            this.controller.get().notifyCommand(new Down());
        }

        if (this.keysPressed.contains(KeyCode.LEFT)) {
        	//AppLogger.getAppLogger().info("Key 'LEFT' pressed.");
            this.controller.get().notifyCommand(new Left());
        }
        
        if (this.keysReleased.contains(KeyCode.SPACE)) {
        	AppLogger.getAppLogger().info("Key 'SPACE' pressed.");
        	this.controller.get().notifyCommand(new Space(this));
        }
        
        if (this.keysReleased.contains(KeyCode.ESCAPE)) {
        	AppLogger.getAppLogger().info("Key 'ESCAPE' pressed");
        	this.controller.get().stop();
        	this.controller.get().notifyCommand(new Esc());
        	this.controller.get().start();
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	//AppLogger.getAppLogger().info("Key 'UP' released.");
        	this.mainPlayer.left.get().getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	//AppLogger.getAppLogger().info("Key 'RIGHT' released.");
            this.mainPlayer.left.get().getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.RIGHT);
        }

        if (this.keysReleased.contains(KeyCode.DOWN)) { 
        	//AppLogger.getAppLogger().info("Key 'DOWN' pressed.");
        	this.keysReleased.remove(KeyCode.DOWN);
        }

        if (this.keysReleased.contains(KeyCode.LEFT)) {
        	//AppLogger.getAppLogger().info("Key 'LEFT' pressed.");
        	this.keysReleased.remove(KeyCode.LEFT); 
        }
        
        if (this.keysReleased.contains(KeyCode.SPACE)) {
        	this.keysReleased.remove(KeyCode.SPACE);
        }
        
        if (this.keysReleased.contains(KeyCode.ESCAPE)) {
        	this.keysReleased.remove(KeyCode.ESCAPE);
        }
        
    }
    
    /*
    private Optional<GameEngine> getController() {
    	return this.controller;
    }
    */

    private void render() throws IOException {
    	//AppLogger.getAppLogger().debug("Inside render() method.");
    	//AppLogger.getAppLogger().debug("appPane: " + this.appPane.getChildren().toString());
    	//AppLogger.getAppLogger().debug("gamePane: " + this.gamePane.getChildren().toString());

    	final int platformSize = this.gameState.getEnvGenerator().getPlatformSize();

    	final Environment env = this.gameState.getGameEnvironment();
    	//final PhysicalObjectSpriteFactory physObjSpriteFactory = new PhysicalObjectSpriteFactoryImpl(this, world);

    	this.mainPlayer.getRight().setPosition(env.getPlayer().get().getPosition().get().getX()
		, env.getPlayer().get().getPosition().get().getY());
    	this.mainPlayer.left.get().renderPosition(this.mainPlayer.getRight().getX(), this.mainPlayer.getRight().getY());

    	if(env.getPlayer().get().hasWeapon()) {
    		if(this.mainWeapon.isEmpty()) {
    			this.weaponSprites.forEach((x, y) -> {
    				if(y.equals(this.mainPlayer.getRight())) {
    					this.mainWeapon = Optional.of(new MutablePair<>(Optional.ofNullable(x), y));
    					AppLogger.getAppLogger().debug("Add main Weapon");
    					this.weaponSprites.remove(x);
    				}
    			});
    		} else {
    			final MutablePosition2D pos = this.mainPlayer.getRight();
    			this.mainWeapon.get().getLeft().get().renderPosition(pos.getX(), pos.getY());
    			//AppLogger.getAppLogger().debug("WeaponView pos: " + this.mainWeapon.get().getLeft().get().getPosition());
    		}
    	}
    	
    	this.platformSprites.forEach((x, y) -> {
    		x.renderMovingPosition();
    	});
    	//AppLogger.getAppLogger().debug("Platforms sprite position updated");

    	this.enemySprites.forEach((x, y) ->  {
    		x.renderPosition(y.getX(), y.getY());
    	});
		//AppLogger.getAppLogger().debug("Enemies sprite position updated");

		this.itemSprites.forEach((x, y) -> {
			x.renderMovingPosition();
		});
		//AppLogger.getAppLogger().debug("Item sprite position updated");

		this.obstacleSprites.forEach((x, y) -> {
			x.renderMovingPosition();
    		//AppLogger.getAppLogger().debug("ObstaclePos: " + y.toString());
		});
		//AppLogger.getAppLogger().debug("Obstacles sprite position updated");
		
		
		this.weaponSprites.forEach((x, y) -> x.renderPosition(y.getX(), y.getY()));
		//AppLogger.getAppLogger().debug("Weapons sprite position updated");
		
		this.bulletSprites.forEach((x, y) -> x.renderMovingPosition());
		
		IntStream.range(0, this.hudList.size()).forEach(i -> {
			final Label label = (Label) this.uiPane.getChildren().get(i);
			if (this.checkChildrenById(i, HudLabels.HEALTH)) {
				label.setText("Health: " + env.getPlayer().get().getHealth());
			} else if(this.checkChildrenById(i, HudLabels.SCORE)) {
				label.setText("Score: " + env.getPlayer().get().getCurrentScore().showScore());
			}
		});
		
    }
    
    private final boolean checkChildrenById(final int i, final HudLabels label) {
    	return this.uiPane.getChildren().get(i).getId().equals(label.toString());
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
	public final void setInputController(final GameEngine controller) {
		this.controller = Optional.of(controller);
	}
	@Override
	public final void setHeight(final double heigth) {
		this.appPane.setPrefHeight(heigth);
	}

	@Override
	public final void setWidth(final double width) {
		this.appPane.setPrefWidth(width);
	}
	
	@Override
	public void deleteEnemySpriteImage(final MutablePosition2D position) {
		final MainEnemy enemy = enemySprites.entrySet()
                .stream()
                .filter(entry -> position.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
		//enemySprites.remove(enemy);
		this.gamePane.getChildren().remove(enemy);
	}

	@Override
	public void deleteBulletSpriteImage(final MutablePosition2D position) {
		/*
		final BulletSprite bullet = this.bulletSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.gamePane.getChildren().remove(bullet);
		*/
		/*this.bulletSprites.remove(bullet);
		this.bulletSprites.forEach((x, y) -> {
			AppLogger.getAppLogger().debug("Bullet pos view: " + y.toString());
			if(y.equals(position)) {
				this.gamePane.getChildren().remove(x);
			}
		});*/
	}
	
	@Override
	public void deleteItemSprite(final MutablePosition2D position) {
		final PhysicalObjectSprite item = this.itemSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.getGamePane().getChildren().remove(item);
	}

	@Override
	public void deleteWeaponSpriteImage(final MutablePosition2D position) {
		final WeaponSprite weapon = this.weaponSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.gamePane.getChildren().remove(weapon);
		this.mainWeapon = Optional.empty();
	}

	@Override
	public void generateBullet(final MutablePosition2D pos) throws IOException {
		//final MutablePosition2D pos = this.mainWeapon.get().getRight();
		final BulletSprite bullet = new BulletSprite(pos.getX(), pos.getY());
		this.bulletSprites.put(bullet, pos);
		this.gamePane.getChildren().add(bullet);		
	}
}
