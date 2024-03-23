package application;
//1210773-Rasha Mansour
import java.util.Objects;

public class Month implements Comparable<Month> {
	// attributes
	private int month;
	private SingleLinkedList<Day> days;

	// constructor
	public Month(int month) {
		this.month = month;
		this.days = new SingleLinkedList<>();
	}

	// getters & setters
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public SingleLinkedList<Day> getDays() {
		return days;
	}

	public void setDays(SingleLinkedList<Day> days) {
		this.days = days;
	}

	// compare to method
	@Override
	public int compareTo(Month otherMonth) {
		return Integer.compare(this.month, otherMonth.month);
	}

	// equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Month other = (Month) obj;
		return month == other.month;
	}

	// to string method
	@Override
	public String toString() {
		return String.valueOf(month);
	}

}
