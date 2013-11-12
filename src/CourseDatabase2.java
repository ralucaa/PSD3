import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class CourseDatabase2 {
	

	private static ArrayList<String> FirstName;
     
     private static ArrayList<String> Surname;
     
     private static ArrayList<String> IDnumber;
     
     private static LinkedList<HashMap<String, String>> Assignments;
     
     
     public static void setAssignments(
			LinkedList<HashMap<String, String>> assignments) {
		Assignments = assignments;
	}







	
     
     public static ArrayList<String> getFirstName() {
 		return FirstName;
 	}

 	public static void setFirstName(ArrayList<String> firstName) {
 		FirstName = firstName;
 	}

 	public static ArrayList<String> getSurname() {
 		return Surname;
 	}

 	public static void setSurname(ArrayList<String> surname) {
 		Surname = surname;
 	}

 	public static ArrayList<String> getIDnumber() {
 		return IDnumber;
 	}

 

 	public static void setIDnumber(ArrayList<String> iDnumber) {
		IDnumber = iDnumber;
	}

	public static LinkedList<HashMap<String, String>> getAssignments() {
 		return Assignments;
 	}

 	public static void setAssignments1(LinkedList<HashMap<String, String>> assignments) {
 		Assignments = assignments;
 	}
 	
 	
 	
 	public int getNumberOfStudentsAtCourse(){
 		return IDnumber.size();
 	}
 	
 	
 	
 	
 	

	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void addStudentToCourseSession() throws Exception {
	// Create or Modify a file for Database
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Course.csv",true)));
	String FirstName="Lavender";
	String Surname="Brown";
	String IDNumber="9801001";
	String status="present";
	boolean moreRecords = false;
	// Read Data
	do
		{
	// Print to File
	pw.println(FirstName);
	pw.println(Surname);
	pw.println(IDNumber);
	pw.println(status);
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



	public void clearCourseRecords() throws Exception{
	
		FirstName.clear();
 		Surname.clear();
 		IDnumber.clear();
 		Assignments.clear();
 		//overwrite file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Course.csv")));
		
		pw.close();
		System.out.println("All course records successfully deleted.");
		}
			
 
 	
 	
 	
 	
 	public int getAssignmentsOfStudent(String StudentID){
 		int countAssignments=0;
 		for(int i=0;i<Assignments.size();i++)
 			if(Assignments.get(i).containsValue("present"))
 				countAssignments++;
 		return countAssignments;
 	}

 	
 	
 	
 	public void showMenu() throws Exception{
		System.out.print("1 : Add Student to course session\n2 : Get number of students present\n3 :Get the number of assignments to be completed\n4 : Check if the student was present during a certain laboratory\n5 : Quit\n\nChoose Option 1-5: ");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
			case 1:
				addStudentToCourseSession();break;
			case 2:
				getNumberOfStudentsAtCourse();break;
			case 3:
				getAssignments();break;
			case 4:
				getAssignmentsOfStudent("9801002");break;
			case 5:
				System.exit(1); break;
			default:
				System.out.println("\nTry again");
			break;
			}
	}
 	
}
