package user;

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
    protected ArrayList<Resource> resourceBorrow = new ArrayList<>(); //List of currently borrowed books
    protected ArrayList<String[]> transactionHistory = new ArrayList<>(); //Transaction History
    protected ArrayList<String[]> borrowHistory = new ArrayList<>(); // Borrow history
    protected Integer accountBalance; //current account balance
    protected String profImage; //profile image address

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
	protected void setAccountBalance(int amount){this.accountBalance = amount;}

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
	 * @return
	 */
    public String getSecondLineAddress(){return this.secondLineAddress;}
    public String getPostCode(){return this.postCode;}
    public String getTownName(){return this.townName;}
    public String getFullAddress(){
        return getFirstLineAddress() + "\n" + getSecondLineAddress()
                + "\n" + getTownName() + "\n" + getPostCode();
    }
    public String getProfImage(){return this.profImage;}
    public Resource getResource(String resource){
        for (Resource resourceObj : this.resourceBorrow){
            if (resourceObj.getUniqueID().equals(resource)){
                return resourceObj;
            }
        }
        return null;
    }
    public ArrayList getAllResources(){return this.resourceBorrow;}
    public ArrayList getTransactions(){return this.transactionHistory;}
    public String getAccountBalance(){
        String s = "";
        if(String.valueOf(this.accountBalance).length() >= 3){
            s = "£" + String.valueOf(this.accountBalance).substring(0, String.valueOf(this.accountBalance).length() - 2)
                    +"."+String.valueOf(this.accountBalance).substring(String.valueOf(this.accountBalance).length() - 2);
        }else if (String.valueOf(this.accountBalance).length() >= 2){
            s = "£0."+String.valueOf(this.accountBalance);
        }else if (String.valueOf(this.accountBalance).length() >= 1){
            s = "£0.0"+String.valueOf(this.accountBalance);
        }
        return s;
    }

    public void addResource(Resource resource){this.resourceBorrow.add(resource);}
    //Not used as we do not collect stats
    protected void addResourceToHistory(String resourceID){}
    public void addAccountBalance(int amount){this.accountBalance = this.accountBalance + amount;}

    public void subtractAccountBalance(int amount){this.accountBalance = this.accountBalance - amount;}
    protected void addTransaction(int amount){
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = dataFormat.format(new Date());

        String[] data = {dateString, String.valueOf(amount)};

        transactionHistory.add(data);
    }
    public void removeResource(String resourceID){}

    public String toString(){
        String names = "";
        for (Resource re : resourceBorrow){
            names = names + re.getTitle() + "\n";
        }
        String transInfo = "";
        for (String[] a : transactionHistory){
            transInfo = transInfo + a[0] + " " +a[1]+"\n";
        }
        return  "Username: "+ getUserName()
                +"Name: " + getFirstName() + " " + getLastName() + "\n"
                +"Mobile Number: " +getMobileNumber() + "\n"
                +"Address:\n" + getFullAddress()
                +"Currently Borrowed:\n"+ names
                +"Transaction History: \n" + transInfo
                +"Account balance: "+ getAccountBalance();
    }
}
