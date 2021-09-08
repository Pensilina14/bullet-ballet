package it.unibo.pensilina14.bullet.ballet.environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameEnvironment implements Environment {

  private final double gravity;
  private final Dimension2D gameMapDim;
  private Map<ImmutablePosition2D, Optional<PhysicalObject>> gameMap;
  
  /*
   * Constructor method in which {@link gravity} 
   * is set at earth one's value and {@link gameMap}'s 
   * coordinates are automatically set.
   * {@link gameMapSize} set to 500 by default.
   */
  public GameEnvironment() {
    this.gravity = 9.81;
    this.gameMapDim = new Dimension2Dimpl(500, 500);
    //this.gameMap = new HashMap<>();
    //TODO: generate virtual map's coordinates as gameMap keys
  }
  
  /*
   * 
   */
  public GameEnvironment(final double gravity, final Dimension2D size) {
    this.gravity = gravity;
    this.gameMapDim = size;
    this.gameMap = new HashMap<>();
  }
  
  @Override
  public double getGravity() {
    return this.gravity;
  }
  
  @Override
  public Dimension2D getDimension() {
    return this.gameMapDim;
  }

  @Override
  public Map<ImmutablePosition2D, Optional<PhysicalObject>> getMap() {
    return Map.copyOf(this.gameMap);
  }

  @Override
  public Optional<ImmutablePosition2D> findObjInMap(final PhysicalObject obj) {
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
  public Optional<List<PhysicalObject>> getObjListInMap() {
    //TODO
    return Optional.empty();
  }
        
}
