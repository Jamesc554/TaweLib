package user;

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



}
