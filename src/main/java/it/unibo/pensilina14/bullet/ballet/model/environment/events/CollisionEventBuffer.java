package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollisionEventBuffer implements EventBuffer {
	
	private final Optional<List<GameEvent>> collisionEvents;
	
	public CollisionEventBuffer() {
		this.collisionEvents = Optional.of(new ArrayList<>());
	}
	
	@Override
	public final void addEvent(final GameEvent event) {
		this.collisionEvents.get().add(event);
	}
	
	@Override
	public final List<GameEvent> getEvents() {
		final List<GameEvent> events = List.copyOf(this.collisionEvents.get());
		this.collisionEvents.get().clear();
		return events;
	}
}
