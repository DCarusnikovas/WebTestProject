package web.framework.CoreSteps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import web.framework.CoreSteps.Support.CoreStepsHelper;

public class CoreStepsDefinition {
	
    @Given("^I execute step \"([^\"]*)\"$")
    public static void i_execute_step_text(String stepDetails) throws Throwable {
    	
    	CoreStepsHelper.printDebug("i_execute_step_something", stepDetails, false);
       
    }
    
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to_something(String url) throws Throwable {
       WebDriverManager.startBrowser(url);
    }
}
