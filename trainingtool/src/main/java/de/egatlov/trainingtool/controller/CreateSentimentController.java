package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Neutrals;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.sentiment.Valences;
import de.egatlov.trainingtool.browser.FileBrowser;
import de.egatlov.trainingtool.data.ApplicationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public final class CreateSentimentController {

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
	void create(ActionEvent event) throws Exception {
		// create sentiment
		Sentiment sentiment = createSentiment(); // TODO catch exception and
													// show window json invalid
													// message
		// update sentimentcu
		ApplicationData.get().getControlUnit().sentiments().put(sentiment, 0.0);
		// update masonry
		ApplicationData.get().mainView().addSentimentEvent();
		// close window
		cancel(event);
	}

	private Sentiment createSentiment() throws Exception {
		Neutrals neutrals;
		if (neutralsTF.getText() != null && !neutralsTF.getText().isEmpty()) {
			neutrals = new Neutrals(new Json(neutralsTF.getText()));
		} else {
			neutrals = new Neutrals();
		}
		Valences valences;
		if (valencesTF.getText() != null && !valencesTF.getText().isEmpty()) {
			valences = new Valences(new Json(valencesTF.getText()));
		} else {
			valences = new Valences();
		}
		return new Sentiment(neutrals, valences, nameTF.getText(), descriptionTF.getText());
	}

}
