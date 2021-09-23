package it.unibo.pensilina14.bullet.ballet.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import it.unibo.pensilina14.bullet.ballet.input.Command;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;

public class GameEngine implements Controller {
	
	private static final int QUEUE_CAPACITY = 100;
	
	private final long period = 20;
	
	private Environment world;
	/*
	 * TODO: add view
	 */
	private final BlockingQueue<Command> cmdQueue;
	
	public GameEngine() {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
	}
	
	public void setup() {
		this.world = new GameEnvironment();
		/*
		 * TODO: add entities to the world
		 */
		/*
		 * TODO: init view
		 */
	}
	
	public void mainLoop() {
	    long lastTime = System.currentTimeMillis();
		while (true) {
			final long current = System.currentTimeMillis();
			final int elapsed = (int) (current - lastTime);
			this.processInput();
			this.updateGame(elapsed);
			this.render();
			this.waitForNextFrame(elapsed);
			lastTime = current;
		}
	}
	
	private void waitForNextFrame(final long current) {
		final long dt = System.currentTimeMillis() - current;
		if (dt < this.period) {
			try {
				Thread.sleep(period - dt);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processInput() {
		final Command cmd = this.cmdQueue.poll();
		if (cmd != null) {
			cmd.execute(this.world);
		}
	}
	
	private void updateGame(final int elapsed) {
		this.world.updateState(elapsed);
	}
	
	private void render() {
		/*
		 * TODO: call view's render method
		 */
	}
	
	
	@Override
	public void notifyCommand(final Command cmd) {
        this.cmdQueue.add(cmd);
	}

}
