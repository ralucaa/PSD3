package uk.gla.ac.psd3.Prototype2;

import java.sql.Time;
import java.util.Date;

public class Session {
	private Date date;
	private Time time;
	private Time duration;
	private String repeatFrequency;
	private String lecturer;
	private int maxAttendance;
	private boolean compulsory;
	private String venue;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Time getDuration() {
		return duration;
	}
	public void setDuration(Time duration) {
		this.duration = duration;
	}
	public String getRepeatFrequency() {
		return repeatFrequency;
	}
	public void setRepeatFrequency(String repeatFrequency) {
		this.repeatFrequency = repeatFrequency;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public int getMaxAttendance() {
		return maxAttendance;
	}
	public void setMaxAttendance(int maxAttendance) {
		this.maxAttendance = maxAttendance;
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
