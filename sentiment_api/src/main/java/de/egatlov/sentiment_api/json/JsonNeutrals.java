package de.egatlov.sentiment_api.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egatlov.sentiment_api.sentiment.Neutrals;
import de.egatlov.sentiment_api.sentiment.Valences;

public class JsonNeutrals implements Json<Neutrals> {

	private final String pathToJson;

	public JsonNeutrals(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	@Override
	public Neutrals buildObject() throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		Neutrals neutrals = null;
		try {
			neutrals = new ObjectMapper().readValue(inJson, Neutrals.class);
		} catch (Exception e) {
			throw new Exception("Couldnt create Neutrals", e.getCause());
		}
		return neutrals;
	}

}
