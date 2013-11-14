package Menus;

import Objects.Menu;

public class EditAttendance {
	private StudentAttendance db;
	
	public EditAttendance(StudentAttendance db) {
		this.db = db;
	}

	private void session() {
		//TODO: get list of students and attendance
		String choice;
		Menu menu = new Menu("PSD3 lab1: select a student to toggle");
		menu.add("1", "Martynas\tP");
		menu.add("2", "Helen\tA");
		menu.add("b", "back");
		do {
			choice = menu.show();
			if (!choice.equals("b"))
				System.out.println("Not implemented yet");
		} while (!choice.equalsIgnoreCase("b"));
	}

	private void course() {
		String choice;
		Menu menu = new Menu("PSD3: select a session");
		menu.add("1", "Lab 1");
		menu.add("b", "back");
		do {
			choice = menu.show();
			if (choice.equals("1"))
				session();
		} while (!choice.equalsIgnoreCase("b"));
	}

	/**main menu for editing attendance*/
	public void go() {
		String choice;
		Menu menu = new Menu("Select a course (hint: PSD3):");
		menu.add("1", "PSD3");
		menu.add("2", "ALG3");
		menu.add("3", "AP3");
		menu.add("b", "back");
		do {
			choice = menu.show();
			if (choice.equals("1"))
				course();
			else if (choice.equals("2") || choice.equals("3"))
				System.out.println("Nothing here yet!");
		} while (!choice.equalsIgnoreCase("b"));
	}
	
	/**for testing*/
	public static void main(String[] args) {
		StudentAttendance sa = new StudentAttendance();
		EditAttendance ea = new EditAttendance(sa);
		ea.go();
	}
}

