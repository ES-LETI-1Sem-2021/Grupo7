package api;	
import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import data.GitDetails;
import data.TrelloDetails;

@SuppressWarnings("serial")
public class Win extends JFrame{  

	private static Win frame;
	private static final int WIDTH = 1100;
	private static final int HEIGHT = 330;
	
	private GitDetails github;
	private TrelloDetails trello;

	private JTextField gitToken;

	/**
	 * Create singleton of Window class.
	 */
	public static Win getInstance() {
		if (frame == null)
			frame = new Win();
		return frame;
	}

	/**
	 * Create a simple GUI window.
	 */
	private Win() {      
		super("API");

		github = new GitDetails();
		trello = new TrelloDetails();

		setSize(WIDTH,HEIGHT);

		add(trello, BorderLayout.NORTH);
		add(github);
		setVisible(true);
	}  


	public String getGitHubToken() {
		return gitToken.getText();
	}

	public Window getFrame() {
		return frame;
	}
	
	public static int getHorizontalSize () {
		return WIDTH;
	}
	
	public static int getVerticalSize () {
		return HEIGHT;
	}
}