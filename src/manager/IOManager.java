package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOManager implements Runnable {

	public static StringBuilder buffer;
	public static BufferedWriter writer;
	private BufferedReader reader;

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

	public void menu() throws Exception {
		buffer.append(" << 스톱워치 >> \n");
		buffer.append("[1] START\n");
		buffer.append("[2] STOP\n");
		buffer.append("[3] RESET\n");

		writer.append(buffer);
		writer.flush();
	}

	public void input() {
		try {
			String input = reader.readLine();
			int number = -1;
			number = Integer.parseInt(input);
			if (number >= 1 && number <= 3) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (isRun) {
			input();
		}
	}
}
