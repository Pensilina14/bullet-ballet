package it.unibo.pensilina14.bullet.ballet.environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class GameEnvironment implements Environment {

  private final double gravity;
  private final int gameMapSize;
  private Map<ImmutablePosition2D<Integer, Integer>, Optional<PhysicalObject>> gameMap;
  
  /*
   * Constructor method in which {@link gravity} 
   * is set at earth one's value and {@link gameMap}'s 
   * coordinates are automatically set.
   * {@link gameMapSize} set to 500 by default.
   */
  public GameEnvironment() {
    this.gravity = 9.81;
    this.gameMapSize = 500;
    //this.gameMap = new HashMap<>();
    int i = 0;
    //TODO: generate virtual map's coordinates as gameMap keys
  }
  
  /*
   * 
   */
  public GameEnvironment(final double gravity, final int size) {
    this.gravity = gravity;
    this.gameMapSize = size;
    this.gameMap = new HashMap<>();
  }
  
  @Override
  public double getGravity() {
    return this.gravity;
  }

  @Override
  public Map<ImmutablePair<Integer, Integer>, Optional<PhysicalObject>> getMap() {
    return Map.copyOf(this.gameMap);
  }

  @Override
  public Optional<ImmutablePair<Integer, Integer>> findObjInMap(final PhysicalObject obj) {
    for (final Map.Entry<ImmutablePair<Integer, Integer>, Optional<PhysicalObject>> 
            entry : this.gameMap.entrySet()) {
      if (entry.getValue().get().equals(obj)) {
        return Optional.of(ImmutablePair.of(obj.getPosition().getX(), obj.getPosition().getY()));
      }
    } 
    return Optional.empty();
  }


  @Override
  public Optional<List<PhysicalObject>> getObjListInMap() {
    //TODO
    return Optional.empty();
  }
        
}
