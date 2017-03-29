package de.egatlov.sentiment_api.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egatlov.sentiment_api.sentiment.Valences;
import de.egatlov.sentiment_api.sentiment.control.ControlUnit;
import de.egatlov.sentiment_api.sentiment.control.SentimentCU;

public class JsonCu implements Json<ControlUnit> {

	private final String pathToJson;

	public JsonCu(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	@Override
	public ControlUnit buildObject() throws Exception {
		InputStream inJson = Valences.class.getResourceAsStream(pathToJson);
		SentimentCU scu = null;
		try {
			scu = new ObjectMapper().readValue(inJson, SentimentCU.class);
		} catch (Exception e) {
			throw new Exception("Couldnt create SentimentControlUnit", e.getCause());
		}
		return scu;
	}

}
