package io;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import resources.Book;
import resources.Resource;
import user.User;
import utils.Queue;

/**
 * @author Samuel Jankinson
 */

public class WriteFile extends IO {
	@SuppressWarnings({ "unchecked" })
	// will add borrow history etc after discussed with meeting.
	public static void writeUsers(User user) {
		JSONObject object = new JSONObject();
		JSONArray resourceArray = new JSONArray();
		JSONArray transactionArray = new JSONArray();
		object.put("username", user.getUserName());
		object.put("firstName", user.getFirstName());
		object.put("lastName", user.getLastName());
		object.put("mobileNumber", user.getMobileNumber());
		object.put("firstLineAddress", user.getFirstLineAddress());
		object.put("secondLineAddress", user.getSecondLineAddress());
		object.put("postCode", user.getPostCode());
		object.put("townName", user.getTownName());
		object.put("imageAddress", user.getProfImage());
		object.put("accountBalance", user.getAccountBalance());
		
		for (Object resource : user.getAllResources()) {
			resourceArray.add(((Resource) resource).getUniqueID());
		}
		object.put("resourceBorrow", resourceArray);
		
		ArrayList<String[]> test = user.getTransactions();
		for (String[] transaction : test) {
			transactionArray.add(transaction[0]);
			transactionArray.add(transaction[1]);
		}
		object.put("transactionHistory", transactionArray);
		
		try {
			FileWriter file = new FileWriter(IO.getUsersFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing user to " + IO.getUsersFilePath() + " " + user.getUserName());
		}
	}
	
    @SuppressWarnings("unchecked")
	public static void writeBook(Book book) {
    	JSONObject object = new JSONObject();
    	JSONArray languageArray = new JSONArray();
    	JSONArray bookQueueArray = new JSONArray();
    	JSONArray listOfCopiesArray = new JSONArray();
    	object.put("year", book.getYear());
    	object.put("title", book.getTitle());
    	object.put("thumbnailImg", book.getThumbnailImageRef());
    	object.put("uniqueID", book.getUniqueID());
    	object.put("author", book.getAuthor());
    	object.put("genre", book.getGenre());
    	object.put("isbn", book.getIsbn());
    	object.put("publisher", book.getPublisher());
    	
    	for (String language : book.getLanguages()) {
    		languageArray.add(language);
    	}
    	object.put("languages", languageArray);
    	
    	Queue<User> bookQueue = book.getQueueOfReservations();
    	while(!bookQueue.isEmpty()) {
    		bookQueueArray.add(bookQueue.peek().getUserName());
    		bookQueue.dequeue();
    	}
    	object.put("bookQueue", bookQueueArray);
    	
    	for (String copies : book.getArrayListOfCopies()) {
    		listOfCopiesArray.add(copies);
    	}
    	object.put("listOfCopies", listOfCopiesArray);
    	
    	try {
    		FileWriter file = new FileWriter(IO.getResourceFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
    	} catch (IOException e) {
    		System.out.println("Error writing book to " + IO.getResourceFilePath() + " " + book.getUniqueID());
    	}
	}
    
    // will be changed to Dvd dvd and Laptop laptop when those classes are added to git.
    public static void writeDvd(Book book) {
		
	}
    
    public static void writeLaptop(Book book) {
		
	}
    
    public static void writeOutData(String outData) {
		
	}

    public static void writeBookQueue(String bookQueueData) {
	
    }

    public static void writeTransaction(String transactionData) {
	
    }
    
    public static void overwriteUsers(ArrayList<User> user) {
    	
    }
    
    // Will be changed to laptop and dvd when those classes are added to git.
    public static void overwriteResources(ArrayList<Book> book, ArrayList<Book> dvd, ArrayList<Book> laptop) {
    	
    }
    
    public static void overwriteOutData(String outData) {
    	
    }
    
    public static void overwriteBookQueues(String bookQueueData) {
    	
    }
    
    public static void overwriteTransactions(String transactionData) {
    	
    }
    
    public static void fullWrite(String usersData, String resourceData, String outData, String bookQueueData, String statsDate, String transactionData) {
    	
    }
    
    public static void backupCurrent() {
    	
    }
}
