package it.unibo.pensilina14.bullet.ballet.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

public final class AppLogger {
	
	private static final AppLogger SINGLETON = new AppLogger();
	private final Logger logger;
	
	private AppLogger() {
		this.logger = (Logger) LogManager.getLogger("BulletBallet");

		final ConsoleAppender consoleAppender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
		this.logger.addAppender(consoleAppender);
	}
	
	public static AppLogger getAppLogger() {
		return AppLogger.SINGLETON;
	}
	
	public void debug(final String msg) {
		this.logger.debug(msg);
	}
	
	public void info(final String msg) {
		this.logger.info(msg);
	}
	
	public void warn(final String msg) {
		this.logger.warn(msg);
	}
	
	public void error(final String msg) {
		this.logger.error(msg);
	}
	
	public void fatal(final String msg) {
		this.logger.fatal(msg);
	}
}
