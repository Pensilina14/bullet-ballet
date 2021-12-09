package it.unibo.pensilina14.bullet.ballet.logging;

public interface SimpleLogger {
    enum Headers {
        FATAL("[!][!][!]"),
        ERROR("{ERR0R}"),
        WARN("[WARNING]"),
        INFO("[INFO]"),
        DEBUG("[DEBUG]");

        private final String header;

        Headers(final String str) {
            this.header = str;
        }

        public String get() {
            return this.header;
        }
    }

    void fatal(String msg);

    void error(String msg);

    void warn(String msg);

    void info(String msg);

    void debug(String msg);
}
