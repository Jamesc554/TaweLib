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
		for(User user : ReadFile.readUsers()) {
			System.out.println(user.getUserName());
		}
		System.out.println(ReadFile.readBooks());
		for(DVD dvd : ReadFile.readDvds()) {
			System.out.println(dvd.getUniqueID());
		}
	}
}
