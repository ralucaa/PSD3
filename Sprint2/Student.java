import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.Calendar;

/*

structure of the returned array in the getStudentSessionsDay() method:
data[0][0] >> date
data[1][0] >> start time
data[1][1] >> end time
data[1][2] >> course id
data[1][3] >> session name
data[1][4] >> compulsory
data[1][5] >> staff name
data[1][6] >> venue id


*/



public class Student {
	
	public String[][] getStudentSessionsDay(String studentID){
		String[][] data;
	    
		if (studentID.equals("1")){
			data = new String[][]{{"Wed 4 December 2013"},{"11:00","13:00","COMPSCI4009","First Algorithmics Lab","Compulsory:No","Gethin Norman","BO711"},
					{"14:00","15:00","COMPSCI4010","C Tutorial","Compulsory:No","Joe Sventek","BO720"}};
			return data;
		}
		else if(studentID.equals("2")){
			data = new String[][]{{"Wed 4 December 2013"},{"11:00","13:00","COMPSCI4009","First Algorithmics Lab","Compulsory:No","Gethin Norman","BO711"},
					{"14:00","15:00","COMPSCI4010","C Tutorial","Compulsory:No","Joe Sventek","BO720"}};
			return data;
		}
		
		return null;
		
	}

	
}




//FOR FUTURE USAGE
/*
 * public String[] getStudentSessionsDay(String studentID,String Date){
    String query = "SELECT Session.CourseID, Session.Name,Session.StartTime,Session.EndTime,"
    				 +"Session.Frequency,Session.StaffID,Session.Compulsory,Session.VenueID"
                    +"FROM Session,Student,Staff "
                    +"WHERE Staff.ID=Session.StaffID "
                    +"AND Student.ID"
                    +"AND Attendance.StudentID =\""+studentID+"\"";
    try {
            ResultSet rs = DatabaseAdapter.executeSQLQuery(query);
            
            while(rs.next()){
           	 rs.ge
           	 
            }
            
    }catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println("Could not connect.");

    }
  
}*/

		/*
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		Calendar calendar = Calendar.getInstance();
	    calendar.set(2013, 12, 04) ;
	    int i=calendar.get(Calendar.DAY_OF_WEEK);
	    */
