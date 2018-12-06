package resources;

import java.util.ArrayList;
import java.util.List;

import user.User;
import utils.Queue;
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
	protected List<CopyData> copiesList;

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
		String thumbnailImageRef, String uniqueID, Integer noOfCopies, List<String> loanDuration, 
		List<List<BorrowHistoryData>> copyBorrowHistory, List<BorrowHistoryData> currentBorrowData) {
		
		this.year = year;
		this.title = title;
		this.thumbnailImageRef = thumbnailImageRef;
		this.uniqueID = uniqueID;
		this.queueOfReservations = new Queue<User>();
		this.copiesList = new ArrayList<>();
		
		for (int i = 0; i < noOfCopies; i++) {
			CopyData newCopy = null;
			if (copyBorrowHistory.isEmpty() || copyBorrowHistory.get(i) == null || currentBorrowData.isEmpty() || currentBorrowData.get(i) == null) {
				newCopy = new CopyData(String.valueOf(i), new ArrayList<>(), null, loanDuration.get(i));
			} else {
				newCopy = new CopyData(String.valueOf(i), copyBorrowHistory.get(i), currentBorrowData.get(i), loanDuration.get(i));
			}
			copiesList.add(newCopy);
		}
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

	public List<CopyData> getArrayListOfCopies() {
		return this.copiesList;
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

	public List<BorrowHistoryData> getBorrowHistory(String copyID) {
		return copiesList.get(Integer.parseInt(copyID)).getBorrowHistory();
	}
	
	/**
	 * Sets the borrow history for a resource
	 * @param borrowHistory the borrow history of a resource
	 */
	public void setBorrowHistory(String copyID, List<BorrowHistoryData> borrowHistory) {
		copiesList.get(Integer.parseInt(copyID)).setBorrowHistory(borrowHistory);
	}

	public int getNoOfCopies() {
		return copiesList.size();
	}
	
	/* #############################################################
	 * ########  BELOW ARE THE COMPLEX METHODS OF Resource  ########
	 * #############################################################
	 */

	/**
	 * Returns the current loanee of a copy
	 * @param copyID the id of the copy possibly on loan
	 * @return the username of the user who is loaning the book.
	 */
	public String getCurrentLoanee(String copyID) {
		return copiesList.get(Integer.parseInt(copyID)).getCurrentInfo().getUserID();
	}


	/**
	 * Adds a new copy to this Resource.
	 * @param loanDuration
	 * The length of the loan for this copy
	 */
	public void addCopy(String loanDuration) {
		copiesList.add(new CopyData(String.valueOf(copiesList.size() - 1), new ArrayList<BorrowHistoryData>(), null, loanDuration));
	}

	/**
	 * Removes a copy from the Resource
	 */
	public void removeCopy(String copyID) {
		//Remove  copy from dictionary of copies
		copiesList.remove(Integer.valueOf(copyID));
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

	public String getLoanDuration(String copyID) {
		return copiesList.get(Integer.valueOf(copyID)).getLoanDuration();
	}
	
	public void loanResource(String copyID, String username){
		copiesList.get((Integer.valueOf(copyID))).loanCopy(username);
	}
	
	public void requestReturn(Integer copyId){
		copiesList.get(copyId).requestReturn();
	}
	
	public void returnResource(Integer copyId) {
		copiesList.get(copyId).returnCopy();
	}
	
	public boolean checkIfAvailable(){
		for (CopyData copy : copiesList) {
			if (copy.isAvailable())
				return true;
		}
		return false;
	}
	public boolean checkIfCopyAvailable(String copyID){
		return copiesList.get(Integer.parseInt(copyID)).isAvailable();
	}
}
