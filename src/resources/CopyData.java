package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import library.Library;

public class CopyData {

	
	private String id;
	private List<BorrowHistoryData> borrowHistory; // [4] [0] - User ID [1] - date borrowed [2] - date returned [3] - requested return date
	private BorrowHistoryData currentInfo; // [4] [0] - User ID [1] - date borrowed [2] - date returned [3] - requested return date
	private String loanDuration;
	private String reservedUserID;
	
	public CopyData(String copyID, List<BorrowHistoryData> borrowHistory, BorrowHistoryData currentInfo, String loanDuration) {
		this.id = copyID;
		this.borrowHistory = borrowHistory;
		this.loanDuration = loanDuration;
		
		if (currentInfo == null)
			this.currentInfo = new BorrowHistoryData();
		else 
			this.currentInfo = currentInfo;
		
		reservedUserID = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<BorrowHistoryData> getBorrowHistory() {
		return borrowHistory;
	}

	public void setBorrowHistory(List<BorrowHistoryData> borrowHistory) {
		this.borrowHistory = borrowHistory;
	}

	public BorrowHistoryData getCurrentInfo() {
		return currentInfo;
	}

	public void setCurrentInfo(BorrowHistoryData currentInfo) {
		this.currentInfo = currentInfo;
	}

	public String getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(String loanDuration) {
		this.loanDuration = loanDuration;
	}
	
	public boolean isAvailable() {
		if (currentInfo.getDateBorrowed().equals("")) {
			return true;
		}

		return false;
	}
	
	public boolean isReserved() {
		if (reservedUserID != "" || reservedUserID != null)
			return true;
		
		return false;
	}
	
	public String getReservedUser() {
		if (reservedUserID != "" || reservedUserID != null)
			return reservedUserID;
		
		return null;
	}
	
	public void loanCopy(String username){
		this.currentInfo.setUserID(username);
		this.currentInfo.setDateBorrowed(Library.getCurrentDateTime());
	}
	
	public void requestReturn(){
		this.currentInfo.setDateRequestedReturn(getEstimatedReturnData().getTime().toString());
	}
	
	public void returnCopy(){
		currentInfo.setDateReturned(Library.getCurrentDateTime());
		this.borrowHistory.add(currentInfo);
		currentInfo = new BorrowHistoryData();
	}
	
	public void reserveCopy(String userID) {
		this.reservedUserID = userID;
	}
	
	public Calendar getEstimatedReturnData() {
		String date = currentInfo.getDateBorrowed().split(" ")[0];

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy" ); //Not Required
		Date selectedDate=null;
		try {
			selectedDate=dateFormat.parse(date);// replace it with selected date
		} catch (ParseException e) {
			System.out.println("date in wrong format");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(selectedDate);
		cal.add( Calendar.DATE,Integer.valueOf(loanDuration) );
		
		if (cal.getTime().before(new Date())) {
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return cal;
	}
}
