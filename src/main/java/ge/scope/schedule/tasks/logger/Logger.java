
package ge.scope.schedule.tasks.logger;

import java.io.PrintStream;

public class Logger {

	private static Logger logger = new Logger();

	private Logger() {
	}

	public static Logger getLogger() {
		return Logger.logger;
	}

	private void log(Level level, Object msg) {
		level.getPrintStream().println("[" + level + "]: " + msg);
	}

	public void info(Object msg) {
		this.log(Level.INFO, msg);
	}

	public void error(Object msg) {
		this.log(Level.ERROR, msg);
	}

	private enum Level {
		INFO(System.out),
		ERROR(System.err);

		private PrintStream out;

		private Level(PrintStream out) {
			this.out = out;
		}

		public PrintStream getPrintStream() {
			return this.out;
		}
	}
}
