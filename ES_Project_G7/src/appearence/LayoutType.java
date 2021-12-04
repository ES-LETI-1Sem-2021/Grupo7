package appearence;

import java.awt.*;
import javax.swing.*;

public enum LayoutType {
	LAYOUT_SPRING(new SpringLayout()),
	LAYOUT_GRID(new GridLayout()),
	LAYOUT_BORDER(new BorderLayout());

	private Object layout;
	
	LayoutType(Object layoutType) {
		this.layout = layoutType;
	}
	
	public LayoutType getLayout() {
		return (LayoutType) layout;
	}
}
