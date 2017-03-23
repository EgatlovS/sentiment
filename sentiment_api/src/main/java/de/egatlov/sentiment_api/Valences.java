package de.egatlov.sentiment_api;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Valences {

	@JsonProperty
	private int timesLearned;
	@JsonProperty
	private final Map<String, Integer> values;

	public Valences(String pathToJson) throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		Valences valences = null;
		try {
			valences = new ObjectMapper().readValue(inJson, Valences.class);
		} catch (Exception e) {
			throw new Exception("Couldnt initialize Valences", e.getCause());
		}
		this.timesLearned = valences.timesLearned();
		values = valences.values();
	}

	public Valences(Map<String, Integer> map, int timesLearned) {
		values = map;
		this.timesLearned = timesLearned;
	}

	public Valences() {
		this(new HashMap<String, Integer>(), 1);
	}

	public void increment(List<String> words) {
		for (String word : words) {
			int count = values.containsKey(word) ? values.get(word) : 0;
			values.put(word, count + 1);
		}
		timesLearned++;
	}

	public void decrement(List<String> words) {
		for (String word : words) {
			if (values.containsKey(word)) {
				values.put(word, values.get(word) - 1);
			}
		}
		destroyAllZeroKeySets();
		if (timesLearned > 1) {
			timesLearned--;
		}
	}

	public int timesLearned() {
		return timesLearned;
	}

	public Map<String, Integer> values() {
		return values;
	}

	private void destroyAllZeroKeySets() {
		if (values.containsValue(0)) {
			values.values().removeAll(Collections.singleton(0));
		}
	}

}
