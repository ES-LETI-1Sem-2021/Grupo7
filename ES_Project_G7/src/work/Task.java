package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import data.TrelloConnect;
import gui.MainWindow;
import timings.Date;
import timings.Hours;

public class Task extends CardFunctions {
	private  TrelloConnect trelloconnect=MainWindow.getFrame().getTrelloConnect();
	private Trello trello= trelloconnect.getTrello();
	private Hours hour;
	private Member membro = null;
	private List<Hours> cardhoursList;
	private List<Hours> memberhoursList;
	private List<Hours> cardhoursListestimadas;
	private List<Hours> memberhoursListestimadas;
	private Card card;

	public Task(Card card) {

		super(card);
		//window = MainWindow.getInstance();

	}


	public Member getmembrodocardpeloUsername(String id,Card card) {
		this.card =card;
		for(Member m:	trello.getCardMembers(card.getId()) ){
			if (m.getId().equals(id)){
				membro=m;
			}
		}
		return membro;
	}
	public void getCardHoursList(Card card) {
		this.card =card;
		List<Hours> memberhoursListaux= new ArrayList<>();
		
				for(Member m:	trello.getCardMembers(this.card.getId())) {
					
						memberhoursListaux.add(new Hours (m,this.card));
				}	
			
		
		this.memberhoursList=memberhoursListaux;

	}
	@Override
		public double getTimeSpent() {
		double timeSpent = 0;
			for(Action a: trello.getCardActions(this.getCardId())) {
				if(a.getData().getText()!=null) {
					String data=a.getData().getText();
					if(!data.contains("@global")) {
					String [] auxiliar=data.split("/");
					String [] auxiliar2=auxiliar[0].split("!");
					timeSpent=timeSpent+Double.parseDouble(auxiliar2[1]);
				}
			}
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
	public boolean memberhasTimeSpent(String memberUsername ,Card card,String idmember) {
		this.card =card;
		if (membergetTimeSpent(memberUsername,card,idmember) != 0)
			return true;
		else
			return false;
	}

	public void getHoursList(String memberUsername,Card card,String idmember) {
		this.card =card;
		List<Hours> memberhoursListaux= new ArrayList<>();
				for(Member m:	trello.getCardMembers(this.card.getId())) {
					if(m.getId().equals(idmember))
						memberhoursListaux.add(new Hours (m,this.card));
				}	
			
		this.memberhoursList=memberhoursListaux;

	}

	@Override
	public double membergetTimeSpent(String memberUsername,Card card ,String idmember) {
		this.card =card;
		getHoursList(memberUsername,this.card,idmember);
		double timeSpent = 0;
		for (Hours h : memberhoursList) {


			if (h.getMember().getUsername().equals(memberUsername) ){
     
				timeSpent += h.membergetTimeSpent();

			}}
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
				//if (h.getUtilizador().equals(memberUsername))
				estimatedTime += h.membergetTimeEstimated();
			}
		}
		return estimatedTime;
		

	}
	public static void main(String[] args) throws IOException {

		TrelloConnect trelloconnect = MainWindow.getInstance().getTrelloConnect();
		Trello trello= trelloconnect.getTrello();
		Card c;
		c=trello.getCard("61ad5d6977185c052ba9bca0");
		System.out.println(c.getName());
		Task t=new Task(c);
		for(Member m:	trello.getCardMembers(c.getId())) {
			
		if(m.getUsername().equals("tiagoalmeida01")) {
			System.out.println("Tempo gasto pelo Tiago");
				System.out.println(t.membergetTimeSpent("tiagoalmeida01",c,m.getId()));
		}}
		System.out.println("Tempo Total da Task");
	System.out.println(t.getTimeSpent());
	}


	@Override
	public double getTimeSpent(String sprintName) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean hasTimeSpent(String sprintName) {
		// TODO Auto-generated method stub
		return false;
	}


	


	@Override
	public double membergetTimeSpent(String memberUsername, String idmember, String sprintName) {
		// TODO Auto-generated method stub
		return 0;
	}
}