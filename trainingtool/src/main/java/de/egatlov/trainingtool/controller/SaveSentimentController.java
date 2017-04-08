package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.trainingtool.browser.DirectoryBrowser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SaveSentimentController {

	@FXML
	private JFXTextField saveToTF;

	@FXML
	private JFXButton browseBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	private JFXComboBox<?> choseSentimentCB;

	@FXML
	void browse(ActionEvent event) {
		DirectoryBrowser directoryBrowser = new DirectoryBrowser();
		try {
			saveToTF.setText(directoryBrowser.chosenPath());
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
	void save(ActionEvent event) {

	}

}
