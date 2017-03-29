package de.egatlov.sentiment_api.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.sentiment.Valences;

public class JsonSentiment implements Json<Sentiment> {

	private final String pathToJson;

	public JsonSentiment(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	@Override
	public Sentiment buildObject() throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		Sentiment sentiment = null;
		try {
			sentiment = new ObjectMapper().readValue(inJson, Sentiment.class);
		} catch (Exception e) {
			throw new Exception("Couldnt create Sentiment", e.getCause());
		}
		return sentiment;
	}

}
