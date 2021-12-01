package definitions;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

@SuppressWarnings("serial")
public class TextField extends JTextField {

	private static final Font FONT_FIELD = new FontUIResource("Calibri", Font.ITALIC, 12);
	
	public TextField() {
		super();
		super.setFont(FONT_FIELD);
		super.setColumns(25);
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}
	
	
	/**
	 * '25' - number of columns
	 */
	public TextField(String string) {
		super(string, 25);
		super.setFont(FONT_FIELD);
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}
	
	public TextField(String string, int columns) {
		super(string, columns);
		super.setFont(FONT_FIELD);
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}

}
