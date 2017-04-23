package de.egatlov.sentiment_api.json;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.egatlov.sentiment_api.sentiment.Sentiment;

public class ControlUnitSerializer extends JsonSerializer<Map<Sentiment, Double>> {

	@Override
	public void serialize(Map<Sentiment, Double> map, JsonGenerator jgen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		Set<Sentiment> sentiments = map.keySet();

		jgen.writeObject(sentiments);

	}

}
