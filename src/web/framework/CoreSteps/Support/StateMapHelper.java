package web.framework.CoreSteps.Support;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StateMapHelper {
	

	private final static Map<String, String> stateVariableMap= new HashMap<String, String>();
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
	 * This method will get a value for a key from state variables table
	 * @param key - key unique string and never null
	 * @return - String value from table
	 * @author DCaru
	 */
	public static String getSettingMap(String key) {
		assertTrue("Key should have atlleast 1 character", !key.trim().isEmpty());
		assertTrue("The provided key -> "+key+" <- don't exist in settingsVariableMap table",mappingVariableMap.containsKey(key.trim().toLowerCase()));
		return mappingVariableMap.get(key.trim().toLowerCase());
	}
	
	
	/**
	 * This method will add or update existing settings variables table with a key and value which can be used later 
	 * @param key - String - unique key to insure that not override existing data unless required and cannot be null or blank
	 * @param value - any String value
	 * @author DCaru
	 */
	private static void setSettingsMap(String key, String value) {
		key=key.trim().toLowerCase();
		assertTrue("Key should have atlleast 1 character", !key.isEmpty());
		if(mappingVariableMap.containsKey(key))
			CoreStepsHelper.printDebug("setSettingsMap", "The map already has value set for key:"+key+" and value is set for '"+mappingVariableMap.get(key)+ "' =>be carefull as it will override this value with new: "+value, false);
		
		mappingVariableMap.put(key, value);
		
		CoreStepsHelper.printDebug("setSettingsMap", key+" and "+value+ "been set in settingsVariableMap table", false);
	}

	
	//TODO: need to create loader
	public static String getMapping(String elementDetails) {
		// TODO Auto-generated method stub
		return elementDetails;
	}

}
