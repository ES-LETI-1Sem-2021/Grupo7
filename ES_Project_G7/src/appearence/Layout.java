package appearence;

import java.awt.*;

import javax.swing.*;

import gui.MainWindow;

@SuppressWarnings("serial")
public class Layout extends JPanel {

	private LayoutType layoutType;
	private Object layout;
	private Container pane;
	private LayoutManager layMan;

	private boolean layoutMade;

	private static final int LEFT_SPACE = 5;
	private static final int SPACE_TO_FIELD = 150;
	private static final int GAP = 5;

	public Layout(LayoutType layoutType, Container pane, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {
		super(layoutType.getLayout(), true);
		this.pane = pane;
		this.layoutType = layoutType;
		layoutMade = false;
		addToLayout(title, labels, textFields, startingPoint);
	}

	public Layout(Layout layout, TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		this.pane = layout.getPane();
		this.layoutType = layout.getLayoutType();
		layoutMade = true;
		addToLayout(title, labels, textFields, startingPoint);
	}

	public void addToLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		if (layoutType == LayoutType.LAYOUT_SPRING) {
			if (!layoutMade) {
				this.layout = new SpringLayout();
				System.out.println("Este layout não estava feito.");
				layoutMade = true;
			} else {
				this.layout = layoutType.getLayout();
				System.out.println("Este layout estava feito.");
			}
			pane.setLayout((LayoutManager) this.layout);
			System.out.println("Pane layout: " + pane.getLayout());
			addToSpringLayout(title, labels, textFields, startingPoint);
		} else if (layoutType == LayoutType.LAYOUT_GRID) {
			if (!layoutMade) {
				this.layout = new GridLayout(1 + labels.length + textFields.length, 2, GAP, GAP);
				pane.setLayout((LayoutManager) this.layout);
				layoutMade = true;
			}
		} else
			throw new NullPointerException("Wrong Layout.");
	}

	/* SPRINGLAYOUT */

	//private?
	public void addToSpringLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		pane.add(title);
		putConstraintSL(title, pane, LEFT_SPACE, startingPoint);

		for (int obj = 0; obj < labels.length; obj++) {
			pane.add(labels[obj]);
			putConstraintSL(labels[obj], pane, LEFT_SPACE, startingPoint + 35);
			pane.add(textFields[obj]);
			putConstraintSL(textFields[obj], pane, LEFT_SPACE + SPACE_TO_FIELD, startingPoint + 35);

			startingPoint += 25;
		}
	}

	private void putConstraintSL(Component comp, Component reference, int x, int y) {
		((SpringLayout) layout).putConstraint(SpringLayout.WEST, comp, x, SpringLayout.WEST, reference);
		((SpringLayout) layout).putConstraint(SpringLayout.NORTH, comp, y, SpringLayout.NORTH, reference);
	}

	/* GETTERS & SETTERS */

	public Container getPane() {
		return pane;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public boolean isLayoutMade() {
		return layoutMade;
	}
	
	public Layout getPanel() {
		return this;
	}

}