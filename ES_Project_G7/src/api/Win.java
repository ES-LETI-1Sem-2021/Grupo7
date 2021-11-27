package api;	
import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import data.GitDetails;
import data.TrelloDetails;

@SuppressWarnings("serial")
public class Win extends JFrame{  

	private static Win frame;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	private GitDetails github;
	private TrelloDetails trello;

	private JTextField gitToken;

	/**
	 * Create singleton of Window class.
	 * @throws IOException 
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


		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		//		Panel p2 = new Panel();
		//		p2.setLayout(new BorderLayout());
		//		p2.add(new Label("GitRepository"),BorderLayout.NORTH ); 
		//		p2.add(new Label("Trello"));
		//		p2.setFont(new Font("sansserif", Font.BOLD, 18));
		//		frame.add(p2);
		//		
		//		int w = JOptionPane.showConfirmDialog(null, ,
		//				GameName, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

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