package data;

import java.awt.*;
import java.io.*;

import javax.swing.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import appeareceBackup.LayoutBackup;
import appearence.FontType;
import appearence.Layout;
import appearence.LayoutType;
import appearence.TextField;
import appearence.TextLabel;
import gui.MainWindow;

public class TrelloConnect implements Connection {

	private Layout layout;
	private Trello trelloMvn;
	private static final int STARTING_POINT = 5;
	private boolean connected;
	private boolean hasButton;

	private TextLabel title;
	private TextLabel [] labels;
	private TextField [] textFields;	
	
	// Key & AccessToken - Tiago
	private String accessKey = "61d79cb5bcc75c155c7fd74aef6f1b4f";
	private String accessToken = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
	private String boardName = "ES Project";
	private String boardID = "614de795e5e8b75fb65a9cdc";

	public TrelloConnect(Container pane, boolean hasButton) throws IOException {
		getDataLayout(pane);
		connected = false;
		connectTo();
	}
	
	public TrelloConnect(Layout layout, boolean hasButton) throws IOException {
		getDataLayout(layout);
		connected = false;
		connectTo();
	}

	@Override
	public void getDataLayout(Container pane) {
		getData();		
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	@Override
	public void getDataLayout(Layout layout) {
		getData();
		layout = new Layout(layout, title, labels, textFields, STARTING_POINT);
	}
	
	@Override
	public void getData() {
		title = new TextLabel("Login Trello", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Username: ", 15, FontType.FONT_BOLD);
		TextField login = new TextField();
		TextLabel token_lab = new TextLabel("Access Token: ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField();
		TextLabel card_lab = new TextLabel("CardBoard: ", 15, FontType.FONT_BOLD);
		TextField cardBoard = new TextField();

		TextLabel[] labels = { user_lab, token_lab, card_lab };
		this.labels = labels;
		TextField[] textFields = { login, accessToken, cardBoard };
		this.textFields = textFields;
	}
	
	@Override
	public void connectTo() throws IOException {
		trelloMvn = new TrelloImpl(accessKey, accessToken, new ApacheHttpClient());
		connected = true;
	}

	@Override
	public void getAPI() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean connected() {
		// TODO Auto-generated method stub
		return connected;
	}

	
	/* GETTERS & SETTERS */
	
	public Layout getLayout() {
		return layout;
	}
	
}

/*
 * Implementaçao com JFrame
 * 
 * private JPanel panel; private TextLabel title; private TextLabel [] labels;
 * private TextField [] textFields; private Button button;
 * 
 * private boolean connected; private boolean hasButton; private static final
 * int STARTING_POINT = 5; private static final int X_LOCATION =
 * MainWindow.getxLocation(); private static final int Y_LOCATION =
 * MainWindow.getyLocation(); private static final int WIDTH =
 * MainWindow.getHorizontalSize(); private static final int HEIGHT =
 * MainWindow.getVerticalSize();
 * 
 * // Key & AccessToken - Tiago private String login =
 * "61d79cb5bcc75c155c7fd74aef6f1b4f"; private String accessToken =
 * "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118"; private
 * String boardName = "ES Project"; private String boardID =
 * "614de795e5e8b75fb65a9cdc";
 * 
 * public TrelloConnect(MainWindow window, boolean hasButton) { //
 * setBounds(X_LOCATION, Y_LOCATION, WIDTH, HEIGHT); this.hasButton = hasButton;
 * // setVisible(true); // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * getData(window); }
 * 
 * @Override public void getData(MainWindow window) { title = new
 * TextLabel("Login Trello", 15, FontType.FONT_TITLE); TextLabel user_lab = new
 * TextLabel("Username: ", 15, FontType.FONT_BOLD); TextField login = new
 * TextField(); TextLabel token_lab = new TextLabel("Access Token: ", 15,
 * FontType.FONT_BOLD); TextField accessToken = new TextField(); TextLabel
 * card_lab = new TextLabel("CardBoard: ", 15, FontType.FONT_BOLD); TextField
 * cardBoard = new TextField();
 * 
 * TextLabel[] labels = {user_lab, token_lab, card_lab}; TextField[] textFields
 * = { login, accessToken, cardBoard }; this.labels = labels; this.textFields =
 * textFields;
 * 
 * new Layout(LayoutType.LAYOUT_SPRING, MainWindow.getFrame(), title, labels,
 * textFields, STARTING_POINT, hasButton); }
 * 
 * @Override public void connectTo() throws IOException {
 * 
 * }
 * 
 * @Override public void getAPI() {
 * 
 * }
 * 
 * @Override public boolean connected() { return connected; }
 * 
 */