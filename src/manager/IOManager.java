package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOManager {

	private StringBuilder buffer;
	private BufferedWriter writer;
	private BufferedReader reader;

	private IOManager() {
		this.buffer = new StringBuilder();
		this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
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

}
