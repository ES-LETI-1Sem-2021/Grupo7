package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;

import data.TrelloConnect;
import gui.MainWindow;

public class ProductBacklog extends CardFunctions {
	private  TrelloConnect trelloconnect=MainWindow.getFrame().getTrelloConnect();
	private Trello trello= trelloconnect.getTrello();
	private List<Card> taskList;


	public ProductBacklog() {
		super ();	
		
	}
	public void getListtarefa(String sprintName) {
		this.taskList=trelloconnect.listCardsSprint(trelloconnect.getSprint(sprintName));
	}
	@Override
	public double getTimeSpent() {
		double timeSpent = 0;
	//	getListtarefa(");
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
	public boolean memberhasTimeSpent(String memberUsername, Card card, String idmember){

		if (membergetTimeSpent(memberUsername,card,idmember) == 0)
			return false;
		else
			return true;
	}

	@Override
	public double  membergetTimeSpent(String memberUsername, Card card, String idmember) {
		double timeSpent = 0;
		for (Card c : this.taskList) {
			Task t = new Task(c);
			if (t.hasTimeSpent())
				timeSpent += t.membergetTimeSpent(memberUsername,card,idmember);
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
