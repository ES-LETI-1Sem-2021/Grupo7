package timings;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;

public class Hours implements MemberTimes {

    private Trello trello = null;
	private Member membro;
	private Card card;
	private double timeSpent;
	private double timeEstimated;

	public Hours(Member membro) {
		this.membro = membro;
		this.timeEstimated = 0;
		this.timeSpent = 0;
		//iniciar trello
	}

	public String getUtilizador() {
		return membro.getId();
	}

	public void setMember(Member member,Card card) {
		this.card=card;
		this.membro=member;
	}

	@Override
	public boolean memberhasTimeSpent() {
		if(timeSpent==0)
			return false;
		else 
			return true;
	}

	@Override
	public double membergetTimeSpent() {
		
		for(Action a: trello.getCard(card.getId()).getActions()) {
			String data=a.getData().getText();
			String [] auxiliar=data.split("/");
			String [] auxiliar2=auxiliar[0].split("!");
			timeSpent=timeSpent+Double.parseDouble(auxiliar2[1]);

		}
		return timeSpent;
	}

	@Override
	public double membergetTimeEstimated() {
		///////////////////////////////////////////////
		//Obter tempo estimado
		for(Action a: trello.getCard(card.getId()).getActions()) {
			String data=a.getData().getText();
			String [] auxiliar=data.split("/");
			String [] auxiliar2=auxiliar[0].split("!");
			timeSpent=timeSpent+Double.parseDouble(auxiliar2[1]);

		}

		return timeEstimated;
	}

	public String getCardId() {
		return card.getId();
	}


}


