package user;

import library.Library;
import resources.Resource;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * This class models a Library User.
 * @author Dominik R Wojtasiewicz
 * @since 18/11/2018
 */

public class User {
    protected String userName; //unique userName
    protected String firstName; //First name
    protected String lastName; //Last name
    protected String mobileNumber; //mobile number
    protected String firstLineAddress; //First line of address
    protected String secondLineAddress; //Second line of address
    protected String postCode; //Post code
    protected String townName; //Town name
    protected ArrayList<String> resourceCurrentlyBorrowed = new ArrayList<>(); //List of currently borrowed books
	protected ArrayList<String> resourceCurrentlyRequested  = new ArrayList<>();
	protected ArrayList<String> resourceCurrentlyReserved = new ArrayList<>();
    protected ArrayList<String[]> transactionHistory = new ArrayList<>(); //Transaction History
    protected ArrayList<String[]> borrowHistory = new ArrayList<>(); // Borrow history
    protected Double accountBalance; //current account balance
    protected String profImage; //profile image address
	protected String lastLogIn; //Date last log in.

	/**
	 * Generic constructor
	 * @param userName unique username of the user
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param mobileNumber mobile number of the user
	 * @param firstLineAddress first line address of the user
	 * @param secondLineAddress second line address of the user
	 * @param postCode post code of the user
	 * @param townName town name of the user
	 * @param accountBalance current account balance of the user
	 * @param profImage path to the profile image of the user
	 */
    public User(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
                String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setMobileNumber(mobileNumber);
        setFirstLineAddress(firstLineAddress);
        setSecondLineAddress(secondLineAddress);
        setPostCode(postCode);
        setTownName(townName);
        setAccountBalance(accountBalance);
        setProfImage(profImage);
    }

	/**
	 * This sets the user name of the user. Can only be done once.
	 * @param userName username of the user.
	 */
	protected void setUserName(String userName){this.userName = userName;}

	/**
	 * This sets the first name of the user. Can only be done once.
	 * @param firstName name of the user.
	 */
    protected void setFirstName(String firstName){this.firstName = firstName;}

	/**
	 * This sets the last name of the user can be changed later.
	 * @param lastName last name of the user.
	 */
	public void setLastName(String lastName){this.lastName = lastName;}

