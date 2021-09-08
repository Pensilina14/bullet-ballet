package it.unibo.pensilina14.bullet.ballet.environment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import it.unibo.pensilina14.bullet.ballet.environment.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.environment.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.environment.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.environment.PhysicalObject;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

public class GameEnvironmentTest {

  @Test
  public void testGameEnvironment() {
    final Environment gameEnv = new GameEnvironment();
    
    assertEquals(gameEnv.getGravity(), 9.81);
    assertSame(gameEnv.getDimension(), new Dimension2Dimpl(500, 500));
    
    final Map<ImmutablePosition2D, Optional<PhysicalObject>> actual_map = null;
    //Map.of(new ImmutablePosition2Dimpl(0, 0), Optional.empty(),
    //TODO: generate map ---> streams <3
  }
}
