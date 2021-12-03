package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;

public class ProductBacklog extends CardFunctions {

	private List<Task> taskList;

	public ProductBacklog() {
		super();
		this.taskList = new ArrayList<Task>();
	}

	@Override
	public double getTimeSpent() {
		double timeSpent = 0;
		for (Task t : this.taskList) {
			if (t.hasTimeSpent())
				timeSpent += t.getTimeSpent();
		}
		return timeSpent;
	}

	@Override
	public double getTimeEstimated() {
		double estimatedTime = 0;
		for (Task t : this.taskList) {
			estimatedTime += t.getTimeEstimated();
		}
		return estimatedTime;
	}

	@Override
	public boolean memberhasTimeSpent(String idMember) {
		if (membergetTimeSpent(idMember) == 0)
			return false;
		else
			return true;
	}

	@Override
	public double membergetTimeSpent(String idMember) {
		double timeSpent = 0;
		for (Task t : this.taskList) {
			if (t.hasTimeSpent())
				timeSpent += t.membergetTimeSpent(idMember);
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
