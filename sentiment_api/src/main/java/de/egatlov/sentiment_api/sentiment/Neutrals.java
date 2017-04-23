package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

/**
 * Neutrals.class</br>
 * </br>
 * Neutrals presents the neutral words, which should not be considered in
 * sentiment analysis.
 * 
 * @author egatlov
 */
public final class Neutrals {

	/**
	 * The neutral words.
	 */
	@JsonProperty
	private final List<String> words;
	/**
	 * Candidates for neutral words. Note:</br>
	 * Key: the candidate word.</br>
	 * Value: times unlearned.</br>
	 */
	@JsonProperty
	private final Map<String, Integer> candidates;

	/**
	 * Creates Neutrals out of Json.
	 * 
	 * @param json
	 *            - the json representing Object.
	 * @throws Exception
	 *             - Exception is thrown if object couldn't be created.
	 */
	public Neutrals(Json json) throws Exception {
		Neutrals neutrals = json.buildObject(Neutrals.class);
		this.words = neutrals.words();
		this.candidates = neutrals.candidates();
	}

	/**
	 * Create neutrals out of words and candidates.
	 * 
	 * @param words
	 *            - the neutral words.
	 * @param candidates
	 *            - the candidates.
	 */
	public Neutrals(List<String> words, Map<String, Integer> candidates) {
		this.words = words;
		this.candidates = candidates;
	}

	/**
	 * Create cleared Neutrals.
	 */
	public Neutrals() {
		this(new ArrayList<String>(), new HashMap<String, Integer>());
	}

	/**
	 * Adds given words to candidates or increments there values if already
	 * present.
	 * 
	 * @param words
	 *            - the words to add or increment.
	 */
	public void addOrIncrementCandidates(List<String> words) {
		for (String word : words) {
			int count = candidates.containsKey(word) ? candidates.get(word) : 0;
			candidates.put(word, count + 1);
		}
		words.addAll(this.candidatesToPromote());
	}

	/**
	 * Checks candidates for the words which should be promoted.
	 * 
	 * @return Returns candidates to promote.
	 */
	private List<String> candidatesToPromote() {
		List<String> candidatesToPromote = new ArrayList<String>();
		for (Entry<String, Integer> entry : candidates.entrySet()) {
			if (entry.getValue() > 50) {
				candidates.remove(entry.getKey());
				candidatesToPromote.add(entry.getKey());
			}
		}
		return candidatesToPromote;
	}

	/**
	 * Representing all candidates.
	 * 
	 * @return Returns all candidates.
	 */
	public Map<String, Integer> candidates() {
		return candidates;
	}

	/**
	 * Representing neutral words.
	 * 
	 * @return Returns all candidates.
	 */
	public List<String> words() {
		return words;
	}

}
