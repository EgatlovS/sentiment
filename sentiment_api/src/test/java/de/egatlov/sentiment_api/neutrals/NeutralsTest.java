package de.egatlov.sentiment_api.neutrals;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.egatlov.sentiment_api.sentiment.Neutrals;

public class NeutralsTest {

	@Test
	public void creationSuccess() {
		Neutrals neutrals = new Neutrals();
		assertThat(neutrals.words()).isEmpty();
	}

	@Test
	public void creationSuccesWithWordsAndCandidates() {
		List<String> words = new ArrayList<String>();
		words.add("Word");
		words.add("Some");
		Neutrals neutrals = new Neutrals(words, new HashMap<String, Integer>());
		assertThat(neutrals.words()).hasSize(2);
	}

}
