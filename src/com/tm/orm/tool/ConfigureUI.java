package com.tm.orm.tool;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;

import com.tm.orm.session.*;
import com.tm.orm.util.*;
import com.tm.orm.exception.*;

public class ConfigureUI {

	private static JPanel mainPanel;

	private static ORMTool parent;

	//Panels
	private static JPanel formPanel;
	private static JPanel buttonPanel;
	private static JPanel errorPanel;

	private static TMLabel TITLE_LABEL = new TMLabel("Configuration");
	
	private static String[] labels = {"Database", "Server Address", "Port Number", "Username", "Password"};
	private static int numPairs = labels.length;
	private static JLabel error;


	//Text fields
	private static java.util.Map<String, JTextField> textFields;

	//Buttons
	private static JButton saveButton, clearButton, cancelButton;

	private ConfigureUI() {}
	
	public static void init(JPanel panel, ORMTool frame) {
		mainPanel = panel;
		parent = frame;
		setUp();
	}

	private static void setUp() {
		initComponents();
		styleComponents();
		addListeners();
		addComponents();
	}

	private static void initComponents() {

		//Panels
		formPanel = new JPanel();
		buttonPanel = new JPanel();
		errorPanel = new JPanel();

		//JTextFields
		textFields = new HashMap<>();

		//Buttons
		saveButton   = new JButton("Save");
		clearButton  = new JButton("Clear");
		cancelButton = new JButton("Cancel");

		//Label
		error = new JLabel("");

	}

	private static void addListeners() {

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				saveButtonClicked();
			}

		});
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				clearButtonClicked();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				cancelButtonClicked();
			}
		});
	}


	public static void addComponents() {


		//Adding Labels
		for(int i = 0; i < numPairs; ++i) {
			TMLabel label = new TMLabel(labels[i] + ": ", JLabel.TRAILING);
			formPanel.add(label);
			JTextField field = new JTextField(30);
			field.setBorder(new LineBorder(Color.BLACK, 2));
			textFields.put(labels[i], field);
			label.setLabelFor(field);
			formPanel.add(field);
		}

		SpringUtilities.makeCompactGrid(formPanel,
                                        numPairs, 2, //rows, cols
                                        50, 0,        //initX, initY
                                        20, 20);       //xPad, yPad
		

		//setBounds();

		//Adding Buttons
		buttonPanel.add(clearButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);


		addEmptyLines(2);
		mainPanel.add(TITLE_LABEL);
		addEmptyLines(2);
		mainPanel.add(formPanel);
		mainPanel.add(buttonPanel);
		addEmptyLines(5);
		mainPanel.add(error);

	}

	private static void addEmptyLines(int n) {
		for(int i = 0; i < n; ++i) mainPanel.add(new JPanel());
	}


	private static void styleComponents() {

		//Panels
		formPanel.setLayout(new SpringLayout());
		buttonPanel.setLayout(new FlowLayout());
		errorPanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		//TITLE_Lable styling
		TITLE_LABEL.setFont(new Font("Times Roman", Font.BOLD, 22));

		error.setPreferredSize(new Dimension(250, 50));
	}

	public static void setParent(ORMTool frame) {
		parent = frame;
	}

	public static ORMTool getParent() {
		return parent;
	}

	private static void clearTextFields() {
		for(JTextField field : textFields.values()) 
			field.setText("");
	}

	private static void saveButtonClicked() {

		String res = validateFieldValues();

		if(res.isEmpty()) {
			parent.saveButtonClickedEvent();
			error.setText("Status: Okay");
		}
		else {
			error.setText("Error: " + res);
		}
	}
	private static void cancelButtonClicked() {
		parent.cancelButtonClickedEvent();
	}
	private static void clearButtonClicked() {
		clearTextFields();
	}

	private static String validateFieldValues() {

		String errorMessage;

		JTextField field;
		String value;

		TMSession session = new TMSession();

		field = textFields.get("Database");
		value = field.getText().trim(); 
		if(value.isEmpty()) {
			errorMessage = "Database name required.";
			field.setBorder(new LineBorder(Color.RED, 2));
			return errorMessage;
		}
		else {
			session.setDatabase(value);
			field.setBorder(new LineBorder(Color.BLACK, 2));
		}

		field = textFields.get("Server Address");
		value = field.getText().trim();
		if(value.isEmpty()) {
			errorMessage = "Server Address required.";
			field.setBorder(new LineBorder(Color.RED, 2));
			return errorMessage;
		}
		else {
			session.setServerAddress(value);
			field.setBorder(new LineBorder(Color.BLACK, 2));
		}		
		
		field = textFields.get("Port Number");
		value = field.getText().trim(); 
		if(field.getText().trim().isEmpty()) {
			errorMessage = "Port number required.";
			field.setBorder(new LineBorder(Color.RED, 2));
			return errorMessage;
		}
		else {
			try {
				int portNumber = Integer.parseInt(value);
				if(portNumber < 0) throw new ORMException();
				session.setPortNumber(portNumber);
				field.setBorder(new LineBorder(Color.BLACK, 2));
			} catch(Exception exception) {
				errorMessage = "Invalid portNumber";
				field.setBorder(new LineBorder(Color.RED, 2));
				return errorMessage;
			}
		}	

		field = textFields.get("Username"); 
		value = field.getText().trim();
		if(field.getText().trim().isEmpty()) {
			errorMessage = "Username required.";
			field.setBorder(new LineBorder(Color.RED, 2));
			return errorMessage;
		}
		else {
			session.setUsername(value);
			field.setBorder(new LineBorder(Color.BLACK, 2));
		}	

		value = textFields.get("Password").getText().trim();
		session.setPassword(value);


		//As of now we know that all the fields has certain data except password

		try {
			Map<String, Table> tables = DBAnalyser.analyseDatabase(session);
			String path = System.getProperty("user.dir");
			path = path.substring(0, path.length() - 19) + "conf" + File.separator + "session.txt";
			session.save(path);
			parent.setInfoContent(tables);
		} catch(ORMException exception) {
			return exception.getMessage();
		}
		return "";	
	}

}