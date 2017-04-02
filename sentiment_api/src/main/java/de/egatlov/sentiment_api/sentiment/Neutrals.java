package de.egatlov.sentiment_api.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egatlov.sentiment_api.json.Json;

public final class Neutrals {

	@JsonProperty
	private final List<String> words;
	@JsonProperty
	private final Map<String, Integer> candidates;

	public Neutrals(Json json) throws Exception {
		Neutrals neutrals = json.buildObject(Neutrals.class);
		this.words = neutrals.words();
		this.candidates = neutrals.candidates();
	}

	public Neutrals(List<String> words, Map<String, Integer> candidates) {
		this.words = words;
		this.candidates = candidates;
	}

	public Neutrals() {
		this(new ArrayList<String>(), new HashMap<String, Integer>());
	}

	public void addOrIncrementCandidates(List<String> words) {
		for (String word : words) {
			int count = candidates.containsKey(word) ? candidates.get(word) : 0;
			candidates.put(word, count + 1);
		}
		words.addAll(this.candidatesToPromote());
	}

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

	public Map<String, Integer> candidates() {
		return candidates;
	}

	public List<String> words() {
		return words;
	}

}
