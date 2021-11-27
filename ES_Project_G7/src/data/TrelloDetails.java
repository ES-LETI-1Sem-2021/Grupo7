package data;

import javax.swing.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import definitions.TextField;
import definitions.TextLabel;

@SuppressWarnings("serial")
public class TrelloDetails extends JPanel {

	private Trello trelloMvn;
//	private String login;
//	private String accessToken;
	//private String cardBoard;

	/**
	 * 
	 */
	public TrelloDetails() {
		super();		
		//		setAlignmentY(TOP_ALIGNMENT);

		TextLabel t = new TextLabel("Login Trello", 15);
		add(t);

		TextField user = new TextField();
		add(user);


//		connectTrello(login, accessToken);
	}






	/**
	 * Establish connection to Trello.
	 */
	@SuppressWarnings("unused")
	private void connectTrello (String login, String accessToken) {
		trelloMvn = new TrelloImpl(login, accessToken, new ApacheHttpClient());
	}

	/**
	 * Get Trello API.
	 */
	public Trello getTrello() {
		return trelloMvn;
	}
}
