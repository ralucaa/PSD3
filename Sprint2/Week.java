import java.util.*;

public class Week {
	Date d1, d2;

	/** make a week containing the specified date */
	public Week(int year, int month, int dayOfMonth) {
		GregorianCalendar cal1 = new GregorianCalendar(year, month, dayOfMonth);
		GregorianCalendar cal2 = new GregorianCalendar(year, month, dayOfMonth);
		
		while (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			cal1.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		while (cal2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			cal2.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		d1 = cal1.getTime();
		d2 = cal2.getTime();
	}
	
	/** the Monday starting this week */
	public Date getStartDate() {
		return d1;
	}
	
	/** the Sunday ending this week */
	public Date getEndDate() {
		return d2;
	}
	/*
	public static void main(String[] args) {
		Week week = new Week(2013, Calendar.NOVEMBER, 1);
		System.out.println(week.getStartDate());
		System.out.println(week.getEndDate());
		week = new Week(2013, Calendar.NOVEMBER, 30);
		System.out.println(week.getStartDate());
		System.out.println(week.getEndDate());
	}*/
}

