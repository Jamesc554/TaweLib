package resources;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>DVD.</h1>
 * <p>The DVD class creates DVD objects to be used by other classes.</p>
 * @author Deyan Naydenov Peter Daish, James Carter.
 * @version 1.0
 * @since 18/11/2018
*/
public class DVD extends Resource {
	private final double FINE = 2.00; //The daily fine rate of this DVD.
	private final double MAX_FINE = 25.00; // The maximum fine this DVD can reach.
	
	private static String highestDVDID = "DVDID:0"; // used to calculate a unique DVD ID.

	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
	private String director; //The director of this DVD
	private String runtime; //The runtime of this DVD.
	private String language; //The languages this DVD is available in.
	ArrayList<String> subLang = new ArrayList<String>(); //The subtitle languages this DVD is available in.
	
	/**
	 * The constructor for a DVD
	 * @param director
	 * The director of this DVD
	 * @param runtime
	 * The runtime of this DVD
	 * @param language
	 * The language this available in.
	 * @param subLang
	 * The subtitle languages this DVD is available in.
	 * @param year
	 * The year this DVD was released.
	 * @param title
	 * The title of this DVD.
	 * @param thumbnailImg
	 * The filepath for the avatar of this DVD.
	 * @param uniqueID
	 * The unique id of this DVD.
	 * @param noOfCopies
	 * The number of copies the library currently owns of this DVD.
	 * @param loanDuration
	 * The loan duration of this DVD.
	 * @param borrowHistory
	 * The borrow history of a copy of this DVD
	 * @param currentBorrowData
	 * The information for borrower and dates associated with a copy of a resource.
	 */
	public DVD (String director, String runtime, String language, ArrayList<String> subLang, String year, String title, 
			String thumbnailImg, String uniqueID, Integer noOfCopies, ArrayList<String> loanDuration,
			List<List<BorrowHistoryData>> borrowHistory, List<BorrowHistoryData> currentBorrowData) {
		super(year, title, thumbnailImg, generateDVDID(uniqueID), noOfCopies, loanDuration, borrowHistory, currentBorrowData);
		this.director = director;
		this.runtime = runtime;
		this.language = language;
		this.subLang = subLang;
	}
	
	/**
	 * Generates a unique DVD ID if not already set.
	 * @param id
	 * The id of the DVD.
	 * @return highestDVDID
	 * The id of the latest created book.
	 */
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
	 * @param hDVDID 
	 * The highest current value of any DVD ID.
	 */
	public void setHighestDVDID(String hDVDID) {
		this.highestDVDID = hDVDID;
	}
	
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID 
	 * The newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	/**
	 * Sets the highest copy id to the latest ID of any DVD
	 * @param hCopyID
	 * The highest current copyID.
	 */
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the ID of the latest DVD.
	 * @return highestDVDID 
	 * The highest current id of any book.
	 */
	public String getHighestDVDID() {
		return this.highestDVDID;
	}
	
	/**
	 * Converts DVD to single string
	 * @return a String representation of this DVD.
	 * Returns the details of this DVD.
	 */
	public String toSingleString() {
		return super.toSingleString() + this.director + this.runtime + this.language;
	}
	
	/**
	 * Finds the director of the DVD
	 * @return director 
	 * The director of this DVD
	 */
	public String getDirector() {
		return this.director;
	}
	
	/**
	 * Finds the length of the DVD
	 * @return runtime 
	 * The runtime of this DVD
	 */
	public String getRuntime() {
		return this.runtime;
	}
	
	/**
	 * Finds the original language of the DVD
	 * @return language 
	 * The spoken language this DVD is available in.
	 */
	public String getLanguage() {
		return this.language;
	}
	
	/**
	 * Returns the subtitle languages available in this DVD
	 * @return subLang
	 * The languages which have subtitles in this DVD
	 */
	public ArrayList<String> getSubLang() {
		return this.subLang;
	}
	
	/**
	 * Updates the subtitle languages available
	 * @param langs 
	 * The subtitle languages available including new additions if any.
	 */
	public void setSubLang(ArrayList<String> langs) {
		this.subLang = langs;
	}
	
	/**
	 * Defines the director of the DVD.
	 * @param director
	 * The director of this DVD.
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * Defines the length of the DVD.
	 * @param runtime
	 * The runtime of this DVD.
	 */
	public void setRuntime (String runtime) {
		this.runtime = runtime;
	}
	
	/**
	 * Sets the spoken language of the DVD.
	 * @param language
	 * The spoken language of the DVD.
	 */
	public void setLanguage (String language) {
		this.language = language;
	}
	
	/**
	 * Adds a copy to this DVD's unique Array of copies.
	 * @param loanDuration Length of a loan duration in days.
	 */
	public void addToCopies(String loanDuration) {
		super.addCopy(loanDuration);
	}

	/**
	 * Returns the daily fine rate of this resource.
	 * @return FINE
	 * The daily fine rate of this resource.
	 */
	public double getFineAmount() {
		return this.FINE;
	}

	/**
	 * Returns the maximum fine this resource can reach.
	 * @return MAX_FINE
	 * The maximum fine a resource can reach.
	 */
	public double getMaxFine() {
		return  this.MAX_FINE;
	}
}
