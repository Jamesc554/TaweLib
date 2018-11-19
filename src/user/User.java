package user;

import resources.Resource;

import java.util.ArrayList;

public class User {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String mobileNumber;
    protected String firstLineAddress;
    protected String secondLineAddress;
    protected String postCode;
    protected String townName;
    protected ArrayList<String> resourceBorrowID = new ArrayList<>();
    protected ArrayList<String[]> transactionHistory = new ArrayList<>();
    protected ArrayList<String[]> borrowHistory = new ArrayList<>();
    protected Integer accountBalance;
    protected String profImage;

    public User(){}
    protected void setUserName(){}
    protected void setFirstName(){}
    public void setLastName(){}
    public void setMobileNumber(){}
    public void setFirstLineAddress(){}
    public void setSecondLineAddress(){}
    public void setPostCode(){}
    public void setTownName(){}
    public void setProfImage(){}

    public String getUserName(){}
    public String getFirstName(){}
    public String getLastName(){}
    public String getMobileNumber(){}
    public String getFirstLineAddress(){}
    public String getSecondLineAddress(){}
    public String getPostCode(){}
    public String getTownName(){}
    public String getFullAddress(){}
    public String getProfImage(){}
    public Resource getResource(String resourceID){}
    public ArrayList getAllResources(){}
    public ArrayList getTransactions(){}

    public void addResource(Resource resource){}
    protected void addResourceToHistory(String resourceID){}
    public void addAccountBalance(int balance){}

    public void subtractAccountBalance(int balance){}
    protected void addTransaction(int amount){}
    public void removeResource(String resourceID){}

}
