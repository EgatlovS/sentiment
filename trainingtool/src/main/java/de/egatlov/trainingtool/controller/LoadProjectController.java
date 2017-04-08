package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LoadProjectController {

	@FXML
	private JFXTextField pathToFileTF;

	@FXML
	private JFXButton browseBtn;

	@FXML
	private JFXButton loadBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	void browse(ActionEvent event) {
		//browse file with directory chooser
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void load(ActionEvent event) {
		//load project from json file given in label
		//close window
	}

}
