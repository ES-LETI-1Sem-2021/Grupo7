package work;

import java.util.List;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;

import dates.Date;

public class Task implements Times {

	private Card card;
	private List<Hours> hoursList;
	private List<String> membersIDs;     // List<Member> ?
	private List<Label> labels;
	private Date dueDate;
	private List<String> membersUsernames;
	
	public Task(Card card) {
		super();
		this.card = card;
//		this.hours = ; -------------------------------> obter horas do TrelloPlus
		this.membersIDs = card.getIdMembers(); //--------> obter Membros associados à tarefa
		this.labels = card.getLabels();
		this.dueDate = (Date)card.getDue();
//		this.membersUsernames = ; --------------------> lista de membros por username
	}


	public boolean hasTimeSpent() {
		if (getTimeSpent() == 0)
			return false;
		else
			return true;
	}
	
	public double getTimeSpent() {
		double timeSpent = 0;
		for (Hours h : hoursList) {
			timeSpent += h.getTimeSpent();
		}
		return timeSpent;
	}

	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Hours h : hoursList) {
			estimatedTime += h.getTimeEstimated();
		}
		return estimatedTime;
	}

	public Card getCard() {
		return card;
	}

	public List<Hours> getHours() {
		return hoursList;
	}

	public List<String> getMembersIDs() {
		return membersIDs;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public List<String> getMembersUsernames() {              //-----> Resolver problema de listagem: deveria ser de <Member> e não de <String>
//		for (String t : membersIDs) {
//			membersUsernames.add(t.getUsername());
//		}
		return membersUsernames;
	}



	
}