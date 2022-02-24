package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.GameMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Maps;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainEnemy;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.input.Left;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Space;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
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
import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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
    private ImageView backgroundView;
    private final GameMap map = new BackgroundMap();
    private MutablePair<Optional<MainPlayer>, MutablePosition2D> mainPlayer;
    private Optional<MutablePair<Optional<PhysicalObjectSprite>, MutablePosition2D>> mainWeapon;

    private final GameState gameState;
    private Optional<GameEngine> controller;
    private Map<PhysicalObjectSprite, MutablePosition2D> enemySprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> platformSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> itemSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> obstacleSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> weaponSprites;
    private Map<PhysicalObjectSprite, MutablePosition2D> bulletSprites;
    private List<Hud> hudList;
    private final SoundsFactory soundsFactory;

    public MapScene(final GameState gameState) {
        this.gameState = gameState;
        this.controller = Optional.empty();
        this.appPane.setMinWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
        this.soundsFactory = new SoundsFactoryImpl();
    }	

    public MapScene(final GameState gameState, final GameEngine ctrlr) {
        this.gameState = gameState;
        this.controller = Optional.of(ctrlr);
        this.appPane.setMinWidth(AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
        this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
        this.soundsFactory = new SoundsFactoryImpl();
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
        final Hud healthInfo = new Hud(HudLabels.HEALTH, Pos.TOP_LEFT, ContentDisplay.CENTER,
        		this.uiPane, new Insets(20, 0, 0, 20));
		final Hud scoreInfo = new Hud(HudLabels.SCORE, Pos.TOP_CENTER, ContentDisplay.RIGHT,
				this.uiPane, new Insets(20, 0, 0, 50));	
		this.hudList = List.of(healthInfo, scoreInfo);
    }

    private void initialize() throws IOException {
    	final Environment world = this.gameState.getGameEnvironment();
	    final PhysicalObjectSpriteFactory spriteFactory = new PhysicalObjectSpriteFactoryImpl();
    	initializePlayer(world);
    	initializePlatforms(world, spriteFactory);
    	initializeEnemies(world, spriteFactory);
    	initializeItems(world, spriteFactory);
		initializeObstacles(world, spriteFactory);
		initializeWeapons(world, spriteFactory);
    }

	private void initializeWeapons(final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
		for (final Weapon x : world.getEntityManager().getWeapons().get()) {
			final MutablePosition2D xPosition = x.getPosition().get();
			if (x.getTypeOfWeapon().equals(EntityList.Weapons.GUN)) {
				final PhysicalObjectSprite weaponSprite = spriteFactory.generateGunWeaponSprite(x);
				this.weaponSprites.put(weaponSprite, xPosition);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Gun rendered");
			} else if (x.getTypeOfWeapon().equals(EntityList.Weapons.SHOTGUN)) {
				final PhysicalObjectSprite weaponSprite = spriteFactory.generateShotgunWeaponSprite(x);
				this.weaponSprites.put(weaponSprite, xPosition);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Shotgun rendered");
			} else if (x.getTypeOfWeapon().equals(EntityList.Weapons.AUTO)) {
				final PhysicalObjectSprite weaponSprite = spriteFactory.generateAutogunWeaponSprite(x);
				this.weaponSprites.put(weaponSprite, xPosition);
				this.gamePane.getChildren().add(weaponSprite);
				AppLogger.getAppLogger().info("Automatic weapon rendered");
			}	
		}
		AppLogger.getAppLogger().debug("Weapons rendered");
	}

	private void initializeObstacles(final Environment world, final PhysicalObjectSpriteFactory spriteFactory)
			throws IOException {
		for (final PhysicalObject x : world.getEntityManager().getObstacles().get()) {
    		final MutablePosition2D xPosition = x.getPosition().get();
    		if (x instanceof Obstacle) {
    			final PhysicalObjectSprite obstacleSprite = spriteFactory.generateBunnySprite(x);
    			obstacleSprite.renderPosition(xPosition.getX(), xPosition.getY());
    			this.obstacleSprites.put(obstacleSprite, xPosition);
    			this.gamePane.getChildren().add(obstacleSprite);
    			AppLogger.getAppLogger().debug("Static Obstacle rendered");
    		} 
    	}
	}

	private void initializeItems(final Environment world, final PhysicalObjectSpriteFactory spriteFactory)
			throws IOException {
		for (final Item x : world.getEntityManager().getItems().get()) {
    	    final MutablePosition2D position = x.getPosition().get();
    		if (x.getItemId().equals(Items.DAMAGE)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generateDamagingItemSprite(x);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.HEART)) {
        	    final PhysicalObjectSprite itemSprite = spriteFactory.generateHealingItemSprite(x);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.POISON)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generatePoisoningItemSprite(x);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.COIN)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generateCoinItemSprite(this.map.getCoinType(), x);
        	    itemSprite.renderPosition(position.getX(), position.getY());
        	    this.itemSprites.put(itemSprite, position);
        	    this.gamePane.getChildren().add(itemSprite);
    		} else if (x.getItemId().equals(Items.CHARGER)) {
    			final PhysicalObjectSprite itemSprite = spriteFactory.generateAmmoSprite(x);
    			this.itemSprites.put(itemSprite, position);
    			this.gamePane.getChildren().add(itemSprite);
    		} else if(x.getItemId().equals(Items.FLAG)) {
				final PhysicalObjectSprite itemSprite = spriteFactory.generateFlagSprite(x);
				this.itemSprites.put(itemSprite, position);
				this.gamePane.getChildren().add(itemSprite);
			}
    	}
    	AppLogger.getAppLogger().debug("Items rendered.");
	}

	private void initializeEnemies(final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
		for (final Enemy x : world.getEntityManager().getEnemies().get()) {
    		final MutablePosition2D xPosition = x.getPosition().get();
    		final PhysicalObjectSprite enemySprite = spriteFactory.generateEnemySprite(x);
    		this.enemySprites.put(enemySprite, xPosition);
    		this.gamePane.getChildren().add(enemySprite);
    	}
    	AppLogger.getAppLogger().debug("Enemies rendered.");
	}

	private void initializePlatforms(final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
		for (final Platform x : world.getEntityManager().getPlatforms().get()) {
    		final MutablePosition2D xPosition = x.getPosition().get();
    		final PhysicalObjectSprite newSprite = spriteFactory.generatePlatformSprite(this.map.getPlatformType(), x);
    		this.platformSprites.put(newSprite, xPosition);
    		this.gamePane.getChildren().add(newSprite);
    	}
    	AppLogger.getAppLogger().debug("Platforms rendered.");
	}

	private void initializePlayer(final Environment world) throws IOException {
		if (world.getEntityManager().getPlayer().isPresent()) {
    		final MutablePosition2D playerPos = world.getEntityManager().getPlayer().get().getPosition().get();
    		this.mainPlayer.setLeft(Optional.of(new MainPlayer(playerPos.getX(), 
    				playerPos.getY())));
    		this.mainPlayer.setRight(playerPos);
        	this.gamePane.getChildren().add(this.mainPlayer.getLeft().get());
       		AppLogger.getAppLogger().debug(String.format("Player %s rendered.", world.getEntityManager().getPlayer().get()));
    	}
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

    	this.mainPlayer.left.get().getSpriteAnimation().play();

        if (this.keysPressed.contains(KeyCode.UP)) { 
        	//AppLogger.getAppLogger().info("Key 'UP' pressed.");
        	this.mainPlayer.left.get().getSpriteAnimation().stop();
            this.controller.get().notifyCommand(new Up());
        }

        if (this.keysPressed.contains(KeyCode.RIGHT)) {
        	//AppLogger.getAppLogger().info("Key 'RIGHT' pressed.");
            this.mainPlayer.left.get().getSpriteAnimation().play();
            this.controller.get().notifyCommand(new Right());
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
        	this.soundsFactory.createSound(Sounds.HEALTH_INCREMENT);
        	this.controller.get().stop();
        	final PageLoader pageLoaderImpl = new PageLoaderImpl();
        	final Window window = pageLoaderImpl.goToSelectedPageOnInput(Frames.PAUSEMENU);
        	window.setOnCloseRequest(e -> {
        		this.controller.get().start();
        	});
        }

        if (this.keysReleased.contains(KeyCode.UP)) {
        	this.mainPlayer.left.get().getSpriteAnimation().play();
        	this.keysReleased.remove(KeyCode.UP);
        }

        if (this.keysReleased.contains(KeyCode.RIGHT)) {
        	//AppLogger.getAppLogger().info("Key 'RIGHT' released.");
            this.mainPlayer.left.get().getSpriteAnimation().stop();
        	this.keysReleased.remove(KeyCode.RIGHT);
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

    private void render() throws IOException {
    	final Environment env = this.gameState.getGameEnvironment();

    	this.mainPlayer.getRight().setPosition(env.getEntityManager().getPlayer().get().getPosition().get().getX(), 
    			env.getEntityManager().getPlayer().get().getPosition().get().getY());
    	this.mainPlayer.left.get().renderPosition(this.mainPlayer.getRight().getX(), this.mainPlayer.getRight().getY());

    	if (env.getEntityManager().getPlayer().get().hasWeapon()) {
    		if (this.mainWeapon.isEmpty()) {
    			this.weaponSprites.forEach((x, y) -> {
    				if (y.equals(this.mainPlayer.getRight())) {
    					this.mainWeapon = Optional.of(new MutablePair<>(Optional.ofNullable(x), y));
    					AppLogger.getAppLogger().debug("Add main Weapon");
    					this.weaponSprites.remove(x);
    				}
    			});
    		} else {
    			final MutablePosition2D pos = this.mainPlayer.getRight();
    			this.mainWeapon.get().getLeft().get().renderPosition(pos.getX(), pos.getY());
    		}
    	}

    	this.platformSprites.forEach((x, y) -> {
    		x.renderMovingPosition();
    	});

    	this.enemySprites.forEach((x, y) ->  {
    		x.renderPosition(y.getX(), y.getY());
    	});

		this.itemSprites.forEach((x, y) -> {
			x.renderMovingPosition();
		});

		this.obstacleSprites.forEach((x, y) -> {
			x.renderPosition(y.getX(), y.getY());
		});

		this.weaponSprites.forEach((x, y) -> x.renderPosition(y.getX(), y.getY()));

		this.bulletSprites.forEach((x, y) -> x.renderPosition(y.getX(), y.getY()));

		IntStream.range(0, this.hudList.size()).forEach(i -> {
			final Label label = (Label) this.uiPane.getChildren().get(i);
			if (this.checkChildrenById(i, HudLabels.HEALTH)) {
				label.setText("Health: " + env.getEntityManager().getPlayer().get().getHealth());
			} else if (this.checkChildrenById(i, HudLabels.SCORE)) {
				label.setText("Score: " + env.getEntityManager().getPlayer().get().getCurrentScore().showScore());
			}
		});
    }

    private boolean checkChildrenById(final int i, final HudLabels label) {
    	return this.uiPane.getChildren().get(i).getId().equals(label.toString());
    }

    public final void setMap(final Maps map) {
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
	public final void deleteEnemySpriteImage(final MutablePosition2D position) {
		final PhysicalObjectSprite enemy = enemySprites.entrySet()
                .stream()
                .filter(entry -> position.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
		this.gamePane.getChildren().remove(enemy);
	}

	@Override
	public final void deleteBulletSpriteImage(final MutablePosition2D position) {
		final PhysicalObjectSprite bullet = this.bulletSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.gamePane.getChildren().remove(bullet);
	}
	
	@Override
	public final void deleteItemSprite(final MutablePosition2D position) {
		final PhysicalObjectSprite item = this.itemSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.getGamePane().getChildren().remove(item);
	}

	@Override
	public final void deleteWeaponSpriteImage(final MutablePosition2D position) {
		final PhysicalObjectSprite weapon = this.weaponSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.gamePane.getChildren().remove(weapon);
		this.mainWeapon = Optional.empty();
	}

	@Override
	public final void generateBullet(final PhysicalObject bullet) throws IOException {
		final PhysicalObjectSpriteFactory factory = new PhysicalObjectSpriteFactoryImpl();
		final PhysicalObjectSprite bulletSprite = factory.generateBulletSprite(bullet);
		this.bulletSprites.put(bulletSprite, bullet.getPosition().get());
		this.gamePane.getChildren().add(bulletSprite);
	}
	
	@Override
	public final void stopPlayerAnimation() {
		this.mainPlayer.getLeft().get().getSpriteAnimation().stop();
	}
	
	@Override
	public final void autoKill() {
		final Window window = this.getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	@Override
	public final void deleteObstacleSpriteImage(final MutablePosition2D position) {
		final PhysicalObjectSprite obstacle = this.obstacleSprites.entrySet()
				.stream()
				.filter(entry -> position.equals(entry.getValue()))
				.map(x -> x.getKey())
				.findFirst().get();
		this.getGamePane().getChildren().remove(obstacle);
	}
	
}
