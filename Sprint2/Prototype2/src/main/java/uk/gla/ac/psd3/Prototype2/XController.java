package uk.gla.ac.psd3.Prototype2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class XController {

	@RequestMapping(value = "/addsession")
	@ResponseBody
	public String addSession(@ModelAttribute("session") Session session, BindingResult result){
		try {
			DatabaseAdapter.executeSQLQuery("INSERT INTO Session (Course, Name, StartTime, EndTime, Frequency, Staff, MaxAttendance, Compulsory, Venue, StartDate, EndDate) "
					+ "VALUES (" + 
					session.getCourse() + ", " + 
					session.getSession_name() + ", " + 
					session.getSession_start_time() + ", " + 
					session.getSession_end_time() + ", " + 
					session.getSession_frequency() + ", " + 
					session.getStaff_member() + ", " +  
					session.getMax_attendance() + ", " +  
					session.isIs_compulsory() + ", " +  
					session.getVenue() + ", " +  
					session.getStart_date() + ", " +  
					session.getEnd_date() + ", " +  
					")");
			return "Added successfully!";
		} catch (SQLException e) {
			return "Adding failed! Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "/viewsessions", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewSessions(@RequestParam(value="student_id", required=true) String student_id, ModelMap model){
		//Get the sessions from the database. If student_id is invalid show an error message.
		ArrayList<Session> sessions = new ArrayList<Session>();
		ResultSet sessionIDs;
		try {
			sessionIDs = DatabaseAdapter.executeSQLQuery("SELECT SessionID FROM Registration WHERE StudentID = \'" + student_id + "\'");
			while(true){
				//Exit loop if done.
				if (sessionIDs.getInt("SessionID") == 0) break;

				ResultSet sessionRS = DatabaseAdapter.executeSQLQuery("SELECT ID, Course, Name, StartTime, EndTime, Frequency, Staff, MaxAttendance, Compulsory, Venue, StartDate, EndDate FROM Session WHERE ID = " + sessionIDs.getInt("SessionID"));
				while(true){
					//Exit loop if done.
					if (sessionRS.getInt("ID") == 0) break;

					//Create the session object.
					Session session = new Session();
					session.setCourse(sessionRS.getString("Course"));
					session.setSession_name(sessionRS.getString("Name"));
					session.setSession_start_time(sessionRS.getTime("StartTime"));
					session.setSession_duration(sessionRS.getTime("StartTime"), sessionRS.getTime("EndTime"));
					session.setSession_frequency(sessionRS.getInt("Frequency"));
					session.setStaff_member(sessionRS.getString("Staff"));
					session.setMax_attendance(sessionRS.getInt("MaxAttendance"));
					session.setIs_compulsory(sessionRS.getBoolean("Compulsory"));
					session.setVenue(sessionRS.getString("Venue"));
					session.setStart_date(sessionRS.getDate("StartDate"));
					session.setEnd_date(sessionRS.getDate("EndDate"));

					//Add it to the ArrayList.
					sessions.add(session);

					//Move on.
					sessionRS.next();
				}

				//Move on.
				sessionIDs.next();
			}

			//Add the Session IDs as parameters to the ModelMap.
			//model.addAttribute("student_id", student_id);

			return new ModelAndView("view_sessions", "session_array", sessions.toArray());
		} catch (SQLException ex) {
			return null;
		}
	}
}
