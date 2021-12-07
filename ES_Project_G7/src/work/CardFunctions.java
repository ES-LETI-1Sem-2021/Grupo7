package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import gui.MainWindow;
import timings.Date;
import timings.Times;

public abstract class CardFunctions implements Times {
	private Trello trello = MainWindow.getFrame().getTrelloConnect().getTrello();
	private Card card;
	private Date dueDate;
	private List<Label> labelList;
	private List<String> membersIDs; // List<Member> ?
	private List<Member> members;
	private List<Card> tasklist;
	private double timeSpent;
	private double timeEstimated;

	public CardFunctions() {
		setbacklogtasklist(tasklist);
		for (Card c : tasklist) {
			this.setCard((trello.getCard(c.getId())));
			this.setLabelList((trello.getCard(c.getId())));
			this.setMembersIDs((trello.getCard(c.getId())));
			this.setDueDate((Date) (trello.getCard(c.getId())).getDue());
		}
	}

	public CardFunctions(Card card) {
		this.setCard(card);

		this.setLabelList(card);
		this.setMembersIDs(card); // --------> obter Membros associado
		this.setDueDate((Date) card.getDue());
	}

	///// descobrir funcao pa obter lista de tarefas do backlog
	private void setbacklogtasklist(List<Card> tasklist) {
		// ver comando lista tasks productbacklog pra ja fica este
		tasklist = new ArrayList<>();
	}

	@Override
	public boolean hasTimeSpent() {
		if (getTimeSpent() == 0)
			return false;
		else
			return true;
	}

	@Override
	public double getTimeSpent() {
		return timeSpent;
	}

	@Override
	public double getTimeEstimated() {
		return timeEstimated;
	}

	@Override
	public boolean memberhasTimeSpent(String memberUsername, Card card, String idmember)  {
		return false;
	}

	@Override
	public double membergetTimeSpent(String memberUsername, Card card, String idmember) {
		return 0;
	}

	@Override
	public double membergetTimeEstimated(String memberUsername) {
		return 0;
	}

	/* GETTERS & SETTERS */

	public void setCard(Card card) {

		this.card = trello.getCard(card.getId());
		;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(Card card) {
		this.labelList = trello.getBoardLabels(card.getIdBoard());

	}

	public Card getCard() {
		return card;
	}

	public List<String> getMembersIDs() {
		for (Member m : members)
			membersIDs.add(m.getId());
		return this.membersIDs;
	}

	public void setMembersIDs(Card card) {
		//		for (Member m : trello.getCardMembers(card.getId())) {
		//			this.membersIDs.add(m.getId());
		//		}
		members = trello.getCardMembers(card.getId());
	}

	public double getTimeSpent(Card card) {

		return timeSpent;
	}






}


