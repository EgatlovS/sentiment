package de.egatlov.sentiment_api;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Neutrals {

	@JsonProperty
	private final List<String> words;

	public Neutrals(String pathToJson) throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		Neutrals neutrals = null;
		try {
			neutrals = new ObjectMapper().readValue(inJson, Neutrals.class);
		} catch (Exception e) {
			throw new Exception("Couldnt initialize Neutrals", e.getCause());
		}
		this.words = neutrals.words();
	}

	public Neutrals(List<String> words) {
		this.words = words;
	}

	public Neutrals() {
		this(new ArrayList<String>());
	}

	public List<String> words() {
		return words;
	}

}
