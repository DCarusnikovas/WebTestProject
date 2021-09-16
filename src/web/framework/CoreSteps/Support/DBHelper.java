package web.framework.CoreSteps.Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	private static Connection conn = null;

	public static Connection getConnection() throws Exception {

		if (conn == null || conn.isClosed()) {
			
			String url ="jdbc:mysql://sql4.freesqldatabase.com:3306";
			String username = "sql4437652";
			String password = "E31eEWyh2z";
			
			try {
				conn = DriverManager.getConnection(url,username,password);

				// Do something with the Connection

			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				throw new Exception("SQL Connection Error: " + ex);
			}
	
		}
		
		return conn;
	}
	
	public static void runDML() throws Exception {
		
		String query = "select * from sql4437652.Automation";
		Statement st = null;
		ResultSet rs= null;
		
		try {
			st = getConnection().createStatement();
			rs=st.executeQuery(query);
			
			while(rs.next())  
				System.out.println("Query Returned " +rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
				  
		} catch (Exception e) {
			throw new Exception("SQL Error: " + e);
		}
		finally {
			conn.close();
		}
		
		
	}
}
