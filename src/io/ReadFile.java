package io;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	public static ArrayList<ArrayList> readUsers() {
		JSONParser parser = new JSONParser();
		ArrayList<ArrayList> userList = new ArrayList<ArrayList>();
		try {
			file = new FileReader(IO.getUsersFilePath());
			reader = new BufferedReader(file);
			while((currentLine = reader.readLine()) != null) {
				JSONObject object = (JSONObject) parser.parse(currentLine);
				ArrayList<String> user = new ArrayList<String>();
				user.add((String) object.get("username"));
				user.add((String) object.get("firstName"));
				user.add((String) object.get("lastName"));
				user.add((String) object.get("mobileNumber"));
				user.add((String) object.get("firstLineAddress"));
				user.add((String) object.get("secondLineAddress"));
				user.add((String) object.get("postCode"));
				user.add((String) object.get("townName"));
				user.add((String) object.get("imageAddress"));
				user.add((String) object.get("accountBalance"));
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
	
	public static String readResources() {
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
