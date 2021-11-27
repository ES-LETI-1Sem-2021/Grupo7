package definitions;

import java.awt.Font;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import api.Win;

@SuppressWarnings("serial")
public class TextLabel extends JLabel {
	
	private static final Font FONT_LABEL = new FontUIResource("Calibri", Font.BOLD, 20);
	
	/*
	 * 
	 */
	public TextLabel(String text, int y_Position){
		super(text);
		super.setFont(FONT_LABEL);
		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}
	
}
