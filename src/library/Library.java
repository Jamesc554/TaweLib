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

	public Book createBook(String year,String title, String thumbnailImg, String uniqueID,
						   String author, String genre, String isbn, String publisher, ArrayList<String> lang){
		return new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, lang);
	}
	public DVD createDVD(String year, String title, String thumbnailImageRef, String uniqueID,
						 String director, String runtime, String language, ArrayList<String> subLang){
		return new DVD(year, title, thumbnailImageRef, uniqueID, director, runtime, language, subLang);
	}
	public Laptop creatLaptop(String year, String title, String thumbnailImageRef, String uniqueID,
							  String manufacturer, String model,  String operatingSys){
		return new Laptop(year, title, thumbnailImageRef, uniqueID, manufacturer, model, operatingSys);
	}
	public User creatUser(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
						  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
		return new User(userName, firstName, lastName, mobileNumber, firstLineAddress,
				secondLineAddress, postCode, townName, accountBalance, profImage);
	}
	public User creatLibrarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
								  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
								  int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		return new Librarian(userName, firstName, lastName, mobileNumber, firstLineAddress, secondLineAddress,
				postCode, townName, accountBalance, profImage, empDay, empMonth, empYear, staffNumber, noOfEmploys);
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

}
