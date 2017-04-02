package de.egatlov.sentiment_api.util;

/**
 * Text.class</br>
 * </br>
 * Text wraps a String so other help classes can just take it and make live a
 * lot easier.
 * 
 * @author egatlov
 */
public class Text implements HelpClass<String> {

	/**
	 * The wrapped String
	 */
	private final String string;

	/**
	 * Create Text with the given String.
	 * 
	 * @param string
	 *            - the string to be wrapped.
	 */
	public Text(String string) {
		this.string = string;
	}

	/**
	 * @return Returns the wrapped String.
	 */
	@Override
	public String get() {
		return string;
	}

}
