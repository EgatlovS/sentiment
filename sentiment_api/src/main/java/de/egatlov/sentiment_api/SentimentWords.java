package de.egatlov.sentiment_api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class SentimentWords extends HashMap<String, Integer> {

	private static final long serialVersionUID = 1L;
	private int timesLearned;

	// make some useful constructors
	public SentimentWords() {
		this.timesLearned = 1;
	}

	public void increment(List<String> words) {
		for (String word : words) {
			int count = this.containsKey(word) ? this.get(word) : 0;
			this.put(word, count + 1);
		}
		timesLearned++;
	}

	public void decrement(List<String> words) {
		for (String word : words) {
			if (this.containsKey(word)) {
				this.put(word, this.get(word) - 1);
			}
		}
		destroyAllZeroKeySets();
		timesLearned--;
	}

	private void destroyAllZeroKeySets() {
		if (this.containsValue(0)) {
			this.values().removeAll(Collections.singleton(0));
		}
	}

	public int timesLearned() {
		return timesLearned;
	}

}
