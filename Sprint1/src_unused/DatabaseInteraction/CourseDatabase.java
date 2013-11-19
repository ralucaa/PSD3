package DatabaseInteraction;
import java.util.ArrayList;
import java.io.*;

import Objects.Course;

public class CourseDatabase {
	public static ArrayList<Course> getAllCourses() {
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		ArrayList<Course> courses = new ArrayList<Course>();

		try {
			String line1,line2;
			br1 = new BufferedReader(new FileReader("Course.csv"));
			br2 = new BufferedReader(new FileReader("AllGrades.csv"));
			while ((line1 = br1.readLine()) != null && (line2=br2.readLine())!=null) {
				String[] components1 = line1.split(",");
				String[] components2 = line2.split(",");
				Course course = new Course(components1[0], components1[1],Integer.parseInt(components2[0]), Integer.parseInt(components2[1]));
				courses.add(course);
			}
			return courses;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (br1 != null && br2!=null) {
					br1.close();
					br2.close();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return null;
			}
		}
	}

	public static Course getCertainCourse(String id){
		ArrayList<Course> courses = getAllCourses();

		for(Course course : courses) {
			if(course.getId().equals(id))
				System.out.println("Course " + id + " has been found!");
			return course;
		}

		System.out.println("The course is not in the database.");
		return null;
	}
}