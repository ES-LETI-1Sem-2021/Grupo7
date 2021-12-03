package work;

public interface Times {
	boolean hasTimeSpent();
	double getTimeSpent();
	double getTimeEstimated();
	boolean memberhasTimeSpent(String idMmber);
	double membergetTimeSpent(String idMember);
	double membergetTimeEstimated(String idMember);
}
