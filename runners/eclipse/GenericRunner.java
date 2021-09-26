package eclipse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import CoreSteps.BaseRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)

@CucumberOptions(tags ="@TC00004", features="tests", glue ="CoreSteps", stepNotifications = true )

public class GenericRunner {
	
	private static CoreSteps.BaseRunner  runner = new BaseRunner();
	@BeforeClass
	public static void Setup() {
		
		runner.Setup();
		
	}
	
	@AfterClass
	public static void TearDown() {
		
		runner.TearDown();
		
	}

}
