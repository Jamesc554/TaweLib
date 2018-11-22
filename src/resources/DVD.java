package resources;

import java.util.ArrayList;
/**
 * <h1>DVD</h1>
 * <p>The DVD class creates DVD objects to be used by other classes
 * @author Deyan Naydenov
 * @version 0.0.0.1
 * @since 18/11/2018
*/

public class DVD {
	private String director;
	private String runtime;
	private String language;
	ArrayList<String> subLang = new ArrayList<String>();
	
	/**
	 * Constructor used to create DVD objects.
	 * @param director
	 *  Who the director of the DVD was.
	 * @param runtime
	 *  How long the DVD is.
	 * @param language
	 * What the DVDs original language is 
	 * @param subLang
	 * The list of available subtitle languages for the DVD 
	 */
	
	public DVD (String director, String runtime, String language, ArrayList<String> subLang) {
	}
	/**
	 * Finds the director of the DVD
	 * @return director 
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * Finds the length of the DVD
	 * @return runtime 
	 */
	public String getRuntime() {
		return runtime;
	}
	/**
	 * Finds the original language of the DVD
	 * @return language 
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Finds the list of available subtitle languages of the DVD
	 * @return manufacturer 
	 */
	public ArrayList<String> getSubLang() {
		return subLang;
	}
	/**
	 * Defines the director of the DVD.
	 * @param director
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * Defines the length of the DVD.
	 * @param runtime
	 */
	public void setRuntime (String runtime) {
		this.runtime = runtime;
	}
	/**
	 * Defines a original language of the DVD.
	 * @param language
	 */
	public void setLanguage (String language) {
		this.language = language;
	}
	
}
