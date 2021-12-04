package gui;

import java.io.IOException;
import javax.swing.*;

import appeareceBackup.GitConnectBackup;
import appeareceBackup.TrelloConnectBackup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appearence.*;
import appearence.Button;
import data.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow FRAME;
	private Layout layout;
	private static final int X_LOCATION = 250;
	private static final int Y_LOCATION = 250;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private int page_number = 0;

//	private GitConnectBackup github;
//	private TrelloConnectBackup trello;
	private TrelloConnect trello;
	private GitConnect github;

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

		setBounds(X_LOCATION, Y_LOCATION, WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (page_number == 0)
			initialize();
//		pack();
	}

	/**
	 * Initialization of GUI.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
//		trello = new TrelloConnectBackup(this);
//		github = new GitConnectBackup(this);
		
		trello = new TrelloConnect(this.getContentPane(), false);
		github = new GitConnect(trello.getLayout().getPanel(), false);
		
//		github = new GitConnect(this.getContentPane(), false);
//		trello = new TrelloConnect(github.getLayout().getPanel(), false);
		
		Button button = new Button("LOGIN");
		
		
		
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				try {
//					github = new GitConnect(MainWindow.getFrame());
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}	
//		});
//		add(button);
		
//		Layout layout = new Layout(LayoutType.LAYOUT_BORDER, this, BorderLayout.LINE_END, button);
//		Layout layout = new Layout(LayoutType.LAYOUT_SPRING, JPanel.TOP_ALIGNMENT, this, button);
	}

	/**
	 * Get Window Frame.
	 */
	public static MainWindow getFrame() {
		return FRAME;
	}

	public static int getxLocation() {
		return X_LOCATION;
	}

	public static int getyLocation() {
		return Y_LOCATION;
	}

	public static int getHorizontalSize() {
		return WIDTH;
	}

	public static int getVerticalSize() {
		return HEIGHT;
	}
}