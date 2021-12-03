package appearence;

import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	public Button(Icon icon) {
		super(icon);
	}

	public Button(String text) {
		super(text);
	}

	public Button(String text, Icon icon) {
		super(text, icon);
	}

}
