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

	public long getCurrentTimeMs() {
		return System.currentTimeMillis();
	}

	public String getDateTimeFormat() {
		this.calendar = Calendar.getInstance(TIME_ZONE);
		return SDF.format(calendar.getTime());
	}

	@Override
	public void run() {
		initStartTime();

		while (isRun) {
			IOManager.buffer.append(getDateTimeFormat());
			IOManager.buffer.append(getCurrentTimeMs() - startTime);

			try {
				IOManager.writer.append(IOManager.buffer);
				IOManager.writer.flush();

				Thread.sleep(1000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}