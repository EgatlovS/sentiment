package de.egatlov.sentiment_api.scu;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import de.egatlov.sentiment_api.sentiment.Neutrals;
import de.egatlov.sentiment_api.sentiment.Sentiment;
import de.egatlov.sentiment_api.sentiment.SentimentControlUnit;
import de.egatlov.sentiment_api.sentiment.Valences;

public final class SentimentControlUnitTest {

	@Test
	public void scuCreationSuccess() {
		SentimentControlUnit scu = new SentimentControlUnit(new Sentiment(new Neutrals(), new Valences(), "name"),
				new Sentiment(new Neutrals(), new Valences(), "name"));
		assertThat(scu.sentiments().size()).isEqualTo(2);
	}

	@Test
	public void analyzingDoesSomething() {
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		Sentiment sentiment2 = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment, sentiment2);
		assertThat(scu.analyzed("Text to be analyzed")).isIn(sentiment, sentiment2);
	}

	@Test
	public void addsNewWordsInValencesIfAnalysisWon() {
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);
		scu.analyzed("Text to be analyzed");
		assertThat(sentiment.valences().values()).hasSize(4);
	}

	@Test
	public void doesntAddNewWordsWhenInNeutralsIfAnalysisWon() {
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		// add to neutral then it shouldnt add it
		sentiment.neutrals().words().add("text");
		scu.analyzed("Text to be analyzed");
		assertThat(sentiment.valences().values()).hasSize(3);
	}

	@Test
	public void incrementsTheWinnerAfterAnalyzing() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		Valences words = sentiment.valences();
		assertThat(words.values().get("text")).isEqualTo(1);
	}

	@Test
	public void scuCanTeach() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		Valences words = sentiment.valences();
		assertThat(words.values().get("text")).isEqualTo(1);

		scu.teach(sentiment, "Text to be analyzed");
		assertThat(words.values().get("text")).isEqualTo(2);
	}

	@Test
	public void scuCanUnTeach() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		scu.teach(sentiment, "Text to be analyzed");
		Valences words = sentiment.valences();
		assertThat(words.values().get("text")).isEqualTo(2);

		scu.unteach(sentiment, "Text to be analyzed");
		assertThat(words.values().get("text")).isEqualTo(1);
	}

	@Test
	public void unteachRemovesKeySetFromValencesIfValueIsZero() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new Neutrals(), new Valences(), "name");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		Valences words = sentiment.valences();
		assertThat(words.values().get("text")).isEqualTo(1);

		scu.unteach(sentiment, "Text to be analyzed");
		assertThat(words.values()).doesNotContainKey("text");
	}

}
