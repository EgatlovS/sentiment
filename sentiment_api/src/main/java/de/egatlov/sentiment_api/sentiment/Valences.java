package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

public final class Valences {

	@JsonProperty
	private int timesLearned;
	@JsonProperty
	private final Map<String, Integer> values;

	public Valences(Json<Valences> json) throws Exception {
		Valences valences = json.buildObject();
		this.timesLearned = valences.timesLearned();
		this.values = valences.values();
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
