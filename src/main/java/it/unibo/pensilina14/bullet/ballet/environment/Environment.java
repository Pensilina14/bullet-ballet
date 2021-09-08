package it.unibo.pensilina14.bullet.ballet.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * This interface wraps all the virtual game world
 * and permits interaction between objects inside a virtual map.
 */
public interface Environment {
  /*
   * @return environment's gravity.
   */
  double getGravity();
  
  /*
   * @return environment's dimension composed by height and width,
   * they refer to the imaginary grid that fills the virtual map.
   */
  Dimension2D getDimension();
  
  /*
   * @return a {@link #Map} of coordinates and {@link PhysicalObject}s representing the map.
   */
  Map<ImmutablePosition2D, Optional<PhysicalObject>> getMap();

  /*
   * Finds the given @param obj in the map: 
   * 
   * if found, @return location in coordinates;
   * else @return {@link Optional#Empty}.
   */
  Optional<ImmutablePosition2D> findObjInMap(PhysicalObject obj);
  
  /*
   * @return a {@link List} containing all the current game objects
   * present in the game map.
   */
  Optional<List<PhysicalObject>> getObjListInMap();
}
