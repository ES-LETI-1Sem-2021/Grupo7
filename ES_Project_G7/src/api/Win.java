package api;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import data.GitDetails;
import data.TrelloDetails;
import definitions.Button;
import definitions.Layout;

@SuppressWarnings("serial")
public class Win extends JFrame {

	private static Win FRAME;
	private Layout layout;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private int page_number = 0;

	private GitDetails github;
	private TrelloDetails trello;

	private JTextField gitToken;

	/**
	 * Create singleton of Window class.
	 * 
	 * @throws IOException
	 */
	public static Win getInstance() throws IOException {
		if (FRAME == null)
			FRAME = new Win();
		return FRAME;
	}

	/**
	 * Create a simple GUI window.
	 * 
	 * @throws IOException
	 */
	private Win() throws IOException {
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
		trello = new TrelloDetails(this);
		github = new GitDetails(this);

//		add(trello, BorderLayout.NORTH);
//		add(github, BorderLayout.CENTER);

	}

	/**
	 *
	 */
	public String getGitHubToken() {
		return gitToken.getText();
	}

	/**
	 * Get Window Frame.
	 */
	public static Win getFrame() {
		return FRAME;
	}
	
	/**
	 * Get Window's layout.
	 */
//	public Layout getLayout() {
//		return layout;
//	}

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
}