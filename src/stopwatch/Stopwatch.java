package stopwatch;

import manager.IOManager;
import manager.TimeManager;

public class Stopwatch {

	private IOManager ioManager;
	private TimeManager timeManager;

	private Thread ioManagerT;
	private Thread timeManagerT;

	private Stopwatch() {
		this.ioManager = IOManager.getInstance();
		this.timeManager = TimeManager.getInstance();
		this.ioManagerT = new Thread(ioManager);
	}

	private static Stopwatch instance = new Stopwatch();

	public static Stopwatch getInstance() {
		return instance;
	}

	public void run() {
		ioManagerT.start();
	}

	public void start() {
		if ((timeManagerT == null || !timeManagerT.isAlive())) {
			timeManager.start();
			timeManagerT = new Thread(timeManager);
			timeManagerT.start();
		}
	}

	public void stop() {
		if (timeManager.getStartTime() != 0) {
			timeManager.stop();
			timeManager.printElapsedTime();
		}
	}

	public void reset() {
		if (timeManagerT != null) {
			timeManager.stop();
			timeManagerT.interrupt();
			timeManagerT = null;
			timeManager.resetTime();
		}
	}
}