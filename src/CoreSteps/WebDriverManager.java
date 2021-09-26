package CoreSteps;

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

import CoreStepsSupport.CoreStepsHelper;
import CoreStepsSupport.StateMapHelper;

public class WebDriverManager {

	private static WebDriver webDriver =null;
	private static final String browser = BaseRunner.getProperty("setting.mainBrowser");
	private static final String browserDriverFolder ="/lib/drivers/"+BaseRunner.getProperty("setting."+browser+"Driver");
	private static final String webDriverPath = System.getProperty("user.dir")+browserDriverFolder;
	private static final int waitTime =120;
	private static final int shortWaitTime =5;
	private static int highlightColor =7;
	
	
	/**
	 * This method can be called to open requested URL. it would also load mappings
	 * @param url - web url (http(s)://...)
	 * @throws Exception
	 */
	public static void startBrowser(String url) throws Exception {



		CoreStepsHelper.printDebug("startBrowser", browser + " Browser: Attempt to navigate to: " + url, false);
		System.setProperty("webdriver.chrome.driver", webDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("allow-running-insecure-content");
		options.addArguments("--ignore-certificate-errors");

		webDriver = new ChromeDriver(options);
		webDriver.get(url);

		assertTrue("Please check chrome version or url.",
				getDriver().getCurrentUrl().toLowerCase().contains(url.toLowerCase().split("//")[1]));

		CoreStepsHelper.printDebug("startBrowser", "Successfully access web URL: " + url, false);

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
	 * @throws InterruptedException 
	 * @throws NumberFormatException 
	 */
	public static WebElement findAndHightlight(String elementDetails) throws NumberFormatException, InterruptedException {
		WebDriverManager.waitForPageRender();
		WebElement element = findElement(elementDetails,true);
		scrollAndHighlight(element,true);
		return element;
	}
	
/**
 * This method will wait for page rendering
 * @throws NumberFormatException
 * @throws InterruptedException
 */
	public static void waitForPageRender() throws NumberFormatException, InterruptedException {
		Thread.sleep(Long.parseLong(BaseRunner.getProperty("settting.minimumWaitTimeInMilliSeconds")));
		if(getDriver().getCurrentUrl().contains("look-gorgeous"))
			findElement("//*[@id=\"comp-ktmnuc3g\"]", true);
		
	}

	/**
	 * This method will do attempt to scroll and highlight element
	 * 
	 * @param element - Webelement of element to highlight
	 * @param scroll  - true or false for scroll
	 */
	public static void scrollAndHighlight(WebElement element, boolean scroll) {

		highlightColor = highlightColor % 7;
		final String[] rainbowCol = { "red", "orange", "yellow", "green", "blue", "indigo", "violet" };
		String nextColor = rainbowCol[highlightColor];
		String script = "arguments[0].style.cssText=arguments[0].style.cssText +' border : 3px solid " + nextColor
				+ " !important'";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		if (scroll)
			script = "arguments[0].scrollIntoView({block:'center'}); " + script;

		js.executeScript(script, element);

		highlightColor++;

	}

	/**
	 * use this method if expected to find only single element
	 * 
	 * @param elementDetails - xpath or xpath mapping
	 * @param waitTodisplay  - true or false ( if true max wait 120 seconds, if
	 *                       false 5 seconds)
	 * @return - webelement of item
	 */
	public static WebElement findElement(String elementDetails, boolean waitTodisplay) {
		List<WebElement> items = findElementsList(elementDetails, waitTodisplay);
		int size = items==null?0:items.size();
		assertTrue("Expected to find only 1 element for => " + elementDetails + " <= but found " + size,
				size == 1);
		return items.get(0);
	}
	
	/**
	 * Use this method if expected to find 0 or multiple items element
	 * 
	 * @param elementDetails - xpath or xpath mapping
	 * @param waitTodisplay  - true or false ( if true max wait 120 seconds, if
	 *                       false 5 seconds)
	 * @return - List<WebElement> - List of items
	 */
	public static List<WebElement> findElements(String elementsDetails, boolean waitTodisplay) {
		List<WebElement> items = findElementsList(elementsDetails, waitTodisplay);

		return items;
	}

	/**
	 * 
	 * @param elementDetails - can be xpath or mapping
	 * @param waitTodisplay  - true or false - to wait if no element found or not
	 * @return - WebElement if found otherwise null
	 * @author DCaru
	 */
	private static List<WebElement> findElementsList(String elementDetails, boolean waitTodisplay) {

		String elementToFind = StateMapHelper.getMapping(elementDetails);
		int time = shortWaitTime;
		if (waitTodisplay)
			time = waitTime;

		List<WebElement> items = null;

		try {
			items = new WebDriverWait(getDriver(), time)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(elementToFind)));

		} catch (Exception e) {

			CoreStepsHelper.printDebug("findElementsList", "Exception thwon so no visible web elements found", false);
		}

		return items;

	}
	
	/**
	 * This Method will close browser if was started
	 * @param close - if true will kill chrome and chromedriver, if false will delete webdriver
	 */
	public static void closeBrowser(boolean close) {
		if (webDriver != null && close) {
			getDriver().close();
			getDriver().quit();
			
		}
		webDriver = null;
	}
	


	public static boolean visibilityHighlight(String element, boolean visible ,boolean wait) {
		List<WebElement> elementToLook=findElementsList(element,wait);
		
		int size = elementToLook==null?0:elementToLook.size();
		if(visible) {
			assertTrue("Expected to find only 1 element for "+element +" and found :"+size,size==1);
			assertTrue("Element "+element+ " not displayed ase requested, please check", elementToLook.get(0).isDisplayed());
			scrollAndHighlight(elementToLook.get(0), true);
			return true;
		}else{
			
			assertTrue(size==0?"Element displayed when was expecting to be not visible":"More then 1 element present in the DOM please check xpaths. Current amount found: "+size,size==0||(size==1&&!elementToLook.get(0).isDisplayed()));
			
			return false;

		}
	}


}
