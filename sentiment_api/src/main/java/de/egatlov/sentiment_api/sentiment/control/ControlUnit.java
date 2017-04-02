package de.egatlov.sentiment_api.sentiment.control;

import java.util.Map;

import de.egatlov.sentiment_api.sentiment.Sentiment;

/**
 * ControlUnit.class</br>
 * </br>
 * ControlUnit is an interface to describe what each CU should provide.
 * 
 * @author egatlov
 */
public interface ControlUnit {

	/**
	 * All sentiments controlled by this ControlUnit are returned.
	 * 
	 * @return Returns a Map of the sentiments controlled.</br>
	 *         Note:</br>
	 *         Key: Sentiment</br>
	 *         Value: Last analysis result of that given sentiment</br>
	 */
	Map<Sentiment, Double> sentiments();

	/**
	 * Analyzes every Sentiment controlled and returns the one matches most.
	 * 
	 * @param toBeAnalyzed
	 *            - the text which should be analyzed
	 * @return Returns the Sentiment, which matches the most.
	 */
	Sentiment analyzed(String toBeAnalyzed);

}
