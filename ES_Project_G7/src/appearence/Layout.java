package appearence;

import java.awt.*;
import javax.swing.*;

import gui.MainWindow;

public class Layout {

//	private GridLayout layout;
	private SpringLayout layout;

	private boolean layoutMade;

	public Layout() {
//		layout = new GridLayout(8,3,5,5);
		layout = new SpringLayout();
	}

	public void addToSpringLayout(MainWindow win, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {

		Container pane = win.getContentPane();
		System.out.println("layoutMade antes da if-statement: " + layoutMade);

		if (!layoutMade) {
			pane.setLayout(layout);
			layoutMade = true;
			System.out.println("layoutMade if-statement: " + layoutMade);
		}

		pane.add(title);
		layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, title, startingPoint, SpringLayout.NORTH, pane);

		for (int det = 0; det < labels.length; det++) {
			pane.add(labels[det]);
			layout.putConstraint(SpringLayout.WEST, labels[det], 0, SpringLayout.WEST, pane);
			layout.putConstraint(SpringLayout.NORTH, labels[det], startingPoint + 35, SpringLayout.NORTH, pane);

			pane.add(textFields[det]);
			layout.putConstraint(SpringLayout.WEST, textFields[det], 10, SpringLayout.EAST, labels[det]);
			layout.putConstraint(SpringLayout.NORTH, textFields[det], 0, SpringLayout.NORTH, labels[det]);

			startingPoint += 25;
		}

//		pane.add(title);
//		for (int det = 0; det < labels.length; det++) {
//			pane.add(labels[det]);
//			pane.add(textFields[det]);
//			startingPoint += 25;
//		}

	}

	public void addButton(MainWindow win) {
	}

	public Layout getLayout() {
		return this;
	}

	public boolean isSpringLayoutMade() {
		return layoutMade;
	}

	public void setSpringLayoutMade(boolean springLayoutMade) {
		this.layoutMade = springLayoutMade;
	}

//	private SpringLayout layout;
//
//	private boolean springLayoutMade;
//	private int northLastPosition;
//	private int westLastPosition;
//	private int southLastPosition;
//	private int eastLastPosition;
//	private int[] lastCoordinates = { northLastPosition, westLastPosition, southLastPosition, eastLastPosition };
//	private String lastComponent;
//
//	public Layout() {
//		layout = new SpringLayout();
//	}
//
//	public void addToSpringLayout(Win win, TextLabel title, TextLabel[] labels, TextField[] textFields,
//			int startingPoint) {
//
//		Container pane = win.getContentPane();
//		System.out.println("springLayoutMade antes da if-statement: " + springLayoutMade);
//
//		if (!springLayoutMade) {
//			pane.setLayout(layout);
//			springLayoutMade = true;
//			System.out.println("springLayoutMade if-statement: " + springLayoutMade);
//		}
//		pane.add(title);
//		layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, pane);
//		layout.putConstraint(SpringLayout.NORTH, title, startingPoint, SpringLayout.NORTH, pane);
//
//		for (int det = 0; det < labels.length; det++) {
//			pane.add(labels[det]);
//			layout.putConstraint(SpringLayout.WEST, labels[det], 0, SpringLayout.WEST, pane);
//			layout.putConstraint(SpringLayout.NORTH, labels[det], startingPoint + 35, SpringLayout.NORTH, pane);
//
//			pane.add(textFields[det]);
//			layout.putConstraint(SpringLayout.WEST, textFields[det], 10, SpringLayout.EAST, labels[det]);
//			layout.putConstraint(SpringLayout.NORTH, textFields[det], 0, SpringLayout.NORTH, labels[det]);
//
//			eastLastPosition = textFields[det].getHorizontalAlignment();
//			southLastPosition = labels[det].getVerticalAlignment();
//
//			startingPoint += 25;
//		}
//	}
//
//	public void addButton(Win win) {
//		Container pane = win.getContentPane();
//
//		Button b = new Button("Submit data");
//
//		if (!springLayoutMade) {
//			pane.setLayout(layout);
//			springLayoutMade = true;
//		}
//		layout.putConstraint(SpringLayout.EAST, b, Win.getVerticalSize(), SpringLayout.WEST, pane);
//		layout.putConstraint(SpringLayout.SOUTH, b, Win.getHorizontalSize(), SpringLayout.NORTH, pane);
//	}
//
//	public Layout getLayout() {
//		return this;
//	}
//	
//	public boolean isSpringLayoutMade() {
//		return springLayoutMade;
//	}
//
//	public void setSpringLayoutMade(boolean springLayoutMade) {
//		this.springLayoutMade = springLayoutMade;
//	}
}
