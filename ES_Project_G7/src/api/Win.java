package api;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import data.GitDetails;
import data.TrelloDetails;

@SuppressWarnings("serial")
public class Win extends JFrame {

	private static Win FRAME;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private int page_number = 0;

	private GitDetails github;
	private TrelloDetails trello;

	private JTextField gitToken;

	/**
	 * Create singleton of Window class.
	 */
	public static Win getInstance() {
		if (FRAME == null)
			FRAME = new Win();
		return FRAME;
	}

	/**
	 * Create a simple GUI window.
	 */
	private Win() {
		super("API");

		setSize(WIDTH, HEIGHT);
		setVisible(true);

		if (page_number == 0)
			initialize();

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		// Panel p2 = new Panel();
		// p2.setLayout(new BorderLayout());
		// p2.add(new Label("GitRepository"),BorderLayout.NORTH );
		// p2.add(new Label("Trello"));
		// p2.setFont(new Font("sansserif", Font.BOLD, 18));
		// frame.add(p2);
		//
		// int w = JOptionPane.showConfirmDialog(null, ,
		// GameName, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * Initialization of GUI.
	 */
	private void initialize() {
		trello = new TrelloDetails(this);
		github = new GitDetails(this);
		
		add(trello, BorderLayout.NORTH);
		add(github, BorderLayout.CENTER);
	}

	public String getGitHubToken() {
		return gitToken.getText();
	}

	public static Win getFrame() {
		return FRAME;
	}

	public static int getHorizontalSize() {
		return WIDTH;
	}

	public static int getVerticalSize() {
		return HEIGHT;
	}
}