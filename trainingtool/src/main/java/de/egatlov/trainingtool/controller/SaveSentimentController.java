package de.egatlov.trainingtool.controller;

import java.util.Map;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.trainingtool.browser.DirectoryBrowser;
import de.egatlov.trainingtool.data.ApplicationData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public final class SaveSentimentController {

	@FXML
	private JFXTextField saveToTF;

	@FXML
	private JFXButton browseBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	private JFXComboBox<String> chooseSentimentCB;

	public void initialize() {
		Map<Sentiment, Double> sentiments = ApplicationData.get().getControlUnit().sentiments();
		ObservableList<String> sentimentNames = FXCollections.observableArrayList();
		for (Sentiment sentiment : sentiments.keySet()) {
			sentimentNames.add(sentiment.name());
		}
		chooseSentimentCB.setItems(sentimentNames);
	}

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
	void save(ActionEvent event) throws Exception {
		// TODO catch exception and tell user that it couldnt be saved
		Sentiment toSave;
		Set<Sentiment> sentiments = ApplicationData.get().getControlUnit().sentiments().keySet();
		for (Sentiment sentiment : sentiments) {
			if (sentiment.name().equals(chooseSentimentCB.getValue())) {
				toSave = sentiment;
				new Json(saveToTF.getText() + "/" + chooseSentimentCB.getValue() + ".json").write(toSave);
				break;
			}
		}
		cancel(event);
	}

}
