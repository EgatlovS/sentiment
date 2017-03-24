package de.egatlov.sentiment_api.util;

import java.util.ArrayList;
import java.util.Arrays;

public class SplittedText extends ArrayList<String> {

	private static final long serialVersionUID = 1L;

	public SplittedText(Text text) {
		// split on spaces to get words
		this.addAll(Arrays.asList(text.toString().split(" ")));
	}

}
