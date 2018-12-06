package resources;

public class BorrowHistoryData {
	
	private String userID;
	private String dateBorrowed;
	private String dateReturned;
	private String dateRequestedReturn;
	
	public BorrowHistoryData() {
		userID = "";
		dateBorrowed = "";
		dateReturned = "";
		dateRequestedReturn = "";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(String dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public String getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(String dateReturned) {
		this.dateReturned = dateReturned;
	}

	public String getDateRequestedReturn() {
		return dateRequestedReturn;
	}

	public void setDateRequestedReturn(String dateRequestedReturn) {
		this.dateRequestedReturn = dateRequestedReturn;
	}

	public void clearInfo(){
		this.userID = "";
		this.dateBorrowed = "";
		this.dateReturned = "";
		this.dateRequestedReturn = "";
	}
}
