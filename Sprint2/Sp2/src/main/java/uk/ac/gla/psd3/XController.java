package uk.ac.gla.psd3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView addSession(@ModelAttribute("session") Session session, BindingResult result){
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
			//return "Added successfully!";
			return new ModelAndView("addsessionresult", "result", "User was added");
		} catch (SQLException e) {
			//return "Adding failed! Error: " + e.getMessage();
			return new ModelAndView("addsessionresult", "result", "User not added");
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

				ResultSet sessionRS = DatabaseAdapter.executeSQLQuery("SELECT ID, Course, Name, StartTime, EndTime, Frequency, Staff, MaxAttendance, Compulsory, Venue, StartDate, EndDate FROM Session WHERE ID = " + sessionIDs.getInt("SessionID") + " ORDER BY StartTime");
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
					Calendar startDateCalendar = Calendar.getInstance(); 
					startDateCalendar.setTime(sessionRS.getDate("StartDate"));
					session.setStart_date(startDateCalendar);
					Calendar endDateCalendar = Calendar.getInstance(); 
					endDateCalendar.setTime(sessionRS.getDate("EndDate"));
					session.setEnd_date(endDateCalendar);

					//Add it to the ArrayList.
					sessions.add(session);
				}
			}

			//Process the sessions and display them.
			return new ModelAndView("view_sessions", "session_array", processSessions(sessions));
		} catch (SQLException ex) {
			return null;
		}
	}

	private static String[][][] processSessions(ArrayList<Session> sessions){
		//Instantiate the ArrayLists.
		ArrayList<String[]> today = new ArrayList<String[]>();
		ArrayList<String[]> thisWeek = new ArrayList<String[]>();
		ArrayList<String[]> allTime = new ArrayList<String[]>();

		//Instantiate the calendars.
		Calendar now = Calendar.getInstance();
		Calendar thisWeekStart = Calendar.getInstance();
		thisWeekStart.add(Calendar.DATE, - (now.get(Calendar.DAY_OF_WEEK) - 1) % 7);
		Calendar thisWeekEnd = Calendar.getInstance();
		thisWeekStart.add(Calendar.DATE, (6 - now.get(Calendar.DAY_OF_WEEK)));

		//Distribute the sessions.
		for(Session session : sessions){
			for (Calendar date : session.getDates()){
				//Add to the all time list.
				allTime.add(session.toArray(date));
				//See if the date is this week.
				if (!date.before(thisWeekStart) && !date.after(thisWeekEnd)) {
					thisWeek.add(session.toArray(date));

					//See if the date is today.
					if (date.equals(now)){
						today.add(session.toArray());
					}
				}
			}
		}
		
		//Parse to a 3d String array.
		String[][][] result = new String[3][][];
		result[0] = today.toArray(new String[today.size()][]);
		result[1] = thisWeek.toArray(new String[thisWeek.size()][]);
		result[2] = allTime.toArray(new String[allTime.size()][]);
		
		return result;
	}
}