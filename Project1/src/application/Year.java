package application;
//1210773-Rasha Mansour
public class Year implements Comparable<Year> {

	// attributes
	private int year;
	private SingleLinkedList<Month> list;

	// constructor
	public Year(int year) {
		this.year = year;
		this.list = new SingleLinkedList<>();
	}

	// getters & setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public SingleLinkedList<Month> getList() {
		return list;
	}

	public void setList(SingleLinkedList<Month> list) {
		this.list = list;
	}

	// compare to method
	@Override
	public int compareTo(Year otherYear) {
		return Integer.compare(this.year, otherYear.year);
	}

	// equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Year other = (Year) obj;
		return year == other.year;
	}

	// to string method
	@Override
	public String toString() {
		return String.valueOf(year);
	}
}
