import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainMenu {

	private static void showWelcomeMessage() {
		System.out.println("Welcome to TeamKMoodle!");
	};
	
	private static void closeApp() {
		System.out.println("Thank you for using TeamKMoodle!");
		System.exit(0);
	}
	
	private static void printInvalidInput() {
		System.out.println("Invalid input.");
	}
	
	private static void printInformationNotAvailable() {
		System.out.println("The information you have requested is not available.");
	}
	
	private static void displaySeparator() {
		System.out.println("================================================================================================\n");
	}
	
	private static boolean authenticate(BufferedReader reader) {
		try {
			// Read credentials.
			System.out.print("Username: ");
			String username = reader.readLine();
			System.out.print("Password:");
			String password = reader.readLine();
			
			// Return authentication result.
			return UserAuthentication.checkCredentials(username, password);
		} catch (Exception e) {
			printInvalidInput();
			return false;
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
			String[] response = StudentAttendance.getAttendanceByCourse(course, sessionType, username);
			displaySeparator();
			for (String s : response) {
				System.out.printf("Course: %s | Session type: %s | Name: %s | Session attended: %s\n", course, sessionType, username, s);
			}
			displaySeparator();
			
		} catch (Exception e) {
			printInformationNotAvailable();
			monitorAttendanceSingle(reader);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Please input your credentials (Username: admin, Password: teamk): ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// Loop until the credentials are valid.
		while (!authenticate(reader)) {
			System.out.println("Invalid credentials. Do you want to retry? [Y/N]");
			try {
				String response = reader.readLine().toLowerCase();
				
				if (!response.equalsIgnoreCase("y")) {
					closeApp();
				}
			} catch (Exception e) {
				printInvalidInput();
				closeApp();
			}
		}
		
		showWelcomeMessage();
		
		// Output the top-level menu.
		String input = "";
		do {
			System.out.println("\nWhat do you want to do?\n1: Export attendance to CSV\n2: Monitor attendance\nq: Quit\n");
			try {				
				input = reader.readLine();
				
				if (input.equals("1")) {					
					System.out.println("Menu 1 selected!");
				} else if (input.equals("2")){
					System.out.println("Menu 2 selected!\n");
					monitorAttendanceSingle(reader);					
				}
			} catch (Exception e) {
				break;
			}
		} while (!input.equals("q"));
		
		closeApp();
	}

}
