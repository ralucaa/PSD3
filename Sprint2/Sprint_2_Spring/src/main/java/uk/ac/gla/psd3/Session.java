package uk.ac.gla.psd3;

import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Session {
	private String course, session_name;
	private String start_date, end_date;
	private String start_time, end_time;
	private int session_frequency;
	private String staff_member;
	private int max_attendance, session_duration;
	private boolean compulsory;
	private String venue;

	//Gets all the dates when this session will take place.
	public ArrayList<Calendar> getDates(){
		ArrayList<Calendar> dates = new ArrayList<Calendar>();
		Calendar startDate = Helpers.stringToCal(start_date);
		Calendar endDate = Helpers.stringToCal(end_date);

		if (session_frequency > 0) {
			for (Calendar d = startDate; !d.after(endDate); d.add(Calendar.DATE, session_frequency)){
				dates.add((Calendar) d.clone());
			}
		} else {
			dates.add(startDate);
		}
		return dates;
	}

	//Sets the duration of the session as the difference of the end_time and start_time.
	public void setSession_duration(Time start_time, Time end_time) {
		Time t = new Time(end_time.getTime() - start_time.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(t);
		this.session_duration = 60 * cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE);
		
		updateEndTime();
	}

	//Returns the current session as an array.
	public String[] toArray(){
		String[] result = new String[6];
		result[0]  = "Course: " + course;
		result[1]  = "Session: " + session_name;
		result[2]  = "Time: " + start_time.toString().substring(0, 5) + " - " + end_time.toString().substring(0, 5);
		result[3]  = "Staff: " + staff_member;
		if (compulsory) {
			result[4]  = "Attendance is compulsory";
		} else {
			result[4]  = "Attendance is not compulsory";
		}
		result[5] = "Venue: " + venue;

		return result;
	}

	//Returns the current session as an array, specifying the date as the date received.
	public String[] toArray(Calendar date){
		String[] result = new String[7];
		result[0]  = "Course: " + course;
		result[1]  = "Session: " + session_name;
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMM yyyy");
		result[2]  = "Date: " + sdfDate.format(date.getTime());
		result[3]  = "Time: " + start_time.toString().substring(0, 5) + " - " + end_time.toString().substring(0, 5);
		result[4]  = "Staff: " + staff_member;
		if (compulsory) {
			result[5]  = "Attendance is compulsory";
		} else {
			result[5]  = "Attendance is not compulsory";
		}
		result[6] = "Venue: " + venue;

		return result;
	}

	//Attempts to update the end time.
	private void updateEndTime() {
		//Update end time.
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(Helpers.stringToTime(start_time));
			cal.add(Calendar.MINUTE, session_duration);
			Time t = new Time(cal.getTimeInMillis());
			end_time = Helpers.SDF_TIME.format(t); 
		} catch (Exception ex) { }
	}
	
	//////////////////////////////
	//// Getters and setters. ////
	//////////////////////////////
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public Date getStart_dateDate() {
		return new Date(Helpers.stringToCal(start_date).getTimeInMillis());
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_dateDate() {
		if (session_frequency == 0) 
			return new Date(Helpers.stringToCal(start_date).getTimeInMillis()); 
		return new Date(Helpers.stringToCal(end_date).getTimeInMillis());
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getSession_frequency() {
		return session_frequency;
	}
	public void setSession_frequency(int session_frequency) {
		//Transform to weeks.
		this.session_frequency = 7 * session_frequency;
	}
	public void setSession_frequencyDays(int session_frequency) {
		//Transform to weeks.
		this.session_frequency = session_frequency;
	}
	public Time getStart_timeTime() {
		return Helpers.stringToTime(start_time);
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
		updateEndTime();
	}
	public Time getEnd_timeTime() {
		return Helpers.stringToTime(end_time);
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getSession_duration() {
		return session_duration;
	}
	public void setSession_duration(int session_duration) {
		this.session_duration = session_duration;
		updateEndTime();
	}
	public String getStaff_member() {
		return staff_member;
	}
	public void setStaff_member(String staff_member) {
		this.staff_member = staff_member;
	}
	public int getMax_attendance() {
		return max_attendance;
	}
	public void setMax_attendance(int max_attendance) {
		this.max_attendance = max_attendance;
	}
	public boolean isCompulsory() {
		return compulsory;
	}
	public void setCompulsory(boolean compulsory) {
		this.compulsory = compulsory;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
}
