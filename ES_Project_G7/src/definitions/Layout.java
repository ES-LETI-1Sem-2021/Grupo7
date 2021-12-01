package definitions;

import java.awt.Container;

import javax.swing.SpringLayout;

import api.Win;

public class Layout {

	public static void defineSpringLayout(Win win, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {
		Container pane = win.getContentPane();
		SpringLayout layout = new SpringLayout();

		pane.setLayout(layout);
		pane.add(title);

		for (int det = 0; det < labels.length; det++) {
			int startingPointWest = startingPoint;
			pane.add(labels[det]);
			layout.putConstraint(SpringLayout.WEST, labels[det], startingPointWest + 5, SpringLayout.WEST, pane);
			layout.putConstraint(SpringLayout.NORTH, labels[det], startingPoint + 50, SpringLayout.NORTH, pane);

			pane.add(textFields[det]);
			layout.putConstraint(SpringLayout.WEST, textFields[det], Win.getHorizontalSize() - 100, SpringLayout.EAST,
					pane);
			layout.putConstraint(SpringLayout.NORTH, textFields[det], 0, SpringLayout.NORTH, labels[det]);

			startingPoint += 10;
		}
	}
}
