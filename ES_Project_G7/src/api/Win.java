package api;	
import java.awt.*;
import javax.swing.*;
public class Win{  
	private JFrame frame;
	private JTextField gitToken;
	private JTextField trelloKey;
	private JButton nextButton;
	private JTextField trelloToken;
	private JTextField trelloUser;
	// Create a simple GUI window 

	public Win() { createWindow();}

	private void createWindow() {      
		//Create and set up the window.      
		JFrame frame = new JFrame("Window");      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		JLabel textLabel = new JLabel("Git Repository"); 
		frame.setBounds(100, 100, 527, 403);
		textLabel.setBounds(10, 11, 300, 37);
		textLabel.setPreferredSize(new Dimension(300, 300));   
		frame.getContentPane().add(textLabel); 
		//Token github
		gitToken =new JTextField();
		gitToken.setText("write here your github token");
		gitToken.setBounds(20, 59, 404, 29);
		frame.getContentPane().add(gitToken);
		gitToken.setColumns(10);
		//Display the window.
		frame.setLocationRelativeTo(null);  
		frame.pack();    
		frame.setVisible(true);  
	}  
	public String getGitHubToken() {
		return gitToken.getText();
	}

	public String getTrelloKey() {
		return trelloKey.getText();
	}

	public String getTrelloToken() {
		return trelloToken.getText();
	}

	public String getTrelloUser() {
		return trelloUser.getText();
	}

	public Window getFrame() {
		return frame;
	}

}