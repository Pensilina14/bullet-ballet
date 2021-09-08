package it.unibo.pensilina14.bullet.ballet.environment;
import org.apache.commons.lang3.tuple.*;
import java.util.Map;
import java.util.Optional;

/**
 * 
 *
 */
public interface Environment {
	/*
	 * @return environment's gravity.
	 */
	double getGravity();
	/*
	 * @return a {@link #Map} of coordinates and {@link PhysicalObject}s representing the map
	 */
	Map<Pair<Integer, Integer>, Optional<PhysicalObject>> getMap();
	/*
	 * Finds the given {@param in the map: 
	 * 
	 * if found, @return location in coordinates;
	 * else @return {@link Optional#Empty}
	 */
	Optional<Pair<Integer, Integer>> findObjInMap(PhysicalObject obj);
}
