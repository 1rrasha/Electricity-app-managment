package application;

//1210773-Rasha Mansour
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

public class Services {

	// attributes
	private DoubleLinkedList<Year> years;
	private SingleLinkedList<ElectricityRecords> recordsList;

	// constructor
	public Services() {
		years = new DoubleLinkedList<>();
		this.recordsList = new SingleLinkedList<>();
	}

	// setters & getters
	public DoubleLinkedList<Year> getYears() {
		return years;
	}

	public void setYears(DoubleLinkedList<Year> years) {
		this.years = years;
	}

	// methods

	// search
	public ElectricityRecords search(LocalDate date) {
		int yearValue = date.getYear();
		Year year = years.find(new Year(yearValue));

		if (year != null) {

			SingleLinkedList<Month> months = year.getList();
			int monthValue = date.getMonthValue();
			Month month = months.find(new Month(monthValue));
			System.out.println("Found Year: " + yearValue);

			if (month != null) {
				System.out.println("Found month: " + monthValue);

				SingleLinkedList<Day> days = month.getDays();
				int dayValue = date.getDayOfMonth();
				Day day = days.find(new Day(dayValue));
				System.out.println("Found Day: " + dayValue);
				days.print();
				if (day != null) {

					return day.getElectricityRecords();
				}
			}
		}

		return null; // Not found
	}

	// add
	public void addElectricityInfo(ElectricityRecords electricityInfo) {
		int yearValue = electricityInfo.getDate().getYear();
		Year yearToFind = new Year(yearValue);
		Year year = years.find(yearToFind);

		if (year == null) {
			// If the year is not found, create a new Year object
			year = new Year(yearValue);
			years.addSorted(year);
		}

		int monthValue = electricityInfo.getDate().getMonthValue();
		Month monthToFind = new Month(monthValue);
		Month month = year.getList().find(monthToFind);

		if (month == null) {
			// If the month is not found, create a new Month object
			month = new Month(monthValue);
			year.getList().addSorted(month);
		}

		int dayValue = electricityInfo.getDate().getDayOfMonth();
		Day day = new Day(electricityInfo, dayValue);
		month.getDays().addSorted(day);
	}

	// Delete
	public boolean deleteElectricityInfo(LocalDate date) {
		if (date != null) {
			Year year = years.find(new Year(date.getYear()));
			if (year != null) {
				Month month = year.getList().find(new Month(date.getMonthValue()));
				if (month != null) {
					Day day = month.getDays().find(new Day(date.getDayOfMonth()));
					if (day != null) {
						month.getDays().remove(day);
						return true;
					} else {
						// Handle the case where the day is not found
						System.out.println("Cannot delete record. Day not found.");
					}
				} else {
					// Handle the case where the month is not found
					System.out.println("Cannot delete record. Month not found.");
				}
			} else {
				// Handle the case where the year is not found
				System.out.println("Cannot delete record. Year not found.");
			}
		} else {
			// Handle the case where the input date is null
			System.out.println("Cannot delete record. Input date is null.");
		}

		return false;
	}

	// Update
	public void updateElectricityInfo(LocalDate date, ElectricityRecords updatedInfo) {
		if (date == null || updatedInfo == null) {
			System.out.println("Invalid input for updateElectricityInfo.");
			return;
		}

		// delete the record for the date
		deleteElectricityInfo(date);

		// add the updated information
		addElectricityInfo(updatedInfo);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// method to get stats
	public ElectricityStats getStats(String selectedOption, String selectedColumn) {
		double sum = 0;
		double avg = 0;
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;

		for (int i = 0; i < years.size(); i++) {
			Year year = years.get(i);
//			if(year.getYear()==2020) {
			for (int j = 0; j < year.getList().size(); j++) {
				Month month = year.getList().get(j);
//				if(mont==2020) {
				for (int k = 0; k < month.getDays().size(); k++) {
					Day day = month.getDays().get(k);
					ElectricityRecords electricityRecords = day.getElectricityRecords();

					double columnValue = getColumnValue(electricityRecords, selectedColumn);

					// update sum, min, and max
					sum += columnValue;
					min = Math.min(min, columnValue);
					max = Math.max(max, columnValue);
				}
			}
		}
//		}
		// average
		int totalElements = years.size() * 12 * 31;
		avg = sum / totalElements;

		// give the value
		ElectricityStats electricityStats = new ElectricityStats();
		electricityStats.setSum(sum);
		electricityStats.setAvg(avg);
		electricityStats.setMin(min);
		electricityStats.setMax(max);

		return electricityStats;
	}

	// method to get column
	private double getColumnValue(ElectricityRecords electricityRecords, String selectedColumn) {

		switch (selectedColumn) {
		case "Israeli_Lines_MWs":
			return electricityRecords.getIsraeli_Lines_MWs();
		case "Gaza_Power_Plant_MWs":
			return electricityRecords.getGaza_Power_Plant_MWs();
		case "Egyptian Lines":
			return electricityRecords.getEgyptian_Lines_MWs();
		case "Total daily Supply available":
			return electricityRecords.getTotal_daily_Supply_available_in_MWs();
		case "Overall demand":
			return electricityRecords.getOverall_demand_in_MWs();
		case "Power Cuts hours day":
			return electricityRecords.getPower_Cuts_hours_day_400mg();
		case "Temp":
			return electricityRecords.getTemp();
		default:
			throw new IllegalArgumentException("Invalid column name: " + selectedColumn);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	// method to get updated data
	public String getUpdatedData() {
		StringBuilder result = new StringBuilder();

		// Add column names to the first line
		result.append("Date,Israeli_Lines_MWs,Gaza_Power_Plant_MWs,Egyptian_Lines_MWs,"
				+ "Total_daily_Supply_available_in_MWs,Overall_demand_in_MWs," + "Power_Cuts_hours_day_400mg,Temp\n");

		for (int i = 0; i < years.size(); i++) {
			Year year = years.get(i);
			for (int j = 0; j < year.getList().size(); j++) {
				Month month = year.getList().get(j);
				for (int k = 0; k < month.getDays().size(); k++) {
					Day day = month.getDays().get(k);
					ElectricityRecords electricityRecords = day.getElectricityRecords();

					String formattedRecord = formatElectricityRecord(electricityRecords);

					result.append(formattedRecord).append("\n");

					result.append(" ");
				}
			}
		}

		return result.toString();
	}

	// helper method to the format of the file
	private String formatElectricityRecord(ElectricityRecords electricityRecords) {

		return String.format("%s,%s,%s,%s,%s,%s,%s,%s", electricityRecords.getDate(),
				electricityRecords.getIsraeli_Lines_MWs(), electricityRecords.getGaza_Power_Plant_MWs(),
				electricityRecords.getEgyptian_Lines_MWs(), electricityRecords.getTotal_daily_Supply_available_in_MWs(),
				electricityRecords.getOverall_demand_in_MWs(), electricityRecords.getPower_Cuts_hours_day_400mg(),
				electricityRecords.getTemp(), "\n");
	}

}