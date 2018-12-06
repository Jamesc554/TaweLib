package resources;

import java.util.ArrayList;
/**
 * <h1>DVD</h1>
 * <p>The DVD class creates DVD objects to be used by other classes
 * @author Deyan Naydenov Peter Daish
 * @version 0.0.0.2
 * @since 18/11/2018
*/

public class DVD extends Resource {
	
	private static String highestDVDID = "DVDID:0"; // used to calculate a unique DVD ID.
	
	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
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
	
	public DVD (String director, String runtime, String language, ArrayList<String> subLang, String year, String title, 
			String thumbnailImg, String uniqueID, Integer noOfCopies, ArrayList<String> loanDuration) {
		super(year, title, thumbnailImg, generateDVDID(uniqueID), noOfCopies, loanDuration);
		this.director = director;
		this.runtime = runtime;
		this.language = language;
		this.subLang = subLang;
	}
	private static String generateDVDID(String id) {
		//split DVD ID into two strings if ID == null.
		//convert anything after ": " into DVD
		//append 1
		// convert back to String
		//assign uniqueID
		//
		//If id != null.
		//we are loading a book from a file thus set highestDVDID to id.
		if (id == null) {
		String[] segmentsOfString = highestDVDID.split(":");
		String typeSegment = segmentsOfString[0];
		String numberSegmentOfString = segmentsOfString[1];
		
		int numberOfID = Integer.parseInt(numberSegmentOfString);
		numberOfID++;
		
		highestDVDID = typeSegment + ":" + Integer.toString(numberOfID);
		return highestDVDID;
		} else {
			highestDVDID = id;
			return highestDVDID;
		}
	}
	/**
	 * Sets a value to calculate DVD IDs from.
	 * @param hDVDID the highest current value of any DVD ID.
	 */
	public void setHighestDVDID(String hDVDID) {
		this.highestDVDID = hDVDID;
	}
	
	/**
	 * Sets a value to calculate a new copy's id from.
	 * @param hCopyID the latest ID of any copy.
	 */
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID the newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the ID of the latest DVD.
	 * @return highestDVDID the highest current id of any book.
	 */
	public String getHighestDVDID() {
		return this.highestDVDID;
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
	 * Updates the subtitle languages available
	 * @param langs the subtitle languages available including new additions if any.
	 */
	public void setSubLang(ArrayList<String> langs) {
		this.subLang = langs;
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
	
	/**
	 * Adds a copy to this DVD's unique Array of copies.
	 */
	public void addToCopies() {
		super.addToCopies(generateCopyID());
	}
	
	/**
	 * Generates a copy ID based off of previous copyID.
	 * @return hCpyID the highest ID of copies of dvd's
	 */
	private String generateCopyID() {
		//
		this.highestCopyID++;
		String hCpyID = Integer.toString(this.highestCopyID);
		return hCpyID;
	}
}
