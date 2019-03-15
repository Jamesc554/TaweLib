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
	private static final String RATINGS_FILEPATH = "./data/ratings.json";
	private static final String RESOURCE_FILEPATH = "./data/resources.json";
	private static final String EVENT_FILEPATH = "./data/events.json";

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
	 * This method returns the file path of the data file that holds the ratings information.
	 * @return a String which contains the file path of the ratings.
	 */
	public static String getRatingsFilePath() {
		return RATINGS_FILEPATH;
	}

	public static String getResourceFilePath() { return RESOURCE_FILEPATH; }

	public static String getEventFilepath() {
		return EVENT_FILEPATH;
	}

}
