package gui;

import java.io.IOException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appearence.*;
import appearence.Button;
import data.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow FRAME;
	private Layout layout;
	private static final int X_LOCATION = 250;
	private static final int Y_LOCATION = 250;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private int pageNumber = 0;

	private TrelloConnect trello;
	private GitConnect github;

	/**
	 * Create singleton of Window class.
	 * 
	 * @throws IOException
	 */
	public static MainWindow getInstance() throws IOException {
		if (FRAME == null)
			FRAME = new MainWindow();
		return FRAME;
	}

	/**
	 * Create a simple GUI window.
	 * 
	 * @throws IOException
	 */
	private MainWindow() throws IOException {
		super("API");

		setBounds(X_LOCATION, Y_LOCATION, WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (pageNumber == 0)
			initialize();
//		pack();
	}

	/**
	 * Initialization of GUI and submission of login data to Trello and GitHub.
	 * Makes the "request" to initialize the next section (window).
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		trello = new TrelloConnect(this.getContentPane());
		github = new GitConnect(trello.getLayout());
		
		Button button = new Button("SUBMIT");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					pageNumber++;
					trello.assumeData();
					github.assumeData();
					getInstance();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		github.getLayout().addToSpringLayout(button, 300);
	}

	/**
	 * Get Window Frame.
	 */
	public static MainWindow getFrame() {
		return FRAME;
	}
	
	public TrelloConnect getTrelloConnect() {
		return trello;
	}

	public static int getxLocation() {
		return X_LOCATION;
	}

	public static int getyLocation() {
		return Y_LOCATION;
	}

	public static int getHorizontalSize() {
		return WIDTH;
	}

	public static int getVerticalSize() {
		return HEIGHT;
	}
}