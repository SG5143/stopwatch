package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import stopwatch.Stopwatch;

public class IOManager implements Runnable {

	public static final int START = 1;
	public static final int STOP = 2;
	public static final int RESET = 3;

	private static StringBuilder buffer;
	private static BufferedWriter writer;
	private BufferedReader reader;

	private Stopwatch stopwatch;

	private int selectMenu;
	private boolean isRun = true;

	private IOManager() {
		IOManager.buffer = new StringBuilder();
		IOManager.writer = new BufferedWriter(new OutputStreamWriter(System.out));
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	private static IOManager instance = new IOManager();

	public static IOManager getInstance() {
		return instance;
	}

	private void menu() {
		try {
			buffer.append(" << 스톱워치 >> \n");
			buffer.append("[1] START\n");
			buffer.append("[2] STOP\n");
			buffer.append("[3] RESET\n");

			writer.append(buffer);
			buffer.setLength(0);
			writer.flush();

		} catch (IOException e) {
		}
	}

	private void input() {
		try {
			String input = reader.readLine();
			int number = -1;
			number = Integer.parseInt(input);
			if (number >= 1 && number <= 3)
				selectMenu = number;
		} catch (Exception e) {
		}
	}

	public static void print(String msg) {
		try {
			buffer.append(msg);
			writer.append(buffer);
			buffer.setLength(0);
			writer.flush();
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		menu();
		while (isRun) {
			input();
			stopwatch = Stopwatch.getInstance();
			switch (selectMenu) {
			case START -> stopwatch.start();
			case STOP -> stopwatch.stop();
			case RESET -> stopwatch.reset();
			}
		}
	}
}