package api;





	import java.awt.*;
	import javax.swing.*;
	public class Win{  
		private JFrame frame;
		private JTextField gitHubToken;
		private JTextField trelloKey;
		private JButton nextButton;
		private JTextField trelloToken;
		private JTextField trelloUser;
	// Create a simple GUI window 
	
	public Win() { createWindow();}
	  
		private void createWindow() {      
			//Create and set up the window.      
			JFrame frame = new JFrame("Simple GUI");      
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
			JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER); 
			textLabel.setPreferredSize(new Dimension(300, 100));   
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);    
			//Display the window.
			frame.setLocationRelativeTo(null);  
			frame.pack();    
			frame.setVisible(true);  
			}  
		public String getGitHubToken() {
			return gitHubToken.getText();
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
	
	

