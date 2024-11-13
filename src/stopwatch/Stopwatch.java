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
		this.timeManagerT = new Thread(timeManager);
	}

	private static Stopwatch instance = new Stopwatch();

	public static Stopwatch getInstance() {
		return instance;
	}

	public void run() {
		ioManagerT.start();
		timeManagerT.start();
	}

	public void start() {
		if (timeManager.getElapsedTime() == 0 && (timeManagerT == null || !timeManagerT.isAlive())) {
			timeManager.start();
		} else {
			timeManager.start();
		}
	}

	public void stop() {
		if (timeManager.getStartTime() != 0) {
			timeManager.stop();
			if (timeManagerT != null && timeManagerT.isAlive()) {
				timeManagerT.interrupt();
			}
		}
	}

	public void reset() {
		if (timeManager.getStartTime() != 0) {
			timeManager.resetTime();
			timeManager.stop();
			if (timeManagerT != null && timeManagerT.isAlive()) {
				timeManagerT.interrupt();
				timeManagerT = null;
			}
		}
	}

}