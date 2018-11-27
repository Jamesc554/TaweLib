package io;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import resources.Book;
import resources.DVD;
import user.User;

/**
 * @author Samuel Jankinson
 */

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		User user1 = new User("ben22", "ben", "smith", "07947147999", "123 Berry Road", "Swansea", "SA20AB", "Sketty", 430, "ben22profile.jpg");
		User user2 = new User("harry23", "harry", "smith", "07483147999", "76 Pumpkin Road", "Oxford", "OX38AB", "Oxford", 0, "harry23profile.jpg");
		ArrayList<String> languages = new ArrayList<String>();
		languages.add("English");
		languages.add("Spanish");
		languages.add("French");
		Book book1 = new Book("1999", "The Great Book", "/img/bookimg.jpg", "430594", "Sam L Jackson", "Horror", "isbn-2349-4399", "Google", languages);
		DVD dvd1 = new DVD("1999", "Dave", "testImg.jpg", languages, "David", "129", "English", "123456");
		WriteFile.writeDvd(dvd1);
		WriteFile.writeUser(user1);
		WriteFile.writeUser(user2);
		WriteFile.writeBook(book1);
		System.out.println(ReadFile.readUsers());
		System.out.println(ReadFile.readBooks());
		WriteFile.backupCurrent();
	}
}
