package de.egatlov.sentiment_api.sentiment.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.util.CleanText;
import de.egatlov.sentiment_api.util.LowerCaseText;
import de.egatlov.sentiment_api.util.SplittedText;
import de.egatlov.sentiment_api.util.Text;

public final class SentimentCU implements ControlUnit {

	@JsonProperty
	private final Map<Sentiment, Double> sentiments;

	public SentimentCU(Json json) throws Exception {
		SentimentCU cu = json.buildObject(SentimentCU.class);
		this.sentiments = cu.sentiments();
	}

	public SentimentCU(Sentiment... sentiments) {
		this.sentiments = new HashMap<Sentiment, Double>();
		for (Sentiment sentiment : sentiments) {
			this.sentiments.put(sentiment, 0.0);
		}
	}

	@Override
	public Map<Sentiment, Double> sentiments() {
		return sentiments;
	}

	@Override
	public Sentiment analyzed(String toBeAnalyzed) {
		List<String> toAnalyze = new SplittedText(new CleanText(new LowerCaseText(new Text(toBeAnalyzed))));
		Set<Sentiment> keys = sentiments.keySet();
		for (Sentiment sentiment : keys) {
			sentiments.put(sentiment, sentiment.analyzed(toAnalyze));
		}
		Sentiment sentiment = sentimentWithHighestValence();
		teach(sentiment, toAnalyze);
		for (Sentiment s : keys) {
			if (!s.equals(sentiment)) {
				unteach(s, toAnalyze);
			}
		}
		return sentiment;
	}

	public void teach(Sentiment sentiment, String toBeTeached) {
		sentiment.learn(new SplittedText(new CleanText(new LowerCaseText(new Text(toBeTeached)))));
	}

	public void teach(Sentiment sentiment, List<String> toBeTeached) {
		sentiment.learn(toBeTeached);
	}

	public void unteach(Sentiment sentiment, String toBeUnTeached) {
		sentiment.unlearn(new SplittedText(new CleanText(new LowerCaseText(new Text(toBeUnTeached)))));
	}

	public void unteach(Sentiment sentiment, List<String> toBeUnTeached) {
		sentiment.unlearn(toBeUnTeached);
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
