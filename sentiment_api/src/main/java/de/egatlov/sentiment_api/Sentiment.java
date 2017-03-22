package de.egatlov.sentiment_api;

import java.util.List;

public class Sentiment {

	private final SentimentWords sentimentWords;
	private final NeutralWords neutralWords;
	private final String name;
	private final String description;
	private double lastResult;
	private int timesLearned;

	public Sentiment(NeutralWords neutralWords, SentimentWords sentimentWords, String name, String description) {
		this.sentimentWords = sentimentWords;
		this.neutralWords = neutralWords;
		this.name = name;
		this.description = description;
		this.timesLearned = 1;
	}

	public SentimentWords sentimentWords() {
		return sentimentWords;
	}

	public NeutralWords neutralWords() {
		return neutralWords;
	}

	public void learn(List<String> words) {
		words = filterdWords(words);
		sentimentWords.increment(words);
		timesLearned++;
	}

	public void unlearn(List<String> words) {
		words = filterdWords(words);
		sentimentWords.decrement(words);
		timesLearned--;
	}

	public List<String> filterdWords(List<String> words) {
		words.removeAll(neutralWords);
		return words;
	}

	public void analyze(List<String> words) {
		words = filterdWords(words);
		lastResult = 0;
		for (String s : words) {
			if (sentimentWords.containsKey(s)) {
				lastResult += sentimentWords.get(s);
			}
		}
	}

	public double lastAnalyzingResult() {
		return lastResult / timesLearned;
	}

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}

}
