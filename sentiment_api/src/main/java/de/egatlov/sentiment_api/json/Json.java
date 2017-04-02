package de.egatlov.sentiment_api.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

	private final String pathToJson;

	public Json(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	public <T> T buildObject(Class<T> clazz) throws Exception {
		InputStream inJson = Json.class.getResourceAsStream(pathToJson);
		T object = null;
		try {
			object = new ObjectMapper().readValue(inJson, clazz);
		} catch (Exception e) {
			throw new Exception("Couldnt create Object", e.getCause());
		}
		return object;
	}

}
