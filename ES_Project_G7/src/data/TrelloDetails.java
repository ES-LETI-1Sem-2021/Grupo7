package data;

import java.awt.BorderLayout;

import javax.swing.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import api.Win;
import definitions.Button;
import definitions.FontType;
import definitions.Layout;
import definitions.TextField;
import definitions.TextLabel;

@SuppressWarnings("serial")
public class TrelloDetails extends JPanel implements Conection {

	private Trello trelloMvn;
	private static final int STARTING_POINT = 0;
//	private String login;
//	private String accessToken;
	// private String cardBoard;

	/**
	 * 
	 */
	public TrelloDetails(Win window) {
		super();
		setAlignmentY(TOP_ALIGNMENT);

		TextLabel title = new TextLabel("Login Trello", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Username: ", 15, FontType.FONT_BOLD);
		TextField login = new TextField();
		TextLabel token_lab = new TextLabel("Access Token: ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField();
		TextLabel card_lab = new TextLabel("CardBoard: ", 15, FontType.FONT_BOLD);
		TextField cardBoard = new TextField();

		Button b = new Button("Submit Trello data");
//		add(b);
//		add(b, RIGHT_ALIGNMENT);

		TextLabel[] labels = { user_lab, token_lab, card_lab };
		TextField[] fields = { login, accessToken, cardBoard };

		Layout.defineSpringLayout(window, title, labels, fields, STARTING_POINT);
		
//		connectTo(login, accessToken);

	}

	/**
	 * Establish connection to Trello.
	 */
	public void connectTo(String login, String accessToken) {
		trelloMvn = new TrelloImpl(login, accessToken, new ApacheHttpClient());
	}

	/**
	 * Get Trello API.
	 */
	private Trello getTrello() {
		return trelloMvn;
	}

	public void getAPI() {
		getTrello();
	}

}
