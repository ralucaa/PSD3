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
	
	
	
	public void getAllStudents() {
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


/*

	public void readRecords() throws Exception{
		try{
			// Open the file
			BufferedReader file = new BufferedReader(new FileReader("Student.csv"));
			String name;
			int i=1;
			// Read records from the file
			while((name = file.readLine()) != null)
			{
			System.out.println("Student Number: " +(i++));
			System.out.println("=========================");
			System.out.println("\nCourseName:" +name);
			file.readLine();
			System.out.println("Attendance Times: ");
			for(int j=0;j<AttendanceTimes.size();j++)
				System.out.println(AttendanceTimes.get(j).toString());
			for(int j=0;j<AttendanceDates.size();j++)
				System.out.println(AttendanceDates.get(j).toString());
			System.out.println();
			}
			file.close();
			showMenu();
			}
		catch(FileNotFoundException e){
			System.out.println("Inexistent File.Try again.");
			}
		
		}

*/


	public void clearStudentRecords() throws Exception{
		// Create a blank file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new
		FileWriter("Student.csv")));
		pw.close();
		System.out.println("All records successfully deleted.");
		}
			
			
			
	public void showMenu() throws Exception{
		System.out.print("1 : Add Student Record\n2 : Get All students\n3 :Clear Student Records\n4 : Quit\n\nChoose Option 1-4: ");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
			case 1:
				addStudentRecord();break;
			case 2:
				getAllStudents();break;
			case 3:
				clearStudentRecords();break;
			case 4:
				System.exit(1);break;
			default:
				System.out.println("\nTry again");
			break;
			}
	}
	
	public int countAttendance(ArrayList<Date> AttendanceDates){
		return AttendanceDates.size();
	}
	
	
	
	public static String getStudentnumber() {
		return getStudentnumber();
	}
	
	
	

}
