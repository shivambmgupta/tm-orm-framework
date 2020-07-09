import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

public class InfoPanel extends JPanel {

	private JFrame parent;

	public void setParent(JFrame frame) {
		this.parent = frame;
	}

	public JFrame getParent() {
		return this.parent;
	}
	
}