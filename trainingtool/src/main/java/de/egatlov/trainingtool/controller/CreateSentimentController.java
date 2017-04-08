package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CreateSentimentController {

	@FXML
	private JFXTextField nameTF;

	@FXML
	private JFXTextArea descriptionTF;

	@FXML
	private JFXTextField neutralsTF;

	@FXML
	private JFXButton neutralsBrowseBtn;

	@FXML
	private JFXTextField valencesTF;

	@FXML
	private JFXButton valencesBrowseBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	private JFXButton createBtn;

	@FXML
	void browseNeutrals(ActionEvent event) {

	}

	@FXML
	void browseValences(ActionEvent event) {

	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void create(ActionEvent event) {

	}

}
