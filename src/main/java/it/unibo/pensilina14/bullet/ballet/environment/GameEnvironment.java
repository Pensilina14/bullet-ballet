package it.unibo.pensilina14.bullet.ballet.environment;

import it.unibo.pensilina14.bullet.ballet.game.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.ImmutablePosition2Dimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of Environment.
 */
public class GameEnvironment implements Environment {

  private final double gravity;
  private final Dimension2D gameMapDim;
  private final Map<ImmutablePosition2D, Optional<PhysicalObject>> gameMap;
  public static final int DEFAULT_DIM = 500;

  /*
   * Constructor method in which {@link gravity} 
   * is set at earth one's value and {@link gameMap}'s 
   * coordinates are automatically set.
   * {@link gameMapSize} set to 500 by default.
   */
  public GameEnvironment() {
    this.gravity = 9.81;
    this.gameMapDim = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
    //generate virtual map's coordinates as gameMap keys
    this.gameMap = new HashMap<>();
    this.initializeMap(this.gameMap, this.gameMapDim);
  }

  /*
   * Constructor method in which {@link gravity}
   * is set at @param gravity and {@link Dimension2D} is 
   * set at @param size 
   * {@link gameMap} is initialized
   */
  public GameEnvironment(final double gravity, final Dimension2D size) {
    this.gravity = gravity;
    this.gameMapDim = size;
    this.gameMap = new HashMap<>();
    this.initializeMap(this.gameMap, this.gameMapDim);
  }

  /*
   * Generates virtual map's coordinates as gameMap keys.
   */
  private final void initializeMap(final Map<ImmutablePosition2D, Optional<PhysicalObject>> map, 
      final Dimension2D mapDim) {
    for (int x = 0; x < mapDim.getWidth(); x++) { 
      for (int y = 0; y < mapDim.getHeight(); y++) {
        map.put(new ImmutablePosition2Dimpl(x, y), Optional.empty());
      }
    }
  }

  @Override
  public final double getGravity() {
    return this.gravity;
  }

  @Override
  public final Dimension2D getDimension() {
    return this.gameMapDim;
  }

  @Override
  public final Map<ImmutablePosition2D, Optional<PhysicalObject>> getMap() {
    return Map.copyOf(this.gameMap);
  }

  @Override
  public final boolean addObjToMap(final PhysicalObject obj, final ImmutablePosition2D pos) {
    if (this.gameMap.containsKey(pos) && this.gameMap.get(pos).equals(Optional.empty())) {
      this.gameMap.put(pos, Optional.ofNullable(obj));
      return true;
    } else {
      return false;
    }
  }

  @Override
  public final void deleteObjByPosition(final ImmutablePosition2D position) {
    this.gameMap.remove(position);
  }

  @Override
  public final Optional<ImmutablePosition2D> findObjInMap(final PhysicalObject obj) {
    for (final Map.Entry<ImmutablePosition2D, Optional<PhysicalObject>> 
            entry : this.gameMap.entrySet()) {
      if (entry.getValue().get().equals(obj)) {
        return Optional.of(
          new ImmutablePosition2Dimpl(obj.getPosition().getX(), 
            obj.getPosition().getY()
        ));
      }
    } 
    return Optional.empty();
  }


  @Override
  public final Optional<List<PhysicalObject>> getObjListInMap() {
    final List<PhysicalObject> objList = new ArrayList<>();
    this.gameMap.entrySet().stream()
        .filter(e -> e.getValue().equals(Optional.empty()))
        .collect(Collectors.toList());
    return Optional.ofNullable(objList);
  } 
}
