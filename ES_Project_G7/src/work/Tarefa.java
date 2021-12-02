package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.domain.Action.Data;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;

public class Tarefa {

	private List<Hours> hours;
	private List<Member> membros;
	private List<Label> labels;
	private  Data datatarefa;
	private List<String> usernamemembros ;
	
	public Tarefa(String sprint) {
		this.membros=new ArrayList<Member>();
		this.hours = new ArrayList<Hours>();
		this.datatarefa= new Data();
		this.usernamemembros=new ArrayList<String>();
	}


	public List<Hours> getHours() {
		return hours;
	}
	
	
	
	public List<Member> getMembros() {
		return membros;
	}
	
	public List<String> getUsernameMembros() {
			for (Member t : this.membros) {
				usernamemembros.add(t.getUsername());
			}
			return  usernamemembros;
	}
	
	public double getSpentTime() {
		double timeSpent=0;
		for (Hours h : this.hours) {
			timeSpent +=h.getTimeSpent();
		}
		return timeSpent;
	}

	public double getEstimateTime() {
		double estimateTime = 0;
		for (Hours h : this.hours) {
			estimateTime  += h.getTimeEstimate();
		}
		return estimateTime ;
	}
	
	public boolean hasSpentTime() {
		if(getSpentTime()==0) return false;
		else return true;
	}


	public List<Label> getLabels() {
		return labels;
	}