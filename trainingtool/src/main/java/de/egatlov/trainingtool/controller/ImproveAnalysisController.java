package de.egatlov.trainingtool.controller;

import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.trainingtool.data.ApplicationData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public final class ImproveAnalysisController {

	@FXML
	private JFXComboBox<String> chooseSentimentCB;

	@FXML
	private JFXButton improveBtn;

	@FXML
	private JFXButton cancelBtn;

	public void initialize() {
		Map<Sentiment, Double> sentiments = ApplicationData.get().getControlUnit().sentiments();
		ObservableList<String> sentimentNames = FXCollections.observableArrayList();
		for (Sentiment sentiment : sentiments.keySet()) {
			sentimentNames.add(sentiment.name());
		}
		chooseSentimentCB.setItems(sentimentNames);
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void improve(ActionEvent event) {

	}

}
