package DatabaseInteraction;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import Objects.Student;


public class StudentDatabase {
	
	private static ArrayList<Student> students;
	
	
	private static Student student;
	
	
	
	public static ArrayList<Student> getStudents() {
		return students;
	}

	
	public static Student getStudent() {
		return student;
	}


	public static void setStudent(Student student) {
		StudentDatabase.student = student;
	}



	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	
	
	/**Given a certain Student ID, return Student if he is in the database*/
	public static void getCertainStudent(String id){
		for(int i=0;i<students.size();i++)
			if(students.get(i).getId().equals(id))
				System.out.println(students.get(i));
			else
				System.out.println("The student is not in our records.");
				
	}
	
	
	/**Return all students in the file*/
	public static void getAllStudents() {
		BufferedReader br = null;
		 
		try {
 
			String line;
 
			br = new BufferedReader(new FileReader("Student.csv"));
 
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] components = line.split(",");
				Student student = new Student(components[0], components[1], components[2]);
			}
 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (br != null)br.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
 
	}
	
	
	
	
	public void addStudentRecord() throws Exception {
	// Create or Modify a file for Database
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Student.csv",true)));
	final String StudentID="1102312";
	final String FirstName="Helen";
	final String Surname="Foster";
	boolean moreRecords = false;
	// Read Data
	do
		{
	// Print to File
	pw.println(StudentID);
	pw.println(FirstName);
	pw.println(Surname);

	System.out.print("\nStudent Record successfully added.Add more?(y/n):");
	String s;
	s = reader.readLine();
	if(s.equalsIgnoreCase("y")){
	moreRecords= true;
	System.out.println();
	}
	else{
		moreRecords = false;
		}
	}
	while(moreRecords);
		pw.close();
	}
			
			
	
			
	public static BufferedReader getReader() {
		return reader;
	}




	public static void setReader(BufferedReader reader) {
		StudentDatabase.reader = reader;
	}



	public void clearStudentRecords() throws Exception{
		// Create a blank file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new
		FileWriter("Student.csv")));
		pw.close();
		System.out.println("All records successfully deleted.");
		}
			
			
	
	
	 /**test*/
    public static void main(String[] args) throws FileNotFoundException {
    	Student s1=new Student("1102353", "John", "Doe");
    	students.add(s1);
    	Student s2=new Student("1145453", "May", "June");
    	students.add(s2);
    	getAllStudents();
    	getCertainStudent("1145453");
    }
	
	

}
