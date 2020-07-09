import javax.swing.*;
import java.awt.*;

public class TMLabel extends JLabel {

	private Font font;

	public TMLabel() {
		super();
		this.font = new Font("Times Roman", Font.BOLD, 14);
	}

	public TMLabel(String name) {
		super(name);
		this.font = new Font("Times Roman", Font.BOLD, 14);
	}

	public TMLabel(String name, int option) {
		super(name, option);
		this.font = new Font("Times Roman", Font.BOLD, 14);	
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
	}

	@Override
	public Font getFont() {
		return this.font;
	}

}