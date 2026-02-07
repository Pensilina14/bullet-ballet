package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

import java.util.Optional;
import java.util.Random;

public class Enemy extends GameEntity implements Characters {

  private static final int PATROL_SWITCH_TICKS = 90;
  private static final double PATROL_STEP = 1.2;

  private static final int SHOOT_COOLDOWN_TICKS = 75;
  private static final double SHOOT_STEP = 6.0;
  private static final double SHOOT_RANGE_X = 650.0;
  private static final double SHOOT_RANGE_Y = 120.0;

  private double health;
  private Optional<Double> mana;
  private String name;

  private Optional<Weapon> weapon;

  private EntityList.Characters.Enemy enemyType;

  private final Random rand = new Random();
  private static final double MAX = 100.0;
  private boolean landed;

  private int patrolTick;
  private boolean patrolRight;

  private int shootCooldown;

  private static final double MAX_RANGE = 7.0;

  private final double enemyRange = getRandomRange();

  public Enemy(
      final String name,
      final double health,
      final Optional<Double> mana,
      final Dimension2D dimension,
      final SpeedVector2D vector,
      final Environment environment,
      final double mass) {

    super(vector, environment, mass, dimension);

    this.name = name;
    this.health = health;
    this.mana = mana;

    this.patrolRight = this.rand.nextBoolean();
    this.patrolTick = 0;
    this.shootCooldown = rand.nextInt(SHOOT_COOLDOWN_TICKS + 1);
  }

  public Enemy(
      final EntityList.Characters.Enemy enemyType,
      final Dimension2D dimension,
      final SpeedVector2D vector,
      final Environment environment,
      final double mass) {
    super(vector, environment, mass, dimension);
    this.enemyType = enemyType;
    setEnemyType();

    this.patrolRight = this.rand.nextBoolean();
    this.patrolTick = 0;
    this.shootCooldown = rand.nextInt(SHOOT_COOLDOWN_TICKS + 1);
  }

  public Enemy(
      final Dimension2D dimension,
      final SpeedVector2D vector,
      final Environment environment,
      final double mass) {
    super(vector, environment, mass, dimension);

    setRandomEnemy();
    setEnemyType();

    this.patrolRight = this.rand.nextBoolean();
    this.patrolTick = 0;
    this.shootCooldown = rand.nextInt(SHOOT_COOLDOWN_TICKS + 1);
  }

  private void setRandomEnemy() {
    final int max = EntityList.Characters.Enemy.values().length;
    final int min = 0;

    final int randomEnemy = rand.nextInt((max - min) + min);
    for (final EntityList.Characters.Enemy e : EntityList.Characters.Enemy.values()) {
      if (e.ordinal() == randomEnemy) {
        this.enemyType = e;
      }
    }
  }

  private void setEnemyType() {
    double minHealth;
    double minMana;
    switch (this.enemyType) {
      case ENEMY1:
        minHealth = 80.0; // 70.0
        minMana = 40.0;
        this.name = "Enemy1";
        this.health = this.rand.nextDouble() * (MAX - minHealth) + minHealth;
        this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
        break;
      case ENEMY2:
        minHealth = 60.0;
        minMana = 55.0;
        this.name = "Enemy2";
        this.health = this.rand.nextDouble() * MAX - minHealth + minHealth;
        this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
        break;
      case ENEMY3:
        minHealth = 40.0;
        minMana = 70.0;
        this.name = "Enemy3";
        this.health = this.rand.nextDouble() * (MAX - minHealth) + minHealth;
        this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
        break;
      default:
        break;
    }
  }

  @Override
  public double getHealth() {
    return this.health;
  }

  @Override
  public Optional<Double> getMana() {
    return this.mana;
  }

  @Override
  public boolean isAlive() {
    return this.health > 0.0;
  }

  @Override
  public void setHealth(final double setHealth) {
    this.health = setHealth;
  }

  @Override
  public Optional<Weapon> getWeapon() {
    return this.weapon;
  }

