import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class StudentDatabase {
	
//	private static final String StudentNumber;

	private static ArrayList<Time> AttendanceTimes;
	
	private static ArrayList<Date> AttendanceDates;
	
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void addStudentRecord() throws Exception {
	// Create or Modify a file for Database
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("StudentDatabase.csv",true)));
	final String StudentNumber;
	final String CourseName;
	final Date CurrentDate;
	boolean moreRecords = false;
	// Read Data
	do
		{
	// Print to File
	pw.println(StudentNumber);
	pw.println(CourseName);
	pw.println(CurrentDate);
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
			
			
			
			
	public void readRecords() throws Exception{
		try{
			// Open the file
			BufferedReader file = new BufferedReader(new
			FileReader("StudentDatabase.csv"));
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
				System.out.println(AttendanceTimes.get(j).toGMTString());
			for(int j=0;j<AttendanceDates.size();j++)
				System.out.println(AttendanceDates.get(j).getDate());
			System.out.println();
			}
			file.close();
			showMenu();
			}
		catch(FileNotFoundException e){
			System.out.println("Inexistent File.Try again.");
			}
		
		}




	public void clearStudentRecords() throws Exception{
		// Create a blank file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new
		FileWriter("StudentDatabase.csv")));
		pw.close();
		System.out.println("All records successfully deleted.");
		}
			
			
			
	public void showMenu() throws Exception{
		System.out.print("1 : Add Student Record\n2 : Display Student Record\n3 :Clear Student Records\n4 : Quit\n\nChoose Option 1-4: ");
		int choice = Integer.parseInt(read.readLine());
		switch(choice){
			case 1:
				addStudentRecord();break;
			case 2:
				readRecords();break;
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
	
	
	public static ArrayList<Time> getAttendanceTimes() {
		return AttendanceTimes;
	}

	public static void setAttendanceTimes(ArrayList<Time> attendanceTimes) {
		AttendanceTimes = attendanceTimes;
	}

	public static ArrayList<Date> getAttendanceDates() {
		return AttendanceDates;
	}

	public static void setAttendanceDates(ArrayList<Date> attendanceDates) {
		AttendanceDates = attendanceDates;
	}
	
	

	public static String getStudentnumber() {
		return getStudentnumber();
	}
	
	
	

}
