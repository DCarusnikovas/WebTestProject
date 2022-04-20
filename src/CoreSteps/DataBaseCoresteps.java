package CoreSteps;

import CoreStepsSupport.DBHelper;
import CoreStepsSupport.StateMapHelper;
import io.cucumber.java.en.And;
import static org.junit.Assert.assertTrue;
import static CoreStepsSupport.CoreStepsHelper.printDebug;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseCoresteps {

	
		
	
    @And("^I execute query \"([^\"]*)\" and save result to state \"([^\"]*)\"$")
    public void i_execute_query_and_save_result_to_state(String query, String stateVariable) throws Throwable {
    	
    	query=StateMapHelper.getValueIfState(query).trim();
    	assertTrue("Please check that STATE variable provided it should start with STATE. - currently provided: "+stateVariable,stateVariable.toUpperCase().startsWith("STATE."));
    	
    	Map<Integer, ArrayList<String>> rsMap=DBHelper.runSelectQuery(query,true,true);
    	
    	
    	StateMapHelper.setState(stateVariable, rsMap.get(1).get(0));
    	
        
    }
    
    
    @And("^I execute query \"([^\"]*)\" and check that query returns \"([^\"]*)\" rows$")
    public void i_execute_query_and_check_rows(String query, String stateVariable) throws Throwable {
    	
    	query=StateMapHelper.getValueIfState(query).trim();
    	stateVariable=StateMapHelper.getValueIfState(stateVariable).trim();
    	
    	int rowsExpected =Integer.parseInt(stateVariable);
    	
    	
    	int rowsReturned=DBHelper.runSelectQuery(query,false,false).size();
    	
    	assertTrue("Query returned "+rowsReturned+" rows and expected result of rows is "+rowsExpected,rowsExpected==rowsReturned);
    	
    	printDebug("i_execute_query_and_check_rows", "Returned correct ammount of rows: "+rowsExpected+" for query: "+query, false);
        
    }
    
    
    @And("^I execute query \"([^\"]*)\" and save rows amount$")
    public void i_execute_query_and_count_rows(String query) throws Throwable {
    	
    	query=StateMapHelper.getValueIfState(query).trim();
       	
    	int rowsReturned=DBHelper.runSelectQuery(query,false,false).size();

    	StateMapHelper.setStateMap("STATE.QueryRows", String.valueOf(rowsReturned));
    	printDebug("i_execute_query_and_count_rows", "Query returned ammount of rows: "+rowsReturned+" and saved to State.QueryRows", false);
        
    }
	
	
	
}
