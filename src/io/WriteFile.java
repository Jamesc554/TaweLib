package io;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class WriteFile extends IO {
	@SuppressWarnings({ "unchecked", "resource" })
	// currently you have to pass in loads of things. Once users class has been made, you can simply just pass in the user object.
	public static void writeUsers(String username, String firstName, String lastName, String mobileNumber, String firstLineAddress, String secondLineAddress, String postCode, String townName, String imageAddress, int accountBalance) {
		JSONObject object = new JSONObject();
		object.put("username", username);
		object.put("firstName", firstName);
		object.put("lastName", lastName);
		object.put("mobileNumber", mobileNumber);
		object.put("firstLineAddress", firstLineAddress);
		object.put("secondLineAddress", secondLineAddress);
		object.put("postCode", postCode);
		object.put("townName", townName);
		object.put("imageAddress", imageAddress);
		object.put("accountBalance", Integer.toString(accountBalance));
		try {
			FileWriter file = new FileWriter(IO.getUsersFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void writeResources(String resourceData) {
		
	}
    
    public static void writeOutData(String outData) {
		
	}

    public static void writeBookQueue(String bookQueueData) {
	
    }

    public static void writeStats(String statsData) {
	
    }

    public static void writeTransactions(String transactionData) {
	
    }
    
    public static void fullWrite(String usersData, String resourceData, String outData, String bookQueueData, String statsDate, String transactionData) {
    	
    }
    
    public static void removeUsers(String usersData) {
    	
    }
    
    public static void removeResources(String resourceData) {
    	
    }
    
    public static void removeOutData(String outData) {
    	
    }
    
    public static void removeBookQueue(String bookQueueData) {
    	
    }
    
    public static void removeStats(String StatsData) {
    	
    }
    
    public static void removeTransactions(String transactionsData) {
    	
    }
    
    public static void backupCurrent() {
    	
    }
}
