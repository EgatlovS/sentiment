package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

/**
 * Valences.class</br>
 * </br>
 * Valences presents the valenced words, which are needed to analyze text.
 * 
 * @author egatlov
 */
public final class Valences {

	/**
	 * The times the valences got incremented or got new words added.
	 */
	@JsonProperty
	private int timesLearned;
	/**
	 * The values of this Valences.</br>
	 * Note:</br>
	 * Key: The valenced word.</br>
	 * Value: The valence of the word.
	 */
	@JsonProperty
	private final Map<String, Integer> values;

	/**
	 * Create Valences out of json.
	 * 
	 * @param json
	 *            - the json representing Object.
	 * @throws Exception
	 *             - Exception is thrown if object couldn't be build.
	 */
	public Valences(Json json) throws Exception {
		Valences valences = json.buildObject(Valences.class);
		this.timesLearned = valences.timesLearned();
		this.values = valences.values();
	}

	/**
	 * Creates Valences out of values and the times learned.
	 * 
	 * @param values
	 *            - the values of the valences.
	 * @param timesLearned
	 *            - the times this valences have learned.
	 */
	public Valences(Map<String, Integer> values, int timesLearned) {
		this.values = values;
		this.timesLearned = timesLearned;
	}

	/**
	 * Creates clear Valences.
	 */
	public Valences() {
		this(new HashMap<String, Integer>(), 1);
	}

	/**
	 * Increments valences which are the same like the given words.
	 * 
	 * @param words
	 *            - the words to be incremented.
	 */
	public void increment(List<String> words) {
		for (String word : words) {
			int count = values.containsKey(word) ? values.get(word) : 0;
			values.put(word, count + 1);
		}
		timesLearned++;
	}

	/**
	 * Decrements valences which are the same like the given words.
	 * 
	 * @param words
	 *            - the words to be decremented.
	 */
	public void decrement(List<String> words) {
		for (String word : words) {
			if (values.containsKey(word)) {
				values.put(word, values.get(word) - 1);
			}
		}
		if (timesLearned > 1) {
			timesLearned--;
		}
	}

	/**
	 * Representing the timesLearned.
	 * 
	 * @return Returns times learned.
	 */
	public int timesLearned() {
		return timesLearned;
	}

	/**
	 * Representing the values.
	 * 
	 * @return Returns the values of this valences.
	 */
	public Map<String, Integer> values() {
		return values;
	}

	/**
	 * Destroys keys with value zero.
	 * 
	 * @return Returns the destroyed keys.
	 */
	public List<String> destroyedZeroKeySets() {
		List<String> destroyedKeySets = new ArrayList<String>();
		for (Entry<String, Integer> entry : values.entrySet()) {
			if (entry.getValue() == 0) {
				destroyedKeySets.add(entry.getKey());
			}
		}
		values.keySet().removeAll(destroyedKeySets);
		return destroyedKeySets;
	}

}
