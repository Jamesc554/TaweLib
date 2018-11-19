package user;

public class Librarian extends User {
	private int employmentMonth;
	private int employmentYear;
	private String staffNumber;
	private static int numberOfEmploys;
	private int employmentDay;

	public Librarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
					 String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
					 int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		super(userName, firstName, lastName, mobileNumber, firstLineAddress, secondLineAddress, postCode, townName,
				accountBalance, profImage);
		setEmploymentDay(empDay);
		setEmploymentMonth(empMonth);
		setEmploymentYear(empYear);
		setStaffNumber(staffNumber);
		setNumberOfEmploys(noOfEmploys);
	}

	private void setEmploymentMonth(int employmentMonth) {
		this.employmentMonth = employmentMonth;
	}

	private void setEmploymentYear(int employmentYear) {
		this.employmentYear = employmentYear;
	}

	private void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public static void setNumberOfEmploys(int numberOfEmploys) {
		Librarian.numberOfEmploys = numberOfEmploys;
	}

	private void setEmploymentDay(int employmentDay) {
		this.employmentDay = employmentDay;
	}

	public int getEmploymentDay() {
		return employmentDay;
	}

	public int getEmploymentMonth() {
		return employmentMonth;
	}

	public int getEmploymentYear() {
		return employmentYear;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public static int getNumberOfEmploys() {
		return numberOfEmploys;
	}

}
