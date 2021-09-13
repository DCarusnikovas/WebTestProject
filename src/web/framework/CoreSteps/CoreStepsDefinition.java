package web.framework.CoreSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import static web.framework.CoreSteps.Support.CoreStepsHelper.printDebug;

public class CoreStepsDefinition {
	
    @Given("^I execute step \"([^\"]*)\"$")
    public static void i_execute_step_text(String stepDetails) throws Throwable {
    	
    	printDebug("i_execute_step_something", stepDetails, false);
       
    }
    
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to_something(String url) throws Throwable {
       WebDriverManager.startBrowser(url);
    }
    
    
    @And("^I click \"([^\"]*)\"$")
    public void i_click_element(String element) throws Throwable {
    	
    	WebDriverManager.getDriver().findElement(By.xpath(element)).click();
    	printDebug("i_click_element", "Successfully clicked on element -> "+element, false);
    }
}
