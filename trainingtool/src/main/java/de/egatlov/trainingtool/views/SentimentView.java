package de.egatlov.trainingtool.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.trainingtool.controller.SentimentController;
import de.egatlov.trainingtool.data.ApplicationData;

public final class SentimentView extends View {

	private List<SentimentController> sentimentController;

	public SentimentView() {
		super("SentimentView", "Sentiment", false, true);
		sentimentController = new ArrayList<>();
	}

	public List<SentimentController> getSentimentController() {
		return sentimentController;
	}

	public void setSentimentController(SentimentController sentimentController) {
		this.sentimentController.add(sentimentController);
		updateSentiments();
	}

	public void updateSentiments() {
		Map<Sentiment, Double> sentiments = ApplicationData.get().getControlUnit().sentiments();
		int counter = 0;
		for (Entry<Sentiment, Double> entry : sentiments.entrySet()) {
			sentimentController.get(counter).sentimentNameLabel().setText(entry.getKey().name());
			sentimentController.get(counter).analyzingResultLabel().setText(String.valueOf(entry.getValue()));
			counter++;
		}
	}

}
