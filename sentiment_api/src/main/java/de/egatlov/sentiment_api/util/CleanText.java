package de.egatlov.sentiment_api.util;

/**
 * CleanText.class</br>
 * </br>
 * This HelpClass turns a String representing HelpClass into a clean text.
 * Note:</br>
 * CleanText means a text without characters not equal to the alphabet or
 * spaces.
 * 
 * @author egatlov
 */
public final class CleanText implements HelpClass<String> {

	/**
	 * The string wrapping HelpClass
	 */
	private final HelpClass<String> hc;

	/**
	 * Create clean text out of a String wrapping Help Class.
	 * 
	 * @param hc
	 *            - the string wrapping help class.
	 */
	public CleanText(HelpClass<String> hc) {
		this.hc = hc;
	}

	/**
	 * @return Returns clean text.
	 */
	@Override
	public String get() {
		return hc.get().replaceAll("[^A-Za-z\\s]", "");
	}

}
