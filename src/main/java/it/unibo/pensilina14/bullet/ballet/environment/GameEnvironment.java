package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.*;
import java.util.Map;
import java.util.Optional;

public class GameEnvironment implements Environment {

	@Override
	public double getGravity() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public Map<ImmutablePair<Integer, Integer>, Optional<PhysicalObject>> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ImmutablePair<Integer, Integer>> findObjInMap(PhysicalObject obj) {
		// TODO Auto-generated method stub
		return null;
	}
        
}
