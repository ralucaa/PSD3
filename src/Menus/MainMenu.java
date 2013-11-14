package Menus;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import DatabaseInteraction.DatabaseAdapter;
import DatabaseInteraction.UserAuthentication;
import Objects.Account;
import Objects.Communication;
import Objects.Menu;
import Menus.StudentAttendance;

public class MainMenu {
	private static Account account;

	private static void closeApp() {
		DatabaseAdapter.closeConnection();
		Communication.showExitMessage();
		System.exit(0);
	}

	private static void authenticate(BufferedReader reader) {
		try {
			// Read credentials.
			System.out.print("Username: ");
			String username = reader.readLine();
			System.out.print("Password: ");
			String password = reader.readLine();

			// Return authentication result.
			account = UserAuthentication.checkCredentials(username, password);
		} catch (Exception e) {
			Communication.printInvalidInput();
		}
	}

	private static void monitorAttendanceSingle(BufferedReader reader) {
		try {
			// Find out who we want to get information about
			System.out.println("Please input the name of the course (i.e. PSD3): ");
			String course = reader.readLine();
			System.out.println("Please input the type of the session (i.e. LAB): ");
			String sessionType = reader.readLine();
			System.out.println("Please input the the name of the student (i.e. Raluca): ");
			String username = reader.readLine();

			// Run the query and print out the results
			StudentAttendance sa = new StudentAttendance();
			String[] response = sa.getAttendanceByCourse(course, sessionType, username);
			Communication.displaySeparator();
			for (String s : response) {
				System.out.printf("Course: %s | Session type: %s | Name: %s | Session attended: %s\n", course, sessionType, username, s);
			}
			Communication.displaySeparator();

		} catch (Exception e) {
			Communication.printInformationNotAvailable();
			monitorAttendanceSingle(reader);
		}
	}

	private static void showAdminMenu(BufferedReader reader){
		//Create the menu.
		Menu menu = new Menu("Administration menu\nWhat do you want to do?");
		menu.add("1", "Export attendance to CSV");
		menu.add("2", "Monitor attendance");
		menu.add("q", "Quit");

		//Show the menu.
		String input = menu.show();
		
		//Process the input.
		if (input.equals("1")) {		
			//TODO Remove this when adding menu!
			System.out.println("Export attendance selected!");
		} else if (input.equals("2")){
			monitorAttendanceSingle(reader);
		}
	}

	private static void showTutorMenu(BufferedReader reader){
		//Create the menu.
		Menu menu = new Menu("Administration menu\nWhat do you want to do?");
		menu.add("1", "Monitor attendance");
		menu.add("q", "Quit");

		//Show the menu.
		String input = menu.show();
		
		//Process the input.
		if (input.equals("1")) {
			monitorAttendanceSingle(reader);
		}
	}

	public static void main(String[] args) {
		System.out.println("Please input your credentials (Username: admin, Password: teamk): ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Loop until the credentials are valid.
		authenticate(reader);
		while (account == null) {
			System.out.println("Invalid credentials. Do you want to retry? [Y/N]");
			try {
				String response = reader.readLine().toLowerCase();

				if (!response.equalsIgnoreCase("y")) {
					closeApp();
				}

				authenticate(reader);
			} catch (Exception e) {
				Communication.printInvalidInput();
				closeApp();
			}
		}

		Communication.showWelcomeMessage();

		// Output the top-level menu.
		if (account.getType() == Account.TYPE_ADMIN) {
			showAdminMenu(reader);
		} else if (account.getType() == Account.TYPE_TUTOR) {
			showTutorMenu(reader);
		}

		closeApp();
	}

}
