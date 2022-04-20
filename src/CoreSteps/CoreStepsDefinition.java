package CoreSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static CoreStepsSupport.CoreStepsHelper.printDebug;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import CoreStepsSupport.CoreStepsHelper;
import CoreStepsSupport.StateMapHelper;

public class CoreStepsDefinition {
	
	/**
	 * This grammar will let user to log a step message to be displayed on report and console 
	 *  
	 * @param stepDetails - String text for step => Example : Step 1. Navigate to website
	 */
    @Given("^I execute step \"([^\"]*)\"$")
    public static void i_execute_step_text(String stepDetails) {
    	
    	printDebug("i_execute_step_something", stepDetails, false);
       
    }
    
    /**
     * This grammar will help user to open any website
     * @param url = Sting as web site URL - example http://www.Google.com
     * @throws Throwable
     */
    @Given("^I navigate to \"([^\"]*)\"$")
    public static void i_navigate_to(String url) throws Throwable {
       WebDriverSetupManager.startBrowser(url,true);
    }
    
    
    /**
     * This grammar will help user to navigate to website
     * @param urlName - Google or LG
     * @throws Throwable
     */
    @Given("^I navigate to (Google|LG|Ellucian)$")
    public static void i_navigate_to_selection(String urlName) throws Throwable {
       assertTrue("Only Google or LG allowed to be passed as urlName. User pass value: "+urlName,
    		   urlName.equals("GOOGLE")
    		   ||urlName.equals("LG")||urlName.equals("Ellucian"));
       WebDriverSetupManager.startBrowser(BaseRunner.getProperty("setting.webSite"+urlName),!urlName.equals("Ellucian"));
    }
    
    /**
     * This grammar will let user to click on the web element
     * @param element - XPATH or Mapping
     */
    @And("^I click \"([^\"]*)\"$")
    public static void i_click_element(String element) throws Exception{
    	
    	WebDriverSetupManager.findAndHightlight(element).click();
    	printDebug("i_click_element", "Successfully clicked on element -> "+element, false);
    }
    
    /**
     * This grammar will let a user to type text to web element
     * @param text - text to enter
     * @param element - XPATH or Mapping
     * @throws Exception 

     */
    @And("^I enter a text \"([^\"]*)\" into \"([^\"]*)\"$")
    public static void i_enter_a_text_into(String text, String element) throws Exception  {
    	WebDriverSetupManager.findAndHightlight(element).sendKeys(StateMapHelper.getValueIfState(text));
    	
    	printDebug("i_enter_a_text_into", "Successfully enter text"+StateMapHelper.getValueIfState(text)+" in to element -> "+element, false);
        
    }
    
    @And("^I update a text \"([^\"]*)\" in \"([^\"]*)\"$")
    public static void i_update_a_text_in(String text, String element) throws Exception  {
    	WebElement webElement = WebDriverSetupManager.findAndHightlight(element);
    	webElement.clear();
    	webElement.sendKeys(StateMapHelper.getValueIfState(text));
    	webElement.sendKeys(Keys.SPACE, Keys.BACK_SPACE);
    	//WebDriverManager.findAndHightlight(element).sendKeys(StateMapHelper.getValueIfState(text),Keys.ENTER);
    	printDebug("i_enter_a_text_into", "Successfully enter text"+StateMapHelper.getValueIfState(text)+" in to element -> "+element, false);
        
    }
    
    /**
     * This grammar will let user to see if element is visible or not
     * @param element - XPATH or Mapping
     * @param visibility - true if visible, false if not
     * 
     * @throws Throwable
     */
    @And("^I check that element \"([^\"]*)\" (is|is not) visible$")
    public static void i_check_element_visibility(String element,String visibility) throws Throwable {
    	
        WebDriverSetupManager.visibilityHighlight(element, visibility.equals("is"),false);
    	printDebug("i_check_element_visibility", "The element -> "+element+" "+visibility+" visible as requested" , false);
    }
    
