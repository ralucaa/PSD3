package uk.gla.ac.psd3.Prototype2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseAdapter {
	//Store the connection statically to prevent multiple connections.
	private static Connection con;

	/**
	 * Executes the given SQL query and returns the result as a ResultSet.
	 * @param query - the query to be executed. Only use SELECT statements!!!
	 * @return a ResultSet object containing the result.
	 * @throws SQLException 
	 */
	public static ResultSet executeSQLQuery(String query) throws SQLException{
		//Execute the query if the connection has been opened.
		if (openConnection()) {
			Statement stmt = con.createStatement() ;
			ResultSet rs = stmt.executeQuery(query);

			//Return the result.
			return rs;
		}
		return null;
	}

	public static int executeSQLUpdate(String query) throws SQLException {
		//Execute the update if the connection has been opened.
		if (openConnection()) {
			Statement stmt = con.createStatement() ;
			int result = stmt.executeUpdate(query);
			//Return the result.
			return result;
		}
		return -1;
	}

	/**
	 * Tries to open the connection if it's not already open.
	 * Will print the error message if it fails.
	 * @return true if the connection is or has been opened, false if it failed.
	 */
	private static boolean openConnection(){
		//Open the connection if not already opened.
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://serengeti.uk.to:3306/PSD3", "psd3", "teamk") ;
			}
			return true;
		} catch (Exception ex) {
			System.out.println("Could not open connection! Error details:\n" + ex.getMessage());
			return false;
		}
	}

	/**
	 * Tries to close the connection if it is open.
	 * Will print the error message if it fails.
	 * @return true if the connection is or has been closed, false if it failed.
	 */
	public static boolean closeConnection(){
		try {
			if (con != null) {
				con.close();
				con = null;
			}
			return true;
		} catch (Exception ex) {
			System.out.println("Could not close connection! Error details: \n" + ex.getMessage());
			return false;
		}
	}
}
