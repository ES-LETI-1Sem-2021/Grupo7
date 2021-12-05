package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;

import timings.Date;
import timings.Times;

public abstract class CardFunctions implements Times {

	private Card card;
	private Date dueDate;
	private List<Label> labelList;
	private List<String> membersIDs; // List<Member> ?

	private double timeSpent;
	private double timeEstimated;

	public CardFunctions() {
		this.setLabelList(new ArrayList<Label>());
	}

	public CardFunctions(Card card) {
		this.setCard(card);
		this.setLabelList(card.getLabels());
		this.setMembersIDs(card.getIdMembers()); // --------> obter Membros associado
		this.setDueDate((Date) card.getDue());
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
	public boolean memberhasTimeSpent(String imemberUsername) {
		return false;
	}

	@Override
	public double membergetTimeSpent(String memberUsername) {
		return 0;
	}

	@Override
	public double membergetTimeEstimated(String memberUsername) {
		return 0;
	}

	/* GETTERS & SETTERS */

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	public List<String> getMembersIDs() {
		return membersIDs;
	}

	public void setMembersIDs(List<String> membersIDs) {
		this.membersIDs = membersIDs;
	}
}
