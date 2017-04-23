package de.egatlov.trainingtool.controller;

import de.egatlov.trainingtool.data.ApplicationData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public final class SentimentController {

	@FXML
	private Label sentimentNameLabel;

	@FXML
	private Label analyzingResultLabel;

	public void initialize() {
		ApplicationData.get().sentimentsView().setSentimentController(this);
	}

	public Label sentimentNameLabel() {
		return sentimentNameLabel;
	}

	public Label analyzingResultLabel() {
		return analyzingResultLabel;
	}
}
