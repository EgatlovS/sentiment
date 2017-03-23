package de.egatlov.sentiment_api.sentiment;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.egatlov.sentiment_api.NeutralWords;
import de.egatlov.sentiment_api.Sentiment;
import de.egatlov.sentiment_api.SentimentWords;

public class SentimentTest {

	@Test
	public void sentimentCreationSuccess() {
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name of sentiment",
				"description of sentiment");
		assertThat(sentiment).isNotNull();
	}

	@Test
	public void sentimentCreationSettingFields() {
		String name = "name of sentiment";
		String description = "description of sentiment";
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), name, description);
		assertThat(sentiment.name()).isEqualTo(name);
		assertThat(sentiment.description()).isEqualTo(description);
	}

	@Test
	public void analyzingReturnsOnlyPositivesOrZero() {
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name of sentiment",
				"description of sentiment");
		List<String> words = new ArrayList<String>(Arrays.asList("Text to be analyzed".split(" ")));
		assertThat(sentiment.analyzed(words)).isGreaterThanOrEqualTo(0);
	}

}
