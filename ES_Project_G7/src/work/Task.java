package work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;

import timings.Date;
import timings.Hours;

public class Task extends CardFunctions {
    private Hours hour;
	private List<Hours> memberhoursList;// ver como obter lista de horas
	private List<String> membersUsernames;
	// private Map<Member,Hours> timeSpentPerMember = new HashMap<>();

	public Task(Card card) {
		super(card);
		// this.hours = ; -------------------------------> obter horas do TrelloPlus
		// this.membersUsernames = ; --------------------> lista de membros por username
	}

	@Override
	public double getTimeSpent() {
		double timeSpent = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId()))
				timeSpent += h.membergetTimeSpent();
		}
		return timeSpent;
	}

	@Override
	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId()))
				estimatedTime += h.membergetTimeEstimated();
		}
		return estimatedTime;
	}

	public String getCardId() {
		return getCard().getId();
	}

	public List<Hours> getHoursList(Member member,Card card) {
		Trello trello = null;
		hour.setMember(member,card);
		List<Hours> newmemberhoursList=new ArrayList<Hours>();
		
		
	this.memberhoursList=newmemberhoursList;
	return memberhoursList;
	}

	public List<String> getMembersUsernames() { // -----> Resolver problema de listagem: deveria ser de <Member> e não
												// de <String>
		// for (String t : membersIDs) {
		// membersUsernames.add(t.getUsername());
		// }
		return membersUsernames;
	}

	@Override
	public boolean memberhasTimeSpent(String idMember) {
		if (membergetTimeSpent(idMember) != 0)
			return true;
		else
			return false;
	}

	@Override
	public double membergetTimeSpent(String idMember) {
		
		double timeSpent = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId())) {
				if (h.getUtilizador().equals(idMember))
					timeSpent += h.membergetTimeSpent();
			}
		}
		return timeSpent;
	}

	@Override
	public double membergetTimeEstimated(String idMember) {
		double estimatedTime = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId())) {
				if (h.getUtilizador().equals(idMember))
					estimatedTime += h.membergetTimeEstimated();
			}
		}
		return estimatedTime;

	}
	
	}

