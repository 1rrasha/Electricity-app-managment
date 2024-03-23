package application;
//1210773-Rasha Mansour
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class ManagementScreen extends Application {

	// attributes
	private DatePicker datePicker;
	private TextField searchTextField;
	private DoubleLinkedList yearList;
	private Services services;
	TextField dateTextField;
	TextField israeliLinesTextField;
	TextField gazaPowerPlantTextField;
	TextField egyptianLinesTextField;
	TextField totalDailySupplyTextField;
	TextField overallDemandTextField;
	TextField powerCutsTextField;
	TextField tempTextField;
	SaveScreen saveScreen = new SaveScreen();

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		services = new Services();
		yearList = new DoubleLinkedList();
		primaryStage.setTitle("Management");

		// Title
		Label titleLabel = new Label("Management");
		titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
		HBox titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setBackground(
				new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		// File Section
		Label fileNameLabel = new Label("File Name:");
		fileNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold;");

		TextField fileTextField = new TextField();
		fileTextField.setEditable(false); // Make it not editable

		Button loadDataButton = new Button("Load Data");
		loadDataButton.setOnAction(e -> loadDataFromFile(fileTextField));
		HBox fileBox = new HBox(fileNameLabel, fileTextField, loadDataButton);
		fileBox.setSpacing(10);

		// Buttons Section
		Button updateButton = createStyledButton("Update", "-fx-background-color: blue;-fx-text-fill: white;");
		updateButton.setOnAction(e -> updateData());
		Button insertButton = createStyledButton("Insert", "-fx-background-color: blue;-fx-text-fill: white;");
		insertButton.setOnAction(e -> insertData());
		Button deleteButton = createStyledButton("Delete", "-fx-background-color: red; -fx-text-fill: white;");
		deleteButton.setOnAction(e -> deleteRecord());
		HBox buttonsBox = new HBox(updateButton, insertButton, deleteButton);
		buttonsBox.setSpacing(10);
		Button saveButton = createStyledButton("Save", "-fx-background-color: green;-fx-text-fill: white;");
		saveButton.setOnAction(e -> saveDataToFile());
		HBox saveBox = new HBox(saveButton);
		saveBox.setSpacing(10);

		// Date Search Section
		Label dateLabel = new Label("Date:");
		dateLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold;");
		searchTextField = new TextField();
		Button searchButton = new Button("Search");
		searchButton.setOnAction(e -> searchData());
		HBox searchBox = new HBox(dateLabel, searchTextField, searchButton);
		searchBox.setSpacing(10);

		// Calendar Section
		datePicker = new DatePicker();
		datePicker.setShowWeekNumbers(true);
		HBox calendarBox = new HBox(datePicker);
		calendarBox.setSpacing(10);

		// Form Section
		GridPane form = createForm();
		VBox formBox = new VBox(form);

		// Main Layout
		VBox mainLayout = new VBox(titleBox, fileBox, searchBox, calendarBox, formBox, buttonsBox, saveBox);
		mainLayout.setSpacing(10);
		mainLayout.setPadding(new Insets(10));
		Scene scene = new Scene(mainLayout, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// methods for help

	private void saveDataToFile() {

		String updatedData = services.getUpdatedData();

		SaveScreen saveScreen = new SaveScreen();

		saveScreen.show();

		saveScreen.setTextFieldText(updatedData);

		saveScreen.saveDataToFile();

		showAlert1("Success", "Data saved successfully!");

		// Close the SaveScreen
		saveScreen.close();
	}

	private Button createStyledButton(String text, String style) {
		Button button = new Button(text);
		button.setStyle(style);
		button.setOnAction(e -> handleButtonAction(text));
		return button;
	}

	private GridPane createForm() {
		GridPane form = new GridPane();
		form.setHgap(10);
		form.setVgap(10);

		// Date
		Label dateLabel = new Label("Date:");
		dateTextField = new TextField();
		form.addRow(0, dateLabel, dateTextField);

		// Israeli Lines MWs
		Label israeliLinesLabel = new Label("Israeli Lines MWs:");
		israeliLinesTextField = new TextField();
		form.addRow(1, israeliLinesLabel, israeliLinesTextField);

		// Gaza Power Plant MWs
		Label gazaPowerPlantLabel = new Label("Gaza Power Plant MWs:");
		gazaPowerPlantTextField = new TextField();
		form.addRow(2, gazaPowerPlantLabel, gazaPowerPlantTextField);

		// Egyptian Lines MWs
		Label egyptianLinesLabel = new Label("Egyptian Lines MWs:");
		egyptianLinesTextField = new TextField();
		form.addRow(3, egyptianLinesLabel, egyptianLinesTextField);

		// Total Daily Supply Available in MWs
		Label totalDailySupplyLabel = new Label("Total Daily Supply (MWs):");
		totalDailySupplyTextField = new TextField();
		form.addRow(4, totalDailySupplyLabel, totalDailySupplyTextField);

		// Overall Demand in MWs
		Label overallDemandLabel = new Label("Overall Demand (MWs):");
		overallDemandTextField = new TextField();
		form.addRow(5, overallDemandLabel, overallDemandTextField);

		// Power Cuts Hours per Day (400mg)
		Label powerCutsLabel = new Label("Power Cuts Hours per Day (400mg):");
		powerCutsTextField = new TextField();
		form.addRow(6, powerCutsLabel, powerCutsTextField);

		// Temp
		Label tempLabel = new Label("Temperature:");
		tempTextField = new TextField();
		form.addRow(7, tempLabel, tempTextField);

		return form;
	}

	// method to handle each button a method
	private void handleButtonAction(String buttonName) {
		switch (buttonName) {
		case "Update":
			updateData();
			break;
		case "Insert":
			insertData();
			break;
		case "Delete":
			deleteRecord();
			break;
		case "Search":
			searchData();
			break;
		default:
			System.out.println("Unhandled button: " + buttonName);
		}
	}

	// method to load data from the file
	private void loadDataFromFile(TextField fileTextField) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Electricity Data File");
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String headerLine = reader.readLine();
				if (headerLine != null) {
					String[] headers = headerLine.split("\t");
					if (headers.length != 8) {
						showAlert("Error!", "Invalid file format. Please check the number of columns.");
						return;
					}
				}

				while (reader.ready()) {
					String line = reader.readLine();
					System.out.println(line);
					String[] data = line.split("\t");

					if (data.length == 8) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
						LocalDate date = LocalDate.parse(data[0], formatter);
						double israeliLinesMWs = Double.parseDouble(data[1]);
						double gazaPowerPlantMWs = Double.parseDouble(data[2]);
						double egyptianLinesMWs = Double.parseDouble(data[3]);
						double totalDailySupplyMWs = Double.parseDouble(data[4]);
						double overallDemandMWs = Double.parseDouble(data[5]);
						double powerCutsHoursPerDay = Double.parseDouble(data[6]);
						double temperature = Double.parseDouble(data[7]);

						ElectricityRecords electricityInfo = new ElectricityRecords(date, israeliLinesMWs,
								gazaPowerPlantMWs, egyptianLinesMWs, totalDailySupplyMWs, overallDemandMWs,
								powerCutsHoursPerDay, temperature);

						services.addElectricityInfo(electricityInfo);
					} else {
						System.out.println("Invalid line format: " + line);
					}
				}

				fileTextField.setText(file.getName());
				showAlert1("Success^-^", "Data loaded successfully!");
			} catch (IOException e) {
				e.printStackTrace();
				showAlert("Error!", "An error occurred while reading the file.");
			} catch (DateTimeParseException | NumberFormatException e) {
				e.printStackTrace();
				showAlert("Error!", "Error parsing the file. Please check the file format.");
			}
		}
	}

	// method to search if there is data for the selected date
	private void searchData() {
		try {
			LocalDate date;
			if (datePicker.getValue() != null) {
				date = datePicker.getValue();
			} else {
				String dateString = searchTextField.getText();
				if (dateString.isEmpty()) {
					showAlert("Error!", "Please enter a date for search.");
					return;
				}
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				date = LocalDate.parse(dateString, inputFormatter);
			}

			DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
			String formattedDate = date.format(dataFormatter);

			System.out.println("Searching for date: " + formattedDate);

			ElectricityRecords result = services.search(date);

			if (result != null) {
				updateForm(result);
				showAlert1("Success^-^", "Data found for the selected date.");

//				searchTextField.clear();
//				datePicker.setValue(null);
			} else {
				showAlert("Not Found!", "No data found for the selected date.");
			}
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			showAlert("Error!", "Invalid date format. Please enter a valid date.");
		}
	}

	// method to update the records
	private void updateData() {
		LocalDate dateToUpdate = datePicker.getValue();

		// Check if the date is null
		if (dateToUpdate == null && !searchTextField.getText().isEmpty()) {
			try {
				// If the datePicker is null the searchTextField
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				dateToUpdate = LocalDate.parse(searchTextField.getText(), inputFormatter);
			} catch (DateTimeParseException e) {
				showAlert("Error", "Invalid date format. Please select a valid date.");
				return;
			}
		}

		// Check if the date is null
		if (dateToUpdate == null) {
			showAlert("Error!", "Please select a date for update.");
			return;
		}

		ElectricityRecords updatedInfo = createElectricityRecords();

		// Update the data
		services.updateElectricityInfo(dateToUpdate, updatedInfo);
		showAlert1("Success^-^", "Data updated successfully!");

		clearDateFields();
	}

	// method to insert the records
	private void insertData() {
		LocalDate date = datePicker.getValue();

		// Check if the date is null in datePicker and searchTextField
		if (date == null && !searchTextField.getText().isEmpty()) {
			try {
				// If the datePicker is null, try to parse the date from the searchTextField
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				date = LocalDate.parse(searchTextField.getText(), inputFormatter);
			} catch (DateTimeParseException e) {
				showAlert("Error!", "Invalid date format. Please select a valid date.");
				return;
			}
		}

		// Check if the date is null
		if (date == null) {
			showAlert("Error!", "Please select a date for insert.");
			return;
		}

		ElectricityRecords electricityInfo = createElectricityRecords();

		// Add the data
		services.addElectricityInfo(electricityInfo);
		showAlert1("Success^-^", "Data added successfully!");

		clearDateFields();
	}

	// method to delete the records
	private void deleteRecord() {
		LocalDate dateToDelete;

		// Check if the datePicker is null
		if (datePicker.getValue() != null) {
			dateToDelete = datePicker.getValue();
		} else {
			// If the datePicker is null try the searchTextField
			try {
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				dateToDelete = LocalDate.parse(searchTextField.getText(), inputFormatter);
			} catch (DateTimeParseException e) {
				showAlert("Error!", "Invalid date format. Please select a valid date.");
				return;
			}
		}

		// delete the record
		boolean success = services.deleteElectricityInfo(dateToDelete);

		if (success) {
			showAlert1("Success^-^", "Data deleted successfully!");

			dateTextField.clear();
			israeliLinesTextField.clear();
			gazaPowerPlantTextField.clear();
			egyptianLinesTextField.clear();
			totalDailySupplyTextField.clear();
			overallDemandTextField.clear();
			powerCutsTextField.clear();
			tempTextField.clear();

			clearDateFields();
		} else {
			showAlert("Not Found!", "No data found for the selected date.");
		}
	}

	// method to update form
	private void updateForm(ElectricityRecords data) {

		dateTextField.setText(String.valueOf(data.getDate()));
		israeliLinesTextField.setText(String.valueOf(data.getIsraeli_Lines_MWs()));
		gazaPowerPlantTextField.setText(String.valueOf(data.getGaza_Power_Plant_MWs()));
		egyptianLinesTextField.setText(String.valueOf(data.getEgyptian_Lines_MWs()));
		totalDailySupplyTextField.setText(String.valueOf(data.getTotal_daily_Supply_available_in_MWs()));
		overallDemandTextField.setText(String.valueOf(data.getOverall_demand_in_MWs()));
		powerCutsTextField.setText(String.valueOf(data.getPower_Cuts_hours_day_400mg()));
		tempTextField.setText(String.valueOf(data.getTemp()));
	}

	// method to create a electricity record form
	private ElectricityRecords createElectricityRecords() {
		LocalDate date = datePicker.getValue();

		// Check if the date is null in datePicker and searchTextField
		if (date == null && !searchTextField.getText().isEmpty()) {
			try {

				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				date = LocalDate.parse(searchTextField.getText(), inputFormatter);
			} catch (DateTimeParseException e) {
				showAlert("Error!", "Invalid date format. Please select a valid date.");
				return null;
			}
		}

		if (date == null) {
			showAlert("Error!", "Please select a date.");
			return null;
		}

		double israeliLinesMWs = Double.parseDouble(israeliLinesTextField.getText());
		double gazaPowerPlantMWs = Double.parseDouble(gazaPowerPlantTextField.getText());
		double egyptianLinesMWs = Double.parseDouble(egyptianLinesTextField.getText());
		double totalDailySupplyMWs = Double.parseDouble(totalDailySupplyTextField.getText());
		double overallDemandMWs = Double.parseDouble(overallDemandTextField.getText());
		double powerCutsHoursPerDay = Double.parseDouble(powerCutsTextField.getText());
		double temperature = Double.parseDouble(tempTextField.getText());

		return new ElectricityRecords(date, israeliLinesMWs, gazaPowerPlantMWs, egyptianLinesMWs, totalDailySupplyMWs,
				overallDemandMWs, powerCutsHoursPerDay, temperature);
	}

	// method to clear the date field
	private void clearDateFields() {
		datePicker.setValue(null);
		searchTextField.clear();
	}

	// alert method
	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);

		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showAlert1(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
