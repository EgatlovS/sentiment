package de.egatlov.sentiment_api.util;

/**
 * LowerCaseText.class</br>
 * </br>
 * This HelpClass turns a String representing HelpClass into a lowerCase String.
 * 
 * @author egatlov
 */
public final class LowerCaseText implements HelpClass<String> {

	/**
	 * The HelpClass wrapping a String
	 */
	private final HelpClass<String> hc;

	/**
	 * Create a lower case text out of a String wrapping HelpClass.
	 * 
	 * @param hc
	 *            - the string wrapping helpclass.
	 */
	public LowerCaseText(HelpClass<String> hc) {
		this.hc = hc;
	}

	/**
	 * @return Returns a lowerCase String.
	 */
	@Override
	public String get() {
		return hc.get().toLowerCase();
	}

}
