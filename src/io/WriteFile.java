package io;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import user.User;

/**
 * @author Samuel Jankinson
 */

public class WriteFile extends IO {
	@SuppressWarnings({ "unchecked" })
	// will add borrow history etc after discussed with meeting.
	public static void writeUsers(User user) {
		JSONObject object = new JSONObject();
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
		try {
			FileWriter file = new FileWriter(IO.getUsersFilePath(), true);
			file.write(object.toJSONString() + "\n");
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void writeResource(String resourceData) {
		
	}
    
    public static void writeOutData(String outData) {
		
	}

    public static void writeBookQueue(String bookQueueData) {
	
    }

    public static void writeTransactions(String transactionData) {
	
    }
    
    public static void fullWrite(String usersData, String resourceData, String outData, String bookQueueData, String statsDate, String transactionData) {
    	
    }
    
    public static void removeUsers(String username) {
    	
    }
    
    public static void removeResources(String resourceData) {
    	
    }
    
    public static void removeOutData(String outData) {
    	
    }
    
    public static void removeBookQueue(String bookQueueData) {
    	
    }
    
    public static void removeTransactions(String transactionsData) {
    	
    }
    
    public static void backupCurrent() {
    	
    }
}
