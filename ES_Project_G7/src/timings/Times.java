package timings;

import com.julienvey.trello.domain.Card;

public interface Times {
	boolean hasTimeSpent();

	double getTimeSpent();

	double getTimeEstimated();

	


	double membergetTimeEstimated(String memberUsername);

	

	

	double membergetTimeSpent(String memberUsername, Card card, String idmember);

	boolean memberhasTimeSpent(String memberUsername, Card card, String idmember);

}
