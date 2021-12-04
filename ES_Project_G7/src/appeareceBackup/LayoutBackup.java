package appeareceBackup;

import java.awt.*;
import java.rmi.activation.ActivationGroupID;

import javax.swing.*;

import appearence.LayoutType;
import appearence.TextField;
import appearence.TextLabel;
import gui.MainWindow;

public class LayoutBackup extends JPanel {

	private LayoutType layoutType;
	private Object layout;
	private Container pane;

	private boolean layoutMade;
	private boolean springLayout;
	private boolean gridLayout;
	private boolean borderLayout;

	private static final int GAP = 5;

	/**
	 * Create JPanel with SpringLayout.
	 */
	public LayoutBackup(LayoutType layoutType, MainWindow window, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {
		super(getLayoutManager(layoutType), true);
		pane = window.getContentPane();

		if (layoutType == LayoutType.LAYOUT_SPRING) {
			layout = new SpringLayout();
			verifyLayout();
			addToSpringLayout(title, labels, textFields, startingPoint);
		} else if (layoutType == LayoutType.LAYOUT_GRID) {
			layout = new GridLayout(1 + labels.length + textFields.length, 2, GAP, GAP);
			verifyLayout();
			addToGridLayout(title, labels, textFields, startingPoint);
		} else 
			throw new NullPointerException("Wrong Layout.");
	}

	public LayoutBackup(LayoutType layoutType, MainWindow window, TextLabel title, TextLabel[] labels, TextField[] textFields,
			String constraints) {
		super(getLayoutManager(layoutType), true);
		pane = window.getContentPane();

		if (layoutType == LayoutType.LAYOUT_BORDER) {
			layout = new BorderLayout(GAP, GAP);
			verifyLayout();
			addToBorderLayout(title, labels, textFields, constraints);
		} else
			throw new NullPointerException("Wrong Layout.");
	}
	
	private static LayoutManager getLayoutManager(LayoutType layoutType) {
		if (layoutType == LayoutType.LAYOUT_SPRING)
			return new SpringLayout();
		else if (layoutType == LayoutType.LAYOUT_GRID)
			return new GridLayout();
		else if (layoutType == LayoutType.LAYOUT_BORDER)
			return new BorderLayout();
		else
			throw new NullPointerException("Wrong Layout.");
	}

	private void verifyLayout() {
		if (!layoutMade) {
			pane.setLayout((LayoutManager) layout);
			layoutMade = true;
		}
	}

	private void addToSpringLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		pane.add(title);
		((SpringLayout) layout).putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, pane);
		((SpringLayout) layout).putConstraint(SpringLayout.NORTH, title, startingPoint, SpringLayout.NORTH, pane);

		for (int det = 0; det < labels.length; det++) {
			pane.add(labels[det]);
			((SpringLayout) layout).putConstraint(SpringLayout.WEST, labels[det], 0, SpringLayout.WEST, pane);
			((SpringLayout) layout).putConstraint(SpringLayout.NORTH, labels[det], startingPoint + 35,
					SpringLayout.NORTH, pane);

			pane.add(textFields[det]);
			((SpringLayout) layout).putConstraint(SpringLayout.WEST, textFields[det], 10, SpringLayout.EAST,
					labels[det]);
			((SpringLayout) layout).putConstraint(SpringLayout.NORTH, textFields[det], 0, SpringLayout.NORTH,
					labels[det]);

			startingPoint += 25;
		}
	}

	private void addToGridLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		pane.add(title);
		for (int det = 0; det < labels.length; det++) {
			pane.add(labels[det]);
			pane.add(textFields[det]);
			startingPoint += 25;
		}
	}
	
	private void addToBorderLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, String constraints) {
		pane.add(title, constraints);
		for (int det = 0; det < labels.length; det++) {
			pane.add(labels[det], BorderLayout.AFTER_LAST_LINE);
			pane.add(textFields[det], BorderLayout.EAST);
		}
	}
}
