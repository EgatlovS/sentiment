package de.egatlov.sentiment_api.util;

public class Text {

	private final String string;

	public Text() {
		this.string = "";
	}

	public Text(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

}
