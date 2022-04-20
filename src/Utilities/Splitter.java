package Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jayway.jsonpath.JsonPath;
import com.google.gson.Gson;

public class Splitter {
	public static void main(String[] args) {
		



		
	

	
	}
	
	
	public void getRestrictionsLists() {
		
		
		String first = "One";
		String Second = "Two";
		String third = "Third";
		
		//Array list
		List<String> included = new ArrayList<>();
		List<String> included2 = new ArrayList<>();
		
		
		included.add(Second);
		included.add(third);
		included.add(first);
		
		
		included2.add(first);
		included2.add(first);
		included2.add(first);
			
		//map
		//hashmap
		Map<Integer,String> mapHash = new HashMap<>();
		//linked
		Map<Integer,String> mapLinked = new LinkedHashMap<>();
		
		
		mapHash.put(2, Second);
		mapHash.put(1, first);
		mapHash.put(3, third);
		mapHash.remove(2);
		mapHash.put(2, Second);
		
		mapHash.forEach((key,value) -> System.out.println("Key "+key+ " Value "+value));
		
	
		System.out.println("Linked map");
		mapLinked.put(1, first);
		mapLinked.put(2, Second);
		
		mapLinked.put(3, third);
		mapLinked.remove(2);
		mapLinked.put(2, Second);
		
		mapLinked.forEach((key,value) -> System.out.println("Key "+key+ " Value "+value));
		
		System.out.println("Array list");
		
		List<List<String>> arrayInArray = new ArrayList<>();
		arrayInArray.add(included2);
		arrayInArray.add(included);
		System.out.println("Array list print per array");
		arrayInArray.forEach(System.out::println);
		
		System.out.println("Array list . prin list per list");
		
		arrayInArray.forEach(list -> list.forEach(value ->{
			
//		if(value.equals("One"))
//			System.out.println(value);
//		}
//		
			String values = value.equals("One") ? value : "";
			String why = value.equals("One") ? "true" : "false";
			
			//System.out.println(value.equals("One") ? value : "");
		}			
				));
		
		
		
		
		
		
//		String apiDATA = "{\"restrictionsDep\": {\"deptIncluded\": \"E\",\"departments\": [{\"deptDescription\":\"Jousting Department\",\"deptCode\":\" JOU\"},{\"deptDescription\":\"Humanities\",\"deptCode\":\"2420\"}],\"fieldOfStudyIncluded\": \"I\",\"fieldsOfStudy\": [{\"majorDescription\": \"Accounting Major\",\"majorCode\": \"103\"},{\"majorDescription\": \"Statistics major\",\"majorCode\": \"107\"}]},\"restrictionsClass\": {\"classIncluded\": \"E\",\"classes\": [{\"classDescription\": \"First Year\",\"classCode\": \"01\"},{\"classDescription\": \"Second Year\",\"classCode\": \"02\"}],\"levelIncluded\": \"I\",\"levels\": [{\"levelDescription\": \"Anish Level1\",\"levelCode\": \"AN\"},{\"levelDescription\": \"Associates 2 year\",\"levelCode\": \"AS\"}]},\"restrictionsDeg\": {\"degIncluded\": \"E\",\"degrees\": [{\"degreeDescription\": \"BA in CE\",\"degreeCode\": \"ABCE\"},{\"degreeDescription\": \"Bachelor of High Business\",\"degreeCode\": \"BHB\"},{\"degreeDescription\": \"Doctor of Philosophy\",\"degreeCode\": \"PHD\"}],\"prgIncluded\": \"I\",\"programs\": [{\"programDescription\": \"Finaid AS-UG1\",\"programCode\": \"AS-UG1\"},{\"programDescription\": \"Undergraduate Athletic program\",\"programCode\": \"ATHLETIC\"}]},\"restrictionsCampColg\": {\"campIncluded\": \"E\",\"campuses\": [{\"campDescription\": \"Jousting Campus-123\",\"campCode\": \" JO\"},{\"campDescription\": \"Study Abroad\",\"campCode\": \"5\"}],\"colgIncluded\": \"I\",\"colleges\": [{\"collDescription\": \"University Toronto\",\"collCode\": \"02\"},{\"collDescription\": \"Campion College1\",\"collCode\": \"05\"}]},\"restrictionsAttrCohort\": {\"attsIncluded\": \"E\",\"attributes\": [{\"attsCode\": \"AB\",\"attsDesc\": \"Australian ATSI\"},{\"attsCode\": \"AUST\",\"attsDesc\": \"Australian - WL testing\"}],\"cohortIncluded\": \"I\",\"cohorts\": [{\"chrtCode\": \" JOUSTING\",\"chrtDesc\": \"Jousting Cohort\"},{\"chrtCode\": \"CCPTPTUG\",\"chrtDesc\": \"IPEDS part time cohort\"}]} }";
//		
//		
//	    List<String> included = new ArrayList<>();
//		List<String> excluded = new ArrayList<>();				
//		
//		Map<Integer, List<String>> map = new LinkedHashMap<>();
//		map.put(1, getRestrictionDetailsFromJsonString("deptIncluded","deptDescription","deptCode", apiDATA));
//		map.put(2, getRestrictionDetailsFromJsonString("fieldOfStudyIncluded","majorDescription","majorCode", apiDATA));
//		map.put(3, getRestrictionDetailsFromJsonString("classIncluded","classDescription","classCode", apiDATA));
//		map.put(4, getRestrictionDetailsFromJsonString("levelIncluded","levelDescription","levelCode", apiDATA));
//		map.put(5, getRestrictionDetailsFromJsonString("degIncluded","degreeDescription","degreeCode", apiDATA));
//		map.put(6, getRestrictionDetailsFromJsonString("prgIncluded","programDescription","programCode", apiDATA));
//		map.put(7, getRestrictionDetailsFromJsonString("campIncluded","campDescription","campCode", apiDATA));
//		map.put(8, getRestrictionDetailsFromJsonString("colgIncluded","collDescription","collCode", apiDATA));
//		map.put(9, getRestrictionDetailsFromJsonString("attsIncluded","attsDesc","attsCode", apiDATA));
//		map.put(10, getRestrictionDetailsFromJsonString("cohortIncluded","chrtDesc","chrtCode", apiDATA));
//			        
//        
//        map.forEach((key,list)->{
//        	for(int x = 1; x < list.size(); x++)
//        		if(list.get(0).equals("E"))
//        			excluded.add(list.get(x));
//        		else if (list.get(0).equals("I")) 
//        			included.add(list.get(x));
////        		else
////        			throw new Exception("Found unexpected value for restriction to be included or not. Value found: "+list.get(0));
//        });
//        							
//        System.out.println("Included:");			
//		included.forEach(System.out::println);
//		System.out.println("Excluded:");	
//		excluded.forEach(System.out::println);
//		
//		Map<String, List<String>> restrictionsMap = new LinkedHashMap<>();
//		restrictionsMap.put("Included",included);
//		restrictionsMap.put("Excluded",excluded);
		
		
	}


