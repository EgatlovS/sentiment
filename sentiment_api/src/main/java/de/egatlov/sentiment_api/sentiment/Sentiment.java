package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

/**
 * Sentiment.class</br>
 * </br>
 * Sentiment represents one Sentiment. It provides various methods to learn and
 * unlearn words. You can think of Sentiments as a control unit for Neutrals and
 * Valences.</br>
 * Note:</br>
 * The fields "Name" and "Description" can be used to identify the Sentiment and
 * make them visible for Graphical User Interfaces.
 * 
 * @author egatlov
 */
public final class Sentiment {

	/**
	 * The valenced words for this sentiment.
	 */
	@JsonProperty
	private final Valences valences;
	/**
	 * The neutral words for this sentiment.
	 */
	@JsonProperty
	private final Neutrals neutrals;
	/**
	 * Name of this sentiment.</br>
	 * Note:</br>
	 * Should be used as an identifier.
	 */
	@JsonProperty
	private final String name;
	/**
	 * The description of this Sentiment.</br>
	 * Note:</br>
	 * Can be used as a description for Graphical User Interfaces.
	 */
	@JsonProperty
	private final String description;

	/**
	 * Creates a Sentiment out of it's json representation, which is given.
	 * 
	 * @param json
	 *            - the json representing object.
	 * @throws Exception
	 *             - Exception is thrown if Object couldn't be build.
	 */
	public Sentiment(Json json) throws Exception {
		Sentiment sentiment = json.buildObject(Sentiment.class);
		this.valences = sentiment.valences();
		this.neutrals = sentiment.neutrals();
		this.name = sentiment.name();
		this.description = sentiment.description();
	}

	/**
	 * Creates a Sentiment out of his neutrals, his valences and his name and
	 * description.
	 * 
	 * @param neutrals
	 *            - the neutral words.
	 * @param valences
	 *            - the valenced words.
	 * @param name
	 *            - the name of this sentiment.
	 * @param description
	 *            - the description of this sentiment.
	 */
	@JsonCreator
	public Sentiment(@JsonProperty(value = "neutrals") Neutrals neutrals, @JsonProperty(value = "valences") Valences valences, @JsonProperty(value = "name") String name, @JsonProperty(value = "description") String description) {
		this.valences = valences;
		this.neutrals = neutrals;
		this.name = name;
		this.description = description;
	}

	/**
	 * Creates a Sentiment out of his neutrals, valences and his name.
	 * 
	 * @param neutrals
	 *            - the neutral words.
	 * @param valences
	 *            - the valenced words.
	 * @param name
	 *            - the name of the sentiment.
	 */
	public Sentiment(Neutrals neutrals, Valences valences, String name) {
		this(neutrals, valences, name, "This is: " + name + " - Sentiment");
	}

	/**
	 * Creates a Sentiment out of his neutrals and his name. The valenced words
	 * will be clear.
	 * 
	 * @param neutrals
	 *            - the neutral words.
	 * @param name
	 *            - the name of the sentiment
	 */
	public Sentiment(Neutrals neutrals, String name) {
		this(neutrals, new Valences(), name);
	}

	/**
	 * Creates a Sentiment out of his valenced words and his name. The neutral
	 * words will be cleared.
	 * 
	 * @param valences
	 *            - the valenced words.
	 * @param name
	 *            - the name of the sentiment.
	 */
	public Sentiment(Valences valences, String name) {
		this(new Neutrals(), valences, name);
	}

	/**
	 * Create a Sentiment with a name. Valenced words and Neutral words will be
	 * clear!
	 * 
	 * @param name
	 *            - the name of the sentiment.
	 */
	public Sentiment(String name) {
		this(new Neutrals(), name);
	}

	/**
	 * Learns the given words.
	 * 
	 * @param words
	 *            - the words to be learned.
	 */
	public void learn(List<String> words) {
		List<String> toLearn = withoutNeutrals(words);
		valences.increment(toLearn);
	}

	/**
	 * Unlearns the given words.
	 * 
	 * @param words
	 *            - the wods to be unlearned.
	 */
	public void unlearn(List<String> words) {
		List<String> toUnlearn = withoutNeutrals(words);
		valences.decrement(toUnlearn);
		neutrals.addOrIncrementCandidates(valences.destroyedZeroKeySets());
	}

	/**
	 * Remove neutrals from a list of words.
	 * 
	 * @param words
	 *            - the list of words which should be removed from.
	 * @return Returns a list of words without neutrals.
	 */
	public List<String> withoutNeutrals(List<String> words) {
		List<String> withoutNeutrals = new ArrayList<>();
		withoutNeutrals.addAll(words);
		withoutNeutrals.removeAll(neutrals.words());
		return withoutNeutrals;
	}

	/**
	 * Analyzes the given words and returns the analysis result.
	 * 
	 * @param words
	 *            - the words to be analyzed.
	 * @return Returns the result of the analysis.
	 */
	public double analyzed(List<String> words) {
		List<String> toAnalyze = withoutNeutrals(words);
		double valenceSum = 0;
		for (String s : toAnalyze) {
			if (valences.values().containsKey(s)) {
				valenceSum += valences.values().get(s);
			}
		}
		return valenceSum / valences.timesLearned();
	}

	/**
	 * Representing the name of the Sentiment.
	 * 
	 * @return Returns the name of the Sentiment.
	 */
	public String name() {
		return name;
	}

	/**
	 * Representing the description of the Sentiment.
	 * 
	 * @return Returns the description of the Sentiment.
	 */
	public String description() {
		return description;
	}

	/**
	 * Representing the valences of the Sentiment.
	 * 
	 * @return Returns the valences of the Sentiment.
	 */
	public Valences valences() {
		return valences;
	}

	/**
	 * Representing the neutral words of the Sentiment.
	 * 
	 * @return Returns the neutral words of the Sentiment.
	 */
	public Neutrals neutrals() {
		return neutrals;
	}

}
