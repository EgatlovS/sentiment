package de.egatlov.sentiment_api.json;

import java.io.File;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json.class</br>
 * </br>
 * This class is made for creating Objects out of JSON in a Single line of Code.
 * 
 * @author egatlov
 */
public final class Json {

	private final String pathToJson;

	/**
	 * Creating a Json-Object.
	 * 
	 * @param pathToJson
	 *            - the path where the json file is located.
	 */
	public Json(String pathToJson) {
		this.pathToJson = pathToJson;
	}

	/**
	 * Build an Object out of the given path in the Constructor.
	 * 
	 * @param clazz
	 *            - The class represented in the json file.
	 * @return Returns an Object from Type of the given class made out of the
	 *         Json file.
	 * @throws Exception
	 *             - Throws an Exception if the Object weren't build.
	 */
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

	public void write(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(pathToJson), object);
		} catch (Exception e){
			throw new Exception("Couldn't write Object", e.getCause());
		}
	}

}
