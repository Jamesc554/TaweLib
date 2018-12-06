package resources;

import utils.Queue;

import java.lang.reflect.Array;
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
	protected String[][] currentOutInfo;
	protected ArrayList<String> loanDuration;
	protected Map<String, ArrayList<String[]>> borrowHistory;//Dictionary of copy history i.e. loan date user who loaned etc. A copy can have multiple histories.
	protected ArrayList<String[]> copyHistory; //changed from String[]
	protected Integer noOfCopies;

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
	 * */

	public Resource(String year, String title,
		String thumbnailImageRef, String uniqueID, Integer noOfCopies, ArrayList<String> loanDuration) {
		
		this.year = year;
		this.title = title;
		this.thumbnailImageRef = thumbnailImageRef;
		this.uniqueID = uniqueID;
		this.queueOfReservations = new Queue<User>();
		this.arrayListOfCopies = new ArrayList<String>();
		this.borrowHistory = new HashMap<String, ArrayList<String[]>>();
		this.noOfCopies = noOfCopies;
		this.currentOutInfo = new String[this.noOfCopies][];
		this.loanDuration = loanDuration;
		creatCurrentOutInfo();
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
	
	/**
	 * Sets the borrow history for a resource
	 * @param borrowHistory the borrow history of a resource
	 */
	public void setBorrowHistory(Map borrowHistory) {
		this.borrowHistory = borrowHistory;
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
	public void addBorrowHistory(String copyID, ArrayList<String[]> loanHistory) {
		/* loanHistory will store [user][dLoan][dRet][dRetBy][loanDuration]
		 * [userName] = userName of user who loaned this copy
		 * [dLoan]= date this copy was loaned
		 * [dRet]= date this copy was returned
		 * [dRetBy] = date this copy was supposed to be returned by.
		 * [loanDuration] = the length of this copy's loan.
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
	 * Returns a copy's history
	 * @param copyID the id of the copy
	 * @return copyHistory the history of this copy
	 */
	public ArrayList<String[]> getCopyHistory(String copyID) {
		return this.borrowHistory.get(copyID);
	}

	/**
	 * Returns the current loanee of a copy
	 * @param copyID the id of the copy possibly on loan
	 * @return the username of the user who is loaning the book.
	 */
	public ArrayList<String[]> getCurrentLoanee(String copyID) {
		//check if borrow date != null and return date is null => still on loan
		if ((this.borrowHistory.get(copyID).get(this.borrowHistory.size()))[1] != null
				&& (this.borrowHistory.get(copyID).get(this.borrowHistory.size()))[2] == null) {

			//return (this.borrowHistory.get(copyID).get(this.borrowHistory.size()))[0]; before
			return this.borrowHistory.get(copyID);

		}
		return null;
	}


	/**
	 * Adds a new copy to this Resource.
	 * @param copyID
	 * The unique ID of this copy.
	 */
	public void addToCopies(String copyID) {
		this.arrayListOfCopies.add(copyID);
	}

	/**
	 * Removes a copy from the Resource
	 */
	public void removeCopy(String copyID) {
		//Remove  copy from dictionary of copies
		for (int i = 0; i < this.arrayListOfCopies.size(); i++) {
			if (this.arrayListOfCopies.get(i) == copyID) {
				this.arrayListOfCopies.remove(i);
			}
		}

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

	/**
	 * Checks if someone requested a book.
	 * @return
	 */
	public Boolean checkIfRequested(){
		return queueOfReservations.isEmpty();
	}
	public String toSingleString(){
		return year + title;
	}

	/**
	 * Sets currently out data.
	 * @param index uniqueID address as integer
	 * @param data format [username, dateBorrowed, dateReturned, dateReturnedBy, loanDuration]
	 */
	public void setCurrentOutInfo(Integer index, String[] data){
		this.currentOutInfo[index] = data;
	}
	private void creatCurrentOutInfo(){
		for(int i = 0; i < this.currentOutInfo.length; i++){
			String[] data = {"","","","", this.loanDuration.get(i)};
			this.currentOutInfo[i] = data;
		}
	}

	public ArrayList<String> getLoanDuration() {
		return loanDuration;
	}
	public void loanResource(Integer copyId, String username, String date){
		String[] data = this.currentOutInfo[copyId];
		data[0] = username;
		data[1] = date;
	}
}
