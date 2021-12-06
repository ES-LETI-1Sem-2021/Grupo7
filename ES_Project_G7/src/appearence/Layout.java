package appearence;

import java.awt.*;

import javax.swing.*;

import gui.MainWindow;

@SuppressWarnings("serial")
public class Layout extends JPanel {

	private LayoutType layoutType;
	private Object layout;
	private Container pane;

	private static final int LEFT_SPACE = 5;
	private static final int SPACE_TO_FIELD = 150;
//	private static final int GAP = 5;                    //para usar com GridLayout

	/**
	 * Create a Layout from scratch.
	 * 
	 * @param <code>layoutType</code>    Gives the LayoutType to be used as base to
	 *                                   add the elements to the Layout.
	 * @param <code>pane</code>          Defines the Container in which will be
	 *                                   added the Layout.
	 * @param <code>title</code>         Defines the title of the Layout section.
	 * @param <code>labels</code>        Defines the different labels that will be
	 *                                   used to define which information it will
	 *                                   ask from the user.
	 * @param <code>textFields</code>    Defines the different text fields
	 *                                   correspondent to the labels.
	 * @param <code>startingPoint</code> Defines in which point of the Container it
	 *                                   will start to add the different elements.
	 */
	public Layout(LayoutType layoutType, Container pane, TextLabel title, TextLabel[] labels, TextField[] textFields,
			int startingPoint) {
		super(layoutType.getLayout(), true);
		this.pane = pane;
		this.layoutType = layoutType;
		createLayout(title, labels, textFields, startingPoint);
	}

	/**
	 * In order to add elements to a Layout, it has to verify which will be the
	 * LayoutType and then if there's a previous Layout to work on as a base. If it
	 * does not exist any Layout, it will create one according to the LayoutType.
	 * 
	 * @param <code>title</code>         Defines the title of the Layout section.
	 * @param <code>labels</code>        Defines the different labels that will be
	 *                                   used to define which information it will
	 *                                   ask from the user.
	 * @param <code>textFields</code>    Defines the different text fields
	 *                                   correspondent to the labels.
	 * @param <code>startingPoint</code> Defines in which point of the Container it
	 *                                   will start to add the different elements.
	 */
	private void createLayout(TextLabel title, TextLabel[] labels, TextField[] textFields, int startingPoint) {
		if (layoutType == LayoutType.LAYOUT_SPRING) {
			this.layout = new SpringLayout();
			pane.setLayout((LayoutManager) this.layout);
			addToSpringLayout(title, labels, textFields, startingPoint);
//		} else if (layoutType == LayoutType.LAYOUT_GRID) {
//			this.layout = new GridLayout(1 + labels.length + textFields.length, 2, GAP, GAP);
//			pane.setLayout((LayoutManager) this.layout);
		} else
			throw new NullPointerException("Wrong Layout.");
	}

/////////////////
//SpringLayout
////////////////

	/**
	 * Add elements to the SpringLayout.
	 * 
	 * @param <code>title</code>         Defines the title of the Layout section.
	 * @param <code>labels</code>        Defines the different labels that will be
	 *                                   used to define which information it will
	 *                                   ask from the user.
	 * @param <code>textFields</code>    Defines the different text fields
	 *                                   correspondent to the labels.
	 * @param <code>startingPoint</code> Defines in which point of the Container it
	 *                                   will start to add the different elements.
	 */
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

	/**
	 * Auxiliary function to put constraints to elements in SpringLayout.
	 * 
	 * @param <code>comp</code>      Gives the component that will be set to a place
	 *                               in the Layout.
	 * @param <code>reference</code> Gives the space reference that will be serve as
	 *                               a guide to place the comp in place.
	 * @param <code>x</code>         Gives the horizontal distance from the
	 *                               reference, defining where the comp will be put
	 *                               in terms of horizontal coordinate.
	 * @param <code>y</code>         Gives the vertical distance from the reference,
	 *                               defining where the comp will be put in terms of
	 *                               vertical coordinate.
	 */
	private void putConstraintSL(Component comp, Component reference, int x, int y) {
		((SpringLayout) layout).putConstraint(SpringLayout.WEST, comp, x, SpringLayout.WEST, reference);
		((SpringLayout) layout).putConstraint(SpringLayout.NORTH, comp, y, SpringLayout.NORTH, reference);
	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get Container from Layout.
	 */
	public Container getPane() {
		return pane;
	}
	
	/**
	 * Get LayoutType implemented in the Layout.
	 */
	public LayoutType getLayoutType() {
		return layoutType;
	}
}