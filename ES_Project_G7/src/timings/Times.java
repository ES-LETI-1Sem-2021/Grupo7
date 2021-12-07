package timings;

import com.julienvey.trello.domain.Card;

public interface Times {
	boolean hasTimeSpent();

	

	double getTimeEstimated();

	


	double membergetTimeEstimated(String memberUsername);

	

	

	double membergetTimeSpent(String memberUsername, Card card, String idmember);

	boolean memberhasTimeSpent(String memberUsername, Card card, String idmember);

	double getTimeSpent(String sprintName);
	double getTimeSpent();



	boolean hasTimeSpent(String sprintName);



	



	double membergetTimeSpent(String memberUsername, String idmember, String sprintName);



	



	
}
