package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

public final class Sentiment {

	@JsonProperty
	private final Valences valences;
	@JsonProperty
	private final Neutrals neutrals;
	@JsonProperty
	private final String name;
	@JsonProperty
	private final String description;

	public Sentiment(Json json) throws Exception {
		Sentiment sentiment = json.buildObject(Sentiment.class);
		this.valences = sentiment.valences();
		this.neutrals = sentiment.neutrals();
		this.name = sentiment.name();
		this.description = sentiment.description();
	}

	public Sentiment(Neutrals neutrals, Valences valences, String name, String description) {
		this.valences = valences;
		this.neutrals = neutrals;
		this.name = name;
		this.description = description;
	}

	public Sentiment(Neutrals neutrals, Valences valences, String name) {
		this(neutrals, valences, name, "This is: " + name + " - Sentiment");
	}

	public Sentiment(Neutrals neutrals, String name) {
		this(neutrals, new Valences(), name);
	}

	public Sentiment(Valences valences, String name) {
		this(new Neutrals(), valences, name);
	}

	public Sentiment(String name) {
		this(new Neutrals(), name);
	}

	public void learn(List<String> words) {
		List<String> toLearn = withoutNeutrals(words);
		valences.increment(toLearn);
	}

	public void unlearn(List<String> words) {
		List<String> toUnlearn = withoutNeutrals(words);
		valences.decrement(toUnlearn);
		neutrals.addOrIncrementCandidates(valences.destroyedZeroKeySets());
	}

	public List<String> withoutNeutrals(List<String> words) {
		List<String> withoutNeutrals = new ArrayList<>();
		withoutNeutrals.addAll(words);
		withoutNeutrals.removeAll(neutrals.words());
		return withoutNeutrals;
	}

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

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}

	public Valences valences() {
		return valences;
	}

	public Neutrals neutrals() {
		return neutrals;
	}

}
