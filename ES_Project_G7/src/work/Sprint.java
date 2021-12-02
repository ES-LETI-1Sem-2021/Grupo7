package work;

import java.util.ArrayList;
import java.util.List;

import com.julienvey.trello.domain.Member;


public class Sprint {
	private List<Member> membros;
	private String sprint;
private List<Tarefa> listtarefa ;
	public Sprint(String sprint) {
		this.sprint = sprint;
		this.membros=new ArrayList<Member>();
		this.listtarefa= new ArrayList<Tarefa>();
	}

	public String getSprint() {
		return sprint;
	}

	
	public List<Tarefa> getListtarefa() {
		return listtarefa;
	}
	
	private double getSpentTime() {
		double timeSpent=0;
		for (Tarefa t : this.listtarefa) {
			if(t.hasSpentTime())
			timeSpent +=t.getSpentTime();
		}
		return timeSpent;
	}
	
	private double getEstimateTime() {
		double estimateTime = 0;
		for (Tarefa t : this.listtarefa) {
			estimateTime  += t.getEstimateTime();
		}
		return estimateTime ;
	}
	
	private boolean hasSpentTime() {
		if(getSpentTime()==0) return false;
		else return true;
	}
	private List<Member> getMembros () {
		List<Member> newList = new ArrayList<Member>();
		for (Tarefa t : this.listtarefa) {
			newList.addAll(t.getMembros());
		}
		this.membros=newList;
		return  membros;
	}
	
}
