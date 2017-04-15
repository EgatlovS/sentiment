package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ValencesController {

	@FXML
	private JFXTextArea valencesTA;

	@FXML
	private JFXButton backBtn;

	public void initialize() {
		// TODO init TA
	}

	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

}