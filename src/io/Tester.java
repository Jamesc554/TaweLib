package io;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import user.User;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		User user1 = new User("ben22", "ben", "smith", "07947147999", "123 Berry Road", "Swansea", "SA20AB", "Sketty", 430, "ben22profile.jpg");
		User user2 = new User("harry23", "harry", "smith", "07483147999", "76 Pumpkin Road", "Oxford", "OX38AB", "Oxford", 0, "harry23profile.jpg");
		WriteFile.writeUsers(user1);
		WriteFile.writeUsers(user2);
		System.out.println(ReadFile.readUsers());
	}
}
