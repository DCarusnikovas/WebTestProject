package eclipse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import web.framework.CoreSteps.BaseRunner;




@RunWith(Cucumber.class)

@CucumberOptions(tags ="@TC0001", features="tests", glue ="web.framework.CoreSteps", stepNotifications = true )

public class GenericRunner {
	
	private static web.framework.CoreSteps.BaseRunner  runner = new BaseRunner();
	@BeforeClass
	public static void Setup() {
		
		runner.Setup();
		
	}
	
	@AfterClass
	public static void TearDown() {
		
		runner.TearDown();
		
	}

}
