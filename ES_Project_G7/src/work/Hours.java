package work;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;

public class Hours implements MemberTimes {
	private Member membro;
	private Card card;
	private double timeSpent;
	private double timeEstimated;

	public Hours(Member membro) {
		this.membro = membro;
		this.timeEstimated = 0;
		this.timeSpent = 0;
	}

	public String getUtilizador() {
		return membro.getId();
	}

	@Override
	public boolean memberhasTimeSpent() {
		return false;
	}

	@Override
	public double membergetTimeSpent() {
		return timeSpent;
	}

	@Override
	public double membergetTimeEstimated() {
		return timeEstimated;
	}

	public String getCardId() {
		return card.getId();
	}

}
