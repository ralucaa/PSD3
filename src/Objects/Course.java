package Objects;

import java.util.ArrayList;

public final class Course {
	private final String name, id;
	private ArrayList<Session> sessions = new ArrayList<Session>();
	
	public Course(String name, String id, int coursework, int exam){
		this.name = name;
		this.id = id;
	}
	
	//Accessors.
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	//Mutators.
	public void addSession(Session session){
		sessions.add(session);
	}
	
	//Shouldn't be needed! Remove final attribute if changed.
	/*
	//Mutators.
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setCoursework(int coursework){
		this.coursework = coursework;
	}
	
	public void setExam(int exam){
		this.exam = exam;
	}
	*/
}
