package de.egatlov.sentiment_api.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.sentiment.Sentiment;

/**
 * Highest.class</br>
 * </br>
 * This class is made for searching in a Map with Key equals Sentiment and Value
 * equals last analysis result for the Sentiment with highest analysis result.
 * 
 * @author egatlov
 */
public class Highest implements HelpClass<Sentiment> {

	/**
	 * Sentiments.</br>
	 * Key: A Sentiment.</br>
	 * Value: The result of the last analysis.
	 */
	@JsonProperty
	private final Map<Sentiment, Double> sentiments;

	/**
	 * Creates an HighestObject.
	 * 
	 * @param sentiments
	 *            - the sentiments to be searched in.
	 */
	public Highest(Map<Sentiment, Double> sentiments) {
		this.sentiments = sentiments;
	}

	/**
	 * Get the sentiment with the highest value in last analysis.
	 * 
	 * @return Returns a sentiment.
	 */
	public Sentiment get() {
		Map.Entry<Sentiment, Double> maxEntry = null;
		for (Map.Entry<Sentiment, Double> entry : sentiments.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}

	/**
	 * Updates every sentiment and adds new ones.
	 * 
	 * @param sentiments
	 *            - the sentiments to be searched in.
	 */
	public void update(Map<Sentiment, Double> sentiments) {
		this.sentiments.clear();
		this.sentiments.putAll(sentiments);
	}

}
