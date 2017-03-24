package de.egatlov.sentiment_api.util;

public class CleanText extends Text {

	private final Text text;
	
	public CleanText(Text text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text.toString().replaceAll("[^A-Za-z\\s]", "");
	}
	
}
