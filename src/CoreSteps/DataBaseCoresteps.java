package CoreSteps;

import CoreStepsSupport.DBHelper;
import io.cucumber.java.en.And;

public class DataBaseCoresteps {

	
	
    @And("^I get connection to DB$")
    public void i_get_connection_to_db() throws Throwable {
        DBHelper.runDML();
    }
	
	
	
	
	
	
}
