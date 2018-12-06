package resources;

import java.util.List;

public class CopyData {
	
	private String id;
	private List<BorrowHistoryData> borrowHistory; // [4] [0] - User ID [1] - date borrowed [2] - date returned [3] - requested return date
	private BorrowHistoryData currentInfo; // [4] [0] - User ID [1] - date borrowed [2] - date returned [3] - requested return date
	private String loanDuration;
	
	public CopyData(String copyID, List<BorrowHistoryData> borrowHistory, String loanDuration) {
		this.id = copyID;
		this.borrowHistory = borrowHistory;
		this.loanDuration = loanDuration;
		this.currentInfo = new BorrowHistoryData();
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
			return false;
		}
		
		return true;
	}

}
