package CoreStepsSupport;

import java.util.HashMap;
import java.util.Map;

import static CoreStepsSupport.CoreStepsHelper.printDebug;
import static org.junit.Assert.assertTrue;

public class StateMapHelper {
	

	private static final Map<String, String> stateVariableMap= new HashMap<String, String>();
	private static Map<String, String> mappingVariableMap= new HashMap<String, String>();
	


	
	
	
	/**
	 * This method will add or update existing state variables table with a key and value which can be used later 
	 * @param key - String - unique key to insure that not override existing data unless required and cannot be null or blank
	 * @param value - any String value
	 * @author DCaru
	 */
	public static void setStateMap(String key, String value) {
		key=key.trim().toLowerCase();
		assertTrue("Key should have atlleast 1 character", !key.isEmpty());
		if(stateVariableMap.containsKey(key))
			CoreStepsHelper.printDebug("setStateMap", "The map already has value set for key:"+key+" and value is set for '"+stateVariableMap.get(key)+ "' =>be carefull as it will override this value with new: "+value, false);
		
		stateVariableMap.put(key, value);	
	}
	
	/**
	 * This method will get a value for a key from state variables table
	 * @param key - key unique string and never null
	 * @return - String value from table
	 * @author DCaru
	 */
	public static String getStateMap(String key) {
		assertTrue("The provided key -> "+key+" <- don't exist in table",!key.trim().isEmpty()&&stateVariableMap.containsKey(key.trim().toLowerCase()));
		return stateVariableMap.get(key.trim().toLowerCase());
	}
	
	
	/**
	 * This method will return value or xpath for mapped key in mappings folder csv
	 * Note: if xpath will be sent as key it will return xpath
	 * @param key - key unique string and never null
	 * @return - String value or xpath
	 * @author DCaru
     */	
	public static String getMapping(String elementDetails) {
		
		elementDetails = elementDetails.trim();
				
		if(elementDetails.startsWith("/")||elementDetails.startsWith("(")||elementDetails.startsWith("."))
			return elementDetails;
		else {
			assertTrue("getMapping function can only accept xpaths or mapped values from csv located in mappings folder, please check value you sent => "+elementDetails,mappingVariableMap.containsKey(elementDetails.toLowerCase()));
			return mappingVariableMap.get(elementDetails.toLowerCase());
		}
		
		
	}
	
	/**
	 * This method should be used only once to initialize mapping from csv
	 * @throws Exception
	 */

	public static void createMapppings() throws Exception {
		if(mappingVariableMap.size()==0)
			mappingVariableMap=FileHelper.getMappingsFromCSV();		
	}
	
	public static int mappingVariableMapSize() {
		return mappingVariableMap.size();
	}
	
	
	/**
	 * This method will print to console all state variables using print debug
	 * @param delete - if true will delete table
	 */
	public static void printAllStateVariables(Boolean delete) {
		
		stateVariableMap.forEach((k,v)->{
			if(k.startsWith("state."))
				CoreStepsHelper.printDebug("printAllStateVariables", String.format("%-45s ===> %s", k,v), true);
		});
		if(delete) {
			stateVariableMap.clear();
			CoreStepsHelper.printDebug("printAllStateVariables",">>>>>>>>>>>>>>>> Deleted State variable table", false);
		}
		
	}
	
	
	public static String getValueIfState(String keyForStateValue) {
		
		String value = keyForStateValue.toUpperCase().trim();
		if(value.startsWith("STATE."))
			return StateMapHelper.getStateMap(keyForStateValue);
		else if(value.startsWith("DEFAULT."))
			return StateMapHelper.getMapping(keyForStateValue);
		else
			return keyForStateValue;
					
	}
	
	public static void setState(String keyForStateMap, String valueForStateMap) {	
		StateMapHelper.setStateMap(keyForStateMap, valueForStateMap);
		printDebug("setStateMap", keyForStateMap+" and "+valueForStateMap+ "been set in state table", false);
		
	}
	
	
	
	
	

}
