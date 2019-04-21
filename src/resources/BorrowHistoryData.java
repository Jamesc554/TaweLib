package resources;

/**
 * <h1>BorrowHistoryData.</h1>
 * <p>This class models the borrow history for a copy.</p>
 * @author James Carter, Dominik Wojtasiewicz, Etienne Badoche 
 */
public class BorrowHistoryData {
	
	private String userID;
	private String dateBorrowed;
	private String dateReturned;
	private String dateRequestedReturn;
	
	/**
	 * Constructor for BorrowHistoryData, 
	 * initialises everything to an empty String.
	 */
	public BorrowHistoryData() {
		userID = "";
		dateBorrowed = "";
		dateReturned = "";
		dateRequestedReturn = "";
	}

	/**
	 * Returns the username of the user who borrowed this copy.
	 * @return usedID
	 * The username of the user who borrowed this copy.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the username of the user who borrowed this copy.
	 * @param userID
	 * The username to set
	 */
	public void setUserID(final String userID) {
		this.userID = userID;
	}

	/**
	 * Returns the date this copy was borrowed.
	 * @return dateBorrowed
	 * The date this copy was borrowed
	 */
	public String getDateBorrowed() {
		return dateBorrowed;
	}

	/**
	 * Sets the date borrowed to the given date.
	 * @param dateBorrowed
	 * The date this copy was borrowed
	 */
	public void setDateBorrowed(final String dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	/**
	 * Returns the date this copy was returned.
	 * @return dateReturned
	 * The date this copy was returned
	 */
	public String getDateReturned() {
		return dateReturned;
	}

	/**
	 * Sets the date returned to the given date.
	 * @param dateReturned
	 * The date this copy was returned
	 */
	public void setDateReturned(final String dateReturned) {
		this.dateReturned = dateReturned;
	}

	/**
	 * Returns the date this copy was requested to be returned.
	 * @return dateRequestedReturn
	 * The date this copy was requested to be returned
	 */
	public String getDateRequestedReturn() {
		return this.dateRequestedReturn;
	}

	/**
	 * Sets the date this copy needed to be returned.
	 * @param dateRequestedReturn
	 * The date this copy is requested to be returned
	 */
	public void setDateRequestedReturn(final String dateRequestedReturn) {
		this.dateRequestedReturn = dateRequestedReturn;
	}

	/**
	 * Sets userID, dateBorrowed, dateReturned.
	 * dateRequestedReturn to an empty String
	 */
	public void clearInfo() {
		this.userID = "";
		this.dateBorrowed = "";
		this.dateReturned = "";
		this.dateRequestedReturn = "";
	}
	
	@Override
	/**
	 * Converts this borrow history to a string.
	 * @return A string representation of this borrowHistory
	 */
	public final String toString() {
		return getUserID() + " - " + getDateBorrowed() + " - " + 
	getDateReturned() + " - " + getDateRequestedReturn();
	}
}
