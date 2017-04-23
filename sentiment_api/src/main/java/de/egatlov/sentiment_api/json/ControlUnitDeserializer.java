package de.egatlov.sentiment_api.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.egatlov.sentiment_api.sentiment.Sentiment;

public class ControlUnitDeserializer extends JsonDeserializer<Map<Sentiment, Double>> {

	@Override
	public Map<Sentiment, Double> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Map<Sentiment, Double> map = new HashMap<>();
		List<Sentiment> sents = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jp);
		for (JsonNode jsonNode : root) {
			Sentiment sentiment = mapper.readValue(jsonNode.toString(), Sentiment.class);
			sents.add(sentiment);
		}
		for (Sentiment sentiment : sents) {
			map.put(sentiment, 0.0);
		}
		return map;
	}

}
