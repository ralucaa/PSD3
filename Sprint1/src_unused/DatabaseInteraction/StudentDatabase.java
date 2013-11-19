package DatabaseInteraction;

import java.io.*;
import java.util.ArrayList;

import Objects.Student;


public class StudentDatabase {
	/**
	 * @return all the students in the database.
	 */
	public static ArrayList<Student> getAllStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		BufferedReader br = null;

		try {
			String line;
			br = new BufferedReader(new FileReader("Student.csv"));

			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] components = line.split(",");
				students.add(new Student(components[0], components[1], components[2]));
			}

			return students;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (br != null)br.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return null;
			}
		}
	}

	/**
	 * @param id - The id of the requested student.
	 * @return the requested student or null if it is not found in the database.
	 */
	public static Student getCertainStudent(String id){
		ArrayList<Student> students = getAllStudents();

		for(Student student : students) {
			if(student.getId().equals(id)) {
				System.out.println("Student " + id + " has been found!");
				return student;
			}
		}

		System.out.println("The student is not in the database.");
		return null;
	}
}
