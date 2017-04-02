package de.egatlov.sentiment_api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SplittedText extends ArrayList<String> implements HelpClass<List<String>> {

	private static final long serialVersionUID = 1L;

	public SplittedText(HelpClass<String> hc) {
		// split on spaces to get words
		this.addAll(Arrays.asList(hc.get().split(" ")));
	}

	@Override
	public List<String> get() {
		return this;
	}

}
