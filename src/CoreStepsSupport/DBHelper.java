package CoreStepsSupport;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import CoreSteps.BaseRunner;

public class DBHelper {

	private static Connection conn = null;

	public static Connection getConnection() throws Exception {

		if (conn == null || conn.isClosed()) {
			
			String [] dbCon =BaseRunner.getProperty("setting.connectionATDB").split(",");
			String url ="jdbc:mysql://"+dbCon[0];
			String username = dbCon[1];
			String password = dbCon[2];
			
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
	
	public static Map<Integer, ArrayList<String>> runSelectQuery(String query,Boolean singleCollumn, Boolean singleRow) throws Exception {
		
    	assertTrue("Only select queries can be used with this grammar and should start from SELECT. current requested query is :" +query,query.toUpperCase().startsWith("SELECT"));
    	
		int countHeaders = query.toUpperCase().split("FROM")[0].split(",").length;
		
		CoreStepsHelper.printDebug("runSelectQuery", "Found headers in query: "+countHeaders, false);
		if(singleCollumn)
			assertTrue("Query has more then 1 collumn, please check query: "+query,countHeaders==1);
		
		Statement st = null;
		ResultSet rs= null;
		
		try {
			st = getConnection().createStatement();
			rs=st.executeQuery(query);
				  
		} catch (Exception e) {
			throw new Exception("SQL Error: " + e);
		}


		Map<Integer, ArrayList<String>> resultMap =new HashMap<Integer, ArrayList<String>>();
		
		while( rs.next()) {
			
			ArrayList<String> ar = new ArrayList<String>();
			for(int x=1;x<=countHeaders; x++)
				ar.add(rs.getString(x));
			resultMap.put(1, ar);
		}
		
		
		
		if(singleRow) 
			assertTrue("Query has returned "+resultMap.size()+" rows, please check query as expected only 1 result",resultMap.size()==1);
			
		
		
		return resultMap;
	}
	
	
	public static void closeConnection() throws SQLException {
		if(conn!=null||!conn.isClosed())
			conn.close();
	}

	public static void logReport(ArrayList<String> list) {

		String insertQuery ="INSERT INTO sql4437652.Automation('execution_tag','result','scenario_tags')"
				+ "  VALUES ('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"')";

				try {
					runDML(insertQuery);
					CoreStepsHelper.printDebug("logReport", "Report Logged", false);
				} catch (Exception e) {
					CoreStepsHelper.printError("logReport", "Issue to connect to DB to log report", false);
				}
				
		
	}
	
	public static void runDML(String query) throws Exception {
		
		
		Statement st = null;

		
		try {
			st = getConnection().createStatement();
			st.executeQuery(query);
				  
		} catch (Exception e) {
			throw new Exception("SQL Error: " + e);
		}


		
	}
	
}

