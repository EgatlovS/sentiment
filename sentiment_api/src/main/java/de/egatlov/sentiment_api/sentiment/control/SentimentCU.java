package de.egatlov.sentiment_api.sentiment.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.egatlov.sentiment_api.json.ControlUnitDeserializer;
import de.egatlov.sentiment_api.json.ControlUnitSerializer;
import de.egatlov.sentiment_api.json.Json;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.util.CleanText;
import de.egatlov.sentiment_api.util.Highest;
import de.egatlov.sentiment_api.util.LowerCaseText;
import de.egatlov.sentiment_api.util.SplittedText;
import de.egatlov.sentiment_api.util.Text;

/**
 * SentimentCU.class</br>
 * </br>
 * SentimentCu implements the ControlUnit and provides additional methods to
 * learn an teach to sentiments. Note that this class learns on every analysis
 * made. So everytime u analyze some words, be aware that those words will be
 * teached to the matching sentiment and unteached to the all the otherones
 * automatically
 * 
 * @author egatlov
 */
public final class SentimentCU implements ControlUnit {

	/**
	 * Sentiments.</br>
	 * Key: A Sentiment.</br>
	 * Value: The result of the last analysis.
	 */
	@JsonProperty
	@JsonDeserialize(using = ControlUnitDeserializer.class)
	@JsonSerialize(using = ControlUnitSerializer.class)
	private final Map<Sentiment, Double> sentiments;

	/**
	 * Create a ControlUnit out of Json.
	 * 
	 * @param json
	 *            the Json representing Object.
	 * @throws Exception
	 *             - Exception is thrown, if object couldn't be build.
	 */
	public SentimentCU(Json json) throws Exception {
		SentimentCU cu = json.buildObject(SentimentCU.class);
		this.sentiments = cu.sentiments();
	}

	/**
	 * Create a ControlUnit, and declare a bunch of sentiments to be tracked.
	 * 
	 * @param sentiments
	 *            - the sentiments to be controlled.
	 */
	public SentimentCU(Sentiment... sentiments) {
		this.sentiments = new HashMap<Sentiment, Double>();
		for (Sentiment sentiment : sentiments) {
			this.sentiments.put(sentiment, 0.0);
		}
	}

	public SentimentCU() {
		this.sentiments = new HashMap<Sentiment, Double>();
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
		Sentiment sentiment = new Highest(sentiments).get();
		teach(sentiment, toAnalyze);
		for (Sentiment s : keys) {
			if (!s.equals(sentiment)) {
				unteach(s, toAnalyze);
			}
		}
		return sentiment;
	}

	/**
	 * Teaches words to a Sentiment.
	 * 
	 * @param sentiment
	 *            - the sentiment to be teached to.
	 * @param toBeTeached
	 *            - the words to be teached.
	 */
	public void teach(Sentiment sentiment, String toBeTeached) {
		sentiment.learn(new SplittedText(new CleanText(new LowerCaseText(new Text(toBeTeached)))));
	}

	/**
	 * Teaches words to a Sentiment.
	 * 
	 * @param sentiment
	 *            - the sentiment to be teached to.
	 * @param toBeTeached
	 *            - the words to be teached.
	 */
	public void teach(Sentiment sentiment, List<String> toBeTeached) {
		sentiment.learn(toBeTeached);
	}

	/**
	 * Unteaches words from a sentiment.
	 * 
	 * @param sentiment
	 *            - the sentiment to be unteached.
	 * @param toBeUnTeached
	 *            - the words to be unteached.
	 */
	public void unteach(Sentiment sentiment, String toBeUnTeached) {
		sentiment.unlearn(new SplittedText(new CleanText(new LowerCaseText(new Text(toBeUnTeached)))));
	}

	/**
	 * Unteaches words from a sentiment.
	 * 
	 * @param sentiment
	 *            - the sentiment to be unteached.
	 * @param toBeUnTeached
	 *            - the words to be unteached.
	 */
	public void unteach(Sentiment sentiment, List<String> toBeUnTeached) {
		sentiment.unlearn(toBeUnTeached);
	}

	public void improve(Sentiment toBeTheWinner, String text) {
		Set<Sentiment> sents = sentiments.keySet();
		Sentiment lastWinner = new Highest(sentiments).get();
		for (Sentiment sentiment : sents) {
			if (sentiment.equals(lastWinner)) {
				unteach(lastWinner, text);
				unteach(lastWinner, text);
			} else if (sentiment.equals(toBeTheWinner)) {
				teach(toBeTheWinner, text);
				teach(toBeTheWinner, text);
			}

		}
	}

}
