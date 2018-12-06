package io;

/**
 * @author Samuel Jankinson
 * This class holds all of the file locations of all of the data files that this program uses.
 * It contains the getters for all of the file locations, which are private variables.
 */
public abstract class IO {
	//Private attributes, they contain the location of all of the data files of the program.
	private static final String usersFilePath = "./data/users.json";
	private static final String librarianFilePath = "./data/librarians.json";
	private static final String bookFilePath = "./data/book.json";
	private static final String dvdFilePath = "./data/dvd.json";
	private static final String laptopFilePath = "./data/laptop.json";

	/**
	 * This method returns the file path of the data file that holds the users information.
	 * @return a String which contains the file path of the users data file.
	 */
	public static String getUsersFilePath() {
		return usersFilePath;
	}

	/**
	 * This method returns the file path of the data file that holds the librarians information.
	 * @return a String which contains the file path of the librarian data file.
	 */
	public static String getLibrarianFilePath() {
		return usersFilePath;
	}

	/**
	 * This method returns the file path of the data file that holds the books information.
	 * @return a String which contains the file path of the book data file.
	 */
	public static String getBookFilePath() {
		return bookFilePath;
	}

	/**
	 * This method returns the file path of the data file that holds the DVDs information
	 * @return a String which contains the file path of the DVD data file.
	 */
	public static String getDvdFilePath() {
		return dvdFilePath;
	}

	/**
	 * This method returns the file path of the data file that holds the laptops information.
	 * @return a String which
	 */
	public static String getLaptopFilePath() {
		return laptopFilePath;
	}
}
