package definitions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import api.Win;

public class Layout {

	private SpringLayout layout;
	
	private boolean springLayoutMade;
	private int northLastPosition;
	private int westLastPosition;
	private int southLastPosition;
	private int eastLastPosition;
	private int [] lastCoordinates = {northLastPosition, westLastPosition, southLastPosition, eastLastPosition};
	private String lastComponent;
	
	public Layout() {
		layout = new SpringLayout();
		springLayoutMade = false;
	}

	public void addToSpringLayout(Win win, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {
		
		Container pane = win.getContentPane();
System.out.println(springLayoutMade);
		
		if (!springLayoutMade) {
			pane.setLayout(layout);
			springLayoutMade = true;
			System.out.println(springLayoutMade);
		}
		pane.add(title);
		layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, title, startingPoint, SpringLayout.NORTH, pane);

		for (int det = 0; det < labels.length; det++) {
//			int startingPointWest = startingPoint;
			pane.add(labels[det]);
			layout.putConstraint(SpringLayout.WEST, labels[det], 0, SpringLayout.WEST, pane);
			layout.putConstraint(SpringLayout.NORTH, labels[det], startingPoint + 35, SpringLayout.NORTH, pane);
			
			pane.add(textFields[det]);
			layout.putConstraint(SpringLayout.WEST, textFields[det], 10, SpringLayout.EAST, labels[det]);
			layout.putConstraint(SpringLayout.NORTH, textFields[det], 0, SpringLayout.NORTH, labels[det]);
			
			eastLastPosition = textFields[det].getHorizontalAlignment();
			southLastPosition = labels[det].getVerticalAlignment();			
			
			startingPoint += 25;
		}
	}
	
	public void addButton(Win win) {
		Container pane = win.getContentPane();

		Button b = new Button("Submit data");
					
		if (!springLayoutMade) {
			pane.setLayout(layout);
			springLayoutMade = true;
		}
		layout.putConstraint(SpringLayout.EAST, b, Win.getVerticalSize(), SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.SOUTH, b, Win.getHorizontalSize(), SpringLayout.NORTH, pane);
	}
	
	public Layout getLayout() {
		return this;
	}
}
