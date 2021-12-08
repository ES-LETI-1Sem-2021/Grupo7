package data;

import appearence.*;
import appearence.TextField;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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

	private List<Member> members = new ArrayList<>();
	private List<TList> sprints = new ArrayList<>();
	private List<Card> cardsSprint = new ArrayList<>();

	// DataTest - Tiago
	private String accessKey = "61d79cb5bcc75c155c7fd74aef6f1b4f";
	private String accessToken = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
	private String boardName = "ES Project";

/////////////////
//Constructors
////////////////

	/**
	 * Create TrelloConnect without any previous Layout made.
	 * 
	 * @param pane Uses a <code>Container</code> as base to define where the
	 *             implementation of the <code>getData()</code> functions.
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
	 * @param layout Uses a predefined <code>Layout</code> as a parameter, to define
	 *               where will be added the <code>getData()</code> functions.
	 * @throws IOException
	 */
	public TrelloConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		board.setName(boardName);
		connected = false;
	}

/////////////////
//Implementation
////////////////

	/**
	 * Get Trello data when there's no previous layout.
	 * 
	 * @param pane Uses a <code>Container</code> as base to define where will be
	 *             created the new <code>Layout</code> (which is the layout
	 *             instance).
	 * @throws IOException
	 */
	@Override
	public void getDataLayout(Container pane) throws IOException {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get Trello data when exists a previous layout.
	 * 
	 * @param layout Uses a <code>Layout</code> as base to define where will be
	 *               added the <code>getData()</code> function and defines it as the
	 *               layout instance.
	 * @throws IOException
	 */
	@Override
	public void getDataLayout(Layout layout) throws IOException {
		getData();
		this.layout = layout;

		if (layout.getLayoutType() == LayoutType.LAYOUT_SPRING)
			this.layout.addToSpringLayout(title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get Trello data from user in order to establish connection.
	 * 
	 * @throws IOException
	 */
	@Override
	public void getData() throws IOException {
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
//		accessKey = textFields[0].getText();
//		accessToken = textFields[1].getText();
//		boardName = textFields[2].getText();
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
	 * Get description from Sprint Planning Meeting.
	 * 
	 * @param sprint Defines from which sprint we intend to get the planning
	 *               description.
	 * @return
	 */
	public String getSprintPlanningDesc(TList sprint) {
		Card c = getCardFromSprint("Sprint Planning", sprint.getName());
		if (c.getDesc() != "")
			return c.getDesc();
		return "ERROR: No description found!!";
	}

	/**
	 * Get description from Sprint Review Meeting.
	 * 
	 * @param sprint Defines from which sprint we intend to get the review meeting
	 *               description.
	 * @return
	 */
	public String getSprintReviewDesc(TList sprint) {
		Card c = getCardFromSprint(sprint.getName() + " - Sprint Review", "Sprint Review");
		if (c.getDesc() != "")
			return c.getDesc();
		return "ERROR: No description found!!";
	}

	/**
	 * Get description from Sprint Retrospective Meeting.
	 * 
	 * @param sprint Defines from which sprint we intend to get the retrospective
	 *               meeting description.
	 * @return
	 */
	public String getSprintRetrospectiveDesc(TList sprint) {
		Card c = getCardFromSprint(sprint.getName() + " - Sprint Retrospective", "Sprint Retrospective");
		if (c.getDesc() != "")
			return c.getDesc();
		return "ERROR: No description found!!";

	}

	/**
	 * Get Product Backlog items from a sprint.
	 * @param sprint
	 * @return
	 */
	public String[] getPBEachSprint(TList sprint){
		String[] pb = new String[500];
		int i=0;
		for(Card c : listCardsSprint(sprint)) {
			if(c.getName().contains("#PB")) {
				String [] vec = c.getName().split(" ");
				String s = vec[vec.length-1];
				pb[i]=s;
				i++;
			}
		}
		return pb; 
	}
	
	/**
	 * Get Members from Trello.
	 */
	public String getMembers() {
		String result = "";
		List<Board> allBoards = trelloMvn.getMemberBoards("me", new Argument("fields", "name"));
		for (Board bd : allBoards)
			if (bd.getName().equals(boardName)) {
				board = trelloMvn.getBoard(bd.getId());
				List<Member> boardMembers = board.fetchMembers();
				for (Member m : boardMembers) {
					members.add(m);
					result = result + "\nNome: " + m.getFullName() + " | Username: @" + m.getUsername();
				}
				return result;
			}
		return "ERROR: No members founds!";
	}

	/**
	 * Updates the list of Trello's columns (Lists).
	 */
	private void getLists() {
		List<Board> allBoards = trelloMvn.getMemberBoards("me", new Argument("fields", "name"));
		for (Board bd : allBoards)
			if (bd.getName().equals(boardName)) {
				board = trelloMvn.getBoard(bd.getId());
				List<TList> sprintsaux = trelloMvn.getBoardLists(board.getId());
				for (TList list : sprintsaux)
					sprints.add(list);
				
			}
	}

	/**
	 * Returns a specific sprint.
	 * 
	 * @param sprintName Determines which one is the sprint, according to the name
	 *                   of the list.
	 * @return
	 */
	public TList getSprint(String sprintName) {
		getLists();
		for (TList list : sprints)
			if (list.getName().contains(sprintName))
				return list;
		return null;
	}

	/**
	 * Returns the list of cards for ones sprint.
	 * 
	 * @param sprint Determines from which sprint it will get the list of cards.
	 * @return
	 */
	public List<Card> listCardsSprint(TList sprint) {
		List<Card> cards = trelloMvn.getListCards(sprint.getId());
		for (Card card : cards)
			cardsSprint.add(card);
		return cardsSprint;
	}

	/**
	 * Returns a specific card from a specific sprint (TList).
	 * 
	 * @param cardName   Determines which one is the card.
	 * @param sprintName Determines from which sprint.
	 * @return
	 */
	public Card getCardFromSprint(String cardName, String sprintName) {
		List<Card> aux = listCardsSprint(getSprint(sprintName));
		for (Card c : aux)
			if (c.getName().contains(cardName))
				return c;
		return null;
	}

	/**
	 * Get all the sprint lists.
	 * @return
	 */
	public List<String> getAllSprints() {
		getLists();
		List<String> result = new ArrayList<>();
		// List<TList> aux = list
		for (TList tl : sprints) {
			// System.out.println("HUGO " + tl.getName());
			String[] aux = tl.getName().split(" ");
			if (aux.length > 1)
				if (aux[1].contains("Sprint"))
					if (!result.contains(tl.getName()))
						result.add(tl.getName());
		}
		return result;
	}

	/**
	 * Returns the starting date of the sprint.
	 * 
	 * @param sprintName Determines from which sprint.
	 * @return
	 */
	private Date getSprintStartDate(TList sprintName) {
		String cardName = sprintName.getName() + " - Sprint Planning";
		Card c = getCardFromSprint(cardName, sprintName.getName());
		return c.getDue();
	}

	/**
	 * Returns the ending date of the sprint.
	 * 
	 * @param sprintName Determines from which sprint.
	 * @return
	 */
	private Date getSprintEndDate(TList sprintName) {
		String cardName = sprintName.getName() + " - Sprint Review";
		Card c = getCardFromSprint(cardName, "Sprint Review");
		return c.getDue();
	}

	/**
	 * Returns the information regarding to starting and ending date of the sprint.
	 * 
	 * @param sprintName Determines from which sprint.
	 * @return
	 */
	public String [] getSprintDates(TList sprintName) {
		String[] dates = new String[2];
		dates[0] = "Sprint started at " + getSprintStartDate(sprintName);
		dates[1] = "Sprint ended at " + getSprintEndDate(sprintName);
		return dates;
	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get Trello API.
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Get boardName.
	 * 
	 * @return
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

//		List<Board> boards = trelloMvn.getMemberBoards("me", new Argument("fields", "name"));
//		for (Board board : boards)
//			System.out.println(board.getId() + ":" + board.getName());

//		List<TList> lists = trelloMvn.getBoardLists("614de795e5e8b75fb65a9cdc");
//		for (TList list : lists)
//			System.out.println(list.getId() + ":" + list.getName());

//		List<Card> cards = trelloMvn.getListCards("61a643e38f57036c86f38a72");
//		for (Card card : cards)
//			System.out.println(card.getId() + ":" + card.getName());
	}
}