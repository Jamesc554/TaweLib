package user;

import resources.Resource;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String mobileNumber;
    protected String firstLineAddress;
    protected String secondLineAddress;
    protected String postCode;
    protected String townName;
    protected ArrayList<Resource> resourceBorrow = new ArrayList<>();
    protected ArrayList<String[]> transactionHistory = new ArrayList<>();
    protected ArrayList<String[]> borrowHistory = new ArrayList<>();
    protected Integer accountBalance;
    protected String profImage;

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
    protected void setUserName(String userName){}
    protected void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}
    public void setFirstLineAddress(String firstLineAddress){this.firstLineAddress = firstLineAddress;}
    public void setSecondLineAddress(String secondLineAddress){this.secondLineAddress = secondLineAddress;}
    public void setPostCode(String postCode){this.postCode = postCode;}
    public void setTownName(String townName){this.townName = townName;}
    public void setProfImage(String profImage){this.profImage = profImage;}
    protected void setAccountBalance(int amount){this.accountBalance = amount;}

    public String getUserName(){return this.userName;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getMobileNumber(){return this.mobileNumber;}
    public String getFirstLineAddress(){return this.firstLineAddress;}
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
