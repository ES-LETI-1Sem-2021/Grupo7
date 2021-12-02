package work;

public class Hours {
	private String utilizador;
	private double timeSpent;
	private double timeEstimate;
	
	public Hours(String utilizador) {
		this.utilizador = utilizador;
		this.timeEstimate = 0;
		this.timeSpent = 0;
		
		
		
		
		
	}
	public String getUtilizador() {
		return utilizador;
	}
	public double getTimeSpent() {
		return timeSpent;
	}
	public double getTimeEstimate() {
		return timeEstimate;
	}
}