	/**
	 * This sets mobile number of the user. Can be changed later.
	 * @param mobileNumber the mobile number of the user.
	 */
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}

	/**
	 * Sets the first line address of the user. Can be changed later.
	 * @param firstLineAddress first line address of the user.
	 */
	public void setFirstLineAddress(String firstLineAddress){this.firstLineAddress = firstLineAddress;}

	/**
	 * Sets the second line address of the user. Can be changed later.
	 * @param secondLineAddress second line address of the user.
	 */
    public void setSecondLineAddress(String secondLineAddress){this.secondLineAddress = secondLineAddress;}

	/**
	 * Sets the post code of the user. Can be changed later.
	 * @param postCode the postcode of the user.
	 */
	public void setPostCode(String postCode){this.postCode = postCode;}

	/**
	 * This sets the town name of the user. Can be changed later.
	 * @param townName the name of the town the user lives in.
	 */
	public void setTownName(String townName){this.townName = townName;}

	/**
	 * This sets the path for the profile image of the user.
 	 * @param profImage the path to the image of the user.
	 */
    public void setProfImage(String profImage){this.profImage = profImage;}

	/**
	 * This sets the account balance of the user.
	 * @param amount the start amount in pence.
	 */
	protected void setAccountBalance(double amount){this.accountBalance = amount;}

	/**
	 * Returns the username.
	 * @return username as String.
	 */
    public String getUserName(){return this.userName;}

	/**
	 * Returns the first name of the user.
	 * @return first name as String.
	 */
	public String getFirstName(){return this.firstName;}

	/**
	 * Return the last name of the user.
	 * @return last name as String.
	 */
    public String getLastName(){return this.lastName;}

	/**
	 * This return first and last name separated by a space as one string.
	 * @return String of "First Last" name
	 */
	public String getFullName(){return this.firstName + " " + this.lastName;}

	/**
	 * Returns the mobile number of the user.
	 * @return the number as a String.
	 */
    public String getMobileNumber(){return this.mobileNumber;}

	/**
	 * Returns the first line of address.
	 * @return String of first line of address.
	 */
	public String getFirstLineAddress(){return this.firstLineAddress;}

	/**
	 * Returns the second line of address.
	 * @return string of second line of address.
	 */
    public String getSecondLineAddress(){return this.secondLineAddress;}

	/**
	 * Returns the post code of the user.
	 * @return a string representing the post code of the user
	 */
	public String getPostCode(){return this.postCode;}

	/**
	 * Returns the name of the town of the user
	 * @return a string representation the name of the town of the usr.
	 */
    public String getTownName(){return this.townName;}

	/**
	 * This returns the full address formatted for postage
	 * @return single String
	 */
	public String getFullAddress(){
		String address = getFirstLineAddress();
		if (!getSecondLineAddress().equals("")){
			return address + getSecondLineAddress()
					+ "\n" + getTownName() + "\n" + getPostCode();
		}else{
			return address + "\n" + getTownName() + "\n" + getPostCode();
		}
    }
	/**
	 * This returns the path to the profile image of the user
	 * @return String
	 */
	public String getProfImage(){return this.profImage;}

	/**
	 * Get the resource with specific ID from the user currently borrowed
	 * @param resourceID The unique ID of the user
	 * @return Resource object representing resource with ID, returns null if unique ID does not match
	 * any item in the list.
	 */
    public String getResource(String resourceID){
        for (String id : this.resourceCurrentlyBorrowed){
            if (id.equals(resourceID)){
                return id;
            }
        }
        return null;
    }

	/**
	 * Returns full list of resources borrowed by the user.
	 * @return ArrayList storing all Resource objects.
	 */
	public ArrayList<String> getCurrentlyBorrowedResources(){return this.resourceCurrentlyBorrowed;}

	/**
	 * Returns all transaction history of the user.
	 * @return a ArrayList. This arrayList holds arrays in the following way,
	 * DateOfTransaction	Amount of Transaction
	 */
    public ArrayList<String[]> getTransactions(){return this.transactionHistory;}

	/**
	 * This returns the current account balance as a String. Following format "£X.XX".
	 * @return a string representation of the balance.
	 */
	public String getAccountBalanceString(){
			return "£"+String.valueOf(this.accountBalance);
    }
	
	public double getAccountBalanceDouble() {
		return this.accountBalance;
	}

	/**
	 * Adds a resource to users current loan.
	 * @param id The resource to be added to the current borrow.
	 */
    public void loanResource(String id){
    	this.resourceCurrentlyBorrowed.add(id);
    	addResourceToHistory(id);
    }
    //Not used as we do not collect stats
    protected void addResourceToHistory(String resourceID){
    	String[] data = new String[3];
    	data[0] = getCurrentDate();
    	data[1] = resourceID;
    	data[2] = "Currently Out ";
    	borrowHistory.add(data);
	}

	/**
	 * Add balance to the account.
	 * @param amount the amount to be added.
	 */
	public void addAccountBalance(int amount){this.accountBalance = this.accountBalance + amount;}

	/**
	 * Remove balance from account.
	 * @param amount the amount to be removed from account.
	 */
    public void subtractAccountBalance(int amount){this.accountBalance = this.accountBalance - amount;}

	/**
	 * Add's transaction to history of the user.
	 * @param amount
	 */
	protected void addTransaction(int amount){
		//Computes current system data
        String[] data = {getCurrentDate(), String.valueOf(amount), ""};
        transactionHistory.add(data);
    }
    public void returnResource(String resourceID){
		int j = 0;
		Boolean done = false;
		while(j >= this.resourceCurrentlyBorrowed.size()-1 && !done){
			if(this.resourceCurrentlyBorrowed.get(j).equals(resourceID)){
				this.resourceCurrentlyBorrowed.remove(j);
				done = true;
			}
			j++;
		}
		done = false;
		int i = this.borrowHistory.size()-1;
		while(!done && i >= 0){
			String[] data = borrowHistory.get(i);
			if (data[1].equals(resourceID)){
				data[2] = getCurrentDate();
				borrowHistory.remove(i);
				borrowHistory.add(i, data);
				done = true;
			}
			i--;
		}
	}

	/**
	 * Returns the history of the user
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> getBorrowHistory(){
		return this.borrowHistory;
	}

	/**
	 * Returns the history of the user.
	 * @param ID of resource to request.
	 */
	public void requestResource(String ID){
		this.resourceCurrentlyRequested.add(ID);
	}

	/**
	 * Get's all of users requested resources.
	 * @return ArrayList<String>.
	 */
	public ArrayList<String> getAllRequested(){
		return this.resourceCurrentlyRequested;
	}

	/**
	 * Get all of users reserved items.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getAllReserved() { return this.resourceCurrentlyReserved;}

	/**
	 * Gets current system date in following format DD-MM-YYYY HH:MM:SS.
	 * @return String
	 */
    private String getCurrentDate(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dataFormat.format(new Date());
	}

	public String getLastLogIn() {
		return lastLogIn;
	}
	public void setLastLogIn(){
    	this.lastLogIn = getCurrentDate();
	}
}
