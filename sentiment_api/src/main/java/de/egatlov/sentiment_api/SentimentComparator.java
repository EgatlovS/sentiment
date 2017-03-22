package de.egatlov.sentiment_api;

import java.util.Comparator;

public class SentimentComparator implements Comparator<Sentiment> {

	public int compare(Sentiment sentiment, Sentiment sentiment2) {
		if (sentiment.lastAnalyzingResult() > sentiment2.lastAnalyzingResult())
			return -1; // highest value first
		if (sentiment.lastAnalyzingResult() == sentiment2.lastAnalyzingResult())
			return 0;
		return 1;
	}

}
