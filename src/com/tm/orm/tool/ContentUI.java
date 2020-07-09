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

public class ContentUI {

	private static ORMTool parent;
	private static JPanel  infoPanel;

	private static java.util.Map<String, Table> content;

	private ContentUI() {}

	public static void init(JPanel panel, ORMTool frame) {
		infoPanel = panel;
		parent 	  = frame;
	}

	public static void setParent(ORMTool frame) {
		parent = frame;
	}

	public static ORMTool getParent() {
		return parent;
	}

	public static void setContent(java.util.Map<String, Table> map) {
		content = map;
	}

	public static java.util.Map<String, Table> getContent() {
		return content;
	}

	public static void setUp() {
		if(content == null) {
			infoPanel.add(new JLabel("Error occurred."));
			return;
		}
		if(content.isEmpty()) {
			infoPanel.add(new JLabel("Seems like database has no tables. Please add tables to the database."));
			return;
		}

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(950, 400));
		ImageIcon icon = new ImageIcon("./resources/icons/tableIcon.png");
		String tabTip = "t";
		int i = 1;	
		for(Map.Entry<String, Table> entry : content.entrySet()) {
			String tableName = entry.getKey();
			Table table = entry.getValue();
			JPanel panel =  makeTextPanel(table);
			tabbedPane.addTab(tableName, icon, panel, tableName);
			++i;
		}

		infoPanel.add(tabbedPane);
		infoPanel.setLayout(new FlowLayout());
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	private static JPanel makeTextPanel(Table table) {
		JPanel panel = new JPanel(false);
		int rowCount = table.getAttributes().size();
		int colCount = 2;
		String[][] data = new String[rowCount + 1][colCount];

		String[] columns = {"Name(Database)", "Name(Java Code)"};

		data[0][0] = table.getName();
		data[0][1] = getClassName(table.getName());

		int i = 1;
		Map<Attribute, String> columnToFieldMap = new HashMap<>();
		for(Attribute attribute: table.getAttributes().values()) {
        	data[i][0] = attribute.getName();
        	data[i][1] = getFieldName(attribute.getName());
        	columnToFieldMap.put(attribute, data[i][1]);
        	++i;
    	}

    	JTable jtable = new JTable(data, columns);
    	JScrollPane pane = new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	panel.add(pane);
        panel.setLayout(new GridLayout(1, 1));

        JPanel bottomPanel = new JPanel();
        JButton button = new JButton("Generate Code");
        bottomPanel.add(button);

        JPanel mainPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel("");
        statusPanel.add(statusLabel);
        mainPanel.add(panel);
        mainPanel.add(bottomPanel);
        mainPanel.add(statusPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {

        		JFileChooser chooser = new JFileChooser();
        		chooser.setCurrentDirectory(new java.io.File("."));
        		chooser.setDialogTitle("Path to save code");
        		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        		chooser.setAcceptAllFileFilterUsed(false);
        		if (chooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) { 
      				String path = chooser.getSelectedFile().toString();
      				CodeGenerator.init(data[0][0], data[0][1], columnToFieldMap, path);
      				CodeGenerator.generate();
      				if(statusLabel.getText().trim().isEmpty())
      					statusLabel.setText("Java file has been witten to path: " + path);
      				else
      					statusLabel.setText(statusLabel.getText() + ", and " + path);
      			}
        	}
        });

        return mainPanel;
	}

	private static String getFieldName(String name) {
			name = name.trim();
			if(name.isEmpty()) return name;

			String refName = "";
			int i = 0;
			int len = name.length();
			while(i < len) {
				char ch = name.charAt(i);
				if(ch == '_') {
					while(name.charAt(i) == '_' && i < len) ++i;
					// i = len or i == just after _
					if(i == len) break;
					ch = Character.toUpperCase(name.charAt(i));
					refName += ch;

				}
				else refName += ch;
				++i;
			}

			return refName;

	}

	private static String getClassName(String name) {
			name = name.trim();
			if(name.isEmpty()) return name;

			String refName = "";
			int i = 0;
			if(Character.isLowerCase(name.charAt(0))) {
				refName +=  Character.toUpperCase(name.charAt(0));
				i = 1;
		    } 

			int len = name.length();
			while(i < len) {
				char ch = name.charAt(i);
				if(ch == '_') {
					while(name.charAt(i) == '_' && i < len) ++i;
					// i = len or i == just after _
					if(i == len) break;
					ch = Character.toUpperCase(name.charAt(i));
					refName += ch;
				}
				else refName += ch;
				++i;
			}

			return refName;

	}

}