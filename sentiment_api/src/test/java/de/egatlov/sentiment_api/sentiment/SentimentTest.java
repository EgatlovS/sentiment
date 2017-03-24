package de.egatlov.sentiment_api.sentiment;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SentimentTest {

	@Test
	public void sentimentCreationSuccess() {
		String name = "name of sentiment";
		String description = "description of sentiment";
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), name, description);
		assertThat(sentiment).isNotNull();
		assertThat(sentiment.name()).isEqualTo(name);
		assertThat(sentiment.description()).isEqualTo(description);
	}

	@Test
	public void sentimentCreationSuccessWithoutDefaultDescription() {
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		assertThat(sentiment.description()).isNotNull();
	}

	@Test
	public void sentimentCreationSuccessWithoutNeutrals() {
		Sentiment sentiment = new Sentiment(new Valences(), "name");
		assertThat(sentiment.neutrals().words()).isEmpty();
		assertThat(sentiment.description()).isNotNull();
	}

	@Test
	public void sentimentCreationSuccessWithoutValences() {
		Sentiment sentiment = new Sentiment(new Neutrals(), "name");
		assertThat(sentiment.valences().values()).isEmpty();
		assertThat(sentiment.description()).isNotNull();
	}

	@Test
	public void simpleSentimentCreationSuccess() {
		Sentiment sentiment = new Sentiment("name");
		assertThat(sentiment.valences().values()).isEmpty();
		assertThat(sentiment.neutrals().words()).isEmpty();
		assertThat(sentiment.description()).isNotNull();
	}

	@Test
	public void analyzingReturnsOnlyPositivesOrZero() {
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name of sentiment",
				"description of sentiment");
		List<String> words = new ArrayList<String>(Arrays.asList("Text to be analyzed".split(" ")));
		assertThat(sentiment.analyzed(words)).isGreaterThanOrEqualTo(0);
	}

	@Test
	public void canLearn() {
		Sentiment sentiment = new Sentiment("name");
		List<String> words = new ArrayList<String>(Arrays.asList("Text to be analyzed".split(" ")));
		assertThat(sentiment.valences().values()).isEmpty();

		sentiment.learn(words);
		assertThat(sentiment.valences().values()).hasSize(4);
		assertThat(sentiment.valences().values().get("Text")).isEqualTo(1);

		sentiment.learn(words);
		assertThat(sentiment.valences().values().get("Text")).isEqualTo(2);
	}

	@Test
	public void canUnlearn() {
		Valences valences = new Valences();
		valences.values().put("Word", 1);
		Sentiment sentiment = new Sentiment(valences, "name");
		List<String> words = new ArrayList<String>();
		words.add("Word");

		sentiment.unlearn(words);
		assertThat(sentiment.valences().values().get("Word")).isNull();
	}

	@Test
	public void canRemoveNeutralsFromList() {
		Neutrals neutrals = new Neutrals();
		neutrals.words().add("Word");
		Sentiment sentiment = new Sentiment(neutrals, "name");
		List<String> words = new ArrayList<String>();
		words.add("Word");

		assertThat(sentiment.withoutNeutrals(words)).hasSize(0);
	}

}
