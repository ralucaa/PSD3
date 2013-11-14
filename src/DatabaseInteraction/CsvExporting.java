package DatabaseInteraction;

import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class CsvExporting extends DatabaseAdapter{

	public void exportSingleStudentAttendance(String studentID,String csvFileName){
		String query = "SELECT Course.name, Course.id, Session.name, Attendance.status "
				+"FROM Course, Session, Attendance "
				+"WHERE Course.id = Session.courseID "
				+"AND Session.id = Attendance.sessionID "
				+"AND Attendance.\""+studentID+"\"";
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

					writer.flush();
					writer.close();
				}
			}
			catch (Exception ex) {
				System.out.println("Cry Cry " + ex.getMessage());
			}
		}catch (Exception ex) {
			System.out.println("Could not connect.");

		}
}
	public void exportStudenntDataForCourse(String courseID,String csvFileName){
		String sessionsQuery="SELECT id, name from Session where courseID = \""+courseID+"\"";
		String studentsQuery = "SELECT DISTINCT Student.firstName, Student.lastName, Student.id"
								+"FROM Student, Attendance, Session"
								+"WHERE Student.id = Attendance.studentID"
								+"AND Attendance.sessionID = Session.id"
								+"AND Session.courseID =\""+courseID+"\"";

		try {
			ResultSet studentsSet = executeSQLQuery(studentsQuery);
			ResultSet sessionSet = executeSQLQuery(sessionsQuery);
			ResultSetMetaData sessionSetMD = sessionSet.getMetaData();
			String sessions = "";
			ArrayList<String> sessionIds = new ArrayList<String>();
			ArrayList<String[]> studentInfo = new ArrayList<String[]>();
			
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
				info[0] += studentsSet.getString(1);
				info[1] += studentsSet.getString(2);
				info[2] += studentsSet.getString(3);
				studentInfo.add(info);
			}
			
			    
			try {
				FileWriter writer = new FileWriter(csvFileName);
				writer.append("First name,Surname,ID number,"+sessions);
				
				for(String[] stuid: studentInfo){
					writer.append(stuid[0]+",");
					writer.append(stuid[1]+",");
					writer.append(stuid[2]);
					for(String sesid: sessionIds){
						String statusQuery = "SELECT status from Attendance"
											+"WHERE Attendance.studentID =\""+stuid[2]+"\""
											+"AND Attendance.sessionID =\""+sesid+"\"";
						ResultSet statusSet = executeSQLQuery(sessionsQuery);
						statusSet.next();
						writer.append(","+statusSet.getString(1));
					}
					writer.append("\n");
					
					
				}
				writer.flush();
				writer.close();
				
			}
			catch (Exception ex) {
				System.out.println("Cry Cry " + ex.getMessage());
			}
		}catch (Exception ex) {
			System.out.println("Could not connect.");

		}
}
}