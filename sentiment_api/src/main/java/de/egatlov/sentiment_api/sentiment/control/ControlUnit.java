package de.egatlov.sentiment_api.sentiment.control;

import java.util.Map;

import de.egatlov.sentiment_api.sentiment.Sentiment;

public interface ControlUnit {

	Map<Sentiment, Double> sentiments();

	Sentiment analyzed(String toBeAnalyzed);

}
