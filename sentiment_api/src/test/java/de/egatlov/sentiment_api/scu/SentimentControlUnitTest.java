package de.egatlov.sentiment_api.scu;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import de.egatlov.sentiment_api.NeutralWords;
import de.egatlov.sentiment_api.Sentiment;
import de.egatlov.sentiment_api.SentimentControlUnit;
import de.egatlov.sentiment_api.SentimentWords;

public class SentimentControlUnitTest {

	@Test
	public void scuCreationSuccess() {
		SentimentControlUnit scu = new SentimentControlUnit(
				new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d"),
				new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d"));
		assertThat(scu.sentiments().size()).isEqualTo(2);
	}

	@Test
	public void analyzingDoesSomething() {
		SentimentControlUnit scu = new SentimentControlUnit(
				new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d"),
				new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d"));
		assertThat(scu.analyzed("Text to be analyzed")).isIn(scu.sentiments().get(0), scu.sentiments().get(1));
	}

	@Test
	public void addsNewWordsInSentimentWordsIfAnalysisWon() {
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		assertThat(sentiment.sentimentWords()).hasSize(4);
	}

	@Test
	public void doesntAddNewWordsWhenInNeutralWordsIfAnalysisWon() {
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		// add to neutral then it shouldnt add it
		sentiment.neutralWords().add("Text");
		scu.analyzed("Text to be analyzed");
		assertThat(sentiment.sentimentWords()).hasSize(3);
	}

	@Test
	public void incrementsTheWinnerAfterAnalyzing() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		SentimentWords words = sentiment.sentimentWords();
		assertThat(words.get("Text")).isEqualTo(1);
	}

	@Test
	public void scuCanTeach() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		SentimentWords words = sentiment.sentimentWords();
		assertThat(words.get("Text")).isEqualTo(1);

		scu.teach(sentiment, "Text to be analyzed");
		assertThat(words.get("Text")).isEqualTo(2);
	}

	@Test
	public void scuCanUnTeach() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		scu.teach(sentiment, "Text to be analyzed");
		SentimentWords words = sentiment.sentimentWords();
		assertThat(words.get("Text")).isEqualTo(2);

		scu.unteach(sentiment, "Text to be analyzed");
		assertThat(words.get("Text")).isEqualTo(1);
	}

	@Test
	public void unteachRemovesKeySetFromSentimentWordsIfValueIsZero() {
		// only one Analysis so it wins everytime
		Sentiment sentiment = new Sentiment(new NeutralWords(), new SentimentWords(), "name", "d");
		SentimentControlUnit scu = new SentimentControlUnit(sentiment);

		scu.analyzed("Text to be analyzed");
		SentimentWords words = sentiment.sentimentWords();
		assertThat(words.get("Text")).isEqualTo(1);

		scu.unteach(sentiment, "Text to be analyzed");
		assertThat(words).doesNotContainKey("Text");
	}

}
