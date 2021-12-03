package work;

import java.util.ArrayList;
import java.util.List;
import com.julienvey.trello.domain.Member;

import timings.Times;

public class Sprint implements Times {
	private List<String> membersIDs; // List<Member> ?
	private String sprint;
	private List<Task> taskList;

	public Sprint(String sprint) {
		this.sprint = sprint;
		this.membersIDs = new ArrayList<String>();
		this.taskList = new ArrayList<Task>();
	}

	public String getSprint() {
		return sprint;
	}

	public List<Task> getListtarefa() {
		return taskList;
	}

	private List<String> getMembersIDs() {
		List<String> newList = new ArrayList<String>();
		for (Task t : this.taskList) {
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
		for (Task t : this.taskList) {
			if (t.hasTimeSpent())
				timeSpent += t.getTimeSpent();
		}
		return timeSpent;
	}

	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Task t : this.taskList) {
			estimatedTime += t.getTimeEstimated();
		}
		return estimatedTime;
	}

	@Override
	public boolean memberhasTimeSpent(String Idmember) {
		if (membergetTimeSpent(Idmember) == 0)
			return false;
		else
			return true;
	}

	@Override
	public double membergetTimeSpent(String Idmember) {
		double timeSpent = 0;
		for (Task t : this.taskList) {
			if (t.hasTimeSpent())
				timeSpent += t.membergetTimeSpent(Idmember);
		}
		return timeSpent;

	}

	@Override
	public double membergetTimeEstimated(String idMember) {
		double estimatedTime = 0;
		for (Task t : this.taskList) {
			estimatedTime += t.membergetTimeEstimated(idMember);
		}
		return estimatedTime;

	}

}
