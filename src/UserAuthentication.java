public class UserAuthentication implements Authentication {

	private static final String CORRECT_USERNAME = "admin";
	private static final String CORRECT_PASSWORD = "teamk";

	public boolean checkCredentials(String username, String password) {
		return (username.compareTo(CORRECT_USERNAME) == 0 && password.compareTo(CORRECT_PASSWORD) == 0);
	}
}
