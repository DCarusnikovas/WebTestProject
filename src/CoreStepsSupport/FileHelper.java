package CoreStepsSupport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


public class FileHelper {

	/**
	 * Load mapping from Csv files located in mapping folder
	 * 
	 * @return - Map with key and value
	 * @throws Exception
	 */
	public static Map<String, String> getMappingsFromCSV() throws Exception {

		File folder = new File("mappings");
		String[] extensions = { "csv" };
		Collection<File> listOfFiles = FileUtils.listFiles(folder, extensions, true);
		Map<String, String> copyFromCsv = new HashMap<String, String>();

		for (File csvPath : listOfFiles) {
			System.out.println(">>>>>>>>>>>>> Reading from " + csvPath.getPath());

			try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
				String line;

				while ((line = br.readLine()) != null){
					boolean commentLine =line.trim().startsWith("#")||line.trim().startsWith("/")||line.trim().isBlank();
							
					if (!commentLine)
						if (!line.trim().startsWith("#")
								&& !copyFromCsv.containsKey(line.trim().toLowerCase().split(",")[0]))
							copyFromCsv.put(line.trim().toLowerCase().split(",")[0],
									line.substring(line.indexOf(",") + 1).trim());
						else
							throw new Exception(
									"Duplicated key found please check that only 1 unique key is used. The duplicate key is "
											+ line.trim().split(",")[0]);

				}
			}
		}

		return copyFromCsv;
	}

	/**
	 * This method load all Grammars steps to list from CoresSteps package
	 * 
	 * @return - return list
	 * @throws IOException
	 */
	public static List<String> getGrammarList() throws IOException {

		File folder = new File("src/CoreSteps");
		String[] extensions = { "java" };
		Collection<File> listOfFiles = FileUtils.listFiles(folder, extensions, true);

		List<String> copyFromJava = new ArrayList<String>();

		for (File javaFiles : listOfFiles) {

			System.out.println(">>>>>>>>>>>>> Reading from " + javaFiles.getPath());

			try (BufferedReader br = new BufferedReader(new FileReader(javaFiles))) {
				String line;

				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.trim().startsWith("@") && !(line.contains("<") && line.contains(">"))
							&& (line.startsWith("@Given") || line.startsWith("@Then")) || line.startsWith("@When")
							|| line.startsWith("@And")) {

						line = line.replaceFirst("@Then|@Given|@And|@When", "").trim()
								   .replaceAll("\\(\"|\"\\)", "")
								   .replaceAll("[@$^*]", "")
								   .replaceAll("\\\\\"\\([^a-zA-Z]+\\\\\"", "\"\"");

						copyFromJava.add(line);
					}

				}
			}
		}
		return copyFromJava;

	}

}