  @Override
  public void setWeapon(final Weapon weapon) {
    this.weapon = Optional.of(weapon);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean manaLeft() {
    return this.mana.filter(i -> i > 0.0).isPresent();
  }

  @Override
  public void decreaseMana(final double decreaseValue) {
    if (this.mana.isPresent()) {
      this.mana = Optional.of(this.mana.get() - decreaseValue);
    }
  }

  @Override
  public void increaseMana(final double increaseValue) {
    if (this.mana.isPresent()) {
      this.mana = Optional.of(this.mana.get() + increaseValue);
    }
  }

  @Override
  public void increaseHealth(final double increaseHealth) {
    this.health += increaseHealth;
  }

  @Override
  public void decreaseHealth(final double decreaseHealth) {
    this.health -= decreaseHealth;
  }

  public EntityList.Characters.Enemy getEnemyType() {
    return this.enemyType;
  }

  @Override
  public void updateState() {
    super.updateState();

    this.updatePatrol();
    this.tryShootAtPlayer();

    if (!this.isAlive()) {
      this.getGameEnvironment()
          .get()
          .deleteObjByPosition(new ImmutablePosition2Dimpl(this.getPosition().get()));
    }
  }

  private void updatePatrol() {
    this.patrolTick++;
    if (this.patrolTick >= PATROL_SWITCH_TICKS) {
      this.patrolTick = 0;
      this.patrolRight = !this.patrolRight;
    }

    final boolean moved = this.patrolRight ? this.moveRight(PATROL_STEP) : this.moveLeft(PATROL_STEP);
    if (!moved) {
      this.patrolRight = !this.patrolRight;
    }
  }

  private void tryShootAtPlayer() {
    if (this.getGameEnvironment().isEmpty()
        || this.getGameEnvironment().get().getEntityManager().getPlayer().isEmpty()) {
      return;
    }

    if (this.shootCooldown > 0) {
      this.shootCooldown--;
      return;
    }

    final var player = this.getGameEnvironment().get().getEntityManager().getPlayer().get();
    final MutablePosition2D playerPos = player.getPosition().get();
    final MutablePosition2D enemyPos = this.getPosition().get();

    final double dx = playerPos.getX() - enemyPos.getX();
    final double dy = Math.abs(playerPos.getY() - enemyPos.getY());
    if (Math.abs(dx) > SHOOT_RANGE_X || dy > SHOOT_RANGE_Y) {
      return;
    }

    // For now enemies shoot only to the left (same direction rule as the player).
    if (dx > 0) {
      return;
    }

    final double step = -SHOOT_STEP;
    final Bullet bullet =
        new BulletFactoryImpl()
            .createEnemyClassicBullet(
                this.getGameEnvironment().get(),
                new SpeedVector2DImpl(
                    new MutablePosition2Dimpl(enemyPos.getX(), enemyPos.getY()), 1.0),
                step);
    this.getGameEnvironment().get().getEntityManager().addBullet(bullet);
    this.shootCooldown = SHOOT_COOLDOWN_TICKS;
  }

  /*
   * Following code could be universalized for every game entity.
   */
  public boolean hasLanded() {
    return this.landed;
  }

  public void land() {
    this.landed = true;
  }

  public void resetLanding() {
    this.landed = false;
  }

  private double getRandomRange() {
    return rand.nextDouble() * Enemy.MAX_RANGE;
  }

  // forse passare l'index dell'enemy come parametro non serve.
  public boolean isPlayerInRange(final int enemyIndex) {
    final double xPlayer =
        this.getGameEnvironment()
            .get()
            .getEntityManager()
            .getPlayer()
            .get()
            .getPosition()
            .get()
            .getX();
    final double yPlayer =
        this.getGameEnvironment()
            .get()
            .getEntityManager()
            .getPlayer()
            .get()
            .getPosition()
            .get()
            .getX();

    final double xEnemy =
        this.getGameEnvironment()
            .get()
            .getEntityManager()
            .getEnemies()
            .get()
            .get(enemyIndex)
            .getPosition()
            .get()
            .getX();
    final double yEnemy =
        this.getGameEnvironment()
            .get()
            .getEntityManager()
            .getEnemies()
            .get()
            .get(enemyIndex)
            .getPosition()
            .get()
            .getY();

    final double distance = Math.sqrt((xPlayer - xEnemy) + (yPlayer - yEnemy));

    return distance <= this.enemyRange;
  }

  public double getRange() {
    return this.enemyRange;
  }

  public double getMaxRange() {
    return Enemy.MAX_RANGE;
  }
}
