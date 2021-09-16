package Utilities;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import web.framework.CoreSteps.Support.FileHelper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ItemEvent;

public class FeatureBuilder {

	private JFrame frame;
	private JLabel lblStepLabel;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBoxExamples;
	private JComboBox<String> comboBoxSteps;
	private static List<String> testSteps = new ArrayList<>();
	private static List<String> preSteps;
	private JButton btnNewLine;
	private JButton btnClearSearch;
	private String typedText;
	private int lenght = 100;
	private static Font monoFont = new Font("Courier New", Font.PLAIN, 14);
	private static final File starting = new File(System.getProperty("user.dir"));
	private static final File folderForExamples = new File(starting, "/Datasources/FeatureExamples/");
	private String appVersion = "v1.4";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("ComboBox.font", monoFont);
					FeatureBuilder window = new FeatureBuilder();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public FeatureBuilder() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1900, 1050);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setTitle("Feature Builder " + appVersion);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// filtered combobox to find step definitions
		comboBoxSteps = new FilterComboBox(populateStepsDefinitionArray());
		comboBoxSteps.setSelectedItem(testSteps.get(0));
		comboBoxSteps.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!comboBoxSteps.getSelectedItem().toString().isEmpty())
					lblStepLabel.setText(comboBox.getSelectedItem().toString() + " "
							+ comboBoxSteps.getSelectedItem().toString().split("\\[")[0].trim());

			}
		});
		comboBoxSteps.setEditable(true);
		comboBoxSteps.setMaximumRowCount(20);
		comboBoxSteps.setBounds(210, 100, 1570, 25);
		frame.getContentPane().add(comboBoxSteps);

		// Label to display final selection
		lblStepLabel = new JLabel("<Please select or enter text in to dropdown box>");
		lblStepLabel.setBounds(205, 165, 1200, 25);
		frame.getContentPane().add(lblStepLabel);

		
		// Label to display final selection
		JLabel lblManualStepsLabel = new JLabel("Build Feature file manually:");
		lblManualStepsLabel.setBounds(50, 75, 200, 25);
		frame.getContentPane().add(lblManualStepsLabel);

		// Editor box to add, copy or amend steps
		JEditorPane editorStepPane = new JEditorPane();
		JScrollPane spEditor = new JScrollPane(editorStepPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spEditor.setBounds(50, 240, 1750, 700);
		editorStepPane.setFont(monoFont);
		frame.getContentPane().add(spEditor);

		// Combo box to select Step starting Prefix
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!comboBox.getSelectedItem().toString().isEmpty())
					lblStepLabel.setText(comboBox.getSelectedItem().toString() + " "
							+ comboBoxSteps.getSelectedItem().toString().split("\\[")[0].trim());
				else
					lblStepLabel.setText(comboBox.getSelectedItem().toString() + " " + typedText.trim());
			}
		});
		comboBox.setBounds(50, 100, 140, 25);
		comboBox.setMaximumRowCount(20);
		comboBox.setModel(new DefaultComboBoxModel(populateStepsDefinitionStartArray().toArray()));
		frame.getContentPane().add(comboBox);

		// Add step button
		JButton btnNewButton = new JButton("Add Step");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (editorStepPane.getText().isEmpty())
					editorStepPane.setText(checkFormat(comboBox.getSelectedItem().toString(),
							comboBoxSteps.getSelectedItem().toString().split("\\[Used")[0].trim()));
				else
					editorStepPane.setText(
							editorStepPane.getText() + "\n" + checkFormat(comboBox.getSelectedItem().toString(),
									comboBoxSteps.getSelectedItem().toString().split("\\[")[0].trim()));

				comboBoxSteps.setSelectedItem(testSteps.get(0));
				lblStepLabel.setText("<Please select or enter text in to dropdown box>");
			}
		});

		btnNewButton.setBounds(50, 160, 140, 25);
		frame.getContentPane().add(btnNewButton);

		// Add empty line button
		btnNewLine = new JButton("Add Empty Line");
		btnNewLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorStepPane.setText(editorStepPane.getText() + "\n");
			}
		});
		btnNewLine.setBounds(50,200, 140, 25);
		frame.getContentPane().add(btnNewLine);

		// Clear search button
		btnClearSearch = new JButton("Clear Search");
		btnClearSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSteps.setSelectedItem(testSteps.get(0));
				lblStepLabel.setText(
						comboBox.getSelectedItem().toString() + " <Please select or enter text in to dropdown box>");
			}
		});
			
		btnClearSearch.setBounds(1625,135, 140, 25);
		frame.getContentPane().add(btnClearSearch);
		
		// Combobox to select Step starting Prefix
		// Label to display final selection
		JLabel lblExamplesLabel = new JLabel("Ready Examples:");
		lblExamplesLabel.setBounds(50, 10, 100, 25);
		frame.getContentPane().add(lblExamplesLabel);

		comboBoxExamples = new JComboBox<String>();
		comboBoxExamples.setBounds(50,30, 500, 25);
		comboBoxExamples.setMaximumRowCount(20);
		comboBoxExamples.setModel(new DefaultComboBoxModel(populateExamplesArray().toArray()));
		frame.getContentPane().add(comboBoxExamples);

		// Add Example button
		JButton btnAddExampleButton = new JButton("Add Example");
		btnAddExampleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String filePath = folderForExamples + "\\" + comboBoxExamples.getSelectedItem();
				String fileText = null;
				try {
					fileText = new String(Files.readAllBytes(Paths.get(filePath)));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if (!editorStepPane.getText().isEmpty()) {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Text Editor will be cleared, before adding new example. Do you want to proceed?", "Notice", dialogButton);

					if (dialogResult == JOptionPane.YES_OPTION)
						editorStepPane.setText(fileText);
				} else
					editorStepPane.setText(fileText);

			}
		});

		btnAddExampleButton.setBounds(570,30, 140, 25);
		frame.getContentPane().add(btnAddExampleButton);
				
		// Add Save button
		JButton btnSaveAsButton = new JButton("Save As...");
		btnSaveAsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editorStepPane.getText().trim().isEmpty())
					JOptionPane.showMessageDialog(frame, "Please add some text to Editor before save!","Error!", JOptionPane.INFORMATION_MESSAGE);

			   else {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").replace("CukeCore", "")));
					BufferedWriter writer = null;
					int option = fileChooser.showSaveDialog(frame);
					if (option == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							writer = new BufferedWriter(new FileWriter(
									file.getAbsolutePath() + (file.getAbsolutePath().contains(".") ? "" : ".feature")));
							writer.write(editorStepPane.getText());
							writer.close();
							
							JOptionPane.showMessageDialog(frame, "File saved at " + file.getAbsolutePath() + (file.getAbsolutePath().contains(".") ? "" : ".feature"),"Saved", JOptionPane.INFORMATION_MESSAGE);
		
						} catch (IOException fileSave) {
							JOptionPane.showMessageDialog(frame, "File not saved at " + file.getAbsolutePath() + (file.getAbsolutePath().contains(".") ? "" : ".feature") + "please check path or if you have permission to write file in that location!","Error!", JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}
			}
		});
		

		btnSaveAsButton.setBounds(1650,950, 140, 25);
		frame.getContentPane().add(btnSaveAsButton);
		
		
		// Add Clear text button
		JButton btnClearTextButton = new JButton("Clear Text");
		btnClearTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorStepPane.setText("");			
			}
		});
		btnClearTextButton.setBounds(1450,950, 140, 25);
		frame.getContentPane().add(btnClearTextButton);
				
	}

	/**
	 * Populate step definitions array
	 * 
	 * @return
	 * @throws IOException
	 */
	private List<String> populateStepsDefinitionArray() throws IOException {

		List<String> testStepsTemp = FileHelper.getGrammarList();
		

		// add time of use to grammar to be displayed in filter box
		for (String step : testStepsTemp)
			testSteps.add(step.contains(",") ? step.split(",")[0]
					+ new String(new char[(lenght - step.split(",")[0].trim().length())]).replace('\0', ' ') + "[Used: "
					+ step.split(",")[1] + " times]" : step);
		
		Collections.sort(testStepsTemp);
		return testSteps;
	}



	/**
	 * Populate prefix for step definitions
	 * 
	 * @return
	 */
	private static List<String> populateStepsDefinitionStartArray() {
		preSteps = new ArrayList<String>();
		preSteps.add("Given");
		preSteps.add("When");
		preSteps.add("And");
		preSteps.add("Then");
		preSteps.add("@");
		preSteps.add("#");
		preSteps.add("Feature:");
		preSteps.add("Scenario:");
		preSteps.add("Scenarios Outline:");
		preSteps.add("Examples:");
		preSteps.add("||");
		preSteps.add("|||");
		return preSteps;
	}

	/**
	 * Populate Examples List from folder
	 * 
	 * @return
	 */
	private static List<String> populateExamplesArray() {
		preSteps = new ArrayList<String>();
		preSteps.add("");

		for (String name : folderForExamples.list())
			preSteps.add(name);

		return preSteps;
	}

	/**
	 * This grammar will check format of text to be displayed at editor
	 * 
	 * @author Carusnid
	 * @param preStep
	 * @param stepText
	 * @return
	 */
	private String checkFormat(String preStep, String stepText) {

		String preStepUpdate = null;
		switch (preStep) {
		case "Feature:":
			preStepUpdate = preStep + " " + stepText;
			break;

		case "#":
			preStepUpdate = preStep + stepText;
			break;

		case "@":
			preStepUpdate = preStep + stepText;
			break;

		case "Scenario:":
			preStepUpdate = preStep + " " + stepText;
			break;

		case "Scenarios Outline:":
			preStepUpdate = preStep = " " + stepText;
			break;

		case "Examples:":
			preStepUpdate = preStep = " " + stepText;
			break;

		case "Given":
			preStepUpdate = "  " + preStep + " " + stepText;
			break;
		
		case "When":
			preStepUpdate = "   " + preStep + " " + stepText;
			break;

		case "Then":
			preStepUpdate = "   " + preStep + " " + stepText;
			break;

		case "And":
			preStepUpdate = "    " + preStep + " " + stepText;
			break;

		case "||":
			preStepUpdate = "                " + "|          |";
			break;
		case "|||":
			preStepUpdate = "                " + "|          |          |";
			break;

		default:
			preStepUpdate = " " + preStep + " ";
			break;
		}

		return preStepUpdate;

	}

	/**
	 * FilterComboBox which is extending combobox to add search box functionality
	 * 
	 * @author Carusnid
	 *
	 */
	@SuppressWarnings("rawtypes")
	public class FilterComboBox extends JComboBox {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<String> array;
		private int currentCaretPosition = 0;

		/**
		 * Load arrays to filter combobox
		 * 
		 * @author Carusnid
		 * @param array
		 */
		@SuppressWarnings("unchecked")
		private FilterComboBox(List<String> array) {
			super(array.toArray());
			this.array = array;
			this.setEditable(true);
			final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
			textfield.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							currentCaretPosition = textfield.getCaretPosition();
							if (textfield.getSelectedText() == null) {
								textfield.setCaretPosition(0);
								typedText = textfield.getText();
								lblStepLabel.setText(comboBox.getSelectedItem().toString() + " " + typedText.trim());
								comboFilter(textfield.getText());
								textfield.setCaretPosition(currentCaretPosition);
							}
						}
					});
				}

			});

		}

		/**
		 * This step is required to create a dynamic filter
		 * 
		 * @author Carusnid
		 * @param enteredText
		 */
		@SuppressWarnings("unchecked")
		private void comboFilter(String enteredText) {
			List<String> filterArray = new ArrayList<String>();
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
					filterArray.add(array.get(i));
				}

			}
			if (filterArray.size() > 0) {
				this.setModel(new DefaultComboBoxModel(filterArray.toArray()));
				this.setSelectedItem(enteredText);
				this.showPopup();
			} else {
				this.hidePopup();
			}
		}

	}





}
