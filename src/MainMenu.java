import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MainMenu {

	private static void showWelcomeMessage(){
		System.out.println("Welcome to TeamKMoodle!");
	};
	
	private static void closeApp(){
		System.out.println("Thank you for using TeamKMoodle!");
		System.exit(0);
	}
	
	private static void printInvalidInput(){
		System.out.println("Invalid input.");
	}
	
	private static boolean authenticate(BufferedReader reader) {
		try
		{
			//Read credentials.
			System.out.print("Username: ");
			String username = reader.readLine();
			System.out.print("Password:");
			String password = reader.readLine();
			
			//Return authentication result.
			return UserAuthentication.checkCredentials(username, password);
		}
		catch (Exception ex){
			printInvalidInput();
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Please input your credentials(Username: admin, Password: teamk).");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Loop until the credentials are valid.
		while (!authenticate(reader)) {
			System.out.println("Invalid credentials. Do you want to retry? [Y/N]");
			try
			{
				String response = reader.readLine().toLowerCase();
				
				if (!response.equalsIgnoreCase("y")){
					closeApp();
				}
			}
			catch (Exception ex){
				printInvalidInput();
				closeApp();
			}
		}
		
		showWelcomeMessage();
		
		//Output the top-level menu.
		String input = "";
		do {
			System.out.println("What do you want to do?\n1. Export attendance to CSV\n2. Monitor attendance\nq. Quit");
			try{
				input = reader.readLine();
				System.out.println("Menu 1 selected!");
				if (input.equals("1")) {
					//TODO
					System.out.println("Menu 1 selected!");
				} else if (input.equals("2")){
					//TODO
					System.out.println("Menu 2 selected!");
				}
			} catch (Exception ex) {
				break;
			}
		} while (!input.equals("q"));
		
		closeApp();
	}

}
