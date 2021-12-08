package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import appearence.Layout;
import gui.MainWindow;

class TrelloConnectJUnitCase {

	private TrelloConnect trelloconnect;
	private Trello trello;
	private String accessKey;
	private String accessToken;
	private TList sprint;

	@BeforeEach
	void setUp() throws Exception {
		accessKey = "61d79cb5bcc75c155c7fd74aef6f1b4f";
		accessToken = "c9802440801393a957373bf718d042ff7d4083befa43681de8d93f56282cc118";
		TrelloConnect trelloconnect = MainWindow.getInstance().getTrelloConnect();
		trello = trelloconnect.getTrello();
	}

	@Test
	public void testConnectTo() {
		Trello trello_test = new TrelloImpl(accessKey, accessToken, new ApacheHttpClient());
		assertNotNull(trello_test);
		assertEquals(trello, trello_test);
	}

	@Test
	public void testGetMembers() {
		assertNotNull(trelloconnect.getMembers());
		String members1 = trelloconnect.getMembers();
		String members2 = "Nome: Gabriel Albuquerque Monteiro | Username: @gabrielalbuquerquemonteiro\r\n"
				+ "Nome: Hugo Alexandre Silva Santos | Username: @hugoalexandresilvasantos\r\n"
				+ "Nome: Mara Alves | Username: @maralvess\r\n" + "Nome: Tiago Almeida | Username: @tiagoalmeida01";
		assertEquals(members1, members2);
	}

	@Test
	public void testGetTrello() {
		assertNotNull(trelloconnect.getTrello());
	}
}