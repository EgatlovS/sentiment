package de.egatlov.sentiment_api.util;

public class LowerCaseText extends Text {

	private final Text text;
	
	public LowerCaseText(Text text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text.toString().toLowerCase();
	}
	
}
