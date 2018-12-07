package library;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
import resources.Book;
import resources.BorrowHistoryData;
import resources.DVD;
import resources.Laptop;
import resources.Resource;
import user.Librarian;
import user.User;

/**
 * This class implements all methods required for library operations.
 * @author Dominik R Wojtasiewicz
 * @since 18/11/2018
 */

public class Library {
	private static User currentUser; //Stores the currently logged-in user.

	/**
	 * This methods starts the library. Library is a static method so we can only
	 * have one at a time.
	 */
	public static void start(){ LibraryResources.start();
	}

	/**
	 * This methods adds a new book to the library.
	 * @param year	Year published.
	 * @param title Book title.
	 * @param thumbnailImg Path to the image of the book.
	 * @param uniqueID ID of the book.
	 * @param author The author of the book.
	 * @param genre The genre of the book.
	 * @param isbn ISBN of the book.
	 * @param publisher The name of the published.
	 * @param lang Language of the book.
	 */
	public static void addBook(String year,String title, String thumbnailImg, String uniqueID,
						   String author, String genre, String isbn, String publisher, ArrayList<String> lang, Integer noOfCopies, ArrayList<String> loanDuration, List<List<BorrowHistoryData>> borrowHistory, List<BorrowHistoryData> currentBorrowData){
		LibraryResources.addBook(new Book(year, title, thumbnailImg, uniqueID, author, genre, isbn, publisher, lang, noOfCopies, loanDuration, borrowHistory, currentBorrowData));
	}

	/**
	 * This methods adds a new DVD to the library.
	 * @param year	Year released .
	 * @param title Book title.
	 * @param thumbnailImg Path to the image of the DVD.
	 * @param uniqueID ID of the DVD.
	 * @param director The name of the director.
	 * @param runtime The run time of the movie.
	 * @param language The language of the movie.
	 * @param subLang The subtitles language of the DVD.
	 */
	public static void addDVD(String year, String title, String thumbnailImg, String uniqueID,
						 String director, String runtime, String language, ArrayList<String> subLang, Integer noOfCopies, ArrayList<String> loanDuration, List<List<BorrowHistoryData>> borrowHistory, List<BorrowHistoryData> currentBorrowData){
	    LibraryResources.addDVD(new DVD(year, title, thumbnailImg, subLang, director, runtime, language, uniqueID, noOfCopies, loanDuration, borrowHistory, currentBorrowData));
	}

	/**
	 * This methods adds a laptop to the library.
	 * @param year The year of release.
	 * @param title The name of the laptop.
	 * @param thumbnailImageRef Img of the laptop.
	 * @param uniqueID The id of the laptop.
	 * @param manufacturer The manufacture of the laptop.
	 * @param model The model of the laptop.
	 * @param operatingSys The operating system of the laptop.
	 */
	public static void addLaptop(String year, String title, String thumbnailImageRef, String uniqueID,
							  String manufacturer, String model,  String operatingSys, Integer noOfCopies, ArrayList<String> loanDuration, List<List<BorrowHistoryData>> borrowHistory, List<BorrowHistoryData> currentBorrowData){
	    LibraryResources.addLaptop(new Laptop(year, title, thumbnailImageRef, uniqueID, manufacturer, model, operatingSys, noOfCopies, loanDuration, borrowHistory, currentBorrowData));
	}

	/**
	 * This methods adds a user to the library.
	 * @param userName the username.
	 * @param firstName first name.
	 * @param lastName last name.
	 * @param mobileNumber mobile number.
	 * @param firstLineAddress first line of address.
	 * @param secondLineAddress second line of address.
	 * @param postCode post code.
	 * @param townName name of the town.
	 * @param accountBalance account balance
	 * @param profImage path to the profile image of the user.
	 */
	public static void addUser(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
						  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage){
		LibraryResources.addUser(new User(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage));
	}
	/**
	 * This methods adds a librarian to the library.
	 * @param userName the username.
	 * @param firstName first name.
	 * @param lastName last name.
	 * @param mobileNumber mobile number.
	 * @param firstLineAddress first line of address.
	 * @param secondLineAddress second line of address.
	 * @param postCode post code.
	 * @param townName name of the town.
	 * @param accountBalance account balance
	 * @param profImage path to the profile image of the librarian.
	 * @param empDay the day of employment start.
	 * @param empMonth the month of employment start.
	 * @param empYear the year of employment start.
	 * @param staffNumber staff number.
	 * @param noOfEmploys number of employs.
	 */
	public static void addLibrarian(String userName, String firstName, String lastName, String mobileNumber, String firstLineAddress,
								  String secondLineAddress, String postCode, String townName, int accountBalance, String profImage,
								  int empDay, int empMonth, int empYear, String staffNumber, int noOfEmploys){
		LibraryResources.addUser(new Librarian(userName, firstName, lastName, mobileNumber, firstLineAddress,
                secondLineAddress, postCode, townName, accountBalance, profImage, empDay, empMonth, empYear, staffNumber,
                noOfEmploys));
	}

