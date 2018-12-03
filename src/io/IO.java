package io;

/**
 * @author Samuel Jankinson
 */

public abstract class IO {
	private static final String usersFilePath = "./data/users.json";
	private static final String librarianFilePath = "./data/librarians.json";
	private static final String bookFilePath = "./data/book.json";
	private static final String dvdFilePath = "./data/dvd.json";
	private static final String laptopFilePath = "./data/laptop.json";
	
	public static String getUsersFilePath() {
		return usersFilePath;
	}
	
	public static String getLibrarianFilePath() {
		return usersFilePath;
	}
	
	public static String getBookFilePath() {
		return bookFilePath;
	}
	
	public static String getDvdFilePath() {
		return dvdFilePath;
	}
	
	public static String getLaptopFilePath() {
		return laptopFilePath;
	}
}
