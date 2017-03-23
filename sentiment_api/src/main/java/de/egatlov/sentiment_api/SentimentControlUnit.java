package de.egatlov.sentiment_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SentimentControlUnit {

	private final Map<Sentiment, Double> sentiments;

	public SentimentControlUnit(Sentiment... sentiments) {
		this.sentiments = new HashMap<Sentiment, Double>();
		for (Sentiment sentiment : sentiments) {
			this.sentiments.put(sentiment, 0.0);
		}
	}

	public Map<Sentiment, Double> sentiments() {
		return sentiments;
	}

	public Sentiment analyzed(String toBeAnalyzed) {
		List<String> toAnalyze = new ArrayList<String>(Arrays.asList(toBeAnalyzed.split(" ")));
		Set<Sentiment> keys = sentiments.keySet();
		for (Sentiment sentiment : keys) {
			sentiments.put(sentiment, sentiment.analyzed(toAnalyze));
		}
		Sentiment sentiment = sentimentWithHighestValence();
		teach(sentiment, toAnalyze);
		return sentiment;
	}

	public void teach(Sentiment sentiment, String toBeTeached) {
		sentiment.learn(new ArrayList<String>(Arrays.asList(toBeTeached.split(" "))));
	}

	public void teach(Sentiment sentiment, List<String> toBeTeached) {
		sentiment.learn(new ArrayList<String>(toBeTeached));
	}

	public void unteach(Sentiment sentiment, String toBeUnTeached) {
		sentiment.unlearn(new ArrayList<String>(Arrays.asList(toBeUnTeached.split(" "))));
	}

	public void unteach(Sentiment sentiment, List<String> toBeUnTeached) {
		sentiment.unlearn(new ArrayList<String>(toBeUnTeached));
	}

	private Sentiment sentimentWithHighestValence() {
		Map.Entry<Sentiment, Double> maxEntry = null;
		for (Map.Entry<Sentiment, Double> entry : sentiments.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}

}
