package web.framework.CoreSteps.Support;
import java.io.PrintStream;

import web.framework.CoreSteps.DateCoreSteps;

public class CoreStepsHelper {
	
	
	
	public static void printDebug(String methodName, String textToPrint, boolean logInReport) {
		print(methodName,textToPrint,logInReport, false);
	}
	
	public static void printError(String methodName, String textToPrint, boolean printTraces) {
		if(printTraces) {
			textToPrint=textToPrint +"\nBEGIN STACK TRACE:\n";
			String stackTrace="";
			for(StackTraceElement trace: Thread.currentThread().getStackTrace()) 
				stackTrace+=trace.toString()+"\n";
			
			textToPrint = textToPrint +stackTrace +"END OF STACK TRACE: \n";
		}
		
		print(methodName,"ERROR: "+textToPrint,true, true);
	}

	private static void print(String methodName, String textToPrint, boolean logInReport, boolean isError) {
		
		if(!isError)
			printDebugHelper(methodName,textToPrint,System.out);
		else
			printDebugHelper(methodName,textToPrint,System.err);
		
		//TODO:Reports log	
	}

	private static void printDebugHelper(String methodName, String textToPrint, PrintStream streamType) {
		String timeDetails =DateCoreSteps.getCurrentDateTime("HH:mm:ss");
		streamType.println(String.format("[%-45s] - [%s] - %s",methodName, timeDetails, textToPrint));
		
	}

}
