package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ImproveAnalysisController {

	@FXML
	private JFXComboBox<?> chooseSentimentCB;

	@FXML
	private JFXButton improveBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void improve(ActionEvent event) {

	}

}
