package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;

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

}