	/**
	 * Get's resource when the id of sed resource is known. Used for all resources.
	 * @param id String of ID of the resource.
	 * @return Resource object.
	 */
	public static Resource getResource(String id){
		id = id.split("-")[0];
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
	/**
	 * Gets the user based on username.
	 * @param username String username.
	 * @return user object.
	 */
	public static User getUser(String username){
		return LibraryResources.getUser(username);
	}
	/**
	 * Adds balance to the user account.
	 * @param amount amount to increase the balance.
	 * @param username the username to modify.
	 */
	public static void addBalance(int amount, String username){
		getUser(username).addAccountBalance(amount);
	}

	/**
	 * Removes balance from account.
	 * @param amount amount to be removed.
	 * @param username username of the user.
	 */
	public static void subtractBalance (int amount, String username) {
		if (amount <= 0 ) {
			throw new IllegalArgumentException("Cannot subtract negative or null amount");
		} else if (amount > currentUser.getAccountBalanceDouble()) {
			throw new IllegalArgumentException("Amount superior to account balance");
		}
		getUser(username).subtractAccountBalance(amount);
	}

	/**
	 * Returns current date and time in the following format DD-MM-YYYY HH-MM-SS
	 * @return String.
	 */
	public static String getCurrentDateTime(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dataFormat.format(new Date());

	}

	/**
	 * Loans resource to the user.
	 * @param username Username of the person borrowing a resource.
	 * @param resourceID The id of the resource.
	 */
	public static void loanResource(String username, String resourceID){
		getUser(username).loanResource(resourceID);
		String[] resInfo = resourceID.split("-");
		Resource r = Library.getResource(resInfo[0]);
		r.loanResource(resInfo[1], username);
	}

	/**
	 * Returns resource to the library.
	 * @param username Username
	 * @param resourceID ID of the resource.
	 */
	public static void returnResource(String username, String resourceID){
		getUser(username).returnResource(resourceID);
		checkForRequested(resourceID);
	}

	private static void checkForRequested(String id){
		String[] data = id.split("-");
		Resource r = Library.getResource(data[0]);
		if(!r.checkIfRequested()){
			User u = r.peekQueueOfReservations();
			u.moveToReserved(id);
		}
	}

	/**
	 * This removes a resource from library.
	 * @param id Unique ID of the resource.
	 */
	public static void removeResource(String id){
        LibraryResources.removeResource(id);
    }

	/**
	 * Remove user from the library.
	 * @param username
	 */
	public static void removeUser(String username){
	    LibraryResources.removeUser(username);
	}

	/**
	 * This methods shuts down the library.
	 */
	public static void shutDown(){}

	/**
	 * Checks if user exists.
	 * @param username Username of the user
	 * @return Boolean. True if exists False if not.
	 */
	public static boolean checkForUser(String username){
		return LibraryResources.checkIfValidUsername(username);
	}

	/**
	 * Returns all books in the library.
	 * @return ArrayList<Book> of all books.
	 */
	public static ArrayList<Book> getAllBooks(){
		return LibraryResources.getListOfBooks();
	}
	/**
	 * Returns all Laptops in the library.
	 * @return ArrayList<Laptop> of all books.
	 */
	public static ArrayList<Laptop> getAllLaptops(){
		return LibraryResources.getListOfLaptops();
	}
	/**
	 * Returns all books in the DVD's.
	 * @return ArrayList<DVD> of all books.
	 */
	public static ArrayList<DVD> getAllDVD(){
		return LibraryResources.getListOfDVD();
	}
	/**
	 * Returns all users in the library.
	 * @return ArrayList<User> of all books.
	 */
	public static ArrayList<User> getAllUsers(){
		return LibraryResources.getAllUsers();
	}

	/**
	 * Sets the current logged in user.
	 * @param user the user object.
	 */
	public static void setLoggedInUser(User user) {
		currentUser = user;
	}

	/**
	 * Gets the currently logged in user.
	 * @return User object.
	 */
	public static User getCurrentLoggedInUser() {
		return currentUser;
	}

	/**
	 * Checks if the logged in user is of class Librarian
	 * @return True if current user is a Librarian
	 */
	public static boolean currentUserIsLibrarian() {
		return currentUser instanceof Librarian;
	}

	/**
	 * Changes address of the user.
	 * @param username The username of the user for which the address should be changed.
	 * @param firstLine The first line of address.
	 * @param secondLine Second line of address.
	 */
	public static void changeAddress(String username, String firstLine, String secondLine){
	    User u = getUser(username);
	    u.setFirstLineAddress(firstLine);
	    u.setSecondLineAddress(secondLine);
    }

	/**
	 * Changes the post code of the user.
	 * @param username The username of the user for which the address should be changed.
	 * @param postCode
	 */
	public static void changePoctCode(String username, String postCode){
	    getUser(username).setPostCode(postCode);
    }

	/**
	 * Changes the post code of the user.
	 * @param username The username of the user for which the address should be changed.
	 * @param townName The new town name.
	 */
	public static void changeTownName(String username, String townName){
	    getUser(username).setTownName(townName);
    }

	/**
	 * Changes the post code of the user.
	 * @param username The username of the user for which the address should be changed.
	 * @param phoneNumber The new phone number.
	 */
	public static void changePhoneNumber(String username, String phoneNumber){
	    getUser(username).setMobileNumber(phoneNumber);
    }

	/**
	 * Changes the last name of the user.
	 * @param username The username of which data will be updated.
	 * @param lastname The new last name.
	 */
	public static void changeLastName(String username, String lastname){
	    getUser(username).setLastName(lastname);
    }

	/**
	 * Changes the profile img of the user.
	 * @param username The username of the user for which the address should be changed.
	 * @param path the new path of the profile image.
	 */
	public static void changeImage(String username, String path){
	    getUser(username).setProfImage(path);
    }

	/**
	 * User toString.
	 * @param username username.
	 * @return single string will all user information.
	 */
	public static String userToString(String username){
		User u = getUser(username);
	    String info = u.getUserName()+ "\n";
	    info += u.getFullName()+"\n"+u.getMobileNumber()+"\n"+u.getFullAddress()+"\n";
	    info += "Current Balance: "+u.getAccountBalanceString()+"\n";
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
    /**
     * This method looks up all resource based on partial string.
     * @param text partial search parameter.
     * @return ArrayList<Resource> of object that match the search query.
     */

	public static ArrayList<Resource> searchResources(String text){
		//Sets size of the search for hashing.
		int textSize = text.length();
		//Hashes the text we searching.
	    Integer hashedTextSearch = hashText(text.toLowerCase());

	    //Get's all data needed for search.
	    ArrayList<String> textPart;
        ArrayList<Integer> hashedTextPart;
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Resource> result = new ArrayList<>();

        //Add's all needed data.
        resources.addAll(LibraryResources.getListOfBooks());
        resources.addAll(LibraryResources.getListOfDVD());
        resources.addAll(LibraryResources.getListOfLaptops());

        int i = 0;

        //While there are resources that have not been checked.
        while(i < resources.size()){
        	//Divides the text into substring for hashing.
            textPart = partString(resources.get(i), textSize);
            //Hashes substrings.
            hashedTextPart = hashList(textPart);
            int j = 0;
            //While the are parts the need to be compared
            while(j < hashedTextPart.size()) {
            	//If the hashed numbers are the same.
                if(hashedTextPart.get(j).equals(hashedTextSearch)){
                	//If the corresponding string are the same
                    if(textPart.get(j).equals(text.toLowerCase())){
                    	//Add said resource to list
                        result.add(resources.get(i));
                    }
                }
                j++;
            }
        i++;
        }
        return result;
    }

	/**
	 * This methods divides object information into substring for hashing.
	 * @param res The resource to be split.
	 * @param partSize The part size of the list
	 * @return ArrayList<String> of strings.
	 */
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

	/**
	 * This methods hashed the substring into an arrayList.
	 * @param parts ArrayList<String> to be hashed.
	 * @return ArrayList<Integer> containing hashed data.
	 */
	private static ArrayList<Integer> hashList(ArrayList<String> parts){
	    ArrayList<Integer> list = new ArrayList<>();
	    Integer sum;
	    for(String p : parts){
	        sum = hashText(p);
	        list.add(sum);
        }
        return list;
    }

	/**
	 * Hashes a string into number.
	 * @param text string to be hashed.
	 * @return Integer representation of the number.
	 */
	private static Integer hashText(String text){
	    Integer sum = 0;
	    String t = text.toLowerCase();
	    for(int i=0; i<t.length(); i++){
	        sum += (int)t.charAt(i);
        }
        return sum;
    }

	/**
	 * Allows users to request a book that is not available.
	 * @param id of resource to be requested
	 */
	public static void requestResource(String id){
		currentUser.requestResource(id);
	}

	/**
	 * Returns all requested books of the user currently logged in.
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getAllrequestedResource(){
		return currentUser.getAllRequested();
	}

	/**
	 * Returns all reserved items of the user currently logged in.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getAllReservedResources(){ return currentUser.getAllReserved();}

	public Boolean chekcIfOverDue(String id){
		return Library.getResource(id).checkIfOverdue(Integer.valueOf(id.split("-")[1]));
	}

}
