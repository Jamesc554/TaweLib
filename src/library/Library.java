package library;
import user.*;
import resources.*;
import io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Library {
	private static LibraryResources libraryResources = new LibraryResources();
	private static String currentUser;


	public static void addBook(String year,String title, String thumbnailImg, String uniqueID,
						   String author, String genre, String isbn, String publisher, ArrayList<String> lang){
		libraryResources.addBook(new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, lang));
	}
	public static void addDVD(String year, String title, String thumbnailImageRef, String uniqueID,
						 String director, String runtime, String language, ArrayList<String> subLang){
	    libraryResources.addDVD(new DVD(year, title, thumbnailImageRef, uniqueID, director, runtime, language, subLang));
	}
	public static void addLaptop(String year, String title, String thumbnailImageRef, String uniqueID,
							  String manufacturer, String model,  String operatingSys){
	    libraryResources.addLaptop(new Laptop(year, title, thumbnailImageRef, uniqueID, manufacturer, model, operatingSys));
	}
	public static void addUser(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
						  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
		libraryResources.addUser(new User(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage));
	}
	public static void addLibrarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
								  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
								  int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		libraryResources.addUser(new Librarian(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage, empDay, empMonth, empYear, staffNumber,
                noOfEmploys));
	}
	public static Resource getResource(String id){
		String resourceType = id.substring(0, 1);
		switch (resourceType.toLowerCase()){
			case "l":
				return libraryResources.getLaptop(id);
			case "d":
				return libraryResources.getDVD(id);
			case "b":
				return libraryResources.getBook(id);
			default:
				return null;
		}
	}
	public static User getUser(String username){
		return libraryResources.getUser(username);
	}
	public static void addBalance(int amount, String username){
		getUser(username).addAccountBalance(amount);
	}
	public static void subtractBalance(int amount, String username){
		getUser(username).addAccountBalance(amount);
	}
	public static String getCurrentDateTime(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dataFormat.format(new Date());

	}
	public static void loanResource(String username, String resourceID){
		getUser(username).addResource(resourceID);
	}
	public static void returnResource(String username, String resourceID){
		getUser(username).returnResource(resourceID);
	}
	public static void removeResource(String id){}
	public static void removeUser(String username){}
	public static void shutDown(){}
	
	public static boolean checkForUser(String username){
		return libraryResources.checkIfValidUsername(username);
	}
	
	public static ArrayList<Book> getAllBooks(){
		return null; //placeholder
	}
	public static ArrayList<Laptop> getAllLaptops(){
		return null; //placeholder}
	}
	public static ArrayList<DVD> getAllDVD(){
		return null; //placeholder
	}
	public static ArrayList<User> getAllUsers(){
		return null; //placeholder
	}
	
	// TODO: CHANGE FROM STRING TO USER ONCE IMPLEMENTED
	public static void setLoggedInUser(String username) {
		currentUser = username;
	}
	
	public static String getCurrentLoggedInUser() {
		return currentUser;
	}
	
	public static void changeAddress(String username, String firstLine, String secondLine){}
	public static void changePoctCode(String username, String postCode){}
	public static void changeTownName(String username, String townName){}
	public static void changePhoneNumber(String username, String phoneNumber){}
	public static void changeLastName(String username, String lastname){}
	public static void changeImage(String username, String path){}
	public static void searchResources(String text){}
	public static String userToString(String username){
		User u = getUser(username);
	    String info = u.getUserName()+ "\n";
	    info += u.getFullName()+"\n"+u.getMobileNumber()+"\n"+u.getFullAddress()+"\n";
	    info += "Current Balance: "+u.getAccountBalance()+"\n";
	    info += "Profile Image Path: "+u.getProfImage()+"\n";
	    info += "Currently Borrowed:\n";
	    for(Object id : u.getCurrentlyBorrowedResources()){
            info += getResource((String)id ).getTitle()+"\n";
        }
	    info += "Borrow History:\n";
	    for(String[] data : u.getBorrowHistory()){
	        info += data[0] +" "+ getResource(data[1]).getTitle() +" "+ data[2] + "\n";
        }
	return info;
    }
}
