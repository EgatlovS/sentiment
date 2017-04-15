package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.trainingtool.browser.FileBrowser;
import de.egatlov.trainingtool.data.ApplicationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LoadSentimentController {

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
		FileBrowser fileBrowser = new FileBrowser("Choose Sentiment JSON-File");
		try {
			pathToFileTF.setText(fileBrowser.chosenPath());
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
	void load(ActionEvent event) throws Exception {
		// TODO fix json so it can read and write nested objects
		// TODO open window with text unsaved stuff could be deleted...
		ApplicationData.get().getControlUnit().sentiments().put(new Sentiment(new Json(pathToFileTF.getText())), 0.0);
		ApplicationData.get().getMainController().update();
		cancel(event);
	}

}