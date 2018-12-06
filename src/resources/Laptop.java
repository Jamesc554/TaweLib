package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Laptop</h1>
 * <p>The laptop class creates laptop objects to be used by other classes
 * @author Deyan Naydenov Peter Daish
 * @version 0.0.0.2
 * @since 18/11/2018
 */
public class Laptop extends Resource {
	
	private static String highestLaptopID = "LaptopID:0"; // used to calculate a unique laptop ID.
	
	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
	private String manufacturer;
	private String model;
	private String operatingSys;
	
	/**
	 * Constructor used to create laptop objects.
	 * @param manufacturer
	 *  Who manufactured the laptop.
	 * @param model
	 *  What the laptop's model is.
	 * @param operatingSys
	 * The laptop's operating system
	 */
	public Laptop(String manufacturer, String model,  String operatingSys,String year,
			String title, String thumbnailImg, String uniqueID, Integer noOfCopies, ArrayList<String> loanDuration, List<List<BorrowHistoryData>> copyBorrowHistory, List<BorrowHistoryData> currentBorrowData) {
		
		super(year, title, thumbnailImg, generateLaptopID(uniqueID), noOfCopies,loanDuration, copyBorrowHistory, currentBorrowData);
		
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSys = operatingSys;
	}
	private static String generateLaptopID(String id) {
		//split laptop ID into two strings if ID == null.
		//convert anything after ": " into Int
		//append 1
		// convert back to String
		//assign uniqueID
		//
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
	 * @param hLaptopID the highest current value of any laptop's ID.
	 */
	public void setHighestLaptopID(String hLaptopID) {
		this.highestLaptopID = hLaptopID;
	}
	
	/**
	 * Sets a value to calculate a new copy's id from.
	 * @param hCopyID the latest ID of any copy.
	 */
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID the newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the ID of the latest laptop.
	 * @return highestLaptopID the highest current id of any book.
	 */
	public String getHighestLaptopID() {
		return this.highestLaptopID;
	}

	/**
	 * Finds the manufacturer of the laptop
	 * @return manufacturer 
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * Finds the model of the laptop
	 * @return model 
	 */
	public String getModel() {
		return model;
	}
	/**
	 * Finds the operating system of the laptop
	 * @return operatingSys 
	 */
	public String getOperatingSys() {
		return operatingSys;
	}
	
	/**
	 * Defines a manufacturer for the laptop.
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * Defines a model for the laptop.
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Defines an operating system for the laptop.
	 * @param operatingSys
	 */
	public void setOperatingSys(String operatingSys) {
		this.operatingSys = operatingSys;
	}
	
	/**
	 * Adds a copy to this Laptop unique Array of copies.
	 */
	public void addToCopies(String loanDuration) {
		super.addCopy(loanDuration);
	}
	
	/**
	 * Generates a copy ID based off of previous copyID.
	 * @return hCpyID the highest ID of copies of Laptop
	 */

}
