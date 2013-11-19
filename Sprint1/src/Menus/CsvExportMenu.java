
package Menus;

import Objects.Menu;
import DatabaseInteraction.*;
import java.util.*;

public class CsvExportMenu {

	private static String getCsvFileName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Path to csv file to be written? ");
		return sc.next();
	}

	public static void exportSingleStudentAttendance() {
		CsvExporting exporter = new CsvExporting();
		String studentID = SharedDBMenus.chooseStudentID("Choose a student:");
		String csvFileName = getCsvFileName();
		exporter.exportSingleStudentAttendance(studentID, csvFileName);
	}
	
	public static void exportCourseAttendance() {
		CsvExporting exporter = new CsvExporting();
		String courseID = SharedDBMenus.chooseCourseID("Choose a course:");
		String csvFileName = getCsvFileName();
		exporter.exportStudentDataForCourse(courseID, csvFileName);
	}
}

