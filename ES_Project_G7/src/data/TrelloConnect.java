package data;

import appearence.*;
import appearence.TextField;

import java.awt.*;
import java.io.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

public class TrelloConnect implements Connection {

	private Layout layout;
	private Trello trelloMvn;
	private static final int STARTING_POINT = 5;
	private boolean connected;

	private TextLabel title;
	private TextLabel[] labels;
	private TextField[] textFields;

	// DataTest - Tiago
	private String accessKey = "61d79cb5bcc75c155c7fd74aef6f1b4f";
	private String accessToken = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
	private String boardName = "ES Project";
	private String boardID = "614de795e5e8b75fb65a9cdc";

/////////////////
//Constructors
////////////////

	/**
	 * Create TrelloConnect without any previous Layout made.
	 */
	public TrelloConnect(Container pane) throws IOException {
		getDataLayout(pane);
		connected = false;
		connectTo();
	}

	/**
	 * Create TrelloConnect when there's already a Layout.
	 */
	public TrelloConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		connected = false;
		connectTo();
	}

/////////////////
//Methods
////////////////

	/**
	 * Get Trello data when there's no previous layout.
	 */
	@Override
	public void getDataLayout(Container pane) {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get Trello data when exists a previous layout.
	 */
	@Override
	public void getDataLayout(Layout layout) {
		getData();
		this.layout = layout;

		if (layout.getLayoutType() == LayoutType.LAYOUT_SPRING)
			this.layout.addToSpringLayout(title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get Trello data from user in order to establish connection.
	 */
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

	/**
	 * Establish connection to Trello.
	 */
	@Override
	public void connectTo() throws IOException {
		trelloMvn = new TrelloImpl(accessKey, accessToken, new ApacheHttpClient());
		connected = true;
	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get Trello API.
	 */
	public Trello getTrello() {
		return trelloMvn;
	}

	/**
	 * Check if user is connected to Trello.
	 */
	@Override
	public boolean connected() {
		return connected;
	}

	/**
	 * Get TrelloConnect layout.
	 */
	public Layout getLayout() {
		return layout;
	}
}