package library;
import resources.*;
import user.*;
import io.*;
/**
 * This class implements the LibraryResources database. This class is static.
 * @author Dominik Wojtasiewicz
 * @since 18/11/2018
 */
import java.util.ArrayList;

public class LibraryResources {
	private static ArrayList<Book> listOfBooks = new ArrayList<>(); //List of books
	private static ArrayList<Laptop> listOfLaptops = new ArrayList<>(); //List of laptops
    private static ArrayList<DVD> listOfDVD = new ArrayList<>(); //List of dvd's
    private static ArrayList<User> listOfUsers = new ArrayList<>(); //List of users
	private static ArrayList<Librarian> listOfLibrarians = new ArrayList<>();

	/**
	 * On start create all users, dvd, books, laptops.
	 */
	public static void start() {
		listOfUsers = ReadFile.readUsers();
		listOfDVD = ReadFile.readDvds();
		listOfBooks = ReadFile.readBooks();
		listOfLaptops = ReadFile.readLaptops();
		listOfLibrarians = ReadFile.readLibrarians();
	}

	/**
	 * Adds a book to the libraryResource.
	 * @param book Book object.
	 */
	public static void addBook(Book book) {
		listOfBooks.add(book);
	}

	/**
	 * Adds a laptop to the libraryResource.
	 * @param laptop Laptop object.
	 */
	public static void addLaptop(Laptop laptop) {
		listOfLaptops.add(laptop);
	}

	/**
	 * Adds a dvd to the libraryResources.
	 * @param dvd DVD object.
	 */
	public static void addDVD(DVD dvd) {
		listOfDVD.add(dvd);
	}

	/**
	 * Adds a user to the libraryResources.
	 * @param user a User object.
	 */
	public static void addUser(User user) {
		listOfUsers.add(user);
	}

	/**
	 * Get's a user form the database
	 * @param username the username.
	 * @return a User object.
	 */
	protected static User getUser(String username) {
		for(User u : listOfUsers) {
			if(u.getUserName().equals(username)) {
				return u;
			}
		}

		for(Librarian r : listOfLibrarians) {
			if(r.getUserName().equals(username)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Checks if a user is valid.
	 * @param username the username
	 * @return True is is valid ID else false
	 */
	protected static boolean checkIfValidUsername(String username) {
		if (listOfUsers.isEmpty()) {
			return false;
		}
		for (User u : listOfUsers) {
			if (u.getUserName().equals(username)) {
				return true;
			}
		}

		if (listOfLibrarians.isEmpty()) {
			return false;
		}

		for (Librarian r : listOfLibrarians) {
			if (r.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a book from list based on id.
	 * @param id of the book.
	 * @return Book object. Returns null if no such book.
	 */
	protected static Book getBook(String id) {
		for(Book b : listOfBooks) {
			if(b.getUniqueID().equals(id)) {
				return b;
			}
		}
		return null;
	}

	/**
	 * Gets a DVD from list based on id.
	 * @param id of the DVD.
	 * @return DVD object. Null if no such object.
	 */
	protected static DVD getDVD(String id) {
		for(DVD d : listOfDVD) {
			if(d.getUniqueID().equals(id)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets a Laptop from list based on id.
	 * @param id of the Laptop.
	 * @return Laptop object. Null if no such object.
	 */
	protected static Laptop getLaptop(String id) {
		for(Laptop l : listOfLaptops) {
			if(l.getUniqueID().equals(id)) {
				return l;
			}
		}
		return null;
	}

	/**
	 * Returns all users
	 * @return ArrayList<User> of users.
	 */
	protected static ArrayList<User> getAllUsers() {
		return listOfUsers;
	}

	/**
	 * Return all librarians.
	 * @return ArrayList<Librarian> of librarians.
	 */
	protected static ArrayList<Librarian> getAllLibrarians() {
		return listOfLibrarians;
	}

	/**
	 * Returns all books.
	 * @return ArrayList<Book> of books.
	 */
	protected static ArrayList<Book> getListOfBooks() {
		return listOfBooks;
	}

	/**
	 * Returns all DVD.
	 * @return ArrayList<DVD> of DVDs.
	 */
	protected static ArrayList<DVD> getListOfDVD() {
		return listOfDVD;
	}

	/**
	 * Returns all Laptops.
	 * @return ArrayList<Laptop> of Laptops.
	 */
	protected static ArrayList<Laptop> getListOfLaptops() {
		return listOfLaptops;
	}

	/**
	 * Removes a resource from LibraryResources.
	 * @param id of the resource to be removed.
	 */
	protected static void removeResource(String id) {
		String dataType = id.substring(0,1);
		switch (dataType.toLowerCase()) {
			case "b":
				for(int index = 0; index < listOfBooks.size(); index++) {
					if (listOfBooks.get(index).getUniqueID().equals(id)) {
						listOfBooks.remove(index);
						break;
					}
				}
				break;
			case "l":
				for(int index = 0; index < listOfLaptops.size(); index++) {
					if (listOfLaptops.get(index).getUniqueID().equals(id)) {
						listOfLaptops.remove(index);
						break;
					}
				}
				break;
			case "d":
				for(int index = 0; index < listOfDVD.size(); index++) {
					if (listOfDVD.get(index).getUniqueID().equals(id)) {
						listOfDVD.remove(index);
						break;
					}
				}
				break;
			default:
				break;
		}

	}

	/**
	 * Removes the user from LibraryResources.
	 * @param username of the user to be removed.
	 */
	protected static void removeUser(String username) {
        for(int index = 0; index < listOfUsers.size(); index++) {
            if (listOfUsers.get(index).getUserName().equals(username)) {
                listOfUsers.remove(index);
                break;
            }
        }
	}
}
