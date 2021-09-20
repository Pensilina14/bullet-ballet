package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.exceptions.NoDynamicObjectsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Environment}.
 * 
 * {@inheritDoc}
 */
public class GameEnvironment implements Environment {

	private final double gravity;
	private final Dimension2D gameMapDim;
	private final Map<ImmutablePosition2D, Optional<PhysicalObject>> gameMap;
	/**
	 * DEFAULT_DIM is the default size used to create a new
	 * {@link Dimension2D}.
	 */
	public static final int DEFAULT_DIM = 500;
	/**
	 * EARTH_GRAVITY represents earth's {@link gravity} value.
	 */
	public static final double EARTH_GRAVITY = 9.81;

	/**
	 * Constructor method in which {@link gravity} is set at earth one's value and
	 * {@link gameMap}'s coordinates are automatically set. {@link gameMapSize} set
	 * to 500 by default.
	 */
	public GameEnvironment() {
		this.gravity = EARTH_GRAVITY;
		this.gameMapDim = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
		// generate virtual map's coordinates as gameMap keys
		this.gameMap = new HashMap<>();
		this.initializeMap(this.gameMap, this.gameMapDim);
	}

	/**
	 * Constructor method in which {@link gravity} is set at @param gravity and
	 * {@link Dimension2D} is set at @param size {@link gameMap} is initialized
	 */
	public GameEnvironment(final double gravity, final Dimension2D size) {
		this.gravity = gravity;
		this.gameMapDim = size;
		this.gameMap = new HashMap<>();
		this.initializeMap(this.gameMap, this.gameMapDim);
	}

	/**
	 * Generates virtual map's coordinates as gameMap keys.
	 */
	private void initializeMap(final Map<ImmutablePosition2D, Optional<PhysicalObject>> map, final Dimension2D mapDim) {
		for (int x = 0; x < mapDim.getWidth(); x++) {
			for (int y = 0; y < mapDim.getHeight(); y++) {
				map.put(new ImmutablePosition2Dimpl(x, y), Optional.empty());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double getGravity() {
		return this.gravity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Dimension2D getDimension() {
		return this.gameMapDim;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<ImmutablePosition2D, Optional<PhysicalObject>> getMap() {
		return Map.copyOf(this.gameMap);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean addObjToMap(final PhysicalObject obj, final ImmutablePosition2D head) {
		if (this.gameMap.containsKey(head) && this.gameMap.get(head).equals(Optional.empty())) {
			this.gameMap.put(head, Optional.ofNullable(obj));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteObjByPosition(final ImmutablePosition2D position) {
		this.gameMap.remove(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Optional<ImmutablePosition2D> findObjInMap(final PhysicalObject obj) {
		for (final Map.Entry<ImmutablePosition2D, Optional<PhysicalObject>> entry : this.gameMap.entrySet()) {
			if (entry.getValue().isPresent() && entry.getValue().get().equals(obj)) {
				return Optional.of(new ImmutablePosition2Dimpl(obj.getPosition().getX(), obj.getPosition().getY()));
			}
		}
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Optional<List<PhysicalObject>> getObjListInMap() {
		final List<PhysicalObject> objList = new ArrayList<>();
		this.gameMap.entrySet().stream().filter(e -> e.getValue().equals(Optional.empty()))
				.collect(Collectors.toList());
		return Optional.ofNullable(objList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateState(final int dt) { 
		final Optional<List<PhysicalObject>> gameObjs = this.getObjListInMap();
		if (gameObjs.isPresent()) {
			try {
				final Optional<List<AbstractDynamicComponent>> dynamicObjs = this.getDynamicObjs(gameObjs.get());
				if (dynamicObjs.isPresent()) {
	            	dynamicObjs.get().stream().forEach(obj -> obj.updateState(dt));
	            }
		    } catch (final NoDynamicObjectsException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * Given a {@link List} of objects that extend(in this case implement) {@link PhysicalObject} interface.
	 * 
	 * @param gameObjs
	 * @return an {@link Optional} {@link List} of {@link AbstractDynamicComponent} 
	 * @throws NoDynamicObjectsException
	 */
	private Optional<List<AbstractDynamicComponent>> getDynamicObjs(final List<? extends PhysicalObject> gameObjs) throws NoDynamicObjectsException {
		try {
			final Optional<List<AbstractDynamicComponent>> dynamicObjs = Optional.ofNullable(
					(List<AbstractDynamicComponent>) gameObjs.stream()
	        		.filter(obj -> obj instanceof AbstractDynamicComponent)
	        		.map(obj -> (AbstractDynamicComponent) obj)
	        		.collect(Collectors.toList()));
			if (dynamicObjs.isEmpty()) {
				throw new NoDynamicObjectsException(this);
			} 
			return dynamicObjs;
		} catch (final ClassCastException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
