package web.framework.CoreSteps;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.And;
import static web.framework.CoreSteps.Support.CoreStepsHelper.printDebug;
import web.framework.CoreSteps.Support.StateMapHelper;

public class StateMap {

	
	public static String getValueIfState(String keyForStateValue) {
		
		String value = keyForStateValue.toUpperCase().trim();
		if(value.startsWith("STATE."))
			return StateMapHelper.getStateMap(keyForStateValue);
		else if(value.startsWith("DEFAULT."))
			return StateMapHelper.getMapping(keyForStateValue);
		else
			return keyForStateValue;
					
	}

    @And("^I (put|copy) text \"([^\"]*)\" into state \"([^\"]*)\"$")
    public void i_put_text_into_state(String action,String text, String state) {
    	
    	assertTrue("State value should start from STATE.",state.trim().toUpperCase().startsWith("STATE."));
    	String value =text;
    	if(action.equalsIgnoreCase("copy"))
    		value=getValueIfState(text);
    	
    	setState(state,value);
    
    	printDebug("i_put_text_into_state", "Text value: "+text+" been "+action+" to State variable named: "+state, false);
    	
    	

    }
    
    @And("^I read element \"([^\"]*)\" text to state \"([^\"]*)\"$")
    public void i_read_elemnt_text_to_state(String element, String state)  {
    	
    	String text = WebDriverManager.findAndHightlight(element).getText();   	
    	i_put_text_into_state("put", text, state);
   	
    	
    }
	
	public static void setState(String keyForStateMap, String valueForStateMap) {	
		StateMapHelper.setStateMap(keyForStateMap, valueForStateMap);
		printDebug("setStateMap", keyForStateMap+" and "+valueForStateMap+ "been set in state table", false);
		
	}
	
	
	
	


}
