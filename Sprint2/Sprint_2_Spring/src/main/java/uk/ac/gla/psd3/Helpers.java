package uk.ac.gla.psd3;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helpers {
	public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("MM/dd/yyyy");

	public static short dayOfWeekToIndex(int dow){
		switch (dow){
		case Calendar.MONDAY: return 0;
		case Calendar.TUESDAY: return 1;
		case Calendar.WEDNESDAY: return 2;
		case Calendar.THURSDAY: return 3;
		case Calendar.FRIDAY: return 4;
		case Calendar.SATURDAY: return 5;
		case Calendar.SUNDAY: return 6;
		default: return -1;
		}
	}

	// Converts the specified String to a Time object.
	public static Calendar stringToCal(String dateString) {
		try {
			Calendar dateCal = Calendar.getInstance();
			dateCal.setTime(SDF_DATE.parse(dateString));
			return dateCal;
		} catch (Exception ex) {
			return null;
		}
	}

	// Converts the specified String to a Time object.
	public static Time stringToTime(String timeString) {
		try {
			Calendar timeCal = Calendar.getInstance();
			timeCal.setTime(SDF_TIME.parse(timeString));
			return new Time(timeCal.getTimeInMillis());
		} catch (Exception ex) {
			return null;
		}
	}

	//Determines if a date is between two other dates. Same day as a limit is considered valid.
	//This is to fix a bug caused by the Calendar class.
	public static boolean isBetweenDates(Calendar theDate, Calendar startDate, Calendar endDate){
		//Check year.
		if (theDate.get(Calendar.YEAR) < startDate.get(Calendar.YEAR) || theDate.get(Calendar.YEAR) > endDate.get(Calendar.YEAR)) {
			return false;
		}
		
		//Check day of year.
		if (theDate.get(Calendar.DAY_OF_YEAR) < startDate.get(Calendar.DAY_OF_YEAR) || theDate.get(Calendar.DAY_OF_YEAR) > endDate.get(Calendar.DAY_OF_YEAR)) {
			return false;
		}
		
		//Validate.
		return true;
	}
	
	//Determines if two Calendar dates are the same.
	//This is to fix a bug caused by the Calendar class.
	public static boolean isSameDay(Calendar date1, Calendar date2){
		//Check year.
		if (date1.get(Calendar.YEAR) != date2.get(Calendar.YEAR)) {
			return false;
		}
		
		//Validate.
		return date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR);
	}
}
