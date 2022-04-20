package CoreSteps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import CoreStepsSupport.DBHelper;
import CoreStepsSupport.StateMapHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class BaseRunner {
	

	private static final Properties prop = new Properties();
	private Status status;
	private Scenario scenarioCurrent;
	private static boolean isFailed = false;
	private String scenarios_tags ="";
	private String scenario_name ="";
	private String userName = System.getProperty("user.name");
	private String startTimeDate;
	private String endTimeDate;
	

	public void Setup() {

		startTimeDate = DateCoreSteps.getCurrentDateTime("dd/MM/YYYY - HH:mm:ss");
		System.out.println(">>>>>>>>>>>>>>>> Preparing for test run"+(isEclipse()?" in Eclipse":"")+" by user:"+userName);
		StateMapHelper.setStateMap("State.TestStartTimeAndDate",
				DateCoreSteps.getCurrentDateTime("dd MMM YYYY - HH:mm:ss"));
		System.out.println(">>>>>>>>>>>>>>>> Start Date : " + StateMapHelper.getValueIfState("State.TestStartTimeAndDate"));

		// Get properties Properties from automationProperties file
		System.out.println(">>>>>>>>>>>>>>>> Initializing Setting properties");
		initializeSettingsMap();
		System.out.println(">>>>>>>>>>>>>>>> Properties with Setting set");

		System.out.println(">>>>>>>>>>>>>>>> Test Starting : " + startTimeDate);

	}
	
	@Before
	public void before(Scenario scenario) throws Exception {
		scenarioCurrent =scenario;
		scenario_name = scenarioCurrent.getName();
		for(String tag :scenarioCurrent.getSourceTagNames())
			scenarios_tags +=tag+",";
		scenarios_tags=scenarios_tags.substring(0,scenarios_tags.length()-1); 
		
		System.out.println(">>>>>>>>>>>>>>>> Test Name : "+ scenario_name);
		System.out.println(">>>>>>>>>>>>>>>> Test tags : "+ scenarios_tags);
		if(StateMapHelper.mappingVariableMapSize()<1) {
			System.out.println(">>>>>>>>>>>>>>>> Load mappings ");
			StateMapHelper.createMapppings();

		} 
		
	}
	
    @After
    public void after(Scenario scenario) {
    	
		//print all state variables
		System.out.println(">>>>>>>>>>>>>>>> List of State variables : ");
		StateMapHelper.printAllStateVariables(true);
		System.out.println(">>>>>>>>>>>>>>>> End of  List of State variables");
        status = scenarioCurrent.getStatus();
        isFailed = status == Status.FAILED;
        
		if(BaseRunner.getProperty("setting.closeBrowser").equalsIgnoreCase("true")) {
			System.out.println(">>>>>>>>>>>>>>>> Closing Browser as requested");
			WebDriverSetupManager.closeBrowser(true);
		} else if (BaseRunner.getProperty("setting.closeBrowserIfPass").equalsIgnoreCase("true")&&!isScenarioFailed()){
			System.out.println(">>>>>>>>>>>>>>>> Closing Browser as requested as test Pass");
			WebDriverSetupManager.closeBrowser(true);
		} else
			WebDriverSetupManager.closeBrowser(false);
        
		ArrayList<String> list = new ArrayList<String>();
		list.add(scenarios_tags);
		list.add(isFailed?"FAILED":"PASSED");
		list.add(scenarios_tags);
		if (BaseRunner.getProperty("setting.logReportToDB").equalsIgnoreCase("true"))
		 DBHelper.logReport(list);
        
    }	
    


	public void TearDown() throws SQLException {
		
		//DBHelper.closeConnection();
		
		endTimeDate = DateCoreSteps.getCurrentDateTime("dd/MM/YYYY - HH:mm:ss");
		System.out.println(">>>>>>>>>>>>>>>> Test Ended : " + endTimeDate);



		System.out.println(">>>>>>>>>>>>>>>> TearDown Completed : " + DateCoreSteps.getCurrentDateTime("dd/MM/YYYY - HH:mm:ss"));
	}

	/**
	 * this method will initialize settings from automation.properties file
	 * 
	 * @author DCaru
	 */
	private void initializeSettingsMap() {

		FileReader reader;
		try {
			reader = new FileReader("settings/automation.properties");
			prop.load(reader);
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found please check path");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Failed to read Properties file please check properties");
			e.printStackTrace();
		}

	}

	/**
	 * This will return parameter as text from settings/automation.properties file
	 * 
	 * @param parameterName - String parameter from property file, first part before
	 *                      equal
	 * @return - String value of 2nd part of parameter after equal
	 * @author DCaru
	 */
	public static String getProperty(String parameterName) {
		return prop.getProperty(parameterName);

	}
	
    public static Boolean isScenarioFailed() {
    	return isFailed;
    }

	private boolean isEclipse() {
	    boolean isEclipse = System.getProperty("java.class.path").toLowerCase().contains("eclipse");
	    return isEclipse;
	}	
	
}
