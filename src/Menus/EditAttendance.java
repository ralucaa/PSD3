package Menus;

import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseInteraction.DatabaseAdapter;
import Objects.Menu;

public class EditAttendance {
	private class StudentAttendance {
		String studentID, firstName, lastName, status;
		public String toString() {
			return studentID + "\t" + firstName + " " + lastName + "\t" + status;
		}
	}

	private static String chooseStatus() {
		String choice;
		Menu<String> menu = new Menu<String>("Change status to:");
		menu.add("1", "present");
		menu.add("2", "absent");
		menu.add("3", "mv");
		menu.add("4", "unset");
		menu.add("b", "back (leave it alone)");
		choice = menu.show();
		if (choice.equals("b")) 
			return null;
		if (choice.equals("4"))
			return "";
		return menu.get(choice);
	}

	private void session(String sessionID) {
		ResultSet rs = DatabaseAdapter.executeSQLQuery(
			"SELECT Student.ID, Student.FirstName, Student.LastName, Attendance.Status"
				+ " FROM Student,Attendance"
				+ " WHERE Student.ID=Attendance.StudentID"
				+ " AND Attendance.SessionID = " + sessionID);
		String choice;
		Menu menu = new Menu("Select a student to edit:");
		int i = 1;
		try {
			while (rs.next()) {
				StudentAttendance sa = new StudentAttendance();
				sa.studentID = rs.getString(1);
				sa.firstName = rs.getString(2);
				sa.lastName = rs.getString(3);
				sa.status = rs.getString(4);
				menu.add(i + "", sa);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println("SQL exception!");
		}
		menu.add("i", "import barcodes");
		menu.add("b", "back");
		do {
			choice = menu.show();
			if (!choice.equals("b")) {
				if (choice.equals("i")) {
					System.out.println("not implemented yet");
				} else {
					// it's a student
					StudentAttendance sa = (StudentAttendance)menu.get(choice);
					String newStatus = chooseStatus();
					if (newStatus == null)
						continue;
					sa.status = newStatus;
					String query = "UPDATE Attendance SET status=\"" + newStatus
						+ "\" WHERE studentID=\"" + sa.studentID
						+ "\" AND sessionID=" + sessionID;
					System.out.println(query);
					DatabaseAdapter.executeSQLUpdate(query);
				
				}
			}
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

