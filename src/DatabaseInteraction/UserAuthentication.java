package DatabaseInteraction;

import java.util.ArrayList;

import Objects.Account;

public class UserAuthentication {
	private static ArrayList<Account> accounts;

	private static void instantiateAccounts(){
		if (accounts == null) {
			accounts = new ArrayList<Account>();
			accounts.add(new Account("admin", "admin", Account.TYPE_ADMIN));
			accounts.add(new Account("tutor", "tutor", Account.TYPE_TUTOR));
		}
	}

	/**
	 * @param username - The username.
	 * @param password - The password.
	 * @return a string containing the account type if found and password ok, null otherwise.
	 */
	public static Account checkCredentials(String username, String password) {
		instantiateAccounts();
		
		for(Account account : accounts) {
			if (account.getName().equals(username)){
				if (account.checkPassword(password)) {
					return account;
				}
				//Invalid password, return null;
				return null;
			}
		}
		
		//Username not found, return null;
		return null;
	}
}
