package Menus;

import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseInteraction.DatabaseAdapter;
import Objects.Menu;

//lots of copy-and-paste programming here, but it's a prototype...

public class SharedDBMenus {

	private static class CourseOption {
		String id, name;
		public String toString() {
			return id + " : " + name;
		}
	}
	
	private static class SessionOption {
		String id, name;
		public String toString() {
			return name + " (id" + id + ")";
		}
	}
	
	private static class StudentOption {
		String id, firstName, lastName;
		public String toString() {
			return id + "\t" + firstName + " " + lastName;
		}
	}

	/** Do a menu for the user to choose a course.
	  * @param menuDescription the menu heading
	  * @return the courseID of the course chosen, or null if "back" is chosen
	  */
	public static String chooseCourseID(String menuDescription) {
		ResultSet rs = DatabaseAdapter.executeSQLQuery("SELECT ID,Name FROM Course");
		
		String choice;
		Menu menu = new Menu(menuDescription);
		int i = 1;
		try {
			while (rs.next()) {
				CourseOption opt = new CourseOption();
				opt.id = rs.getString(1);
				opt.name = rs.getString(2);
				menu.add(i + "", opt);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println("SQL exception!");
		}
		menu.add("b", "back");
		
		choice = menu.show();
		
		return choice.equals("b") ? null : ((CourseOption)(menu.get(choice))).id;
	}

	/** Do a menu for the user to choose a session from a course.
	  * @param menuDescription the menu heading
	  * @return the sessionID of the session chosen, or null if "back" is chosen
	  */
	public static String chooseSessionIDinCourse(String menuDescription, String courseID) {
		String query = "SELECT ID,Name FROM Session WHERE courseID=\"" + courseID + "\"";
		//System.out.println(query);
		ResultSet rs = DatabaseAdapter.executeSQLQuery(query);

		String choice;
		Menu menu = new Menu(menuDescription);
		int i = 1;
		try {
			while (rs.next()) {
				SessionOption opt = new SessionOption();
				opt.id = rs.getString(1);
				opt.name = rs.getString(2);
				menu.add(i + "", opt);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println("SQL exception!");
		}
		menu.add("b", "back");
		
		choice = menu.show();
		
		return choice.equals("b") ? null : ((SessionOption)(menu.get(choice))).id;
	}
	
	/** Do a menu for the user to choose a student.
	  * @param menuDescription the menu heading
	  * @return the courseID of the course chosen, or null if "back" is chosen
	  */
	public static String chooseStudentID(String menuDescription) {
		ResultSet rs = DatabaseAdapter.executeSQLQuery("SELECT ID,FirstName,LastName FROM Student");

		String choice;
		Menu menu = new Menu(menuDescription);
		int i = 1;
		try {
			while (rs.next()) {
				StudentOption opt = new StudentOption();
				opt.id = rs.getString(1);
				opt.firstName = rs.getString(2);
				opt.lastName = rs.getString(3);
				menu.add(i + "", opt);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println("SQL exception!");
		}
		menu.add("b", "back");
		
		choice = menu.show();
		
		return choice.equals("b") ? null : ((StudentOption)(menu.get(choice))).id;
	}
}


