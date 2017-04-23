package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.sentiment_api.sentiment.Neutrals;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.sentiment.Valences;
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
	private JFXButton cancelBtn;

	@FXML
	private JFXButton createBtn;

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
		return new Sentiment(new Neutrals(), new Valences(), nameTF.getText(), descriptionTF.getText());
	}

}
