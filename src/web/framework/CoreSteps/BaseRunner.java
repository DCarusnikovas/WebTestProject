package web.framework.CoreSteps;

public class BaseRunner {
	
	private String baseURL;
	
	
	public void Setup() {
		
		System.out.println("Preparing Runner");
		
		//Get required Properties from automationProperties file
		
		System.out.println("Complete Setup");
		
	}
	
	public void TearDown() {
		System.out.println("TearDown started");
		
		System.out.println("Complete TearDown");
	}
	
	
	public static String getParameter(String parameterName) {
		return parameterName;
		
	}
	

}
