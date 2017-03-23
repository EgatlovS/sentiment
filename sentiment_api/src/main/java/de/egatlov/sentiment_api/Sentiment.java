package de.egatlov.sentiment_api;

import java.util.List;

public final class Sentiment {

	private final SentimentWords sentimentWords;
	private final NeutralWords neutralWords;
	private final String name;
	private final String description;

	public Sentiment(NeutralWords neutralWords, SentimentWords sentimentWords, String name, String description) {
		this.sentimentWords = sentimentWords;
		this.neutralWords = neutralWords;
		this.name = name;
		this.description = description;
	}

	public Sentiment(NeutralWords neutralWords, SentimentWords sentimentWords, String name) {
		this(neutralWords, sentimentWords, name, "This is: " + name + " - Sentiment");
	}

	public Sentiment(NeutralWords neutralWords, String name) {
		this(neutralWords, new SentimentWords(), name);
	}

	public Sentiment(SentimentWords sentimentWords, String name) {
		this(new NeutralWords(), sentimentWords, name);
	}

	public Sentiment(String name) {
		this(new NeutralWords(), name);
	}

	public void learn(List<String> words) {
		words = withoutNeutralWords(words);
		sentimentWords.increment(words);
	}

	public void unlearn(List<String> words) {
		words = withoutNeutralWords(words);
		sentimentWords.decrement(words);
	}

	public List<String> withoutNeutralWords(List<String> words) {
		words.removeAll(neutralWords);
		return words;
	}

	public double analyzed(List<String> words) {
		words = withoutNeutralWords(words);
		double valenceSum = 0;
		for (String s : words) {
			if (sentimentWords.containsKey(s)) {
				valenceSum += sentimentWords.get(s);
			}
		}
		return valenceSum / sentimentWords.timesLearned();
	}

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}

	public SentimentWords sentimentWords() {
		return sentimentWords;
	}

	public NeutralWords neutralWords() {
		return neutralWords;
	}

}
