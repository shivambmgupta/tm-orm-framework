package com.tm.orm.tool;


import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import com.tm.orm.util.*;

public class ORMTool extends JFrame {

	private JPanel mainPanel;
	private JPanel infoPanel;
	private Container container;

	private final String FRAME_TITLE = "TM-ORM Tool";
	private ImageIcon frameIcon;

	private int width;
	private int height;

	public ORMTool() throws Exception {
		initComponents();
		styleFrame();
	}

	void styleFrame() {

		setTitle(FRAME_TITLE);

		frameIcon = new ImageIcon("./resources/icons/appIcon.png");
		setIconImage(frameIcon.getImage());

		setLayout(new FlowLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(width, height);
		setLocation((dimension.width/2) - 500, (dimension.height/2) - 250);

		setResizable(false);
		setVisible(true);
	}

	void initComponents() {
		this.container = getContentPane();

		this.width = 1000;
		this.height = 500;

		this.mainPanel = new JPanel();
		ConfigureUI.init(mainPanel, this); //Panel, Parents
		
		this.infoPanel = new JPanel();
		ContentUI.init(infoPanel, this);

		this.container.add(mainPanel);
		this.container.add(infoPanel);

		this.infoPanel.setVisible(false);
		this.mainPanel.setVisible(true);
	}

	public void saveButtonClickedEvent() {
		this.mainPanel.setVisible(false);
		this.infoPanel.setVisible(true);
	}

	public void cancelButtonClickedEvent() {
		this.dispose();
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void setInfoContent(Map<String, Table> content) {
		ContentUI.setContent(content);
		ContentUI.setUp();
	}

	private static void renderSplashScreen() throws Exception {
			final SplashScreen splash = SplashScreen.getSplashScreen();
			if(splash == null) {
				System.err.println("Couldn't load splash screen.");
				return;
			}
			Thread.sleep(500);
			splash.close();
	}

	public static void main(String args[]) {
		try {
			renderSplashScreen();
			new ORMTool();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

}