package application;
//1210773-Rasha Mansour

public class DataElement {
	private Object data; // Object to hold Year, Month, or Day

	public DataElement(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	// to string method
	@Override
	public String toString() {
		if (data instanceof Year) {
			return "Year " + ((Year) data).getYear();
		} else if (data instanceof Month) {
			return "Month " + ((Month) data).getMonth();
		} else if (data instanceof Day) {
			return "Day " + ((Day) data).getDay();
		}
		return "Unknown Data";
	}
}
