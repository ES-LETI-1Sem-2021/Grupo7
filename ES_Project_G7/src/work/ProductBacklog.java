package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;

public class ProductBacklog extends CardFunctions {

	private List<Card> taskList;
	
	private Trello trello = null;
	public ProductBacklog() {
		super ();	
		//iniciar trello
	}

	@Override
	public double getTimeSpent() {
		double timeSpent = 0;
		for (Card c : this.taskList) {
			Task t = new Task(c);
			if (t.hasTimeSpent())
				timeSpent += t.getTimeSpent();
		}
		return timeSpent;
	}

	@Override
	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Card c : this.taskList) {
			Task t = new Task(c);
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
		for (Card c : this.taskList) {
			Task t = new Task(c);
			if (t.hasTimeSpent())
				timeSpent += t.membergetTimeSpent(memberUsername);
		}
		return timeSpent;

	}

	@Override
	public double membergetTimeEstimated(String memberUsername) {
		double estimatedTime = 0;
		for (Card c: this.taskList) {
			Task t = new Task(c);
			estimatedTime += t.membergetTimeEstimated(memberUsername);
		}
		return estimatedTime;

	}
}
