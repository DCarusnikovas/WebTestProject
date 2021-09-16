package web.framework.CoreSteps;

import io.cucumber.java.en.And;
import web.framework.CoreSteps.Support.DBHelper;

public class DataBaseCoresteps {

	
	
    @And("^I get connection to DB$")
    public void i_get_connection_to_db() throws Throwable {
        DBHelper.runDML();
    }
	
	
	
	
	
	
}
