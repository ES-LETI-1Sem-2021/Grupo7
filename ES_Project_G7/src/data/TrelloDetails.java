package data;

import java.io.IOException;

import javax.swing.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

@SuppressWarnings("serial")
public class TrelloDetails extends JPanel {

	private static TrelloDetails trelloPanel;

	private Trello trelloMvn;
	private String login;
	private String accessToken;
	//private String cardBoard;

	
	private TrelloDetails() throws IOException {
		
		trelloPanel = new TrelloDetails();
		setAlignmentY(TOP_ALIGNMENT);
		
		JLabel t = new JLabel("Login Trello");
		add(t);
		
		connectTrello(login, accessToken);
	}

	
	
	
	
	
	/**
	 * Establish connection to Trello.
	 */
	private void connectTrello (String login, String accessToken) throws IOException {
		trelloMvn = new TrelloImpl(login, accessToken, new ApacheHttpClient());
	}
	
	/**
	 * Get Trello API.
	 */
	public Trello getTrello() {
		return trelloMvn;
	}
	
	/**
	 * Create Trello JPanel.
	 */
	public static TrelloDetails getTrelloPanel()	{
		return trelloPanel;
	}	
}
