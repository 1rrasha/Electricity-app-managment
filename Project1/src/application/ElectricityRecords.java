package application;
//1210773-Rasha Mansour
import java.time.LocalDate;
import java.util.*;

public class ElectricityRecords implements Comparable<ElectricityRecords> {

	// attributes
	private LocalDate Date;
	private double Israeli_Lines_MWs;
	private double Gaza_Power_Plant_MWs;
	private double Egyptian_Lines_MWs;
	private double Total_daily_Supply_available_in_MWs;
	private double Overall_demand_in_MWs;
	private double Power_Cuts_hours_day_400mg;
	private double Temp;

	// constructors
	public ElectricityRecords() {
		super();
	}

	public ElectricityRecords(LocalDate date, double israeli_Lines_MWs, double gaza_Power_Plant_MWs,
			double egyptian_Lines_MWs, double total_daily_Supply_available_in_MWs, double overall_demand_in_MWs,
			double power_Cuts_hours_day_400mg, double temp) {
		super();
		Date = date;
		Israeli_Lines_MWs = israeli_Lines_MWs;
		Gaza_Power_Plant_MWs = gaza_Power_Plant_MWs;
		Egyptian_Lines_MWs = egyptian_Lines_MWs;
		Total_daily_Supply_available_in_MWs = total_daily_Supply_available_in_MWs;
		Overall_demand_in_MWs = overall_demand_in_MWs;
		Power_Cuts_hours_day_400mg = power_Cuts_hours_day_400mg;
		Temp = temp;
	}

	// getters & setters
	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		this.Date = Date;
	}

	public double getIsraeli_Lines_MWs() {
		return Israeli_Lines_MWs;
	}

	public void setIsraeli_Lines_MWs(int israeli_Lines_MWs) {
		Israeli_Lines_MWs = israeli_Lines_MWs;
	}

	public double getGaza_Power_Plant_MWs() {
		return Gaza_Power_Plant_MWs;
	}

	public void setGaza_Power_Plant_MWs(double gaza_Power_Plant_MWs) {
		Gaza_Power_Plant_MWs = gaza_Power_Plant_MWs;
	}

	public double getEgyptian_Lines_MWs() {
		return Egyptian_Lines_MWs;
	}

	public void setEgyptian_Lines_MWs(int egyptian_Lines_MWs) {
		Egyptian_Lines_MWs = egyptian_Lines_MWs;
	}

	public double getTotal_daily_Supply_available_in_MWs() {
		return Total_daily_Supply_available_in_MWs;
	}

	public void setTotal_daily_Supply_available_in_MWs(double total_daily_Supply_available_in_MWs) {
		Total_daily_Supply_available_in_MWs = total_daily_Supply_available_in_MWs;
	}

	public double getOverall_demand_in_MWs() {
		return Overall_demand_in_MWs;
	}

	public void setOverall_demand_in_MWs(double overall_demand_in_MWs) {
		Overall_demand_in_MWs = overall_demand_in_MWs;
	}

	public double getPower_Cuts_hours_day_400mg() {
		return Power_Cuts_hours_day_400mg;
	}

	public void setPower_Cuts_hours_day_400mg(double power_Cuts_hours_day_400mg) {
		Power_Cuts_hours_day_400mg = power_Cuts_hours_day_400mg;
	}

	public double getTemp() {
		return Temp;
	}

	public void setTemp(double temp) {
		Temp = temp;
	}

	// toString method
	@Override
	public String toString() {
		return "\"ElectricityRecords{Day [Date=" + Date + ", Israeli_Lines_MWs=" + Israeli_Lines_MWs
				+ ", Gaza_Power_Plant_MWs=" + Gaza_Power_Plant_MWs + ", Egyptian_Lines_MWs=" + Egyptian_Lines_MWs
				+ ", Total_daily_Supply_available_in_MWs=" + Total_daily_Supply_available_in_MWs
				+ ", Overall_demand_in_MWs=" + Overall_demand_in_MWs + ", Power_Cuts_hours_day_400mg="
				+ Power_Cuts_hours_day_400mg + ", Temp=" + Temp + "]}";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ElectricityRecords other = (ElectricityRecords) obj;
		return Date.equals(other.Date);
	}

	@Override
	public int compareTo(ElectricityRecords o) {
		return getDate().compareTo(o.getDate());
	}

}
