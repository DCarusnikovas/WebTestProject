package CoreSteps;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateCoreSteps {
	
	public static String getCurrentDateTime(String format) {
		Calendar cal= Calendar.getInstance();
		return new SimpleDateFormat(format).format(cal.getTime());
	}

}
