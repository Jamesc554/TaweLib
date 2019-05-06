package screen;
import javafx.beans.property.SimpleStringProperty;

/**
 * <h1>BorrowTableData.</h1>
 * <p>This class handles information to do with the users Borrow History.</p>
 * @author James Carter, Peter Daish, Dominik Wojtasiewicz 
 *
 */
public class BorrowTableData {
	private final SimpleStringProperty resourceID;
	private final SimpleStringProperty resourceTitle;
	private final SimpleStringProperty borrowDate;
	private final SimpleStringProperty returnDate;
	private final SimpleStringProperty overdue;
	
	/**
	 * Constructor for BorrowTableData.
	 * @param rID
	 * The resource ID of loaned resource.
	 * @param rTitle
	 * The title of loaned resource.
	 * @param borrowDate
	 * The data the copy of the resource was borrowed.
	 * @param returnDate
	 * The date the copy of the resource was suppoesed to be returned.
	 * @param overdue
	 * Whether the copy of the resource is overdue.
	 */
	public BorrowTableData(String rID, String rTitle, String borrowDate,
			String returnDate, String overdue) {
		
		this.resourceID = new SimpleStringProperty(rID);
		this.resourceTitle = new SimpleStringProperty(rTitle);
		this.borrowDate = new SimpleStringProperty(borrowDate);
		this.returnDate = new SimpleStringProperty(returnDate);
		this.overdue = new SimpleStringProperty(overdue);
	}

	/**
	 * Gets the resource's ID.
	 * @return resourceID
	 * The resource's ID.
	 */
	public String getResourceID() {
		return resourceID.get();
	}
	
	/**
	 * Gets the resource's Title.
	 * @return resourceTitle
	 * The resource's Title.
	 */
	public String getResourceTitle() {
		return resourceTitle.get();
	}
	
	/**
	 * Gets the date the copy of the resource was borrowed on.
	 * @return borrowDate
	 * The date the resource was borrowed.
	 */
	public String getBorrowDate() {
		return borrowDate.get();
	}
	
	/**
	 * Gets the date the copy of the resources was returned on.
	 * @return returnDate
	 * The date the copy was returned.
	 */
	public String getReturnDate() {
		return returnDate.get();
	}
	
	/**
	 * Gets whether the copy is overdue.
	 * @return overdue
	 * Whether the copy is overdue (returned after the returnDate).
	 */
	public String getOverdue() {
		return overdue.get();
	}
}
