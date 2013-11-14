package Menus;

import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseInteraction.DatabaseAdapter;
import Objects.Menu;

public class SharedDBMenus {

	private static class CourseOption {
		String id, name;

		public String toString() {
			return id + " : " + name;
		}
	}

	private static String chooseID(ResultSet rs, String menuDescription) {
		String choice;
		Menu menu = new Menu(menuDescription);
		int i = 1;
		
		try {
			while (rs.next()) {
				CourseOption co = new CourseOption();
				co.id = rs.getString(1);
				co.name = rs.getString(2);
				menu.add(i + "", co);
				i ++;
			}
		} catch (SQLException e) {
			//nothing for now
		}
		menu.add("b", "back");
		
		choice = menu.show();
		
		return choice.equals("b") ? null : ((CourseOption)(menu.get(choice))).id;
	}

	public static String chooseCourseID(String menuDescription) {
		ResultSet rs = DatabaseAdapter.executeSQLQuery("SELECT ID,Name FROM Course");
		return chooseID(rs, menuDescription);
	}
	
	public static String chooseSessionIDinCourse(String menuDescription, String courseID) {
		String query = "SELECT ID,Name FROM Session WHERE courseID=\"" + courseID + "\"";
		//System.out.println(query);
		ResultSet rs = DatabaseAdapter.executeSQLQuery(query);
		return chooseID(rs, menuDescription);
	}
}


