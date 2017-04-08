package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.trainingtool.browser.FileBrowser;
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
		FileBrowser fileBrowser = new FileBrowser("Choose neutrals JSON-File");
		try {
			neutralsTF.setText(fileBrowser.chosenPath());
		} catch (Exception e) {
			// Do nothing because user just didn't pick path
		}
	}

	@FXML
	void browseValences(ActionEvent event) {
		FileBrowser fileBrowser = new FileBrowser("Choose neutrals JSON-File");
		try {
			valencesTF.setText(fileBrowser.chosenPath());
		} catch (Exception e) {
			// Do nothing because user just didn't pick path
		}
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
