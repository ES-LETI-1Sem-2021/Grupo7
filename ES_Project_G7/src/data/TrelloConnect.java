package data;

import appearence.*;
import appearence.TextField;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.domain.*;

public class TrelloConnect implements Connection {

	private Layout layout;
	private Trello trelloMvn;
	private static final int STARTING_POINT = 5;
	private boolean connected;

	private TextLabel title;
	private TextLabel[] labels;
	private TextField[] textFields;
	private Board board = new Board();

	private List<Member> members = new ArrayList<Member>();

	// DataTest - Tiago
	private String accessKey; // = "61d79cb5bcc75c155c7fd74aef6f1b4f";
	private String accessToken; // = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
	private String boardName; // = "ES Project";
	private String boardID = "614de795e5e8b75fb65a9cdc";

/////////////////
//Constructors
////////////////

	/**
	 * Create TrelloConnect without any previous Layout made.
	 * 
	 * @param <code>pane</code> Uses a Container as base to define where the
	 *                          implementation of the getData() functions.
	 * 
	 * @throws IOException
	 */
	public TrelloConnect(Container pane) throws IOException {
		getDataLayout(pane);
		board.setName(boardName);
		connected = false;
	}

	/**
	 * Create TrelloConnect when there's already a Layout.
	 * 
	 * @param <code>layout</code> Uses a predefined layout as a parameter, to define
	 *                            where will be added the getData() functions.
	 * 
	 * @throws IOException
	 */
	public TrelloConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		connected = false;
	}

/////////////////
//Methods
////////////////

	/**
	 * Get Trello data when there's no previous layout.
	 * 
	 * @param <code>pane</code> Uses a Container as base to define where will be
	 *                          created the new Layout (which is the layout
	 *                          instance).
	 */
	@Override
	public void getDataLayout(Container pane) {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get Trello data when exists a previous layout.
	 * 
	 * @param <code>pane</code> Uses a Layout as base to define where will be added
	 *                          the getData() function and defines it as the layout
	 *                          instance.
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
		TextLabel user_lab = new TextLabel("Access Key: ", 15, FontType.FONT_BOLD);
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
	 * Uses data inserted by the user as login instances.
	 * 
	 * @throws IOException
	 */
	@Override
	public void assumeData() throws IOException {
		accessKey = textFields[0].getText();
		accessToken = textFields[1].getText();
		boardName = textFields[2].getText();
		connectTo();

		System.out.println("Access Key: " + accessKey);
		System.out.println("Access Token: " + accessToken);
		System.out.println("Board Name: " + boardName);
	}

	/**
	 * Establish connection to Trello.
	 * 
	 * @throws IOException
	 */
	@Override
	public void connectTo() throws IOException {
		trelloMvn = new TrelloImpl(accessKey, accessToken, new ApacheHttpClient());
		connected = true;
	}

	/**
	 * Get Members from Trello.
	 * 
	 * @param <code>boardName</code> Gives the name of the board where it has to go
	 *                               get the members.
	 */
	public void getMembers() {
//		Board board = new Board();
//		board.setName(boardName);
		List<Board> allBoards = trelloMvn.getMemberBoards("me", new Argument("fields", "name"));
		for (Board bd : allBoards) {
			if (bd.getName().equals(boardName)) {
				board = trelloMvn.getBoard(bd.getId());
				List<Member> boardMembers = board.fetchMembers();
				for (Member m : boardMembers) {
					members.add(m);
					System.out.println("Nome: " + m.getFullName() + " | Username: @" + m.getUsername());
				}
			}
		}
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

	/**
	 * Get boardName.
	 */
	public String getBoardName() {
		return boardName;
	}

/////////////////
//Testing
////////////////

	/**
	 * Test.
	 */
	public void teste() {

		List<Board> boards = trelloMvn.getMemberBoards("me", new Argument("fields", "name"));
		for (Board board : boards)
			System.out.println(board.getId() + ":" + board.getName());

//		List<TList> lists = trelloMvn.getBoardLists("614de795e5e8b75fb65a9cdc");
//		for (TList list : lists)
//			System.out.println(list.getId() + ":" + list.getName());

//		List<Card> cards = trelloMvn.getListCards("61a643e38f57036c86f38a72");
//		for (Card card : cards)
//			System.out.println(card.getId() + ":" + card.getName());
	}

//	MAIN DE TESTE
//	
//	public static void main(String[] args) {
//		//TrelloDetails td = new TrelloDetails(null);
//		Trello trelloApi = new TrelloImpl("61d79cb5bcc75c155c7fd74aef6f1b4f", "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118", new ApacheHttpClient());
//		//List <Board> b = trelloApi.getMemberBoards("me", new Argument("fields", "name"));
//		List <Board> boards = trelloApi.getMemberBoards("me", new Argument("fields", "name"));
//		for (Board board : boards) {
//		    board.getName();
//		    board.getId();
//		    System.out.println(board.getId() + ":" + board.getName());
//		    List <TList> lists = trelloApi.getBoardLists(board.getId());
//		    for (TList list : lists) {
//		        System.out.println(list.getId() + ":" + list.getName());
//		        List <Card> cards = trelloApi.getListCards(list.getId());
//		        for (Card card : cards) {
//		            System.out.println(card.getId() + ":" + card.getName());
//		        }
//		    }
//		}
//	}
}