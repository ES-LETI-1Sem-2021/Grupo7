package api;	
import java.awt.*;

import javax.swing.*;




public class Win{  
	private JFrame frame;
	private JTextField gitToken;
	// Create a simple GUI window 

	public Win() { createWindow();}

	private void createWindow() {      
		 BorderLayout borderlayout = new BorderLayout();

		    JFrame frame = new JFrame("Window");
		    frame.setVisible(true);
		    frame.setSize(600,400);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    Panel p2 = new Panel();
		    p2.setLayout(new BorderLayout());
		    p2.add(new Label("GitRepository"),BorderLayout.NORTH ); 
		    p2.add(new Label("Trello"));
		    p2.setFont(new Font("sansserif", Font.BOLD, 18));
		    frame.add(p2);
		
		
		
		
	}  
	public String getGitHubToken() {
		return gitToken.getText();
	}


	public Window getFrame() {
		return frame;
	}
	
}