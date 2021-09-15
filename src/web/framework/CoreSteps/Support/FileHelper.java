package web.framework.CoreSteps.Support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class FileHelper {

	public static Map<String, String> getMappingsFromCSV() throws Exception {
		
		File folder =new File("mappings");
		String[] extensions = {"csv"};
		Collection<File> listOfFiles =FileUtils.listFiles(folder,extensions,true);
		Map<String, String> copyFromCsv =new HashMap<String, String>();

		for (File csvPath: listOfFiles) {
			System.out.println(">>>>>>>>>>>>> Reading from "+csvPath.getPath());
			
			try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
			    String line;
			    
			    while ((line = br.readLine()) != null) {
			       if(!line.trim().startsWith("#")&&!copyFromCsv.containsKey(line.trim().toLowerCase().split(",")[0]))
			    	   copyFromCsv.put(line.trim().toLowerCase().split(",")[0], line.substring(line.indexOf(",")+1).trim());
			       else
			    	   throw new Exception("Duplicated key found please check that only 1 unique key is used. The duplicate key is "+line.trim().split(",")[0]);
			    		   
			    }
			} 
		}
		
		return copyFromCsv;
	}

}
