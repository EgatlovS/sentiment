package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;

import de.egatlov.trainingtool.data.ApplicationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SentimentController {

	@FXML
	private Label sentimentNameLabel;

	@FXML
	private Label analyzingResultLabel;

	@FXML
	private JFXButton neutralsBtn;

	@FXML
	private JFXButton valencesBtn;

	@FXML
	private JFXButton deleteBtn;

	public void initialize() {
		ApplicationData.get().setSentimentController(this);
	}

	@FXML
	void deleteSentiment(ActionEvent event) {
		// deletes one sentiment
		// take the name of the label
	}

	@FXML
	void neutralsWindow(ActionEvent event) {
		// open window which displays neutrals
	}

	@FXML
	void valencesWindow(ActionEvent event) {
		// open window which displays valences and the times learned
	}

	public void update(String name, Double value) {
		sentimentNameLabel.setText(name);
		analyzingResultLabel.setText(String.valueOf(value));
	}

}
