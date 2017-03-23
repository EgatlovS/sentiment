package de.egatlov.sentiment_api;

import java.util.List;

public final class Sentiment {

	private final Valences valences;
	private final Neutrals neutrals;
	private final String name;
	private final String description;

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
		words = withoutNeutrals(words);
		valences.increment(words);
	}

	public void unlearn(List<String> words) {
		words = withoutNeutrals(words);
		valences.decrement(words);
	}

	public List<String> withoutNeutrals(List<String> words) {
		words.removeAll(neutrals.words());
		return words;
	}

	public double analyzed(List<String> words) {
		words = withoutNeutrals(words);
		double valenceSum = 0;
		for (String s : words) {
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
