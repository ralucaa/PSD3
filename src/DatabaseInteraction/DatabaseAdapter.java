package DatabaseInteraction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DatabaseAdapter {
	//Store the connection statically to prevent multiple connections.
	private static Connection con;

	public static ResultSet executeSQLQuery(String query){
		try {
			//Open the connection if not already opened.
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://serengeti.uk.to:3306/PSD3", "psd3", "teamk") ;
			}

			//Execute the query.
			Statement stmt = con.createStatement() ;
			ResultSet rs = stmt.executeQuery(query);

			//Return the result.
			return rs;
		} catch (Exception ex) {
			System.out.println("Something went wrong! Error details:\n" + ex.getMessage());
			return null;
		}
	}

	public static void closeConnection(){
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception ex) {
			System.out.println("Could not close connection!");
		}
	}

	/*
	public static void main(String args[]){
		try {
			ResultSet rs = executeSQLQuery("SELECT * FROM Student");

			try {
				while ( rs.next() ) {
					int numColumns = rs.getMetaData().getColumnCount();
					for ( int i = 1 ; i <= numColumns ; i++ ) {
						// Column numbers start at 1.
						// Also there are many methods on the result set to return
						//  the column as a particular type. Refer to the Sun documentation
						//  for the list of valid conversions.
						System.out.print( "COLUMN " + i + " = " + rs.getObject(i) );
					}
					System.out.println();
				}
			} catch (Exception ex) {
				System.out.println("Cry Cry " + ex.getMessage());
			}
		} catch (Exception ex) {
			System.out.println("Could not connect.");
		}
	}*/
}
