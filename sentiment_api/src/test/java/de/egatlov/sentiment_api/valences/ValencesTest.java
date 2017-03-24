package de.egatlov.sentiment_api.valences;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.egatlov.sentiment_api.sentiment.Valences;

public class ValencesTest {

	@Test
	public void creationSuccess() {
		Valences valences = new Valences();
		assertThat(valences.values()).isEmpty();
		assertThat(valences.timesLearned()).isEqualTo(1);
	}

	@Test
	public void creationSuccessWithPathToJson() throws Exception {
		Valences valences = new Valences("/json/valences.json");
		assertThat(valences.values()).hasSize(3);
		assertThat(valences.timesLearned()).isEqualTo(1);
	}

	@Test
	public void creationSuccesWithMapAndTimesLearned() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Word", 1);
		map.put("some", 2);
		Valences valences = new Valences(map, 2);
		assertThat(valences.values()).hasSize(2);
		assertThat(valences.timesLearned()).isEqualTo(2);
	}

	@Test
	public void timesLearnedIncreasesOnIncrement() {
		Valences valences = new Valences();
		List<String> words = new ArrayList<String>();
		words.add("Word");
		valences.increment(words);

		assertThat(valences.timesLearned()).isEqualTo(2);
	}

	@Test
	public void timesLearnedDecreasesOnDecrement() {
		Valences valences = new Valences();
		List<String> words = new ArrayList<String>();
		words.add("Word");
		valences.decrement(words);

		// never below one
		assertThat(valences.timesLearned()).isEqualTo(1);

		valences.increment(words);
		valences.increment(words);
		valences.decrement(words);

		assertThat(valences.timesLearned()).isEqualTo(2);
	}

	@Test
	public void incrementIncreasesOnlyRightValences() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Word", 1);
		map.put("some", 2);
		Valences valences = new Valences(map, 2);
		List<String> words = new ArrayList<String>();
		words.add("Word");
		valences.increment(words);

		assertThat(valences.values().get("Word")).isEqualTo(2);
		assertThat(valences.values().get("some")).isEqualTo(2);
	}

	@Test
	public void incrementAddsNewWords() {
		Valences valences = new Valences();
		List<String> words = new ArrayList<String>();
		words.add("Word");
		valences.increment(words);

		assertThat(valences.values()).hasSize(1);
	}

	@Test
	public void decrementDecreasesAllValences() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Word", 1);
		map.put("some", 2);
		Valences valences = new Valences(map, 2);
		List<String> words = new ArrayList<String>();
		words.add("some");
		valences.decrement(words);

		assertThat(valences.values().get("Word")).isEqualTo(1);
		assertThat(valences.values().get("some")).isEqualTo(1);
	}

}
