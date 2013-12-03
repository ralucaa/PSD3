package uk.ac.gla.psd3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class XController {
	
	private static final Logger logger = LoggerFactory.getLogger(XController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home testestest! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	//Shows the Add new session form.
	@RequestMapping(value = "/addsession")
	@ResponseBody
	public ModelAndView addSession(){
		return new ModelAndView("add_session");
	}
	
	//Gets the information from the new session form.
	@RequestMapping(value = "/addsessionresult")
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

	//Gets the sessions for the specified student_id.
	@RequestMapping(value = "/viewsessions", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewSessions(@RequestParam(value="student_id", required=true) String student_id){
		//Get the sessions from the database. If student_id is invalid show an error message.
		ArrayList<Session> sessions = new ArrayList<Session>();
		ResultSet sessionIDs;
		try {
			sessionIDs = DatabaseAdapter.executeSQLQuery("SELECT SessionID FROM Registration WHERE StudentID = \'" + student_id + "\'");
			while(sessionIDs.next()){
				//Exit loop if done.
				if (sessionIDs.getInt("SessionID") == 0) break;

				ResultSet sessionRS = DatabaseAdapter.executeSQLQuery("SELECT ID, Course, Name, StartTime, EndTime, Frequency, Staff, MaxAttendance, Compulsory, Venue, StartDate, EndDate FROM Session WHERE ID = " + sessionIDs.getInt("SessionID"));
				while(sessionRS.next()){
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
				}
			}
			
			//Convert the sessions to an array of arrays of strings.
			String[][] session_array = new String[sessions.size()][];
			for (int i = 0; i < sessions.size(); i++) {
				session_array[i] = sessions.get(i).toArray();
			}

			return new ModelAndView("view_sessions", "session_array", session_array);
		} catch (SQLException ex) {
			return null;
		}
	}
}
