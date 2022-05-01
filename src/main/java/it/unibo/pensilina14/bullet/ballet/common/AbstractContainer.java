package it.unibo.pensilina14.bullet.ballet.common;

import java.util.*;
import java.util.stream.IntStream;

public abstract class AbstractContainer<X> implements Container<X> {
  /** Data structure that keeps track of every single element of the game. */
  private final Map<GameEntities, Optional<List<X>>> container;

  public AbstractContainer() {
    this.container = new HashMap<>();
    IntStream.iterate(0, x -> x + 1)
        .limit(GameEntities.count())
        .forEach(
            x -> this.container.put(GameEntities.getList().get(x), Optional.of(new ArrayList<>())));
  }

  @Override
  public final boolean isEmpty() {
    return !(this.container.values().stream().anyMatch(l -> !l.isEmpty())); // !!!
  }

  @Override
  public final Map<GameEntities, Optional<List<X>>> getContainer() {
    return this.container;
  }
}
