package de.egatlov.sentiment_api.util;

/**
 * Text.class
 * 
 * Text wraps a String so other help classes can just take it and make live a
 * lot easier.
 * 
 * @author alex
 */
public class Text implements HelpClass<String> {

	/**
	 * The wrapped String
	 */
	private final String string;

	/**
	 * Create a clear Text. Note:</br>
	 * It's just for extending purposes.
	 */
	public Text() {
		this.string = "";
	}

	public Text(String string) {
		this.string = string;
	}

	@Override
	public String get() {
		return string;
	}

}
