package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;

import timings.Times;

public class Sprint implements Times {
	private Trello trello=null ;
	private List<String> membersIDs; // List<Member> ?
	private String sprint;
	private List<Card> taskList;

	public Sprint(String sprint) {
		this.sprint = sprint;
		this.membersIDs = new ArrayList<String>();
		this.taskList = new ArrayList<Card>();
		//iniciar trello
	}

	public String getSprint() {
		return sprint;
	}

	public List<Card> getListtarefa() {
		return taskList;
	}

	private List<String> getMembersIDs() {

		taskList=trello.getListCards(getSprint());
		List<String> newList = new ArrayList<String>();
		for (Card c : this.taskList) {
			Task t= new Task (c);
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
		for (Card c : this.taskList) {
			Task t=new Task (c);
			if (t.hasTimeSpent())
				timeSpent += t.getTimeSpent();
		}
		return timeSpent;
	}

	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Card c: this.taskList) {
			Task t= new Task (c);
			estimatedTime += t.getTimeEstimated();
		}
		return estimatedTime;
	}

	@Override
	public boolean memberhasTimeSpent(String memberUsername) {
		if (membergetTimeSpent(memberUsername) == 0)
			return false;
		else
			return true;
	}

	@Override
	public double membergetTimeSpent(String memberUsername) {
		double timeSpent = 0;
		for (Card c: this.taskList) {
			Task t= new Task (c);
			if (t.hasTimeSpent())
				timeSpent += t.membergetTimeSpent(memberUsername);
		}
		return timeSpent;

	}

	@Override
	public double membergetTimeEstimated(String memberUsername) {
		double estimatedTime = 0;
		for (Card c: this.taskList) {
			Task t= new Task (c);
			estimatedTime += t.membergetTimeEstimated(memberUsername);
		}
		return estimatedTime;

	}

}
