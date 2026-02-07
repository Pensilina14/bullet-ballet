package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpriteContainer;
import it.unibo.pensilina14.bullet.ballet.common.SpriteManager;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.GameMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Maps;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PlayerSprite;
import it.unibo.pensilina14.bullet.ballet.input.Left;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Space;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class MapScene extends AbstractScene implements GameView {

  private final Pane appPane = new StackPane();
  private final Pane gamePane = new Pane();
  private final Pane uiPane = new StackPane();
  private final Pane backgroundPane = new Pane();
  private ImageView backgroundView1;
  private ImageView backgroundView2;
  private double backgroundScrollX;
  private final GameMap map = new BackgroundMap();
  private final SpriteManager sprites;
  private Optional<MutablePair<PhysicalObjectSprite, MutablePosition2D>> mainWeapon;
  private final GameState gameState;
  private Optional<GameEngine> controller;
  private List<Hud> hudList;
  private final SoundsFactory soundsFactory;

  public MapScene(final GameState gameState) {
    this.gameState = gameState;
    this.controller = Optional.empty();
    this.appPane.setMinWidth(AbstractScene.SCENE_WIDTH);
    this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
    this.soundsFactory = new SoundsFactoryImpl();
    this.sprites = new SpriteContainer();
    this.mainWeapon = Optional.empty();
  }

  public MapScene(final GameState gameState, final GameEngine ctrlr) {
    this.gameState = gameState;
    this.controller = Optional.of(ctrlr);
    this.appPane.setMinWidth(
        AbstractScene.SCENE_WIDTH); // caso mai la mappa fosse più grande o anche più piccola.
    this.appPane.setMinHeight(AbstractScene.SCENE_HEIGHT);
    this.soundsFactory = new SoundsFactoryImpl();
    this.sprites = new SpriteContainer();
    this.mainWeapon = Optional.empty();
  }

  public final void setup(final GameEngine controller) {
    setInputController(controller);
    this.initScene();

    // The responsive scaling in AbstractScene uses a fixed logical size (FULLHD).
    // Ensure the map/app pane fills that logical space, regardless of the actual window size.
    this.appPane.setMinSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.appPane.setPrefSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.appPane.setMaxSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    AnchorPane.setTopAnchor(this.appPane, 0.0);
    AnchorPane.setLeftAnchor(this.appPane, 0.0);

    // Clip the app pane so the scrolling background doesn't bleed outside the viewport.
    final Rectangle clip = new Rectangle();
    clip.widthProperty().bind(this.appPane.widthProperty());
    clip.heightProperty().bind(this.appPane.heightProperty());
    this.appPane.setClip(clip);

    this.root.getChildren().add(this.appPane);
    AppLogger.getAppLogger().debug("Inside MapScene setup() method.");
    final String bgUrl =
      String.valueOf(getClass().getClassLoader().getResource(this.map.getMap().getPath()));
    final Image bgImage = new Image(bgUrl);
    this.backgroundView1 = new ImageView(bgImage);
    this.backgroundView2 = new ImageView(bgImage);
    this.backgroundView1.setSmooth(false);
    this.backgroundView2.setSmooth(false);
    this.backgroundScrollX = 0.0;
    AppLogger.getAppLogger().debug("Load background image");
    this.mainWeapon = Optional.empty();

    this.backgroundPane.getChildren().addAll(this.backgroundView1, this.backgroundView2);
    this.backgroundView1.fitWidthProperty().bind(this.appPane.widthProperty());
    this.backgroundView2.fitWidthProperty().bind(this.appPane.widthProperty());
    this.backgroundView1.fitHeightProperty().bind(this.appPane.heightProperty());
    this.backgroundView2.fitHeightProperty().bind(this.appPane.heightProperty());
    this.appPane.getChildren().addAll(this.backgroundPane, this.gamePane, this.uiPane);
    AppLogger.getAppLogger().debug("appPane children: " + this.appPane.getChildren().toString());
    try {
      this.initialize();
    } catch (final IOException exc) {
      exc.printStackTrace();
      AppLogger.getAppLogger()
          .error("IOException, probably caused by a problem with components sprite imgs.");
    }
    final Hud healthInfo =
        new Hud(
            HudLabels.HEALTH,
            Pos.TOP_LEFT,
            ContentDisplay.CENTER,
            this.uiPane,
            new Insets(20, 0, 0, 20));
    final Hud scoreInfo =
        new Hud(
            HudLabels.SCORE,
            Pos.TOP_CENTER,
            ContentDisplay.RIGHT,
            this.uiPane,
            new Insets(20, 0, 0, 50));
    final Hud ammoInfo =
        new Hud(
            HudLabels.AMMO,
            Pos.TOP_RIGHT,
            ContentDisplay.LEFT,
            this.uiPane,
            new Insets(20, 150, 0, 0));
    this.hudList = List.of(healthInfo, scoreInfo, ammoInfo);
  }

  private void initialize() throws IOException {
    final Environment world = this.gameState.getGameEnvironment();
    final PhysicalObjectSpriteFactory spriteFactory = new PhysicalObjectSpriteFactoryImpl();
    initializePlayer(world, spriteFactory);
    initializePlatforms(world, spriteFactory);
    initializeEnemies(world, spriteFactory);
    initializeItems(world, spriteFactory);
    initializeObstacles(world, spriteFactory);
    initializeWeapons(world, spriteFactory);
  }

  private void initializeWeapons(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    for (final Weapon x : world.getEntityManager().getWeapons().get()) {
      final MutablePosition2D xPosition = x.getPosition().get();
      if (x.getTypeOfWeapon().equals(EntityList.Weapons.GUN)) {
        final PhysicalObjectSprite weaponSprite = spriteFactory.generateGunWeaponSprite(x);
        this.sprites.addWeaponSprite(weaponSprite, xPosition);
        this.gamePane.getChildren().add(weaponSprite);
        AppLogger.getAppLogger().info("Gun rendered");
      } else if (x.getTypeOfWeapon().equals(EntityList.Weapons.SHOTGUN)) {
        final PhysicalObjectSprite weaponSprite = spriteFactory.generateShotgunWeaponSprite(x);
        this.sprites.addWeaponSprite(weaponSprite, xPosition);
        this.gamePane.getChildren().add(weaponSprite);
        AppLogger.getAppLogger().info("Shotgun rendered");
      } else if (x.getTypeOfWeapon().equals(EntityList.Weapons.AUTO)) {
        final PhysicalObjectSprite weaponSprite = spriteFactory.generateAutogunWeaponSprite(x);
        this.sprites.addWeaponSprite(weaponSprite, xPosition);
        this.gamePane.getChildren().add(weaponSprite);
        AppLogger.getAppLogger().info("Automatic weapon rendered");
      }
    }
    AppLogger.getAppLogger().debug("Weapons rendered");
  }

  private void initializeObstacles(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    for (final PhysicalObject x : world.getEntityManager().getObstacles().get()) {
      final MutablePosition2D xPosition = x.getPosition().get();
      if (x instanceof Obstacle) {
        final PhysicalObjectSprite obstacleSprite = spriteFactory.generateBunnySprite(x);
        obstacleSprite.renderPosition(xPosition.getX(), xPosition.getY());
        this.sprites.addObstacleSprite(obstacleSprite, xPosition);
        this.gamePane.getChildren().add(obstacleSprite);
        AppLogger.getAppLogger().debug("Static Obstacle rendered");
      }
    }
  }

  private void initializeItems(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    for (final Item x : world.getEntityManager().getItems().get()) {
      final MutablePosition2D position = x.getPosition().get();
      if (x.getItemId().equals(Items.DAMAGE)) {
        final PhysicalObjectSprite itemSprite = spriteFactory.generateDamagingItemSprite(x);
        itemSprite.renderPosition(position.getX(), position.getY());
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      } else if (x.getItemId().equals(Items.HEART)) {
        final PhysicalObjectSprite itemSprite = spriteFactory.generateHealingItemSprite(x);
        itemSprite.renderPosition(position.getX(), position.getY());
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      } else if (x.getItemId().equals(Items.POISON)) {
        final PhysicalObjectSprite itemSprite = spriteFactory.generatePoisoningItemSprite(x);
        itemSprite.renderPosition(position.getX(), position.getY());
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      } else if (x.getItemId().equals(Items.COIN)) {
        final PhysicalObjectSprite itemSprite =
            spriteFactory.generateCoinItemSprite(this.map.getCoinType(), x);
        itemSprite.renderPosition(position.getX(), position.getY());
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      } else if (x.getItemId().equals(Items.CHARGER)) {
        final PhysicalObjectSprite itemSprite = spriteFactory.generateAmmoSprite(x);
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      } else if (x.getItemId().equals(Items.FLAG)) {
        final PhysicalObjectSprite itemSprite = spriteFactory.generateFlagSprite(x);
        this.sprites.addItemSprite(itemSprite, position);
        this.gamePane.getChildren().add(itemSprite);
      }
    }
    AppLogger.getAppLogger().debug("Items rendered.");
  }

  private void initializeEnemies(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    for (final Enemy x : world.getEntityManager().getEnemies().get()) {
      final MutablePosition2D xPosition = x.getPosition().get();
      final PhysicalObjectSprite enemySprite = spriteFactory.generateEnemySprite(x);
      this.sprites.addEnemySprite(enemySprite, xPosition);
      this.gamePane.getChildren().add(enemySprite);
    }
    AppLogger.getAppLogger().debug("Enemies rendered.");
  }

  private void initializePlatforms(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    for (final Platform x : world.getEntityManager().getPlatforms().get()) {
      final MutablePosition2D xPosition = x.getPosition().get();
      final PhysicalObjectSprite platformSprite =
          spriteFactory.generatePlatformSprite(this.map.getPlatformType(), x);
      this.sprites.addPlatformSprite(platformSprite, xPosition);
      this.gamePane.getChildren().add(platformSprite);
    }
    AppLogger.getAppLogger().debug("Platforms rendered.");
  }

  private void initializePlayer(
      final Environment world, final PhysicalObjectSpriteFactory spriteFactory) throws IOException {
    if (world.getEntityManager().getPlayer().isPresent()) {
      final MutablePosition2D playerPos =
          world.getEntityManager().getPlayer().get().getPosition().get();
      final PhysicalObjectSprite playerSprite =
          spriteFactory.generatePlayerSprite(world.getEntityManager().getPlayer().get());
      this.sprites.addPlayerSprite(playerSprite, playerPos);
      this.gamePane.getChildren().add(playerSprite);
      AppLogger.getAppLogger()
          .debug(String.format("Player %s rendered.", world.getEntityManager().getPlayer().get()));
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
    this.startPlayerAnimation();

    if (this.keysPressed.contains(KeyCode.UP)) {
      this.stopPlayerAnimation();
      this.controller.get().notifyCommand(new Up());
    }

    if (this.keysPressed.contains(KeyCode.RIGHT)) {
      this.startPlayerAnimation();
      this.controller.get().notifyCommand(new Right());
    }

    if (this.keysPressed.contains(KeyCode.LEFT)) {
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
      final Window owner = this.getWindow();
      final Window window = pageLoaderImpl.goToSelectedPageOnInput(Frames.PAUSEMENU, owner);
      window.setOnCloseRequest(
          e -> {
            this.controller.get().start();
          });
    }

    if (this.keysReleased.contains(KeyCode.UP)) {
      this.startPlayerAnimation();
      this.keysReleased.remove(KeyCode.UP);
    }

    if (this.keysReleased.contains(KeyCode.RIGHT)) {
      this.stopPlayerAnimation();
      this.keysReleased.remove(KeyCode.RIGHT);
    }

      this.keysReleased.remove(KeyCode.LEFT);

      this.keysReleased.remove(KeyCode.SPACE);

      this.keysReleased.remove(KeyCode.ESCAPE);
  }

  private void render() throws IOException {
    final Environment env = this.gameState.getGameEnvironment();

    updateBackgroundScroll();

    if (this.sprites.getPlayerSprite().isPresent()) {
      this.sprites
          .getPlayerSprite()
          .get()
          .get(0)
          .getRight()
          .setPosition(
              env.getEntityManager().getPlayer().get().getPosition().get().getX(),
              env.getEntityManager().getPlayer().get().getPosition().get().getY());
      this.sprites
          .getPlayerSprite()
          .get()
          .get(0)
          .getLeft()
          .renderPosition(
              this.sprites.getPlayerSprite().get().get(0).getRight().getX(),
              this.sprites.getPlayerSprite().get().get(0).getRight().getY());
    }

    if (env.getEntityManager().getPlayer().get().hasWeapon()) {
      if (this.mainWeapon.isEmpty() && this.sprites.getWeaponsSprites().isPresent()) {
        this.sprites.getWeaponsSprites().get().stream()
            .forEach(
                p -> {
                  if (p.getRight().equals(this.sprites.getPlayerSprite().get().get(0).getRight())) {
                    this.mainWeapon = Optional.of(new MutablePair<>(p.getLeft(), p.getRight()));
                    AppLogger.getAppLogger().debug("Add main weapon");
                    this.sprites.deleteSprite(p.getRight());
                  }
                });
      } else {
        final MutablePosition2D pos = this.sprites.getPlayerSprite().get().get(0).getRight();
        if (this.mainWeapon.isPresent()) {
          this.mainWeapon.get().getLeft().renderPosition(pos.getX(), pos.getY());
        }
      }
    }

    if (this.sprites.getPlatformsSprites().isPresent()) {
      this.sprites.getPlatformsSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    if (this.sprites.getEnemiesSprites().isPresent()) {
      this.sprites.getEnemiesSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    if (this.sprites.getItemsSprites().isPresent()) {
      this.sprites.getItemsSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    if (this.sprites.getObstaclesSprites().isPresent()) {
      this.sprites.getObstaclesSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    if (this.sprites.getWeaponsSprites().isPresent()) {
      this.sprites.getWeaponsSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    if (this.sprites.getBulletsSprites().isPresent()) {
      this.sprites.getBulletsSprites().get().stream()
          .forEach(p -> p.getLeft().renderPosition(p.getRight().getX(), p.getRight().getY()));
    }

    IntStream.range(0, this.hudList.size())
        .forEach(
            i -> {
              final Label label = (Label) this.uiPane.getChildren().get(i);
              if (this.checkChildrenById(i, HudLabels.HEALTH)) {
                label.setText("Health: " + env.getEntityManager().getPlayer().get().getHealth());
              } else if (this.checkChildrenById(i, HudLabels.SCORE)) {
                label.setText(
                    "Score: "
                        + env.getEntityManager().getPlayer().get().getCurrentScore().showScore());
              } else if (this.checkChildrenById(i, HudLabels.AMMO)
                  && env.getEntityManager().getPlayer().get().hasWeapon()) {
                label.setText(
                    "Ammo: "
                        + env.getEntityManager().getPlayer().get().getWeapon().get().getAmmoLeft());
              }
            });
  }

  private void updateBackgroundScroll() {
    if (this.backgroundView1 == null || this.backgroundView2 == null) {
      return;
    }
    final double w = this.appPane.getWidth();
    if (w <= 1.0) {
      return;
    }

    // The world moves left (x decreases). Scroll the background left too, so it doesn't look like
    // the background is drifting to the right relative to the map.
    this.backgroundScrollX -= 1.0;
    this.backgroundScrollX = this.backgroundScrollX % w;
    if (this.backgroundScrollX > 0) {
      this.backgroundScrollX -= w;
    }

    this.backgroundView1.setTranslateX(this.backgroundScrollX);
    this.backgroundView2.setTranslateX(this.backgroundScrollX + w);
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
    // Keep the logical size fixed; AbstractScene will scale to the actual window.
    this.appPane.setPrefHeight(Resolutions.FULLHD.getHeight());
    super.setHeight(heigth);
  }

  @Override
  public final void setWidth(final double width) {
    // Keep the logical size fixed; AbstractScene will scale to the actual window.
    this.appPane.setPrefWidth(Resolutions.FULLHD.getWidth());
    super.setWidth(width);
  }

  @Override
  public final void deleteEnemySpriteImage(final MutablePosition2D position) {
    this.deleteFromScene(position);
  }

  @Override
  public final void deleteBulletSpriteImage(final MutablePosition2D position) {
    this.deleteFromScene(position);
  }

  @Override
  public final void deleteItemSprite(final MutablePosition2D position) {
    this.deleteFromScene(position);
  }

  @Override
  public final void deleteWeaponSpriteImage(final MutablePosition2D position) {
    this.deleteFromScene(position);
    this.mainWeapon = Optional.empty();
  }

  private void deleteFromScene(final MutablePosition2D position) {
    final Optional<PhysicalObjectSprite> deleted = this.sprites.deleteSprite(position);
    if (deleted.isPresent()) {
      this.gamePane.getChildren().remove(deleted.get());
    }
  }

  @Override
  public final void generateBullet(final PhysicalObject bullet) throws IOException {
    final PhysicalObjectSpriteFactory factory = new PhysicalObjectSpriteFactoryImpl();
    final PhysicalObjectSprite bulletSprite = factory.generateBulletSprite(bullet);
    this.sprites.addBulletSprite(bulletSprite, bullet.getPosition().get());
    this.gamePane.getChildren().add(bulletSprite);
  }

  public final void startPlayerAnimation() {
    try {
      final PlayerSprite playerSprite =
          (PlayerSprite) this.sprites.getPlayerSprite().get().get(0).getLeft();
      playerSprite.getSpriteAnimation().play();
    } catch (final ClassCastException e) {
      e.printStackTrace();
    }
  }

  @Override
  public final void stopPlayerAnimation() {
    try {
      final PlayerSprite playerSprite =
          (PlayerSprite) this.sprites.getPlayerSprite().get().get(0).getLeft();
      playerSprite.getSpriteAnimation().stop();
    } catch (final ClassCastException e) {
      e.printStackTrace();
    }
  }

  @Override
  public final void autoKill() {
    final Window window = this.getWindow();
    window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
  }

  @Override
  public final void deleteObstacleSpriteImage(final MutablePosition2D position) {
    this.deleteFromScene(position);
  }
}
