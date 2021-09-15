package web.framework.CoreSteps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import web.framework.CoreSteps.Support.CoreStepsHelper;
import web.framework.CoreSteps.Support.StateMapHelper;

public class BaseRunner {

	private static final Properties prop = new Properties();

	public void Setup() {

		System.out.println(">>>>>>>>>>>>>>>> Preparing for test ");
		StateMapHelper.setStateMap("State.TestStartTimeAndDate",
				DateCoreSteps.getCurrentDateTime("dd MMM YYYY - HH:mm:ss"));
		System.out.println(">>>>>>>>>>>>>>>> Start Date : " + StateMap.getValueIfState("State.TestStartTimeAndDate"));

		// Get properties Properties from automationProperties file
		System.out.println(">>>>>>>>>>>>>>>> Initializing Setting properties");
		initializeSettingsMap();
		System.out.println(">>>>>>>>>>>>>>>> Properties with Setting set");

		System.out.println(">>>>>>>>>>>>>>>> Test Starting : " + DateCoreSteps.getCurrentDateTime("dd MMM YYYY - HH:mm:ss"));

	}

	public void TearDown() {
		
		System.out.println(">>>>>>>>>>>>>>>> Test Ended : " + DateCoreSteps.getCurrentDateTime("dd MMM YYYY - HH:mm:ss"));
		
		//print all state variables
		System.out.println(">>>>>>>>>>>>>>>> List of State variables : ");
		StateMapHelper.printAllStateVariables();
		System.out.println(">>>>>>>>>>>>>>>> End of  List of State variables");

		if (BaseRunner.getProperty("setting.closeBrowser").equalsIgnoreCase("true")) {
			System.out.println(">>>>>>>>>>>>>>>> Closing Browser as requested");
			WebDriverManager.closeBrowser();
		}

		System.out.println(">>>>>>>>>>>>>>>> TearDown Completed : " + DateCoreSteps.getCurrentDateTime("dd MMM YYYY - HH:mm:ss"));
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
}
