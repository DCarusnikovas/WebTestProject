package web.framework.CoreSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import static web.framework.CoreSteps.Support.CoreStepsHelper.printDebug;

public class CoreStepsDefinition {
	
    @Given("^I execute step \"([^\"]*)\"$")
    public static void i_execute_step_text(String stepDetails) throws Throwable {
    	
    	printDebug("i_execute_step_something", stepDetails, false);
       
    }
    
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String url) throws Throwable {
       WebDriverManager.startBrowser(url);
    }
    
    
    @And("^I click \"([^\"]*)\"$")
    public void i_click_element(String element) throws Throwable {
    	
    	WebDriverManager.findAndHightlight(element).click();
    	printDebug("i_click_element", "Successfully clicked on element -> "+element, false);
    }
    
    @And("^I enter a text \"([^\"]*)\" into \"([^\"]*)\"$")
    public void i_enter_a_text_into(String text, String element)  {
    	WebDriverManager.findAndHightlight(element).sendKeys(StateMap.getValueIfState(text));
    	printDebug("i_enter_a_text_into", "Successfully enter text"+StateMap.getValueIfState(text)+" in to element -> "+element, false);
        
    }
    
}
