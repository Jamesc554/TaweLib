package io;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import resources.Resource;
import user.User;
import utils.Queue;

/**
 * @author Samuel Jankinson
 */

public class ReadFile extends IO{
	private static FileReader file = null;
	private static BufferedReader reader = null;
	private static String currentLine = null;
	
	public static String readAll() {
		return "";
	}
	
	// When users class is done it will return an arraylist of user objects.
	@SuppressWarnings("rawtypes")
	public static ArrayList<User> readUsers() {
		JSONParser parser = new JSONParser();
		JSONArray resourceArray = new JSONArray();
		JSONArray transactionArray = new JSONArray();
		ArrayList<User> userList = new ArrayList<>();
		try {
			file = new FileReader(IO.getUsersFilePath());
			reader = new BufferedReader(file);
			while((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				User user = new User((String)object.get("username"),
						(String)object.get("firstName"),
						(String)object.get("lastName"),
						(String)object.get("mobileNumber"),
						(String)object.get("firstLineAddress"),
						(String)object.get("secondLineAddress"),
						(String)object.get("postCode"),
						(String)object.get("townName"),
						(int)object.get("accountBalance"),
						(String)object.get("imageAddress"));
				
				resourceArray = (JSONArray) object.get("resourceBorrow");
				String resourceBorrow = "";
				if (resourceArray != null) {
					for (Object resource : resourceArray) {
						String stringResource = (String) resource;
						resourceBorrow = resourceBorrow + stringResource + ",";
					}
				}
				// user.add(resourceBorrow);
				
				transactionArray = (JSONArray) object.get("transactionHistory");
				String transactionHistory = "";
				if (transactionArray != null) {
					for (Object transaction : transactionArray) {
						String stringTransaction = (String) transaction;
						transactionHistory = transactionHistory + stringTransaction + ",";
					}
				}
				//user.add(transactionHistory);
				
				userList.add(user);
			}
			
			
			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getUsersFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getUsersFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
		return userList;
	}
	
	public static ArrayList<ArrayList> readBooks() {
		JSONParser parser = new JSONParser();
		JSONArray languageArray = new JSONArray();
    	JSONArray bookQueueArray = new JSONArray();
    	JSONArray listOfCopiesArray = new JSONArray();
    	ArrayList<ArrayList> bookList = new ArrayList<ArrayList>();
    	
    	try {
			file = new FileReader(IO.getResourceFilePath());
			reader = new BufferedReader(file);
			
			while((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				ArrayList<String> book = new ArrayList<String>();
				book.add((String) object.get("year"));
				book.add((String) object.get("title"));
				book.add((String) object.get("thumbnailImg"));
				book.add((String) object.get("uniqueID"));
				book.add((String) object.get("author"));
				book.add((String) object.get("genre"));
				book.add((String) object.get("isbn"));
				book.add((String) object.get("publisher"));
				
				languageArray = (JSONArray) object.get("languages");
				String languages = "";
				if (languageArray != null) {
					for (Object language : languageArray) {
						String stringLanguage = (String) language;
						languages = languages + stringLanguage + ",";
					}
				}
				book.add(languages);
				
				bookQueueArray = (JSONArray) object.get("bookQueue");
				String bookQueues = "";
				if (bookQueueArray != null) {
					for (Object bookQueue : bookQueueArray) {
						String stringBookQueue = (String) bookQueue;
						bookQueues = bookQueues + stringBookQueue + ",";
					}
				}
				book.add(bookQueues);
				
				listOfCopiesArray = (JSONArray) object.get("resourceBorrow");
				String listOfCopies = "";
				if (listOfCopiesArray != null) {
					for (Object copie : listOfCopiesArray) {
						String stringCopie = (String) copie;
						listOfCopies = listOfCopies + stringCopie + ",";
					}
				}
				book.add(listOfCopies);
				
				bookList.add(book);
			}
			
			reader.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + IO.getResourceFilePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR reading file " + IO.getResourceFilePath());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR parsing users JSON");
			e.printStackTrace();
		}
    	return bookList;
	}
	
	public static String readDvds() {
		return "";
	}
	
	public static String readLaptops() {
		return "";
	}
	
	public static String readOutData() {
		return "";
	}
	
	public static String readBookQueue() {
		return "";
	}
	
	public static String readTransactions() {
		return "";
	}
}
