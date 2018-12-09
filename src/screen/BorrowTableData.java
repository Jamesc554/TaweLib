package screen;

import javafx.beans.property.SimpleStringProperty;

public class BorrowTableData {
	private final SimpleStringProperty resourceID;
	private final SimpleStringProperty resourceTitle;
	private final SimpleStringProperty borrowDate;
	private final SimpleStringProperty returnDate;
	private final SimpleStringProperty overdue;
	
	public BorrowTableData(String rID, String rTitle, String borrowDate,
			String returnDate, String overdue) {
		this.resourceID = new SimpleStringProperty(rID);
		this.resourceTitle = new SimpleStringProperty(rTitle);
		this.borrowDate = new SimpleStringProperty(borrowDate);
		this.returnDate = new SimpleStringProperty(returnDate);
		this.overdue = new SimpleStringProperty(overdue);
	}

	public String getResourceID() {
		return resourceID.get();
	}
	
	public String getResourceTitle() {
		return resourceTitle.get();
	}
	
	public String getBorrowDate() {
		return borrowDate.get();
	}
	
	public String getReturnDate() {
		return returnDate.get();
	}
	
	public String getOverdue() {
		return overdue.get();
	}
}
