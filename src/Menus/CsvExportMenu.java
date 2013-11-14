
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
		System.out.println("not implemented yet");
	}
	
	public static void exportCourseAttendance() {
		CsvExporting exporter = new CsvExporting();
		String courseID = SharedDBMenus.chooseCourseID("Choose a course:");
		String csvFileName = getCsvFileName();
		exporter.exportStudenntDataForCourse(courseID, csvFileName);
	}
}

