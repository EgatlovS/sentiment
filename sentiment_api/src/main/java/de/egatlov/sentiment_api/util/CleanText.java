package de.egatlov.sentiment_api.util;

public final class CleanText implements HelpClass<String> {

	private final HelpClass<String> hc;

	public CleanText(HelpClass<String> hc) {
		this.hc = hc;
	}

	@Override
	public String get() {
		return hc.get().replaceAll("[^A-Za-z\\s]", "");
	}

}
