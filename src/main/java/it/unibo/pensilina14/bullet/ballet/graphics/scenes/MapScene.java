package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpriteContainer;
import it.unibo.pensilina14.bullet.ballet.common.SpriteManager;
import it.unibo.pensilina14.bullet.ballet.core.GameEngine;
import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.GameMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Maps;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSprite;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactory;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PhysicalObjectSpriteFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.PlayerSprite;
import it.unibo.pensilina14.bullet.ballet.input.Left;
import it.unibo.pensilina14.bullet.ballet.input.Right;
import it.unibo.pensilina14.bullet.ballet.input.Space;
import it.unibo.pensilina14.bullet.ballet.input.Up;
import it.unibo.pensilina14.bullet.ballet.Game;
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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.geometry.Rectangle2D;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.util.Optional;

public class MapScene extends AbstractScene implements GameView {

  private static final String PAUSE_ACTION_KEY = "pauseAction";
  private static final String PAUSE_ACTION_RETRY = "retry";
  private static final String PAUSE_ACTION_MENU = "menu";

  private final Pane appPane = new StackPane();
  private final Pane gamePane = new Pane();
  private final Pane uiPane = new Pane();
  private final Pane backgroundPane = new Pane();
  private ImageView backgroundView1;
  private ImageView backgroundView2;
  private double backgroundScrollX;

  private final StackPane gameOverOverlay = new StackPane();
  private boolean isGameOver;

  private ProgressBar healthBar;
  private Label healthPercentLabel;
  private Label scoreLabel;
  private HBox ammoBox;
  private Image ammoIconImage;
  private int lastAmmoShown = -1;
  private static final int MAX_AMMO_ICONS = 10;

  private final GameMap map = new BackgroundMap();
  private final SpriteManager sprites;
  private Optional<MutablePair<PhysicalObjectSprite, MutablePosition2D>> mainWeapon;
  private final GameState gameState;
  private Optional<GameEngine> controller;
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
    this.isGameOver = false;

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

    // appPane is a StackPane: anchor layers to TOP_LEFT to avoid extra centering that breaks HUD.
    StackPane.setAlignment(this.backgroundPane, Pos.TOP_LEFT);
    StackPane.setAlignment(this.gamePane, Pos.TOP_LEFT);
    StackPane.setAlignment(this.uiPane, Pos.TOP_LEFT);

    // Ensure HUD alignment is relative to the whole viewport.
    this.uiPane.setMinSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.uiPane.setPrefSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.uiPane.setMaxSize(Resolutions.FULLHD.getWidth(), Resolutions.FULLHD.getHeight());
    this.uiPane.setPickOnBounds(false);

    setupHud();
    setupGameOverOverlay();
    this.appPane.getChildren().add(this.gameOverOverlay);

    // Allow leaving the game-over overlay even when the engine is stopped.
    this.addEventFilter(
        KeyEvent.KEY_PRESSED,
        e -> {
          if (this.isGameOver && e.getCode() == KeyCode.ENTER) {
            try {
              final javafx.stage.Stage stage = (javafx.stage.Stage) this.getWindow();
              final PageLoader loader = new PageLoaderImpl();
              loader.loadFirstScene(stage);

              // The game sets a fixed resolution and non-resizable stage.
              // Reset the stage so the menu isn't stuck with the game sizing.
              javafx.application.Platform.runLater(
                  () -> {
                    stage.setFullScreen(false);
                    stage.setMaximized(false);
                    stage.setResizable(true);
                    stage.sizeToScene();
                    stage.centerOnScreen();
                  });
            } catch (final IOException ex) {
              ex.printStackTrace();
            }
            e.consume();
          }
        });

