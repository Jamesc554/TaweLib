package library;
import user.*;
import resources.*;
import io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Library {
	private LibraryResources libraryResources = new LibraryResources();

	Library(){ };

	public void addBook(String year,String title, String thumbnailImg, String uniqueID,
						   String author, String genre, String isbn, String publisher, ArrayList<String> lang){
		libraryResources.addBook(new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, lang));
	}
	public void addDVD(String year, String title, String thumbnailImageRef, String uniqueID,
						 String director, String runtime, String language, ArrayList<String> subLang){
	    libraryResources.addDVD(new DVD(year, title, thumbnailImageRef, uniqueID, director, runtime, language, subLang));
	}
	public void addLaptop(String year, String title, String thumbnailImageRef, String uniqueID,
							  String manufacturer, String model,  String operatingSys){
	    libraryResources.addLaptop(new Laptop(year, title, thumbnailImageRef, uniqueID, manufacturer, model, operatingSys));
	}
	public void addUser(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
						  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
		libraryResources.addUser(new User(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage));
	}
	public void addLibrarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
								  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
								  int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		libraryResources.addUser(new Librarian(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage, empDay, empMonth, empYear, staffNumber,
                noOfEmploys));
	}
	public Resource getResource(String id){
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
	public User getUser(String username){
		return libraryResources.getUser(username);
	}
	public void addBalance(int amount, String username){
		getUser(username).addAccountBalance(amount);
	}
	public void subtractBalance(int amount, String username){
		getUser(username).addAccountBalance(amount);
	}
	public String getCurrentDateTime(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dataFormat.format(new Date());

	}
	public void loanResource(String username, String resourceID){
		getUser(username).addResource(resourceID);
	}
	public void returnResource(String username, String resourceID){
		getUser(username).returnResource(resourceID);
	}
	public void removeResource(String id){}
	public void removeUser(String username){}
	public void shutDown(){}
	public Boolean checkForUser(){
		return null; //placeholder
	}
	public ArrayList<Book> getAllBooks(){
		return null; //placeholder
	}
	public ArrayList<Laptop> getAllLaptops(){
		return null; //placeholder}
	}
	public ArrayList<DVD> getAllDVD(){
		return null; //placeholder
	}
	public ArrayList<User> getAllUsers(){
		return null; //placeholder
	}
	public void changeAddress(String username, String firstLine, String secondLine){}
	public void changePoctCode(String username, String postCode){}
	public void changeTownName(String username, String townName){}
	public void changePhoneNumber(String username, String phoneNumber){}
	public void changeLastName(String username, String lastname){}
	public void changeImage(String username, String path){}
	public void searchResources(String text){}
	public String userToString(String username){
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
	        info += data[0] + data[1] + data[2] + "\n";
        }
	return info;
    }
}
