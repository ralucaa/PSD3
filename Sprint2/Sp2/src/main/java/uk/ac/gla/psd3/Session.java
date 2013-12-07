package uk.ac.gla.psd3;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Session {
	private final int ELEMENT_COUNT = 11;
	private String course, session_name;
	private Calendar start_date, end_date;
	private int session_frequency;
	private Time session_start_time, session_duration;
	private String staff_member;
	private int max_attendance;
	private boolean is_compulsory;
	private String venue;


	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Calendar getStart_date() {
		return start_date;
	}
	public void setStart_date(Calendar start_date) {
		this.start_date = start_date;
	}
	public Calendar getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Calendar end_date) {
		this.end_date = end_date;
	}
	public int getSession_frequency() {
		return session_frequency;
	}
	public void setSession_frequency(int session_frequency) {
		this.session_frequency = session_frequency;
	}
	public Time getSession_start_time() {
		return session_start_time;
	}
	public Time getSession_end_time() {
		//To fix TODO
		Time end_time = new Time(session_start_time.getTime() + session_duration.getTime());
		return end_time;
	}
	public void setSession_start_time(Time session_start_time) {
		this.session_start_time = session_start_time;
	}
	public Time getSession_duration() {
		return session_duration;
	}
	public void setSession_duration(Time start_time, Time end_time) {
		//TODO probably needs fixing.
		this.session_duration = new Time(end_time.getTime() - start_time.getTime());
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
	public boolean isIs_compulsory() {
		return is_compulsory;
	}
	public void setIs_compulsory(boolean is_compulsory) {
		this.is_compulsory = is_compulsory;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public ArrayList<Calendar> getDates(){
		ArrayList<Calendar> dates = new ArrayList<Calendar>();
		for (Calendar d = start_date; !d.after(end_date); d.add(Calendar.DATE, session_frequency)){
			dates.add((Calendar) d.clone());
		}
		return dates;
	}

	public String[] toArray(){
		String[] result = new String[6];
		result[0]  = "Course: " + course;
		result[1]  = "Session: " + session_name;
		result[2]  = "Time: " + session_start_time.toString().substring(0, 5) + " - " + (new Time(session_start_time.getTime() + session_duration.getTime())).toString().substring(0, 5);
		result[3]  = "Staff: " + staff_member;
		if (is_compulsory) {
			result[4]  = "Attendance is compulsory";
		} else {
			result[4]  = "Attendance is not compulsory";
		}
		result[5] = "Venue: " + venue;

		return result;
	}
	
	public String[] toArray(Calendar date){
		String[] result = new String[7];
		result[0]  = "Course: " + course;
		result[1]  = "Session: " + session_name;
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		result[2]  = "Date: " + sdf.format(date.getTime());
		result[3]  = "Time: " + session_start_time.toString().substring(0, 5) + " - " + (new Time(session_start_time.getTime() + session_duration.getTime())).toString().substring(0, 5);
		result[4]  = "Staff: " + staff_member;
		if (is_compulsory) {
			result[5]  = "Attendance is compulsory";
		} else {
			result[5]  = "Attendance is not compulsory";
		}
		result[6] = "Venue: " + venue;

		return result;
	}
}
