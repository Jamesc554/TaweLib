package library;
import user.*;
import resources.*;
import io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Library {
	private static User currentUser;

	public static void start(){
		LibraryResources.start();
	}

	public static void addBook(String year,String title, String thumbnailImg, String uniqueID,
						   String author, String genre, String isbn, String publisher, ArrayList<String> lang){
		LibraryResources.addBook(new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, lang));
	}
	public static void addDVD(String year, String title, String thumbnailImageRef, String uniqueID,
						 String director, String runtime, String language, ArrayList<String> subLang){
	    LibraryResources.addDVD(new DVD(year, title, thumbnailImageRef, uniqueID, director, runtime, language, subLang));
	}
	public static void addLaptop(String year, String title, String thumbnailImageRef, String uniqueID,
							  String manufacturer, String model,  String operatingSys){
	    LibraryResources.addLaptop(new Laptop(year, title, thumbnailImageRef, uniqueID, manufacturer, model, operatingSys));
	}
	public static void addUser(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
						  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
		LibraryResources.addUser(new User(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage));
	}
	public static void addLibrarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
								  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
								  int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		LibraryResources.addUser(new Librarian(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage, empDay, empMonth, empYear, staffNumber,
                noOfEmploys));
	}
	public static Resource getResource(String id){
		String resourceType = id.substring(0, 1);
		switch (resourceType.toLowerCase()){
			case "l":
				return LibraryResources.getLaptop(id);
			case "d":
				return LibraryResources.getDVD(id);
			case "b":
				return LibraryResources.getBook(id);
			default:
				return null;
		}
	}
	public static User getUser(String username){
		return LibraryResources.getUser(username);
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
		getUser(username).loanResource(resourceID);
	}
	public static void returnResource(String username, String resourceID){
		getUser(username).returnResource(resourceID);
	}
	public static void removeResource(String id){
        LibraryResources.removeResource(id);
    }
	public static void removeUser(String username){
	    LibraryResources.removeUser(username);
	}
	public static void shutDown(){}

	public static boolean checkForUser(String username){
		return LibraryResources.checkIfValidUsername(username);
	}

	public static ArrayList<Book> getAllBooks(){
		return LibraryResources.getListOfBooks();
	}
	public ArrayList<Laptop> getAllLaptops(){
		return LibraryResources.getListOfLaptops();
	}
	public static ArrayList<DVD> getAllDVD(){
		return LibraryResources.getListOfDVD();
	}
	public static ArrayList<User> getAllUsers(){
		return LibraryResources.getAllUsers();
	}

	// TODO: CHANGE FROM STRING TO USER ONCE IMPLEMENTED
	public static void setLoggedInUser(User user) {
		currentUser = user;
	}

	public static User getCurrentLoggedInUser() {
		return currentUser;
	}

	public static void changeAddress(String username, String firstLine, String secondLine){
	    User u = getUser(username);
	    u.setFirstLineAddress(firstLine);
	    u.setSecondLineAddress(secondLine);
    }
	public static void changePoctCode(String username, String postCode){
	    getUser(username).setPostCode(postCode);
    }
	public static void changeTownName(String username, String townName){
	    getUser(username).setTownName(townName);
    }
	public static void changePhoneNumber(String username, String phoneNumber){
	    getUser(username).setMobileNumber(phoneNumber);
    }
	public static void changeLastName(String username, String lastname){
	    getUser(username).setLastName(lastname);
    }
	public static void changeImage(String username, String path){
	    getUser(username).setProfImage(path);
    }
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
	public static ArrayList<Resource> searchResources(String text){
	    int textSize = text.length();
	    Integer hashedTextSearch = hashText(text.toLowerCase());

	    ArrayList<String> textPart = new ArrayList();
        ArrayList<Integer> hashedTextPart = new ArrayList();
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Resource> result = new ArrayList<>();


        resources.addAll(LibraryResources.getListOfBooks());
        resources.addAll(LibraryResources.getListOfDVD());
        resources.addAll(LibraryResources.getListOfLaptops());

        int i = 0;
        
//        for (Resource r : resources) {
//        	if (r.getTitle().contains(text)) {
//        		
//        	}
//        }

        while(i < resources.size()){
            textPart = partString(resources.get(i), textSize);
            hashedTextPart = hashList(textPart);
            int j = 0;
            while(j < hashedTextPart.size()) {
                if(hashedTextPart.get(j).equals(hashedTextSearch)){
                    if(textPart.get(j).equals(text.toLowerCase())){
                        result.add(resources.get(i));
                    }
                }
                j++;
            }
        i++;
        }
        return result;
    }
    private static ArrayList<String> partString(Resource res, int partSize){
	    ArrayList<String> list = new ArrayList<>();
	    String resString = res.toSingleString();
	    int i = 0;
	    while(i+partSize+1 <= resString.length()){
	        list.add(resString.substring(i,i+partSize).toLowerCase());
	        i++;
        }
        list.add(resString.substring(resString.length()-partSize,resString.length()).toLowerCase());
        return list;
    }
    private static ArrayList<Integer> hashList(ArrayList<String> parts){
	    ArrayList<Integer> list = new ArrayList<>();
	    Integer sum;
	    for(String p : parts){
	        sum = 0;
	        for(int i=0; i < p.length(); i++){
	            sum += (int)p.charAt(i);
            }
            list.add(sum);
        }
        return list;
    }
    private static Integer hashText(String text){
	    Integer sum = 0;
	    String t = text.toLowerCase();
	    for(int i=0; i<t.length(); i++){
	        sum += (int)t.charAt(i);
        }
        return sum;
    }
}
