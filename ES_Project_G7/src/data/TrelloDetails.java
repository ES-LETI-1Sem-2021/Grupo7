package data;

import java.awt.BorderLayout;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.Membership;
import com.julienvey.trello.domain.TList;
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
	private String login = "5a7b15cf57b33466925669791349d0bc";
	private String accessToken = "64243c21c5d74c82d9036439e356a52575f2b91a11f01a93e2449a8aeacf3621";
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

		connectTo(this.login, this.accessToken);
//		connectTo(login.getText(), accessToken.getText());

	}

	/**
	 * Establish connection to Trello.
	 * 
	 * ERRO A VERIFICAR.
	 */
	public void connectTo(String login, String accessToken) {
		trelloMvn = new TrelloImpl(login, accessToken, new TrelloHttpClient() {
			
			public <T> T putForObject(String url, Object body, Class<T> responseType, String... params) {
				return null;
			}
			
			public <T> T postForObject(String url, Object body, Class<T> responseType, String... params) {
				return null;
			}
			
			public URI postForLocation(String url, Object body, String... params) {
				return null;
			}
			
			public <T> T get(String url, Class<T> responseType, String... params) {
				return null;
			}
			
			public <T> T delete(String url, Class<T> responseType, String... params) {
				return null;
			}
		});
		getMembers();
	}

	/**
	 * Get Trello API.
	 */
	private Trello getTrello() {
		return trelloMvn;
	}

	/**
	 * Get members from Trello Board.
	 */
	public void getMembers() {
		Board board;
//		Member host = trelloMvn.getMemberInformation(login);
		List<Board> member = trelloMvn.getMemberBoards("gabrielalbuquerquemonteiro"); 
		for (Board quadro : member) {
			System.out.println(quadro.getName() + "-" + quadro.getId());
			board = trelloMvn.getBoard(quadro.getId());
			List <Membership> boardMembers = board.getMemberships();
			ArrayList <Member> members = new ArrayList<Member>();
			for (Membership ms : boardMembers) {
				Member m = new Member();
				m.setId(ms.getId());
				System.out.println(m.getEmail());
				members.add(m);
			}
//			List<TList> lists = board.fetchLists();
//			for (TList lista : lists) {
//				System.out.println(lista.getName() + "- " + lista.getId() + "-" + lista.getIdBoard());
//			}
		}
	}

	public void getAPI() {
		getTrello();
	}

}
