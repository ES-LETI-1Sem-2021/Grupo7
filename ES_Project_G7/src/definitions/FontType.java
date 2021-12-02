package definitions;

import java.awt.Font;
import javax.swing.plaf.FontUIResource;

public enum FontType {
	FONT_TITLE (new FontUIResource("Calibri", Font.BOLD, 25)),
	FONT_REGULAR (new FontUIResource("Calibri", Font.PLAIN, 14)),
	FONT_BOLD (new FontUIResource("Calibri", Font.BOLD, 14)),
	FONT_TEXT_FIELD (new FontUIResource("Calibri", Font.ITALIC, 12)),
	FONT_BUTTON (new FontUIResource("Calibri", Font.BOLD, 20));

	private Font font;
	
	FontType(FontUIResource fontUIResource) {
		this.font = fontUIResource;
	}
	
	public Font getFont() {
		return font;
	}

}
