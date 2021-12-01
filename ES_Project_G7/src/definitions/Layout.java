package definitions;

import java.awt.Container;

import javax.swing.SpringLayout;

import api.Win;

public class Layout {

	public static void defineSpringLayout(Win win,  TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		Container pane = win.getContentPane();
		SpringLayout layout = new SpringLayout();

		pane.setLayout(layout);
//		pane.add(title);

		for (int lab = 0; lab < labels.length; lab++) {
			int startingPointWest = startingPoint;
			pane.add(labels[lab]);
			layout.putConstraint(SpringLayout.WEST, labels[lab], startingPointWest+5, SpringLayout.WEST, pane);
			layout.putConstraint(SpringLayout.NORTH, labels[lab], startingPoint+50, SpringLayout.NORTH, pane);
			
			pane.add(textFields[lab]);
			layout.putConstraint(SpringLayout.WEST, textFields[lab], Win.getHorizontalSize()-100, SpringLayout.EAST, pane);
			layout.putConstraint(SpringLayout.NORTH, textFields[lab], 0, SpringLayout.NORTH, labels[lab]);
			
			startingPoint += 10;
		}
	}
}