    try {
      this.initialize();
    } catch (final IOException exc) {
      exc.printStackTrace();
      AppLogger.getAppLogger()
          .error("IOException, probably caused by a problem with components sprite imgs.");
    }
  }

  private void setupHud() {
    // Health bar + percentage (top-left)
    this.healthBar = new ProgressBar(1.0);
    this.healthBar.setPrefWidth(260);
    this.healthBar.setMaxWidth(260);
    this.healthBar.setStyle("-fx-accent: red;");

    this.healthPercentLabel = new Label("100%");
    // Must be different from the bar color.
    this.healthPercentLabel.setTextFill(javafx.scene.paint.Color.WHITE);
    this.healthPercentLabel.setFont(javafx.scene.text.Font.font(18));

    final StackPane healthBox = new StackPane(this.healthBar, this.healthPercentLabel);
    healthBox.setMaxWidth(260);
    this.uiPane.getChildren().add(healthBox);
    healthBox.setLayoutX(20);
    healthBox.setLayoutY(20);

    // Score with coin icon above (top-center)
    final String coinUrl =
        String.valueOf(getClass().getClassLoader().getResource(this.map.getCoinType().getPath()));
    final ImageView coinIcon = new ImageView(new Image(coinUrl));
    coinIcon.setSmooth(false);
    coinIcon.setPreserveRatio(true);
    coinIcon.setFitHeight(36);

    this.scoreLabel = new Label("0");
    this.scoreLabel.setTextFill(javafx.scene.paint.Color.RED);
    this.scoreLabel.setFont(javafx.scene.text.Font.font(22));

    final VBox scoreBox = new VBox(4, coinIcon, this.scoreLabel);
    scoreBox.setAlignment(Pos.TOP_CENTER);
    this.uiPane.getChildren().add(scoreBox);
    scoreBox.setLayoutY(14);
    scoreBox
      .layoutXProperty()
      .bind(this.uiPane.widthProperty().subtract(scoreBox.widthProperty()).divide(2.0));

    // Ammo (top-right)
    final String ammoUrl =
      String.valueOf(
        getClass().getClassLoader().getResource(Images.AMMO.getFileName()));
    this.ammoIconImage = new Image(ammoUrl);
    this.ammoBox = new HBox(4);
    this.ammoBox.setAlignment(Pos.CENTER_RIGHT);
    this.uiPane.getChildren().add(this.ammoBox);
    this.ammoBox.setLayoutY(20);
    this.ammoBox
      .layoutXProperty()
      .bind(this.uiPane.widthProperty().subtract(this.ammoBox.widthProperty()).subtract(150.0));
  }

  private void setupGameOverOverlay() {
    this.gameOverOverlay.setVisible(false);
    this.gameOverOverlay.setPickOnBounds(true);
    this.gameOverOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.65);");
    this.gameOverOverlay.prefWidthProperty().bind(this.appPane.widthProperty());
    this.gameOverOverlay.prefHeightProperty().bind(this.appPane.heightProperty());

    final Label title = new Label("GAME OVER");
    title.setTextFill(javafx.scene.paint.Color.RED);
    title.setFont(javafx.scene.text.Font.font(56));

    final ImageView playerPreview =
        new ImageView(
            new Image(
                String.valueOf(
                    getClass().getClassLoader().getResource(Images.PLAYER.getFileName()))));
    playerPreview.setSmooth(false);
    playerPreview.setPreserveRatio(true);
    playerPreview.setFitHeight(220);

    final Label scoreTitle = new Label("Score");
    scoreTitle.setTextFill(javafx.scene.paint.Color.RED);
    scoreTitle.setFont(javafx.scene.text.Font.font(22));
    final Label scoreValue = new Label("0");
    scoreValue.setId("gameOverScoreValue");
    scoreValue.setTextFill(javafx.scene.paint.Color.RED);
    scoreValue.setFont(javafx.scene.text.Font.font(32));

    final Label healthTitle = new Label("Health");
    healthTitle.setTextFill(javafx.scene.paint.Color.RED);
    healthTitle.setFont(javafx.scene.text.Font.font(22));
    final Label healthValue = new Label("0%");
    healthValue.setId("gameOverHealthValue");
    healthValue.setTextFill(javafx.scene.paint.Color.RED);
    healthValue.setFont(javafx.scene.text.Font.font(28));

    final VBox scoreCol = new VBox(6, scoreTitle, scoreValue);
    scoreCol.setAlignment(Pos.CENTER);
    final VBox healthCol = new VBox(6, healthTitle, healthValue);
    healthCol.setAlignment(Pos.CENTER);
    final HBox statsRow = new HBox(60, scoreCol, healthCol);
    statsRow.setAlignment(Pos.CENTER);

    final Label hint = new Label("Premi ENTER per tornare al menu");
    hint.setTextFill(javafx.scene.paint.Color.RED);
    hint.setFont(javafx.scene.text.Font.font(18));

    final VBox content = new VBox(18, title, playerPreview, statsRow, hint);
    content.setAlignment(Pos.CENTER);
    this.gameOverOverlay.getChildren().add(content);
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
    if (this.isGameOver) {
      return;
    }
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
            final Object actionObj = window.getProperties().get(PAUSE_ACTION_KEY);
            final String action = actionObj instanceof String ? (String) actionObj : "";

            if (PAUSE_ACTION_MENU.equals(action)) {
              if (owner instanceof javafx.stage.Stage) {
                final javafx.stage.Stage gameStage = (javafx.stage.Stage) owner;
                try {
                  final PageLoader loader = new PageLoaderImpl();
                  loader.loadFirstScene(gameStage);
                  javafx.application.Platform.runLater(
                      () -> {
                        gameStage.setFullScreen(false);
                        gameStage.setMaximized(false);
                        gameStage.setResizable(true);
                        gameStage.sizeToScene();
                        gameStage.centerOnScreen();
                      });
                } catch (final IOException ex) {
                  ex.printStackTrace();
                }
              }
              return;
            }

            if (PAUSE_ACTION_RETRY.equals(action)) {
              if (owner instanceof javafx.stage.Stage) {
                final javafx.stage.Stage gameStage = (javafx.stage.Stage) owner;
                final Game game = new Game(this.gameState.getPlayerName());
                final AbstractScene gameScene = game.getView();

                final Screen currentScreen =
                    Screen.getScreensForRectangle(
                            gameStage.getX(),
                            gameStage.getY(),
                            gameStage.getWidth(),
                            gameStage.getHeight())
                        .stream()
                        .findFirst()
                        .orElse(Screen.getPrimary());
                final Rectangle2D bounds = currentScreen.getVisualBounds();

                Resolutions chosen = game.getSettings().getCurrentResolution();
                if (chosen.getWidth() > bounds.getWidth() || chosen.getHeight() > bounds.getHeight()) {
                  chosen = Resolutions.bestFit(bounds.getWidth(), bounds.getHeight());
                }
                game.getSettings().setResolution(chosen);

                gameStage.setResizable(false);
                gameStage.setWidth(chosen.getWidth());
                gameStage.setHeight(chosen.getHeight());
                gameScene.setHeight(gameStage.getHeight());
                gameScene.setWidth(gameStage.getWidth());
                gameStage.setScene(gameScene);
                gameStage.show();
                gameStage.centerOnScreen();
                game.start();
              }
              return;
            }

            // Default: resume game.
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

    // HUD update
    if (env.getEntityManager().getPlayer().isPresent()) {
      final var player = env.getEntityManager().getPlayer().get();
      final double health = player.getHealth();
      final double healthProgress = Math.max(0.0, Math.min(1.0, health / 100.0));
      this.healthBar.setProgress(healthProgress);
      this.healthPercentLabel.setText(String.format("%.0f%%", Math.max(0.0, health)));

      this.scoreLabel.setText(String.valueOf(player.getCurrentScore().showScore()));

      final int ammo =
          (player.hasWeapon() && player.getWeapon().isPresent())
              ? player.getWeapon().get().getAmmoLeft()
              : 0;
      if (ammo != this.lastAmmoShown) {
        this.lastAmmoShown = ammo;
        this.ammoBox.getChildren().clear();

        final int iconsToShow = Math.min(ammo, MAX_AMMO_ICONS);
        for (int i = 0; i < iconsToShow; i++) {
          final ImageView icon = new ImageView(this.ammoIconImage);
          icon.setSmooth(false);
          icon.setPreserveRatio(true);
          icon.setFitHeight(26);
          this.ammoBox.getChildren().add(icon);
        }

        if (ammo > MAX_AMMO_ICONS) {
          final Label overflow = new Label("... " + ammo);
          overflow.setTextFill(javafx.scene.paint.Color.RED);
          overflow.setFont(javafx.scene.text.Font.font(20));
          this.ammoBox.getChildren().add(overflow);
        }
      }
    }
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

  @Override
  public final void showGameOverOverlay() {
    this.isGameOver = true;
    this.gameOverOverlay.setVisible(true);
    this.gameOverOverlay.toFront();

    final Environment env = this.gameState.getGameEnvironment();
    if (env.getEntityManager().getPlayer().isPresent()) {
      final var player = env.getEntityManager().getPlayer().get();

      final javafx.scene.Node scoreNode = this.gameOverOverlay.lookup("#gameOverScoreValue");
      if (scoreNode instanceof Label) {
        ((Label) scoreNode).setText(String.valueOf(player.getCurrentScore().showScore()));
      }

      final javafx.scene.Node healthNode = this.gameOverOverlay.lookup("#gameOverHealthValue");
      if (healthNode instanceof Label) {
        ((Label) healthNode).setText(String.format("%.0f%%", Math.max(0.0, player.getHealth())));
      }
    }
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
