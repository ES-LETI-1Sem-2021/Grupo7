package data;

import java.awt.*;
import java.io.*;

import javax.swing.*;

import appearence.FontType;
import appearence.TextField;
import appearence.TextLabel;
import gui.MainWindow;

public class TrelloConnect extends JFrame implements Connection {

	private JPanel panel;
	private TextLabel title;
	private TextLabel [] labels;
	private TextField [] textFields;
	
	private boolean connected;
	private static final int X_LOCATION = MainWindow.getxLocation();
	private static final int Y_LOCATION = MainWindow.getyLocation();
	private static final int WIDTH = MainWindow.getHorizontalSize();
	private static final int HEIGHT = MainWindow.getVerticalSize();

	// Key & AccessToken - Tiago
	private String login = "61d79cb5bcc75c155c7fd74aef6f1b4f";
	private String accessToken = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
	private String boardName = "ES Project";
	private String boardID = "614de795e5e8b75fb65a9cdc";

	public TrelloConnect(MainWindow window) {
		setBounds(X_LOCATION, Y_LOCATION, WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getData(window);
	}

	@Override
	public void getData(MainWindow window) {
		title = new TextLabel("Login Trello", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Username: ", 15, FontType.FONT_BOLD);
		TextField login = new TextField();
		TextLabel token_lab = new TextLabel("Access Token: ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField();
		TextLabel card_lab = new TextLabel("CardBoard: ", 15, FontType.FONT_BOLD);
		TextField cardBoard = new TextField();

		TextLabel[] labels = {user_lab, token_lab, card_lab};
		TextField[] textFields = { login, accessToken, cardBoard };
		this.labels = labels;
		this.textFields = textFields;
	}

	@Override
	public void connectTo() throws IOException {

	}

	@Override
	public void getAPI() {

	}

	@Override
	public boolean connected() {
		return connected;
	}

}
