package it.unibo.pensilina14.bullet.ballet.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.game.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.ImmutablePosition2D;

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
   * @param obj is the object that will be added to the map along with 
   * its head position in the map.
   * 
   * @param pos represents the head position of the given PhysicalObject.
   * 
   * The head of a {@link PhysicalObject} is meant to represent the upper-left corner
   * of the imaginary box that wraps it up.
   * 
   * @return true only if obj has been succesfully put in the map.
   * @return false if it's not possible to do that.
   * Example: {@link ImmutablePosition2D} already occupied by another {@link PhysicalObject}.
   */
  boolean addObjToMap(PhysicalObject obj, ImmutablePosition2D head);
  
  /*
   * @param position is the position of the object to be deleted.
   * 
   * The {@link PhysicalObject} to be deleted is first searched and
   * then removed from the map.
   */
  void deleteObjByPosition(ImmutablePosition2D position);
  
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