    /**
     * This grammar will let user to check if text1 equal/contains text2
     * @param - contains or equal to
     * @param text -  text1
     * @param toThisText - text2
     */
    @And("^I check that this text \"([^\"]*)\" (contains|equal to) text \"([^\"]*)\"$")
    public static void i_check_text_contain_text(String text,String containsOrEqual, String toThisText)  {
    	
    	boolean  isMatch =CoreStepsHelper.twoStringsMatch(StateMapHelper.getValueIfState(text), StateMapHelper.getValueIfState(toThisText),containsOrEqual.equalsIgnoreCase("contains"));
    	assertTrue("Text 1: "+ StateMapHelper.getValueIfState(text)+(containsOrEqual.equalsIgnoreCase("contains")?" didn't contain ":" was not equal to ")+" Text 2: "+StateMapHelper.getValueIfState(toThisText),isMatch);
 	
    	printDebug("i_check_text_contain_text", "Text 1: "+ StateMapHelper.getValueIfState(text)+" "+containsOrEqual+" Text 2 "+StateMapHelper.getValueIfState(toThisText), false);
    } 
    
    
    /**
     * This grammar will let user to check that text1 do not contain text2
     *  
     * @param text -  text1
     * @param toThisText - text2
     */
    @And("^I check that this text \"([^\"]*)\" does not contain text \"([^\"]*)\"$")
    public static void i_check_text_dont_contain_text(String text, String toThisText)  {
    	
    	boolean  isMatch =!CoreStepsHelper.twoStringsMatch(StateMapHelper.getValueIfState(text), StateMapHelper.getValueIfState(toThisText),false);
    	
    	assertTrue("Text 1: "+ StateMapHelper.getValueIfState(text)+" did contain Text 2: "+StateMapHelper.getValueIfState(toThisText)+" => as not expected",isMatch);
     	 	
    	
    	printDebug("i_check_text_dont_contain_text", "Text 1: "+ StateMapHelper.getValueIfState(text)+" didn't contain Text 2: "+StateMapHelper.getValueIfState(toThisText), false);
    }  
    
 
    
    /**
     * This grammar will let user to check if element has following text
     *  
     * @param element - XPATH or Mapping
     * @param containsOrEqual - "contains" or "equal to"
     * @param text - String text to verify
     */
    @And("^I check that element \"([^\"]*)\" (contains|equal to) text \"([^\"]*)\"$")
    public static void i_check_element_contain_text(String element,String containsOrEqual, String text) throws Exception {
    	
    	CoreStepsHelper.elementHasText(element, StateMapHelper.getValueIfState(text), containsOrEqual.equalsIgnoreCase("contains"),true);
 	
    	printDebug("i_check_element_contain_text", "The element -> "+element+" "+containsOrEqual+" follwing text "+StateMapHelper.getValueIfState(text), false);
    } 
    
    
    /**
     * This grammar will let user to check that Web element do not contain following text
     *  
     * @param element - XPATH or Mapping
     * @param text - String text to verify
     */
    @And("^I check that element \"([^\"]*)\" does not contain text \"([^\"]*)\"$")
    public static void i_check_element_dont_contain_text(String element, String text)  throws Exception {
    	
    	CoreStepsHelper.elementHasText(element, StateMapHelper.getValueIfState(text), false,false);  	
    	
    	printDebug("i_check_element_dont_contain_text", "The element -> "+element+" did not contain follwing text "+StateMapHelper.getValueIfState(text), false);
    }  
    
    
    
