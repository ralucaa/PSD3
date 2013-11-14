package Menus;

import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseInteraction.DatabaseAdapter;
import Objects.Menu;

public class EditAttendance {

	private void session(String sessionID) {
		//TODO: get list of students and attendance
		String choice;
		Menu menu = new Menu("PSD3 lab1: select a student to edit");
		menu.add("i", "import barcodes");
		menu.add("1", "Martynas\tP");
		menu.add("2", "Helen\tA");
		menu.add("b", "back");
		do {
			choice = menu.show();
			if (!choice.equals("b"))
				System.out.println("Not implemented yet");
		} while (!choice.equalsIgnoreCase("b"));
	}

	private void course(String courseID) {
		while (true) {
			String sessionID = SharedDBMenus.chooseSessionIDinCourse(courseID + " : select a session", courseID);
			if (sessionID == null) {
				return;
			}
			session(sessionID);
		}
	}

	/**main menu for editing attendance*/
	public void go() {
		while (true) {
			String courseID = SharedDBMenus.chooseCourseID("Select a course (hint: Algorithmics):");
			if (courseID == null) {
				return;
			}
			course(courseID);
		}
	}
	
	/**for testing*/
	public static void main(String[] args) {
		EditAttendance ea = new EditAttendance();
		ea.go();
	}
}

