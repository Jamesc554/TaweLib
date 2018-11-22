package resources;

import utils.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import user.User;
/**
 * <h1>Resource</h1>
 * <p>Resource is an abstract class which provides essential attributes for all resource types
 * @author Peter Daish
 * @version 0.1
 * @since 11/11/2018
 * 
 * 
 */
public abstract class Resource {
	protected String year;
	protected String title;
	protected String thumbnailImageRef;
	protected String uniqueID;
	protected Queue<User> queueOfReservations;
	protected ArrayList<String> arrayListOfCopies;
	protected Map<String, String[]> borrowHistory;//Dictionary of copy history i.e. loan date users who loaned etc.
	protected String[] copyHistory;
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
		String thumbnailImageRef, String uniqueID) {
		
		this.year = year;
		this.title = title;
		this.thumbnailImageRef = thumbnailImageRef;
		this.uniqueID = uniqueID;
		this.queueOfReservations = new Queue<User>();
		this.arrayListOfCopies = new ArrayList<String>();
		this.borrowHistory = new HashMap<String, String[]>();
	}

	/* #############################################################
	 * ########BELOW ARE THE GETTERS AND SETTERS OF Resource########
	 * #############################################################
	 */
	
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

	public ArrayList<String> getArrayListOfCopies() {
		return this.arrayListOfCopies;
	}
	/**
	 * Finds the reservation queue of copies of this Resource
	 * @return queueOfReservations
	 */

	public Queue getQueueOfReservations() {
		return this.queueOfReservations;
	}
	/**
	 * Finds the borrow history of this Resource.
	 * @return borrowHistory.
	 */

	public Map getBorrowHistory() {
		return borrowHistory;
	}
	

	
	/* #############################################################
	 * ########  BELOW ARE THE COMPLEX METHODS OF Resource  ########
	 * #############################################################
	 */
	
	/**
	 * Adds the borrow history of a copy of this Resource.
	 * @param copyID
	 * This Copy's ID.
	 * @param loanHistory
	 * The loan history of this copy
	 */
	public void addBorrowHistory(String copyID, String[] loanHistory) {
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
		copyHistory = loanHistory;
		borrowHistory.put(copyID, copyHistory);
	}
	
	/**
	 * Adds a new copy to this Resource.
	 * @param copyID
	 * The unique ID of this copy.
	 * @param dateAdded
	 * The date this copy is added to the library.
	 */
	public void addToCopies(String copyID) {
		this.arrayListOfCopies.add(copyID);
	}

	/**
	 * Removes a copy from the Resource
	 */
	public void removeCopy() {
		//Remove from dictionary of copies, a copy the latest copy
		this.arrayListOfCopies.remove(this.arrayListOfCopies.size() - 1);
	}
	
	/**
	 * Adds a User to copy request queue.
	 * @param userForQueue the user who requested a copy.
	 */
	public void addUserToRequestQueue(User userForQueue){
		this.queueOfReservations.enqueue(userForQueue);
	}
	
	/**
	 * Removes a User from request for a copy of a resource.
	 * @return userAtFrontOfQueue the user who first requested a copy
	 */
	public User removeUserFromRequestQueue() {
		User userAtFrontOfQueue = this.queueOfReservations.peek();
		this.queueOfReservations.dequeue();
		return userAtFrontOfQueue;
	}
	
	/**
	 * Gets the head of the queueOfReservations
	 * @return queueOfReservations
	 */
	public User peekQueueOfReservations() {
		return this.queueOfReservations.peek();
	}
}
