package it.unibo.pensilina14.bullet.ballet.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;

public abstract class AbstractContainer<X> implements Container<X> {
	/**
	 * 
	 */
	private final Map<GameEntities, Optional<List<X>>> container;
	
	public AbstractContainer() {
		this.container = new HashMap<>();
		IntStream.iterate(0, x -> x + 1)
		.limit(GameEntities.count())
		.forEach(x -> this.container.put(GameEntities.getList().get(x), Optional.of(new ArrayList<>())));
		AppLogger.getAppLogger().debug(container.toString());
	}
	
	@Override
	public boolean isEmpty() {
		return !(this.container.values().stream()
			   .anyMatch(l -> !l.isEmpty())); //!!!
	} 
	
	@Override
	public Map<GameEntities, Optional<List<X>>> getContainer() {
		return this.container;
	}
}
