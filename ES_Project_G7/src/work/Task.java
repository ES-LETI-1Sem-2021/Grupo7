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
	private List<Hours> cardhoursList;
	private List<Hours> memberhoursList;// ver como obter lista de horas
	private List<String> membersUsernames;
	// private Map<Member,Hours> timeSpentPerMember = new HashMap<>();

	public Task(Card card) {
		super(card);
		// this.hours = ; -------------------------------> obter horas do TrelloPlus
		// this.membersUsernames = ; --------------------> lista de membros por username
	}

	
	public Member obtermembrodocardpeloUsername(String id,Card card) {
		Trello trello = null;
		Member membro = null;
		for(Member m:	trello.getCardMembers(card.getId()) ){
			if (m.getId().equals(id)){
				membro=m;
			}
		}
		return membro;
	}
	public void getCardHoursList(Card card) {
		Trello trello = null;
		List<String> membros=trello.getCard(card.getId()).getIdMembers();
		for(String id:	membros) {
		String memberUsername=obtermembrodocardpeloUsername(id,card).getUsername();
			hour.setMember(trello.getMemberInformation(memberUsername),trello.getCard(card.getId()));
			cardhoursList.add(hour);
		}}

	@Override
	public double getTimeSpent() {

		double timeSpent = 0;
		getCardHoursList(this.getCard());
		for (Hours h : cardhoursList) {
			if (h.getCardId().equals(this.getCard().getId()))
				timeSpent += h.membergetTimeSpent();
		}
		return timeSpent;
	}

	@Override
	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Hours h : cardhoursList) {
			if (h.getCardId().equals(this.getCard().getId()))
				estimatedTime += h.membergetTimeEstimated();
		}
		return estimatedTime;
	}

	public String getCardId() {
		return getCard().getId();
	}



	public List<String> getMembersUsernames() { // -----> Resolver problema de listagem: deveria ser de <Member> e não
		// de <String>
		// for (String t : membersIDs) {
		// membersUsernames.add(t.getUsername());
		// }
		return membersUsernames;
	}

	@Override
	public boolean memberhasTimeSpent(String memberUsername) {
		if (membergetTimeSpent(memberUsername) != 0)
			return true;
		else
			return false;
	}
	
	public void getHoursList(String memberUsername,Card card) {
		Trello trello = null;
		for(String id:	trello.getCard(card.getId()).getIdMembers()) {
			if(trello.getMemberInformation(memberUsername).getId().equals(id)) {
				hour.setMember(trello.getMemberInformation(memberUsername),trello.getCard(card.getId()));
				memberhoursList.add(hour);
			}}
	}

	@Override
	public double membergetTimeSpent(String memberUsername) {
		Trello trello = null;
		getHoursList(memberUsername,this.getCard());
		double timeSpent = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId())) {
				if (h.getUtilizador().equals(trello.getMemberInformation(memberUsername).getId()))
					timeSpent += h.membergetTimeSpent();
			}
		}
		return timeSpent;
	}

	@Override
	public double membergetTimeEstimated(String idMember) {
		//getHoursListestimadas(membro do id,this.getCard);
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

