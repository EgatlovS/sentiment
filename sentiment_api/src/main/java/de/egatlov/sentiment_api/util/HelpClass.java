package de.egatlov.sentiment_api.util;

/**
 * HelpClass.class</br>
 * </br>
 * This interface describes a class which is made for helping in some simple
 * logic which if not encapsulated would make code just less readable.
 * 
 * @author egatlov
 */
public interface HelpClass<T> {

	/**
	 * @return The Object represented by this HelpClass.
	 */
	T get();

}
