package de.egatlov.sentiment_api.sentiment.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.util.CleanText;
import de.egatlov.sentiment_api.util.Highest;
import de.egatlov.sentiment_api.util.LowerCaseText;
import de.egatlov.sentiment_api.util.SplittedText;
import de.egatlov.sentiment_api.util.Text;

/**
 * SimpleSentimentCU.class</br>
 * </br>
 * SimpleSentimentCU implements ControlUnit so it provides those declared
 * Methods.</br>
 * Note:</br>
 * It's a SIMPLE ControlUnit so it don't declare additional methods and it also
 * doesn't learn those Sentiments everything. It should be used in the cases
 * where u don't won't to have learning in Production and have already some
 * trained sentiments which are functional for your needs.
 * 
 * @author egatlov
 */
public final class SimpleSentimentCU implements ControlUnit {

	/**
	 * Sentiments.</br>
	 * Key: A Sentiment.</br>
	 * Value: The result of the last analysis.
	 */
	@JsonProperty
	private final Map<Sentiment, Double> sentiments;

	/**
	 * Create a ControlUnit out of Json.
	 * 
	 * @param json
	 *            the Json representing Object.
	 * @throws Exception
	 *             - Exception is thrown, if object couldn't be build.
	 */
	public SimpleSentimentCU(Json json) throws Exception {
		SimpleSentimentCU cu = json.buildObject(SimpleSentimentCU.class);
		this.sentiments = cu.sentiments();
	}

	/**
	 * Create a ControlUnit, and declare a bunch of sentiments to be tracked.
	 * 
	 * @param sentiments
	 *            - the sentiments to be controlled.
	 */
	public SimpleSentimentCU(Sentiment... sentiments) {
		this.sentiments = new HashMap<Sentiment, Double>();
		for (Sentiment sentiment : sentiments) {
			this.sentiments.put(sentiment, 0.0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Sentiment, Double> sentiments() {
		return sentiments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sentiment analyzed(String toBeAnalyzed) {
		List<String> toAnalyze = new SplittedText(new CleanText(new LowerCaseText(new Text(toBeAnalyzed))));
		Set<Sentiment> keys = sentiments.keySet();
		for (Sentiment sentiment : keys) {
			sentiments.put(sentiment, sentiment.analyzed(toAnalyze));
		}
		return new Highest(sentiments).get();
	}

}
