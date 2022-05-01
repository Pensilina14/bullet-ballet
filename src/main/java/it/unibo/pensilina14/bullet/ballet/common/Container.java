package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Specifies a contract applicable to every class that needs to store informations in a
 * shelves-style mode. This means that implementations of this interface must have a data structure
 * for each shelf.
 *
 * @param <X> is the type of the container elements.
 */
public interface Container<X> {
  /**
   * Reveals if a container is empty or not.
   *
   * @return True if container isn't empty. False otherwise.
   */
  boolean isEmpty();
  /**
   * Returns the container data structure.
   *
   * @return container, a data structure composed by a {@link Map} which keys are {@link
   *     GameEntities} and actual entities or sprites of the entities, wrapped up in a {@link List}.
   */
  Map<GameEntities, Optional<List<X>>> getContainer();
}