	/**
	 * This grammar can be used in feature file as a pause of script for required time in minutes. User will get pop up request with Yes and No options to continue or terminate a run  
	 * @param minutes
	 * @throws Throwable
	 */
	@Then("^I put pause here for \"([^\"]*)\" minutes$")
    public static void i_put_pause_here(int minutes) throws Throwable {
		
		int totalTime = minutes;
		
		//loop via timer
		do{


			printDebug("i_put_pause_here", "Break Point timer will expire in :"+totalTime+" minutes", false);

			//reduce timer for 1 minute for do while loop
			totalTime--;

			//create JoptionPane with Yes_no option and timer for 1 minute to take decision
			JOptionPane msg = new JOptionPane("Do you want to continue...?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
			final JDialog dialogBox = msg.createDialog("Select Yes or No");

			//disable close button
			dialogBox.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

			//set active listener with 1 minute timer
			dialogBox.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent e) {
					super.componentShown(e);

					Timer objTimer = new Timer(60000,new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dialogBox.setVisible(false);	 
						}
					}
							);
					//Start object
					objTimer.start();
				}
			});

			// make object visible
			dialogBox.setVisible(true);

			//if no option selected
			if(msg.getValue().toString().equals("1"))
				assertTrue("[i_put_pause_here]: User requested to abort a test", false);

			//if yes option selected
			if(msg.getValue().toString().equals("0")){
				totalTime=0;
				printDebug("i_put_pause_here", "Resume feature file run", false);
			}

		}while(totalTime > 0);

	}

	
	/**
	 * This grammar should be used if no other option.
	 * It will let user to pause run for x amount of time
	 * @param time - Whole number as String
	 * @param type - milliseconds or seconds orminutes
	 */
	@Then("^I wait \"([^\"]*)\" (milliseconds|seconds|minutes)$")
    public static void waitForMilliseconds(String time, String type) 
	{
		
		try 
		{	
			printDebug("waitForMilliseconds", "Sleeping for " + time +" " +type, false);
			
			int milliseconds =Integer.valueOf(time) * (type.contains("milliseconds")?1:type.contains("seconds")?1000:60000);

			Thread.sleep(Integer.valueOf(milliseconds));
		} 
		catch (NumberFormatException nfe) 
		{
			printDebug("waitForMilliseconds", "You have entered an invalid input, can only be positive Integer. You have entered: " + time, false); 
			nfe = new NumberFormatException("You have entered an invalid input, can only be positive Integer. You have entered: " + time);
			throw nfe;
		} 
		catch (InterruptedException ie) 
		{
			printDebug("waitForMilliseconds", "Interrupted Exception thrown, sleep was interrupted, carrying on", false);			
		}		
	}
	
	

	@Then("^An error message (appears|does not appear) with text \"([^\"]*)\"$")
	public static void an_error_message_validation(String isAppear, String text) throws Exception {
		WebDriverSetupManager.waitForPageRender();

		WebElement items = null;
		if (WebDriverSetupManager.getDriver().getCurrentUrl().contains("look-gorgeous"))
			items = WebDriverSetupManager.findElement(StateMapHelper.getMapping("Look.Gorgeous.Error.Message.Label")+"[contains(text(),'"+StateMapHelper.getValueIfState(text)+"')]",isAppear.equalsIgnoreCase("appear"));
		else
			assertTrue("an_error_message_validation grammar not implemented for website", false);

		assertTrue("No error message displayed when expected", items != null);
		boolean errorContainedText = items!=null && items.isDisplayed();
		
		if (isAppear.equalsIgnoreCase("appears")) {

			assertTrue("No errors found with text:" + StateMapHelper.getValueIfState(text), errorContainedText);
			printDebug("an_error_message_validation",
					"Error message contained requested text: " + StateMapHelper.getValueIfState(text), false);
		} else {

			assertTrue("Error found with text:" + StateMapHelper.getValueIfState(text), !errorContainedText);
			printDebug("an_error_message_validation",
					"Error message didn't contained requested text: " + StateMapHelper.getValueIfState(text), false);

		}

	}
	
	@Then("^No error messages on page displayed$")
	public static void no_error_messages() throws Throwable {
		if (WebDriverSetupManager.getDriver().getCurrentUrl().contains("look-gorgeous"))
			i_check_element_visibility("Look.Gorgeous.Error.Message.Label","is not");
		else
			assertTrue("no_error_messages grammar not implemented for website", false);
		
		printDebug("no_error_messages","No error messages on page displayed", false);
	}
	
	
    @And("^I login with user \"([^\"]*)\" and password \"([^\"]*)\"$")
    public static void i_login_with_user_and_password(String userName, String password) throws Throwable { 
    	i_enter_a_text_into(userName, "Ellucian.LoginPage.Username.Input");
    	i_enter_a_text_into(password, "Ellucian.LoginPage.Password.Input");
    	i_click_element("Ellucian.LoginPage.SignIn.Button");
    	i_check_element_visibility("Ellucian.Elloyce.TopPanel.Logo.Img", "is");   

    }
    
    
    
	
}
