package CoreStepsSupport;

import static org.junit.Assert.assertTrue;

import java.io.PrintStream;

import CoreSteps.DateCoreSteps;
import CoreSteps.WebDriverSetupManager;

public class CoreStepsHelper {

	/**
	 * This method will print Debug message to console and if needed to report too
	 * 
	 * @param methodName  - method name as String
	 * @param textToPrint - text message for debug details
	 * @param logInReport - if true will print on report
	 */
	public static void printDebug(String methodName, String textToPrint, boolean logInReport) {
		print(methodName, textToPrint, logInReport, false);
	}

	/**
	 * This method will log error in console and report with option to print stack
	 * traces or not
	 * 
	 * @param methodName  - method name as String
	 * @param textToPrint - text of error
	 * @param printTraces - true if trace needed
	 */
	public static void printError(String methodName, String textToPrint, boolean printTraces) {
		if (printTraces) {
			textToPrint = textToPrint + "\nBEGIN STACK TRACE:\n";
			String stackTrace = "";
			for (StackTraceElement trace : Thread.currentThread().getStackTrace())
				stackTrace += trace.toString() + "\n";

			textToPrint = textToPrint + stackTrace + "END OF STACK TRACE: \n";
		}

		print(methodName, "ERROR: " + textToPrint, true, true);
	}

	/**
	 * This method to support printDebug and PrintError Methods to be displayed on
	 * console and report
	 * 
	 * @param methodName  - method name to print
	 * @param textToPrint - text to print
	 * @param logInReport - log in report
	 * @param isError     - true if need to be displayed as error, false if as
	 *                    normal text
	 */
	private static void print(String methodName, String textToPrint, boolean logInReport, boolean isError) {

		if (!isError)
			printDebugHelper(methodName, textToPrint, System.out);
		else
			printDebugHelper(methodName, textToPrint, System.err);

		// TODO:Reports log
	}

	/**
	 * This method will support print() method to print reports in console
	 * 
	 * @param methodName  - method name as string
	 * @param textToPrint - String text to print
	 * @param streamType  - PrintStream type => normally System.out or System.err
	 */
	private static void printDebugHelper(String methodName, String textToPrint, PrintStream streamType) {
		String timeDetails = DateCoreSteps.getCurrentDateTime("HH:mm:ss");
		streamType.println(String.format("[%-45s] - [%s] - %s", methodName, timeDetails, textToPrint));

	}

	/**
	 * This method can check if Web element text match provided text
	 * 
	 * @param element - XPATH or mapping
	 * @param text    - String value or State variable
	 * @param exact   - if true - should 100% match if false web element text contains in value provided
	 * @matchOrNotMatch - true if match false if not match
	 */
	public static void elementHasText(String element, String text, boolean exact, boolean matchOrNotMatch) throws Exception {
		String textFromElement = WebDriverSetupManager.findAndHightlight(element).getText();
		assertTrue("The text from element: " +element +(!matchOrNotMatch?" contained text which was not expected.":
			(exact?" didn't match text provided.":" didn't contain expected text."))
				+" Text from element: "+textFromElement + " -> User text: "+text +"",twoStringsMatch(textFromElement, textFromElement, exact)==matchOrNotMatch);

	}

	/**
	 * This grammar can check 2 string values that they match or value 1 contains
	 * value 2
	 * 
	 * @param value1 - string value which will be checked
	 * @param Value2 - string value which should present in value 1
	 * @param exact  - if true - should 100% match if false v2 contains in v1
	 * @return - true if matched, false if fail
	 */

	public static Boolean twoStringsMatch(String value1, String Value2, boolean exact) {

		if (exact)
			return value1.equals(Value2);
		else
			return value1.trim().contains(Value2);

	}

}
