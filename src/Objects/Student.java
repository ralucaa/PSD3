package Objects;

import java.util.ArrayList;

public final class Student {
	private final String firstName, lastName, id;
	private ArrayList<Attendance> attendance = new ArrayList<Attendance>();
	
	public Student(String firstName, String lastName, String id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	
	//Mutators.
	public void addAttendance(Attendance at){
		attendance.add(at);
	}
	
	public void addAttendance(String sessionTitle, String status){
		addAttendance(new Attendance(sessionTitle, status));
	}
	
}
