package work;

import com.julienvey.trello.domain.Member;

public class Hours implements Times {
	private Member membro;
	private double timeSpent;
	private double timeEstimated;

	public Hours(Member membro) {
		this.membro = membro;
		this.timeEstimated = 0;
		this.timeSpent = 0;
	}

	public String getUtilizador() {
		return membro.getUsername();
	}

	public boolean hasTimeSpent() {
		return false;
	}

	public double getTimeSpent() {
		return timeSpent;
	}

	public double getTimeEstimated() {
		return timeEstimated;
	}

}
