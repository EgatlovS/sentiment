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

public final class SimpleSentimentCU implements ControlUnit {

	@JsonProperty
	private final Map<Sentiment, Double> sentiments;

	public SimpleSentimentCU(Json json) throws Exception {
		SimpleSentimentCU cu = json.buildObject(SimpleSentimentCU.class);
		this.sentiments = cu.sentiments();
	}

	public SimpleSentimentCU(Sentiment... sentiments) {
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
		return sentimentWithHighestValence();
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
