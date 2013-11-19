package Menus;

import java.util.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseInteraction.*;
import Objects.Menu;

public class EditAttendance {
	private class StudentAttendance {
		String studentID, firstName, lastName, status;
		public String toString() {
			return studentID + "\t" + firstName + " " + lastName + "\t" + status;
		}
	}

	private static void importBarcodes(String sessionID) {
		BarcodeImport bi;
		int errors;
		java.util.Date start, end;
		String query = "SELECT StartTime,EndTime FROM Session WHERE ID=" + sessionID;
		//System.out.println(query);
		ResultSet rs = DatabaseAdapter.executeSQLQuery(query);
		try {
			rs.next();
			start = rs.getTimestamp(1);
			end = rs.getTimestamp(2);
		} catch (SQLException e) {
			System.out.println("SQL exception!");
			return;
		}
		//System.out.println(start + " - " + end);
		bi = new BarcodeImport(start, end);
		Scanner sc = new Scanner(System.in);
		System.out.println("Path to csv file to be read? ");
		String filename =  sc.next();
		try {
			errors = bi.load(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return;
		}
		if (errors != 0) {
			System.out.println("Number of errors: " + errors);
		}
		
		for (String barcode : bi.getBarcodes()) {
			//System.out.println(barcode);
			query = "SELECT ID FROM Student "
				+ "WHERE Barcode=\"" + barcode + "\"";
			rs = DatabaseAdapter.executeSQLQuery(query);
			try {
				if (rs.next()) {
					String studentID = rs.getString(1);
					//System.out.println(studentID);
					query = "UPDATE Attendance SET Status=\"present\""
						+ "WHERE StudentID=\"" + studentID + "\"";
					DatabaseAdapter.executeSQLUpdate(query);
				}
			} catch (SQLException e) {
				System.out.println("SQL exception!");
				return;
			}
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
		String choice;
		do {
			ResultSet rs = DatabaseAdapter.executeSQLQuery(
				"SELECT Student.ID, Student.FirstName, Student.LastName, Attendance.Status"
					+ " FROM Student,Attendance"
					+ " WHERE Student.ID=Attendance.StudentID"
					+ " AND Attendance.SessionID = " + sessionID);
			Menu<Object> menu = new Menu<Object>("Select a student to edit:");
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
			menu.add("u", "unset everyone (so you can test it again!)");
			menu.add("b", "back");
		
			choice = menu.show();
			if (!choice.equals("b")) {
				if (choice.equals("i")) {
					importBarcodes(sessionID);
				} else if (choice.equals("u")) {
					String query = "UPDATE Attendance SET status=\"\" "
						+ "WHERE sessionID=" + sessionID;
					//System.out.println(query);
					DatabaseAdapter.executeSQLUpdate(query);
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
					//System.out.println(query);
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
	/*
	public static void main(String[] args) {
		EditAttendance ea = new EditAttendance();
		ea.go();
	}*/
}

