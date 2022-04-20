package CoreSteps;

import static CoreStepsSupport.CoreStepsHelper.printDebug;
import static org.junit.Assert.assertTrue;

import CoreStepsSupport.StateMapHelper;
import io.cucumber.java.en.And;

public class StateMap {

	

/**
 * With this method user is able to put text into state variable, or copy one state to another
 * @param action
 * @param text
 * @param state
 */
    @And("^I (put text|copy state) \"([^\"]*)\" into state \"([^\"]*)\"$")
    public void i_put_text_into_state(String action,String text, String state) {
    	
    	assertTrue("State value provided is "+state+" , but  should start from STATE. ",state.trim().toUpperCase().startsWith("STATE."));
    	String value =text;
    	if(action.equalsIgnoreCase("copy state")) {
    		assertTrue("Copy state should start from State. Currently value provided is "+text,text.trim().toUpperCase().startsWith("STATE."));
    		value=StateMapHelper.getValueIfState(text);
    	}
    	StateMapHelper.setState(state,value);
    
    	printDebug("i_put_text_into_state", "Text value: "+text+" been "+action+" to State variable named: "+state, false);
    	
    	

    }
    /**
     * This grammar will help user to read a text from web element into state variable
     * @param element - XPATH or mapping
     * @param state - State. variable to save
     * @throws Exception
     */
    @And("^I read element \"([^\"]*)\" text to state \"([^\"]*)\"$")
    public void i_read_elemnt_text_to_state(String element, String state)  throws Exception {
    	
    	String text = WebDriverSetupManager.findAndHightlight(element).getText();   	
    	i_put_text_into_state("put text", text, state);
   	
    	
    }
    
    
    
    
    
    

	
	
	
	


}
