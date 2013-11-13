package Objects;

public final class Attendance {
	public static final String ABSENT = "absent", MOTIVATED = "mv", PRESENT = "present";
	private final String sessionTitle, status;
	
	
	public Attendance(String sessionTitle, String status){
		this.sessionTitle = sessionTitle;
		this.status = status;
	}
	
	//Accessors.
	public String getSessionTitle(){
		return sessionTitle;
	}
	
	public String getStatus(){
		return status;
	}
	
	//Shouldn't be needed! Remove final attribute if changed.
	/*
	//Mutators.
	public void setSessionTitle(String sessionTitle){
		this.sessionTitle = sessionTitle;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	*/
}
