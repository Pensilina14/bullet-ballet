package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import java.util.List;
import java.util.Map;

public interface EventBuffer {
	void addEvent(GameEvent event);
	List<GameEvent> getEvents();
}
