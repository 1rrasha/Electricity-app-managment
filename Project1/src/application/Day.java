package application;
//1210773-Rasha Mansour
import java.time.LocalDate;
import java.util.Objects;

public class Day implements Comparable<Day> {

	// attributes
	private int day;
	private ElectricityRecords electricityRecords;

	// constructors
	public Day(int day) {
		this.day = day;
	}

	public Day(ElectricityRecords electricityRecords, int day) {
		super();
		this.day = day;
		this.electricityRecords = electricityRecords;
	}

	// getters & setters
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public ElectricityRecords getElectricityRecords() {
		return electricityRecords;
	}

	public void setElectricityRecords(ElectricityRecords electricityRecords) {
		this.electricityRecords = electricityRecords;
	}

	public LocalDate getDate() {
		return electricityRecords.getDate();
	}

	// toString method
	@Override
	public String toString() {
		return electricityRecords.toString();
	}

	// equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		return day == other.day;
	}

	// compareTo method
	@Override
	public int compareTo(Day other) {
		return Integer.compare(this.day, other.day);
	}

}