	/**
	 * This method will get a list of restrictions from JSON file
	 * 
	 * @author Denisas Carusnikovas
	 * @param included - node name for included or excluded value
	 * @param description - node name for description of restriction 
	 * @param code - node name for code of restriction
	 * @param apiDATA - JSON body as string
	 * @return - List<String>  as {included},{description1 (code1)},{description2 (code2)}, etc
	 */
	private static List<String> getRestrictionDetailsFromJsonString(String included, String description, String code, String apiDATA) {
		
		List<String> listOfNodesToReturn = new ArrayList<>();
		listOfNodesToReturn.add(included);
		listOfNodesToReturn.add(description);
		listOfNodesToReturn.add(code);
		
		List<ArrayList<String>> collectedValues = getNodeValuesFromJsonString(listOfNodesToReturn,apiDATA) ;
		
		List<String> buildList = new ArrayList<>();
		//included or excluded
		buildList.add(collectedValues.get(0).get(0));
		//Course name with code
		for(int x = 0; x < collectedValues.get(1).size(); x++)
			buildList.add(collectedValues.get(1).get(x)+" ("+collectedValues.get(2).get(x)+")");	
		return buildList;
	}
	
	/**
	 * This method will return node values in set of arrays from JSON string
	 * 
	 * Example:
	 * if user will pass a JSON body and will request to read nodes for multiple users in example: {names, phones} 
	 * it will return: List of array {Names{},Phones{}} in same order as in JSON body. Example of returned arrays:  ["Peter","Mark","Tony"],["0851231231",08512345678","0871231234"]
	 * So when user want to print Mark name and phone he will request print("Name" + list.get(0).get(1) + " Phone "+ list.get(1).get(1))
	 * 
	 * @author Denisas Carusnikovas
	 * @param valuesToReturn - List<String> - list of node names or jsonPaths for node 
	 * @param apiDATA - JSON body as string
	 * @return - List<ArrayList<String>>  -  grouped values for each node from list see example
	 */
	private static List<ArrayList<String>> getNodeValuesFromJsonString(List<String> valuesToReturn, String apiDATA){
		
		List<ArrayList<String>> collectedValues = new ArrayList<>();
		//loop in valuesToReturn and find
		valuesToReturn.forEach(jsonPathOrNodeName -> collectedValues.add(JsonPath.parse(apiDATA).read(jsonPathOrNodeName.startsWith("$") ? jsonPathOrNodeName : String.format("$..%s", jsonPathOrNodeName))));
			
		return collectedValues;
		
	}

}
