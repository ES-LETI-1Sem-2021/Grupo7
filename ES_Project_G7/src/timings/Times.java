package timings;

public interface Times {
	boolean hasTimeSpent();

	double getTimeSpent();

	double getTimeEstimated();

	boolean memberhasTimeSpent(String memberUsername);

	double membergetTimeSpent(String memberUsername);

	double membergetTimeEstimated(String memberUsername);
}
