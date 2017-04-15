package de.egatlov.trainingtool.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.sentiment.control.SentimentCU;
import de.egatlov.trainingtool.controller.MainController;
import de.egatlov.trainingtool.controller.SentimentController;

public final class ApplicationData {

	private static ApplicationData DATA;
	private SentimentCU controlUnit;
	private MainController mainController;
	private List<SentimentController> sentimentController;

	private ApplicationData() {
	}

	public static ApplicationData get() {
		if (DATA == null) {
			DATA = new ApplicationData();
		}
		return DATA;
	}

	public SentimentCU getControlUnit() {
		if (controlUnit == null) {
			controlUnit = new SentimentCU();
		}
		return controlUnit;
	}

	public void setControlUnit(SentimentCU controlUnit) {
		this.controlUnit = controlUnit;
	}

	public MainController getMainController() {
		if (mainController == null) {
			return new MainController();
		}
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void setSentimentController(SentimentController controller) {
		if (sentimentController == null) {
			sentimentController = new ArrayList<>();
		}
		sentimentController.add(controller);
		updateSentiments();
	}

	public void updateSentiments() {
		Map<Sentiment, Double> sentiments = controlUnit.sentiments();
		int counter = 0;
		for (Entry<Sentiment, Double> entry : sentiments.entrySet()) {
			sentimentController.get(counter).update(entry.getKey().name(), entry.getValue());
			counter++;
		}
	}

}
