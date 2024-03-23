package application;
//1210773-Rasha Mansour
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveScreen extends Application {

	// attributes
	private TextField textField;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);

		HBox titleBox = new HBox();
		titleBox.setAlignment(Pos.CENTER);
		Label titleLabel = new Label("Save Screen");
		titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: black ; -fx-font-weight: bold");
		titleBox.getChildren().add(titleLabel);

		vbox.getChildren().add(titleBox);

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(30);

		Label label = new Label("File Name:");
		label.setStyle("-fx-font-size: 15px; -fx-text-fill: black; -fx-font-weight: bold");
		gridPane.add(label, 0, 0);

		textField = new TextField();
		gridPane.add(textField, 1, 0);

		Button button = new Button("Save Data");
		button.setOnAction(e -> saveDataToFile());
		gridPane.add(button, 2, 0);

		vbox.getChildren().add(gridPane);

		Scene scene = new Scene(vbox, 600, 400);
		primaryStage.setScene(scene);
	}

	public void show() {
		// Show the stage
		Stage primaryStage = new Stage();
		start(primaryStage);
		primaryStage.show();
	}

	// method to set the text in the text field
	public void setTextFieldText(String updatedData) {
		textField.setText(updatedData);
	}

	// helper method to save the data to a file
	public void saveDataToFile() {

		String text = textField.getText();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data File");
		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			try {
				Files.write(Paths.get(file.getPath()), text.getBytes());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// method to close the screen after save the file
	public void close() {
		Stage stage = (Stage) textField.getScene().getWindow();
		stage.close();
	}

}
