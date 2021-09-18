package web.framework.CoreSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static web.framework.CoreSteps.Support.CoreStepsHelper.printDebug;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CoreStepsDefinition {
	
    @Given("^I execute step \"([^\"]*)\"$")
    public static void i_execute_step_text(String stepDetails) throws Throwable {
    	
    	printDebug("i_execute_step_something", stepDetails, false);
       
    }
    
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String url) throws Throwable {
       WebDriverManager.startBrowser(url);
    }
    
    @Given("^I navigate to (Google|LG)$")
    public void i_navigate_to_selection(String urlName) throws Throwable {
       WebDriverManager.startBrowser(urlName.equalsIgnoreCase("Google")?"https://www.google.com/":"https://silentium98.wixsite.com/look-gorgeous");
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
    
    
    @And("^I check that element \"([^\"]*)\" (is|is not) visible$")
    public void i_check_element_visibility(String element,String visibility) throws Throwable {
    	
    	boolean isVisble = WebDriverManager.visibilityHighlight(element, visibility.equals("is"),false);
    	printDebug("i_check_element_visibility", "The element -> "+element+" "+visibility+" visible as requested" , false);
    }
    
	/**
	 * This grammar can be used in feature file as a pause of script for required time in minutes. User will get pop up request with Yes and No options to continue or terminate a run  
	 * @author Denisas 05/03/2019
	 * @param minutes
	 * @throws Throwable
	 */
	@Then("^I put pause here for \"([^\"]*)\" minutes$")
	static public void i_put_pause_here(int minutes) throws Throwable {
		
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

	
	// Suspends the current thread for the specified number of milliseconds.
	@Then("^I wait \"([^\"]*)\" (milliseconds|seconds|minutes)$")
	static public void waitForMilliseconds(String time, String type) 
	{
		try 
		{	
			printDebug("CoreStepDefinitions.waitForMilliseconds", "Sleeping for " + time +" " +type, false);
			
			int milliseconds =Integer.valueOf(time) * (type.contains("milliseconds")?1:type.contains("seconds")?1000:60000);

			Thread.sleep(Integer.valueOf(milliseconds));
		} 
		catch (NumberFormatException nfe) 
		{
			printDebug("CoreStepDefinitions.waitForMilliseconds", "You have entered an invalid input, can only be positive Integer. You have entered: " + time, false); 
			nfe = new NumberFormatException("You have entered an invalid input, can only be positive Integer. You have entered: " + time);
			throw nfe;
		} 
		catch (InterruptedException ie) 
		{
			printDebug("CoreStepDefinitions.waitForMilliseconds", "Interrupted Exception thrown, sleep was interrupted, carrying on", false);			
		}		
	}
}
