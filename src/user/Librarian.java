package user;

/**
 * This class models a Librarian and stores all of its information.
 * @author Dominik R Wojtasiewicz, Sam Jankinson, Ammar Alamri
 */
public class Librarian extends User {
	private static int numberOfEmploys;
	private int employmentMonth;
	private int employmentYear;
	private String staffNumber;
	private int employmentDay;

	/**
	 * Librarian constructor
	 * @param userName String username of the user
	 * @param firstName String firstname of the user
	 * @param lastName String lastname of the user
	 * @param mobileNumber String mobile number of the user
	 * @param firstLineAddress String first line address of the user
	 * @param secondLineAddress String  second line address of the user
	 * @param postCode String post code of the user
	 * @param townName String name of the town the user lives at
	 * @param accountBalance int current account balance of the user
	 * @param profImage String representation of the path to the profile image of the user
	 * @param empDay Int of employment date
	 * @param empMonth Int of employment month
	 * @param empYear Int of employment year
	 * @param staffNumber String staff number
	 * @param noOfEmploys Int number of employs.
	 */
	public Librarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
					 String secondLineAddress, String postCode, String townName, double accountBalance, String profImage,
					 int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys) {

		super(userName, firstName, lastName, mobileNumber, firstLineAddress, secondLineAddress, postCode, townName,
				accountBalance, profImage);

		setEmploymentDay(empDay);
		setEmploymentMonth(empMonth);
		setEmploymentYear(empYear);
		setStaffNumber(staffNumber);
		setNumberOfEmploys(noOfEmploys);
	}

	/**
	 * Sets the employment start day of the user.
	 * @param employmentDay Int representation of start day employment of the librarian
	 */
	private void setEmploymentDay(int employmentDay) {
		this.employmentDay = employmentDay;
	}

	/**
	 * Sets the employment start month of the user.
	 * @param employmentMonth Int representation of the month that the librarian started to work.
	 */
	private void setEmploymentMonth(int employmentMonth) {
		this.employmentMonth = employmentMonth;
	}

	/**
	 * Sets the employment start year of the user
	 * @param employmentYear Int representation of the year that the librarian started to work.
	 */
	private void setEmploymentYear(int employmentYear) {
		this.employmentYear = employmentYear;
	}

	/**
	 * Sets the staff number of the user
	 * @param staffNumber String staff number of the user
	 */
	private void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	/**
	 * Sets the number of employs
	 * @param numberOfEmploys
	 */
	public static void setNumberOfEmploys(int numberOfEmploys) {
		Librarian.numberOfEmploys = numberOfEmploys;
	}

	/**
	 * Get's the day of employment of the user
	 * @return Int
	 */
	public int getEmploymentDay() {
		return employmentDay;
	}

	/**
	 * Get's the month of employment of the user
	 * @return Int
	 */
	public int getEmploymentMonth() {
		return employmentMonth;
	}

	/**
	 * Get's the year of employment of the user
	 * @return Int
	 */
	public int getEmploymentYear() {
		return employmentYear;
	}

	/**
	 * Get the stuff number of the librarian as a String
	 * @return
	 */
	public String getStaffNumber() {
		return staffNumber;
	}

	/**
	 * Get's the number of librarians.
	 * @return int of number of staff
	 */
	public static int getNumberOfEmploys() {
		return numberOfEmploys;
	}

	/**
	 * Returns profile image path of profile image.
	 * @return String
	 */
	public String getProfImage() {
		return this.profImage;
	}
}
