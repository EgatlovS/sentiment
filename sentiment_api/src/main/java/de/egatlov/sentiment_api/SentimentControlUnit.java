package de.egatlov.sentiment_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SentimentControlUnit {

	private final List<Sentiment> sentiments;

	public SentimentControlUnit(Sentiment... sentiments) {
		this.sentiments = Arrays.asList(sentiments);
	}

	public List<Sentiment> sentiments() {
		return sentiments;
	}

	public Sentiment analyzed(String toBeAnalyzed) {
		List<String> toAnalyze = new ArrayList<String>(Arrays.asList(toBeAnalyzed.split(" ")));
		for (Sentiment sentiment : sentiments) {
			sentiment.analyze(toAnalyze);
		}
		Sentiment sentiment = Collections.max(sentiments, new SentimentComparator());
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

}
