package Objects;

import java.util.ArrayList;

public final class Student {
	private final String firstName, lastName, id;
	
	public Student(String firstName, String lastName, String id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	
	
	//Accessors.
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getId(){
		return id;
	}
}
