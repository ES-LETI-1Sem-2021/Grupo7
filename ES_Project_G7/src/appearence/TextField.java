package appearence;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField {

	private static final int DEFAULT_COLUMNS = 50;

	public TextField() {
		super();
		super.setFont(FontType.FONT_TEXT_FIELD.getFont());
		super.setColumns(25);
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}

	/**
	 * '25' - number of columns
	 */
	public TextField(String string) {
		super(string, DEFAULT_COLUMNS);
		super.setFont(FontType.FONT_TEXT_FIELD.getFont());
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}

	public TextField(String string, int columns) {
		super(string, columns);
		super.setFont(FontType.FONT_TEXT_FIELD.getFont());
//		super.setBounds(15, y_Position, Win.getHorizontalSize(), 30);
	}

	public static int getFieldWidth() {
		return DEFAULT_COLUMNS;
	}

	public String getString() {
		return this.getString();
	}

}
