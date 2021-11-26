package api;	
import java.awt.*;
import javax.swing.*;

import org.kohsuke.github.GitHub;

import com.julienvey.trello.Trello;

import data.GitDetails;
import data.TrelloDetails;

public class Win extends JFrame{  

	private static Win frame;

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
		setSize(600,400);
		
		
		
		getContentPane().add(GitDetails.getGitPanel());
		getContentPane().add(TrelloDetails.getTrelloPanel());
		
//		frame.getContentPane().add(GitDetails.getGitPanel());
//		frame.getContentPane().add(TrelloDetails.getTrelloPanel());
		
		
//		frame = new JFrame("Window");
//		frame.setVisible(true);
//		frame.setSize(600,400);
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
}