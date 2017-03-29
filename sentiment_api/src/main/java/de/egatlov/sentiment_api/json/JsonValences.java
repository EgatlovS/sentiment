package de.egatlov.sentiment_api.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egatlov.sentiment_api.sentiment.Valences;

public class JsonValences implements Json<Valences> {

	private final String pathToJson;

	public JsonValences(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	@Override
	public Valences buildObject() throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		Valences valences = null;
		try {
			valences = new ObjectMapper().readValue(inJson, Valences.class);
		} catch (Exception e) {
			throw new Exception("Couldnt create Valences", e.getCause());
		}
		return valences;
	}

}
