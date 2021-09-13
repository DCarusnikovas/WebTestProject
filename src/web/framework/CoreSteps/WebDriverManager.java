package web.framework.CoreSteps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import web.framework.CoreSteps.Support.CoreStepsHelper;

public class WebDriverManager {

	private static WebDriver webDriver =null;
	private static final String webDriverPath = System.getProperty("user.dir")+BaseRunner.getParameter("/lib/drivers/chromedriver.exe");
	
	
	
	public static void startBrowser(String url) {
		CoreStepsHelper.printDebug("startBrowser", "Attempt to navigate to: "+url, false);
		System.setProperty("webdriver.chrome.driver", webDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("allow-running-insecure-content");
		options.addArguments("--ignore-certificate-errors");
		
		webDriver =new ChromeDriver(options);
		webDriver.get(url);
		
		assertTrue("Please check chrome version or url.", getDriver().getCurrentUrl().toLowerCase().contains(url.toLowerCase().split("//")[1]));
		
		CoreStepsHelper.printDebug("startBrowser", "Successfully access web URL: "+url, false);
		
	}
	public static WebDriver getDriver() {
		assertTrue("Use a grammar to navigate to URL first",webDriver!=null);

		return webDriver;
		
	}

}
