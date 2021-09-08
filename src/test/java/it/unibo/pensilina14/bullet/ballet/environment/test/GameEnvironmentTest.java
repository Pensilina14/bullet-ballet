package it.unibo.pensilina14.bullet.ballet.environment.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.environment.PhysicalObject;

public class GameEnvironmentTest {

  @Test
  public void testGameEnvironment() {
    final Environment gameEnv = new GameEnvironment();
    
    assertTrue(gameEnv.getGravity() == 9.81);
    assert
    final Map<ImmutablePair<Integer, Integer>, Optional<PhysicalObject>> actual_map = 
    		Map.of(ImmutablePair.of(left, right));
  }
}
