package application;
//1210773-Rasha Mansour
public class ElectricityStats {
	// attributes
	private double sum;
	private double avg;
	private double min;
	private double max;

	// constructors
	public ElectricityStats() {
		// Default constructor
	}

	public ElectricityStats(double sum, double avg, double min, double max) {
		this.sum = sum;
		this.avg = avg;
		this.min = min;
		this.max = max;
	}

	// getters &setters
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	// toString method
	@Override
	public String toString() {
		return "ElectricityStats{" + "sum=" + sum + ", avg=" + avg + ", min=" + min + ", max=" + max + '}';
	}
}
