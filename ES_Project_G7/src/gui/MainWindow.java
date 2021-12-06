package gui;

import java.io.IOException;
import javax.swing.*;

import com.julienvey.trello.Trello;

import appearence.*;
import data.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow FRAME;
	private Layout layout;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private int page_number = 0;

	private GitConnect github;
	private TrelloConnect trello;

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

		layout = new Layout();
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (page_number == 0)
			initialize();

		//		add(trello, BorderLayout.NORTH);
		//		add(github);
	}

	/**
	 * Initialization of GUI.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		trello = new TrelloConnect(this);
		layout.setSpringLayoutMade(true);
		System.out.println(layout.isSpringLayoutMade());
		github = new GitConnect(this);

		//		add(trello, BorderLayout.NORTH);
		//		add(github, BorderLayout.CENTER);

	}

	/**
	 * Get Window Frame.
	 */
	public static MainWindow getFrame() {
		return FRAME;
	}

	/**
	 * Get frame's width.
	 */
	public static int getHorizontalSize() {
		return WIDTH;
	}

	/**
	 * Get frame's height.
	 */
	public static int getVerticalSize() {
		return HEIGHT;
	}
	public TrelloConnect getTrello() {
		return trello;
	}
}