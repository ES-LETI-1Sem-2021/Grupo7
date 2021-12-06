package appearence;

import java.awt.*;
import javax.swing.*;

public enum LayoutType {
	LAYOUT_SPRING(new SpringLayout()),
	LAYOUT_GRID(new GridLayout()),
	LAYOUT_BORDER(new BorderLayout());

	private LayoutManager layout;
	
	LayoutType(LayoutManager layoutType) {
		this.layout = layoutType;
	}
	
	public LayoutManager getLayout() {
		return layout;
	}
}
