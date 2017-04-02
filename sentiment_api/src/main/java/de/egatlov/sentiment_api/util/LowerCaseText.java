package de.egatlov.sentiment_api.util;

public final class LowerCaseText implements HelpClass<String> {

	private final HelpClass<String> hc;

	public LowerCaseText(HelpClass<String> hc) {
		this.hc = hc;
	}

	@Override
	public String get() {
		return hc.get().toLowerCase();
	}

}
