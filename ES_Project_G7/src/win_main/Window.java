import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;



package win_main;


/**
 *Create Window.
 */

public class Window {
	public Window() {
		createWindow();
	}
	
	private void createWindow() {
		//Create and set up the window. 
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("label",SwingConstants.CENTER); 
		textLabel.setPreferredSize(new Dimension(300, 100));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		//Display the window
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	
	
	
}
