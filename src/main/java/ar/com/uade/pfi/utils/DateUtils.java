package ar.com.uade.pfi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public DateUtils() {
	
	}

	public static String getTodayString() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

}
