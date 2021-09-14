package web.framework.CoreSteps;

import web.framework.CoreSteps.Support.CoreStepsHelper;
import web.framework.CoreSteps.Support.StateMapHelper;

public class StateMap {

	
	public static String getValueIfState(String keyForStateValue) {
		
		String value = keyForStateValue.toUpperCase().trim();
		if(value.startsWith("STATE."))
			return StateMapHelper.getStateMap(keyForStateValue);
		else if(value.startsWith("DEFAULT."))
			return StateMapHelper.getSettingMap(keyForStateValue);
		else
			return keyForStateValue;
					
	}

	public static void setState(String keyForStateMap, String valueForStateMap) {	
		StateMapHelper.setStateMap(keyForStateMap, valueForStateMap);
		CoreStepsHelper.printDebug("setStateMap", keyForStateMap+" and "+valueForStateMap+ "been set in state table", false);
		
	}
	
	
	
	


}
