package stopwatch;

public class Stopwatch {

	private Stopwatch() {

	}

	private static Stopwatch instance = new Stopwatch();

	public static Stopwatch getInstance() {
		return instance;
	}

	public void run() {

	}
}