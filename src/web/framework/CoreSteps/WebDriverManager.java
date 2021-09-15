package web.framework.CoreSteps;

import static org.junit.Assert.assertTrue;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import web.framework.CoreSteps.Support.CoreStepsHelper;
import web.framework.CoreSteps.Support.StateMapHelper;

public class WebDriverManager {

	private static WebDriver webDriver =null;
	private static final String browser = BaseRunner.getProperty("setting.mainBrowser");
	private static final String browserDriverFolder ="/lib/drivers/"+BaseRunner.getProperty("setting."+browser+"Driver");
	private static final String webDriverPath = System.getProperty("user.dir")+browserDriverFolder;
	private static final int waitTime =120;
	private static final int shortWaitTime =5;
	private static int highlightColor =7;
	
	
	
	public static void startBrowser(String url) throws Exception {
		
		StateMapHelper.createMapppings();
	
		CoreStepsHelper.printDebug("startBrowser", browser+" Browser: Attempt to navigate to: "+url, false);
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
	
	/**
	 * This method will return web driver details if initiated
	 * @return - current instance of web driver
	 * @author DCaru
	 */
	public static WebDriver getDriver() {
		assertTrue("Use a grammar to navigate to URL first",webDriver!=null);

		return webDriver;
		
	}
	
	/**
	 * This method will return web element if user will pass web element xpath or mapping
	 * Also it will highlight it and navigate to it on the page
	 * @param elementDetails - can be xpath or mapping
	 * @return - WebElement with element details found
	 * @author DCaru
	 */
	public static WebElement findAndHightlight(String elementDetails) {
		WebElement element = findElement(elementDetails,true);
		scrollAndHighlight(element,true);
		return element;
	}
	


	public static void scrollAndHighlight(WebElement element, boolean scroll) {
		
		highlightColor = highlightColor % 7;
		final String[] rainbowCol = {"red","orange","yellow","green","blue","indigo","violet"};
		String nextColor =rainbowCol[highlightColor];
		String script = "arguments[0].style.cssText=arguments[0].style.cssText +' border : 3px solid "+nextColor+" !important'";
		JavascriptExecutor js =(JavascriptExecutor) getDriver();
		
		if (scroll)
			script = "arguments[0].scrollIntoView({block:'center'}); "+script;
		
		js.executeScript(script, element);
		
		highlightColor++ ;
		
		
	}

	public static WebElement findElement(String elementDetails, boolean waitTodisplay) {
		List<WebElement> items =findElementsList(elementDetails, waitTodisplay);
		assertTrue("Expected to find only 1 element for => "+elementDetails+" <= but found "+items.size(),items.size()==1);
		return items.get(0);
	}

	/**
	 * 
	 * @param elementDetails - can be xpath or mapping
	 * @param waitTodisplay - true or false - to wait if no element found or not
	 * @return - WebElement if found otherwise null
	 * @author DCaru
	 */
	private static List<WebElement> findElementsList(String elementDetails, boolean waitTodisplay) {
			
		String elementToFind =StateMapHelper.getMapping(elementDetails);
		int time=shortWaitTime;
		if(waitTodisplay)
			time=waitTime;
		
		List<WebElement> items= null;
			
		try {
			items = new WebDriverWait(getDriver(), time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(elementToFind)));
			
		} catch (Exception e) {
			
			CoreStepsHelper.printDebug("findElementsList", "Exception thwon so no visible web elements found", false);
		}

		return items;

	}

	public static void closeBrowser() {
		getDriver().close();
		getDriver().quit();
		webDriver = null;
		
		
	}



}
