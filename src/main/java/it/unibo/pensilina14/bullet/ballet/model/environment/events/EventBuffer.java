package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import java.util.List;

public interface EventBuffer {
	void addEvent(GameEvent event);
	List<GameEvent> getEvents();
}
