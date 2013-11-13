package Objects;

public final class Attendance {
	public static final String ABSENT = "absent", MOTIVATED = "mv", PRESENT = "present";
	private final Student student;
	private final String status;
	
	
	public Attendance(Student student, String status){
		this.student = student;
		this.status = status;
	}
	
	//Accessors.
	public Student getStudent(){
		return student;
	}
	
	public String getStatus(){
		return status;
	}
	
	//Shouldn't be needed! Remove final attribute if changed.
	/*
	//Mutators.
	public void setStudent(Student student){
		this.student = student;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	*/
}
