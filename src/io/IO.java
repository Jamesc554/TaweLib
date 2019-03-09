package io;

/**
 * <h1>IO.</h1>
 * <p>This class holds all of the file locations of all of the data files that this program uses.
 * This contains the getters for all of the file locations, which are private variables.</p>
 * @author Samuel Jankinson, James Carter, Dominik Wojtasiewicz
 */
public abstract class IO {
	//Private attributes, they contain the location of all of the data files of the program.
	private static final String USERS_FILEPATH = "./data/users.json";
	private static final String LIBRARIAN_FILEPATH = "./data/librarians.json";
	private static final String BOOK_FILEPATH = "./data/book.json";
	private static final String DVD_FILEPATH = "./data/dvd.json";
	private static final String LAPTOP_FILEPATH = "./data/laptop.json";
	private static final String VIDEOGAME_FILEPATH = "./data/videogame.json";

	/**
	 * This method returns the file path of the data file that holds the users information.
	 * @return a String which contains the file path of the users data file.
	 */
	public static String getUsersFilePath() {
		return USERS_FILEPATH;
	}

	/**
	 * This method returns the file path of the data file that holds the librarians information.
	 * @return a String which contains the file path of the librarian data file.
	 */
	public static String getLibrarianFilePath() {
		return LIBRARIAN_FILEPATH;
	}

	/**
	 * This method returns the file path of the data file that holds the books information.
	 * @return a String which contains the file path of the book data file.
	 */
	public static String getBookFilePath() {
		return BOOK_FILEPATH;
	}

	/**
	 * This method returns the file path of the data file that holds the DVDs information
	 * @return a String which contains the file path of the DVD data file.
	 */
	public static String getDvdFilePath() {
		return DVD_FILEPATH;
	}

	/**
	 * This method returns the file path of the data file that holds the laptops information.
	 * @return a String which contains the file path of the laptops.
	 */
	public static String getLaptopFilePath() {
		return LAPTOP_FILEPATH;
	}
	
	/**
	 * This method returns the file path of the data file that holds the Video Game information.
	 * @return a String which contains the file path of the video games.
	 */
	public static String getVideoGameFilePath() {
		return VIDEOGAME_FILEPATH;
	}
}
