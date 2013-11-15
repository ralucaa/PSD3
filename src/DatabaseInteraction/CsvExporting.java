package DatabaseInteraction;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.ArrayList;



//6 hours of work, learnt about the databases and refreshed a lot of java knowledge from last year. Not too bad ;)
public class CsvExporting extends DatabaseAdapter{
	//That method is pretty straightforward. Next one is commented
	public void exportSingleStudentAttendance(String studentID,String csvFileName){
		String query = "SELECT Course.Name, Course.ID, Session.Name, Attendance.Status "
				+"FROM Course, Session, Attendance "
				+"WHERE Course.ID = Session.CourseID "
				+"AND Session.ID = Attendance.sessionID "
				+"AND Attendance.StudentID =\""+studentID+"\"";
		try {
			ResultSet rs = executeSQLQuery(query);

			try {
				FileWriter writer = new FileWriter(csvFileName);
				writer.append("Course,ID number,Assignment,Mark\n");
				while ( rs.next() ) {
					writer.append(rs.getString(1)+",");
					writer.append(rs.getString(2)+",");
					writer.append(rs.getString(3)+",");
					writer.append(rs.getString(4)+"\n");
				}
				writer.close();
			}
			catch (Exception ex) {
				ex.printStackTrace(System.out);
				System.out.println("Cry Cry " + ex.getMessage());
			}
		}catch (Exception ex) {
			ex.printStackTrace(System.out);
			System.out.println("Could not connect.");

		}
}
	public void exportStudentDataForCourse(String courseID,String csvFileName){
		//Queries for retrieving the sessions data and students data needed for the later Query
		String sessionsQuery="SELECT ID, Name FROM Session WHERE courseID = \""+courseID+"\" ";
		String studentsQuery = "SELECT DISTINCT Student.FirstName, Student.LastName, Student.ID "
								+"FROM Student, Attendance, Session "
								+"WHERE Student.ID = Attendance.StudentID "
								+"AND Attendance.SessionID = Session.ID "
								+"AND Session.CourseID =\""+courseID+"\"";
		
		//Store the session IDs and info about the student
		ArrayList<String> sessionIds = new ArrayList<String>();
		ArrayList<String[]> studentInfo = new ArrayList<String[]>();
		String sessions = "";

		try {
			ResultSet studentsSet = executeSQLQuery(studentsQuery);
			ResultSet sessionSet = executeSQLQuery(sessionsQuery);
			
			//Collect the header part with the sessions
			//And session IDs
			//iterate over the sessions.
			while ( sessionSet.next() ) {
				if(!sessionSet.next()){ //if the last session, break
					sessionSet.previous();
					break;
				}
				else{sessionSet.previous();} //if not, go on
				sessionIds.add(sessionSet.getString(1));
				sessions += sessionSet.getString(2)+",";
			    }
				sessionIds.add(sessionSet.getString(1));
			    sessions += sessionSet.getString(2)+"\n";
			    //Done with the sessions ^
			
			//Students
			// Create an array that will store the data for each student
			//first name,surname,student id, 
			String info[];
			while( studentsSet.next() ){
				info = new String[3];
				info[0] = studentsSet.getString(1);
				info[1] = studentsSet.getString(2);
				info[2] = studentsSet.getString(3);
				studentInfo.add(info);
			}
			for(String[] stuid: studentInfo){
				System.out.println(stuid[0]);	
			}
			
			
			    
			try {
				FileWriter writer = new FileWriter(csvFileName);
				//Write the header
				writer.append("First name,Surname,ID number,"+sessions);
				
				//For each student retrieve his/her First name,Surname,ID number
				for(String[] stuid: studentInfo){
					writer.append(stuid[0]+",");
					writer.append(stuid[1]+",");
					writer.append(stuid[2]);
					//then get the sessions of that selected student 
					for(String sesid: sessionIds){
						//Query to get the status of the sessions for the selected student
						String statusQuery = "SELECT Status from Attendance "
											+"WHERE Attendance.StudentID =\""+stuid[2]+"\" "
											+"AND Attendance.SessionID =\""+sesid+"\"";
						ResultSet statusSet = executeSQLQuery(statusQuery);
						statusSet.next();
						writer.append(","+statusSet.getString(1));
					}
					writer.append("\n");//end of row
					
					
				}
				writer.flush();
				writer.close();
				//DONE!!
			}
			catch (Exception ex) {
				ex.printStackTrace(System.out);
				System.out.println("Cry Cry " + ex.getMessage());
			}
		}catch (Exception ex) {
			ex.printStackTrace(System.out);
			System.out.println("Could not connect.");

		}
}
}
