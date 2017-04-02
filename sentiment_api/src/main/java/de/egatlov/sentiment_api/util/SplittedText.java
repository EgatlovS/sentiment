package de.egatlov.sentiment_api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SplittedText.class</br>
 * </br>
 * SplittedText represents an ArrayList. It adds an additional Constructor to
 * make a List out of a String splitted by spaces.
 * 
 * @author egatlov
 */
public final class SplittedText extends ArrayList<String> implements HelpClass<List<String>> {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates an ArrayList<String> out of a HelpClass representing a String
	 * object.
	 * 
	 * @param hc
	 *            - the help class to transform.
	 */
	public SplittedText(HelpClass<String> hc) {
		// split on spaces to get words
		this.addAll(Arrays.asList(hc.get().split(" ")));
	}

	/**
	 * Note:</br>
	 * This class is already representing an ArrayList of Strings so it just
	 * returns itself.
	 * 
	 * @return Returns a List of strings splitted by spaces.
	 */
	@Override
	public List<String> get() {
		return this;
	}

}
