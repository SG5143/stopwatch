package manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeManager implements Runnable {
	private final TimeZone TIME_ZONE;
	private final SimpleDateFormat SDF;

	private Calendar calendar;

	private long startTime;
	private long elapsedTime;
	private boolean isRun = true;

	private TimeManager() {
		this.TIME_ZONE = TimeZone.getTimeZone("Asia/Seoul");
		this.SDF = new SimpleDateFormat("hh시 mm분 ss초 | ");
	}

	private static TimeManager instance = new TimeManager();

	public static TimeManager getInstance() {
		return instance;
	}

	private void initStartTime() {
		this.startTime = getCurrentTimeMs();
	}

	public void resetTime() {
		this.startTime = 0;
		this.elapsedTime = 0;
		IOManager.print("<< 초기화되었습니다 >>");
	}

	public long getStartTime() {
		return this.startTime;
	}

	public long getElapsedTime() {
		return this.elapsedTime;
	}

	private long getCurrentTimeMs() {
		return System.currentTimeMillis();
	}

	public String getDateTimeFormat() {
		this.calendar = Calendar.getInstance(TIME_ZONE);
		return SDF.format(calendar.getTime());
	}

	private void recordElapsedTime() {
		elapsedTime += getCurrentTimeMs() - startTime;
	}

	public void printElapsedTime() {
		try {
			String second = String.format("<< STOP >> %.3f초 소요", (double) elapsedTime / 1000);
			IOManager.print(second);
		} catch (Exception e) {
		}
	}

	public void start() {
		IOManager.print("<< START >>\n");
		initStartTime();
		this.isRun = true;
	}

	public void stop() {
		recordElapsedTime();
		this.isRun = false;
	}

	@Override
	public void run() {
		while (isRun) {
			try {
				IOManager.buffer.append(getDateTimeFormat());
				long milliSecond = elapsedTime + getCurrentTimeMs() - startTime;
				String second = String.format("%.3f초\n", (double) milliSecond / 1000);
				IOManager.print(second);

				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}