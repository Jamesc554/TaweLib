package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Laptop</h1>
 * <p>The laptop class creates laptop objects to be used by other classes </p>
 * @author Deyan Naydenov Peter Daish
 * @version 1.0
 * @since 08/12/2018
 */
public class Laptop extends Resource {
	private final double FINE = 10.00; //The daily fine for this laptop when overdue.
	private final double MAX_FINE = 100.00; //The maximum fine reachable with this laptop.
	
	private static String highestLaptopID = "LaptopID:0"; // used to calculate a unique laptop ID.
	
	private int highestCopyID = 0; // This is used to calculate a unique CopyID.
	private String manufacturer; //The manufacturer of this laptop.
	private String model; //The model of this laptop.
	private String operatingSys; //The operating system of this laptop.

	
	/**
	 * Constructor for Laptop.
	 * @param year
	 * The year the laptop was released.
	 * @param title
	 * The title of the laptop.
	 * @param thumbnailImg
	 * The filepath of the avatar of the laptop.
	 * @param uniqueID
	 * The unique id of this laptop.
	 * @param manufacturer
	 * The manufacturer of this laptop.
	 * @param model
	 * The model of this laptop.
	 * @param os
	 * The operating system of this laptop.
	 * @param noOfCopies
	 * The number of copies of ths laptop the library owns.
	 * @param loanDuration
	 * The loan duration of this laptop.
	 * @param copyBorrowHistory
	 * The borrow history of copies of this laptop.
	 * @param currentBorrowData
	 * The information stored about who currently is loaning this laptop and dates of loan and return etc.
	 */
	public Laptop(String year, String title,  String thumbnailImg,String uniqueID,
			String manufacturer, String model, String os, Integer noOfCopies, ArrayList<String> loanDuration, List<List<BorrowHistoryData>> copyBorrowHistory, List<BorrowHistoryData> currentBorrowData) {
		
		super(year, title, thumbnailImg, generateLaptopID(uniqueID), noOfCopies,loanDuration, copyBorrowHistory, currentBorrowData);
		
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSys = os;
	}
	
	/**
	 * Generates a unique id for this laptop if none present.
	 * @param id
	 * The id of this laptop if present.
	 * @return highestLaptopID
	 * The largest id of any laptop.
	 */
	private static String generateLaptopID(String id) {
		//split laptop ID into two strings if ID == null.
		//convert anything after ": " into Int
		//append 1
		// convert back to String
		//assign uniqueID
		//If id != null.
		//we are loading a book from a file thus set highestLaptopID to id.
		
		if (id == null) {
			String[] segmentsOfString = highestLaptopID.split(":");
			String typeSegment = segmentsOfString[0];
			String numberSegmentOfString = segmentsOfString[1];
			int numberOfID = Integer.parseInt(numberSegmentOfString);
			numberOfID++;
			highestLaptopID = typeSegment + ":" + Integer.toString(numberOfID);
			return highestLaptopID;
		} else {
			highestLaptopID = id;
			return highestLaptopID;
		}
	}
	
	/**
	 * Sets a value to calculate laptop IDs from.
	 * @param hLaptopID 
	 * The highest current value of any laptop's ID.
	 */
	public void setHighestLaptopID(String hLaptopID) {
		this.highestLaptopID = hLaptopID;
	}
	
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID 
	 * The newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	/**
	 * Sets highestcopyID based on the largest current copyID
	 * @param hCopyID
	 * The largest current copy ID of any copy of this laptop.
	 */
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the ID of the latest laptop.
	 * @return highestLaptopID 
	 * The highest current id of any book.
	 */
	public String getHighestLaptopID() {
		return this.highestLaptopID;
	}

	/**
	 * Finds the manufacturer of the laptop
	 * @return manufacturer 
	 * The manufacturer of this laptop.
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	/**
	 * Finds the model of the laptop
	 * @return model
	 * The model of this laptop. 
	 */
	public String getModel() {
		return this.model;
	}
	
	/**
	 * Finds the operating system of the laptop
	 * @return operatingSys 
	 * The operating system currently running on this Laptop.
	 */
	public String getOperatingSys() {
		return this.operatingSys;
	}
	
	/**
	 * Defines a manufacturer for the laptop.
	 * @param manufacturer
	 * The manufacturer of this laptop.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * Defines a model for the laptop.
	 * @param model
	 * The model of this laptop.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Defines an operating system for the laptop.
	 * @param operatingSys
	 * The operating system currently running on this laptop.
	 */
	public void setOperatingSys(String operatingSys) {
		this.operatingSys = operatingSys;
	}
	
	/**
	 * Adds a copy to this Laptops unique list of copies.
	 */
	public void addToCopies(String loanDuration) {
		super.addCopy(loanDuration);
	}
}
