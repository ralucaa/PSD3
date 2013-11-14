package Objects;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Session {
	public static final String TYPE_TUTORIAL = "Tutorial", TYPE_LAB = "Laboratory";
	private String name, type;
	private GregorianCalendar date;
	private ArrayList<Attendance> attendance = new ArrayList<Attendance>();
	
	public Session(String name, GregorianCalendar date, String type){
		this.name = name;
		this.date = date;
		this.type = type;
	}
	
	/**
	 * @return the number of students that have been marked as present.
	 */
	public int getAttendanceCount(){
		int result = 0;
		for (Attendance a : attendance){
			if(a.getStatus() == Attendance.PRESENT){ 
				result++;
			}
		}
		
		return result;
	}
	
	//Accessors.
	public String getName(){
		return name;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	public String getType(){
		return type;
	}
	
	public ArrayList<Attendance> getAttendanceList(){
		return attendance;
	}
	
	//Mutators.
	public void addAttendance(Attendance at){
		attendance.add(at);
	}
}
