package DatabaseInteraction;
public class UserAuthentication {

	private static final String CORRECT_USERNAME = "admin";
	private static final String CORRECT_PASSWORD = "teamk";

	public static boolean checkCredentials(String username, String password) {
		return (username.compareTo(CORRECT_USERNAME) == 0 && password.compareTo(CORRECT_PASSWORD) == 0);
	}
}
