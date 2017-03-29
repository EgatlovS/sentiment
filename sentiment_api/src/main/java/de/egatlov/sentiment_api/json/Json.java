package de.egatlov.sentiment_api.json;

public interface Json<T> {

	T buildObject() throws Exception;
	
}
