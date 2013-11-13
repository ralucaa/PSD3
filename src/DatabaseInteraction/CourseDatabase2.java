package DatabaseInteraction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

import Objects.Course;
import Objects.Student;

public class CourseDatabase2 {
	
	

	private static ArrayList<Course> courses;
     
	private static ArrayList<Student> students;
	
     
     

	public static ArrayList<Course> getCourses() {
		return courses;
	}







	public static void setCourses(ArrayList<Course> courses) {
		CourseDatabase2.courses = courses;
	}







	public static ArrayList<Student> getStudents() {
		return students;
	}







	public static void setStudents(ArrayList<Student> students) {
		CourseDatabase2.students = students;
	}











   

     public void getAllCourses() {
 		BufferedReader br1 = null;
		BufferedReader br2 = null;
 		try {
  
 			String line1,line2;
  
 			br1 = new BufferedReader(new FileReader("Course.csv"));
 			br2 = new BufferedReader(new FileReader("AllGrades.csv"));
 			while ((line1 = br1.readLine()) != null && (line2=br2.readLine())!=null) {
 				String[] components1 = line1.split(",");
 				String[] components2 = line2.split(",");
 				Course course = new Course(components1[0], components1[1],Integer.parseInt(components2[0]), Integer.parseInt(components2[1]));
 				courses.add(course);
 			}
  
 		} catch (Exception ex) {
 			System.out.println(ex.getMessage());
 		} finally {
 			try {
 				if (br1 != null && br2!=null)
 					{br1.close();
 					br2.close();
 					}
 			} catch (Exception ex) {
 				System.out.println(ex.getMessage());
 			}
 		}
  
 	}
 	


     public void retrieveCourse() {
    	 System.out.println("Enter course ID:");
    	 //TODO
    			 
     }

	
    
 	
 	

	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void addCourse() throws Exception {
	// Create or Modify a file for Database
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Course.csv",true)));
	String courseID="COMPSCI2010";
	String courseName="AP3";
	boolean moreRecords = false;
	// Read Data
	do
		{
	// Print to File
	pw.println(courseID);
	pw.println(courseName);
	System.out.print("\nCourse successfully added.Add more?(y/n):");
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

 		//overwrite file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Course.csv")));
		
		pw.close();
		System.out.println("All course records successfully deleted.");
		}
			
 
 	
 	
 	
 	
 	
 	
 	public void showMenu() throws Exception{
		System.out.print("1 : Get all courses\n2 : Get number of students present\n3 : Add course to list of courses\n4 : Retrieve course\n5 : Quit\n\nChoose Option 1-5: ");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
			case 1:
				getAllCourses();break;
			case 2:
				getStudents().size();break;
			case 3:
				addCourse();break;
			case 4:
				retrieveCourse();break;
			case 5:
				System.exit(1); break;
			default:
				System.out.println("\nTry again");
			break;
			}
	}
 	
}
