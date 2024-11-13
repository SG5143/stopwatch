package manager;

import java.io.IOException;
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
	}

	public long getStartTime() {
		return this.startTime;
	}

	public long getElapsedTime() {
		return this.elapsedTime;
	}

	public long getCurrentTimeMs() {
		return System.currentTimeMillis();
	}

	public String getDateTimeFormat() {
		this.calendar = Calendar.getInstance(TIME_ZONE);
		return SDF.format(calendar.getTime());
	}

	public void recordElapsedTime() {
		elapsedTime += getCurrentTimeMs() - startTime;
	}

	public void start() {
		this.isRun = true;
		initStartTime();
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
				IOManager.buffer.append(elapsedTime + getCurrentTimeMs() - startTime +"\n");
				IOManager.writer.append(IOManager.buffer);
				IOManager.buffer.setLength(0);
				IOManager.writer.flush();
				
				Thread.sleep(1000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				recordElapsedTime();
				e.printStackTrace();
			}
		}
	}
}