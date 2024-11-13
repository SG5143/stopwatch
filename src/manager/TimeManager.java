package manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeManager {
	private final TimeZone TIME_ZONE;
	private final SimpleDateFormat SDF;
	
	private Calendar calendar;

	private TimeManager() {
		this.TIME_ZONE = TimeZone.getTimeZone("Asia/Seoul");
		this.SDF = new SimpleDateFormat("hh시 mm분 ss초 | ");
	}

	private static TimeManager instance = new TimeManager();

	public static TimeManager getInstance() {
		return instance;
	}

	public long getCurrentTimeMs() {
		return System.currentTimeMillis();
	}
	
	public String getDateTimeFormat() {
		this.calendar = Calendar.getInstance(TIME_ZONE);
		return SDF.format(calendar.getTime());
	}
}