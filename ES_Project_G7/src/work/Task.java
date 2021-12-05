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
	
	private Trello trello = null;
	private Hours hour;
	private Member membro = null;
	private List<Hours> cardhoursList;
	private List<Hours> memberhoursList;
	private List<Hours> cardhoursListestimadas;
	private List<Hours> memberhoursListestimadas;

	public Task(Card card) {
		super(card);
		//iniciar trello
		
	}


	public Member getmembrodocardpeloUsername(String id,Card card) {
		for(Member m:	trello.getCardMembers(card.getId()) ){
			if (m.getId().equals(id)){
				membro=m;
			}
		}
		return membro;
	}
	public void getCardHoursList(Card card) {
		List<String> membros=trello.getCard(card.getId()).getIdMembers();
		for(String id:	membros) {
			String memberUsername=getmembrodocardpeloUsername(id,card).getUsername();
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
	
	
	//////////////////////////////////////////////////////////////////
     ///////public void getCardHoursListestimated(this.getCard(Card card) {}
	
	
	
	
	@Override
	public double getTimeEstimated() {
		///////getCardHoursListestimated(this.getCard());
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




	@Override
	public boolean memberhasTimeSpent(String memberUsername) {
		if (membergetTimeSpent(memberUsername) != 0)
			return true;
		else
			return false;
	}

	public void getHoursList(String memberUsername,Card card) {

		for(String id:	trello.getCard(card.getId()).getIdMembers()) {
			if(trello.getMemberInformation(memberUsername).getId().equals(id)) {
				hour.setMember(trello.getMemberInformation(memberUsername),trello.getCard(card.getId()));
				memberhoursList.add(hour);
			}}
	}

	@Override
	public double membergetTimeSpent(String memberUsername) {

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
	//////////////////////////////////////////////
	//public void getHoursListestimadas(String memberUsername,Card card) {

	@Override
	public double membergetTimeEstimated(String memberUsername) {
		//getHoursListestimadas(membro do id,this.getCard);
		double estimatedTime = 0;
		for (Hours h : memberhoursList) {
			if (h.getCardId().equals(this.getCard().getId())) {
				if (h.getUtilizador().equals(memberUsername))
					estimatedTime += h.membergetTimeEstimated();
			}
		}
		return estimatedTime;

	}



}

