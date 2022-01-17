package it.unibo.pensilina14.bullet.ballet.logging;

import java.sql.Timestamp;
import java.time.Instant;

public final class AppLogger implements SimpleLogger {
	
	private static final AppLogger SINGLETON = new AppLogger();
	private static final String UNIFIER = " = ";
	
	private AppLogger() {
	}
	
	public static AppLogger getAppLogger() {
		return AppLogger.SINGLETON;
	}
	
	public void debug(final String msg) {
		System.out.println(Headers.DEBUG.get() + UNIFIER + msg);
	}
	
	public void info(final String msg) {
		System.out.println(Headers.INFO.get() + UNIFIER + msg);
	}
	
	public void warn(final String msg) {
		System.out.println(Headers.WARN.get() + UNIFIER + msg);
	}
	
	public void error(final String msg) {
		System.err.println(Headers.ERROR.get() + UNIFIER + msg);
	}
	
	public void fatal(final String msg) {
		System.out.println(Headers.FATAL.get() + UNIFIER + msg);
	}
	
	public void collision(final String msg) {
		System.out.println(Headers.INFO.get() + UNIFIER + msg + Timestamp.from(Instant.now()));
	}
	
}
