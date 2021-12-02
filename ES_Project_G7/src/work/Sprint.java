package work;

import java.util.ArrayList;
import java.util.List;
import com.julienvey.trello.domain.Member;

public class Sprint implements Times {
	private List<String> membersIDs;     // List<Member> ?
	private String sprint;
	private List<Task> listtarefa;

	public Sprint(String sprint) {
		this.sprint = sprint;
		this.membersIDs = new ArrayList<String>();
		this.listtarefa = new ArrayList<Task>();
	}

	public String getSprint() {
		return sprint;
	}

	public List<Task> getListtarefa() {
		return listtarefa;
	}

	private List<String> getMembersIDs() {
		List<String> newList = new ArrayList<String>();
		for (Task t : this.listtarefa) {
			newList.addAll(t.getMembersIDs());
		}
		this.membersIDs = newList;
		return membersIDs;
	}

	public boolean hasTimeSpent() {
		if (getTimeSpent() == 0)
			return false;
		else
			return true;
	}

	public double getTimeSpent() {
		double timeSpent = 0;
		for (Task t : this.listtarefa) {
			if (t.hasTimeSpent())
				timeSpent += t.getTimeSpent();
		}
		return timeSpent;
	}

	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Task t : this.listtarefa) {
			estimatedTime += t.getTimeEstimated();
		}
		return estimatedTime;
	}

}
