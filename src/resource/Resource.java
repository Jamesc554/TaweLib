package resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
//import java.util.Queue; --Don't need since we're implementing Queue ourselves.
/**
 * <h1>Resource</h1>
 * <p>Resource is an abstract class which provides essential attributes for all resource types
 * @author Peter Daish
 * @version 0.1
 * @since 11/11/2018
 */
public abstract class Resource {
	protected String year;
	protected String title;
	protected String thumbnailImageRef;
	protected String uniqueID;
	protected Dictionary dictionaryOfCopies;
	protected Queue queueOfReservations;
	protected Dictionary borrowHistory;//Dictionary of copy history i.e. loan date users who loaned etc.
	protected ArrayList<String[][][]> copyHistory;
	/**
	 * Base Constructor for all resources.
	 * @param year
	 *  The year of release of the resource.
	 * @param title
	 *  The title of the resource.
	 * @param thumbnailImageRef#
	 *  The location of the avatar image on disk.
	 * @param uniqueID
	 *  The ID of a resource.
	 * @param dictOfCopies
	 *  A dictionary which holds the uniqueID of the copy and the date that copy was added to the library. 
	 * @param queueOfReservations
	 *  A Queue for handling the reservation of a copy of a resource.
	 * @param borrowHistory
	 *  A dictionary which stores the borrow history of a copy of a resource.
	 */
	public Resource(String year, String title,
		String thumbnailImageRef, String uniqueID, Dictionary dictOfCopies, 
		Queue queueOfReservations, Dictionary borrowHistory) {
		
		this.year = year;
		this.title = title;
		this.thumbnailImageRef = thumbnailImageRef;
		this.uniqueID = uniqueID;
		this.dictionaryOfCopies = dictOfCopies;
		this.queueOfReservations = queueOfReservations;
		this.borrowHistory = borrowHistory;
		
	}


	/**
	 * Finds the year the resource was published
	 * @return year 
	 */
	public String getYear() {
		return year;
	}
	/**
	 * Allocates the year the resource was published in
	 * @param year
	 */

	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * Finds the title of the Resource.
	 * @return title
	 */

	public String getTitle() {
		return title;
	}
	/**
	 * Allocates a title to this resource.
	 * @param title
	 */

	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Finds the filepath of the avatar of this Resource.
	 * @return thumbnailImageRef
	 */

	public String getThumbnailImageRef() {
		return thumbnailImageRef;
	}
	/**
	 * Allocates a filepath to the avatar of this Resource.
	 * @param thumbnailImageRef
	 */

	public void setThumbnailImageRef(String thumbnailImageRef) {
		this.thumbnailImageRef = thumbnailImageRef;
	}
	/**
	 * Finds this Resource's unique ID.
	 * @return uniqueID
	 */

	public String getUniqueID() {
		return uniqueID;
	}
	/**
	 * Allocates a uniqueID for this Resource.
	 * @param uniqueID
	 */

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	/**
	 * Finds the copies of this Resource
	 * @return dictionaryOfCopies
	 */

	public Dictionary getDictionaryOfCopies() {
		return dictionaryOfCopies;
	}
	/**
	 * Finds the reservation queue of copies of this Resource
	 * @return queueOfReservations
	 */

	public Queue getQueueOfReservations() {
		return queueOfReservations;
	}
	/**
	 * Finds the borrow history of this Resource.
	 * @return borrowHistory.
	 */

	public Dictionary getBorrowHistory() {
		return borrowHistory;
	}
	
	/**
	 * Set the borrow history of a copy of this Resource.
	 * @param copyID
	 * This Copy's ID.
	 * @param loanHistory
	 * The loan history of this copy
	 */
	public void setBorrowHistory(String copyID, String[][][] loanHistory) {
		/*loanHistory will store [user][dLoan][dRet]
		 * [user] = userID of user who loaned this copy
		 * [dLoan]= date this copy was loaned
		 * [dRet]= date this copy was returned
		 * loanHistory thus = userID of user who loaned this copy
		 * from the date dLoan until it was returned on dRet.
		 * 
		 *	######################
		 *	borrowHistory will store a key of copyID and an array of that copy's loan history
		 *	in the format above.
		 */
		copyHistory.add(loanHistory);
		borrowHistory.put(copyID, copyHistory);
	}
	
	/**
	 * Adds a new copy to this Resource.
	 * @param copyID
	 * The unique ID of this copy.
	 * @param dateAdded
	 * The date this copy is added to the library.
	 */
	public void addToCopies(String copyID, Date dateAdded) {
		dictionaryOfCopies.put(uniqueID, dateAdded);
	}
	
}
