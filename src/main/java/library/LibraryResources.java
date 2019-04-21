package library;

import io.ReadFile;
import io.WriteFile;
import user.Librarian;
import user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>LibraryResources.</h1>
 * <p>This class implements the LibraryResources database. This class is static.</p>
 *
 * @author Dominik Wojtasiewicz, Peter Daish, James Carter
 * @since 18/11/2018
 */
public class LibraryResources {
    private static ArrayList<Book> listOfBooks = new ArrayList<>(); //List of books
    private static ArrayList<Laptop> listOfLaptops = new ArrayList<>(); //List of laptops
    private static ArrayList<DVD> listOfDVD = new ArrayList<>(); //List of dvd's
    private static ArrayList<VideoGame> listOfVideoGames = new ArrayList<>(); //List of books
    private static ArrayList<User> listOfUsers = new ArrayList<>(); //List of users
    private static ArrayList<Librarian> listOfLibrarians = new ArrayList<>(); // List of librarians

    /**
     * On start create all users, dvd, books, laptops and video games.
     */
    public static void start() {
        listOfDVD = ReadFile.readDvdsJSON();
        listOfBooks = ReadFile.readBooksJSON();
        listOfLaptops = ReadFile.readLaptopsJSON();
        listOfUsers = ReadFile.readUsers();
        listOfLibrarians = ReadFile.readLibrarians();
        listOfVideoGames = ReadFile.readVideoGamesJSON();
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
     * Adds a video game to the libraryResource.
     * @param videoGame VideoGame object.
     */
    public static void addVideoGame(VideoGame videoGame) {
        listOfVideoGames.add(videoGame);
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
        for (User u : listOfUsers) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }

        for (Librarian r : listOfLibrarians) {
            if (r.getUserName().equals(username)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Checks if a user is valid.
     * @param username Username of person to be checked
     * @return Boolean True if exists, False.
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
        for (Book b : listOfBooks) {
            if (b.getUniqueID().equals(id)) {
                return b;
            }
        }
        return null;
    }
    
    /**
     * Gets a video game from list based on id.
     * @param id of the video game.
     * @return VideoGame object. Returns null if no such video game.
     */
    protected static VideoGame getVideoGame(String id) {
        for (VideoGame v : listOfVideoGames) {
            if (v.getUniqueID().equals(id)) {
                return v;
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
        for (DVD d : listOfDVD) {
            if (d.getUniqueID().equals(id)) {
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
        for (Laptop l : listOfLaptops) {
            if (l.getUniqueID().equals(id)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Returns all users
     * @return ArrayList of users.
     */
    protected static ArrayList<User> getAllUsers() {
        return listOfUsers;
    }

    /**
     * Return all librarians.
     * @return ArrayList of librarians.
     */
    protected static ArrayList<Librarian> getAllLibrarians() {
        return listOfLibrarians;
    }

    /**
     * Returns all books.
     * @return ArrayList of books.
     */
    protected static ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }
    
    /**
     * Returns all video games.
     * @return ArrayList of video games.
     */
    protected static ArrayList<VideoGame> getListOfVideoGames() {
        return listOfVideoGames;
    }

    /**
     * Returns all DVD.
     * @return ArrayList of DVDs.
     */
    protected static ArrayList<DVD> getListOfDVD() {
        return listOfDVD;
    }

    /**
     * Returns all Laptops.
     * @return ArrayList of Laptops.
     */
    protected static ArrayList<Laptop> getListOfLaptops() {
        return listOfLaptops;
    }

    /**
     * Gets a list of all the resources in the library
     * @return List of all Resources
     */
    public static List<Resource> getAllResources(){
        List<Resource> resources = new ArrayList<>();

        resources.addAll(listOfBooks);
        resources.addAll(listOfDVD);
        resources.addAll(listOfLaptops);
        resources.addAll(listOfVideoGames);

        return resources;
    }

    public static String getAllResourcesJSON(){
        return WriteFile.writeResources(listOfBooks, listOfDVD, listOfLaptops, listOfVideoGames);
    }

    public static String getAllUsersJSON(boolean librarian) {
        if (librarian)
            return WriteFile.writeLibrarians(getAllLibrarians());
        else
            return WriteFile.writeUsers(getAllUsers());
    }

    /**
     * Removes a resource from LibraryResources.
     * @param id of the resource to be removed.
     */
    protected static void removeResource(String id) {
        String dataType = id.substring(0, 1);
        switch (dataType.toLowerCase()) {
            case "b":
                for (int index = 0; index < listOfBooks.size(); index++) {
                    if (listOfBooks.get(index).getUniqueID().equals(id)) {
                        listOfBooks.remove(index);
                        index = listOfBooks.size();
                    }
                }
                break;
            case "l":
                for (int index = 0; index < listOfLaptops.size(); index++) {
                    if (listOfLaptops.get(index).getUniqueID().equals(id)) {
                        listOfLaptops.remove(index);
                        index = listOfLaptops.size();
                    }
                }
                break;
            case "v":
                for (int index = 0; index < listOfVideoGames.size(); index++) {
                    if (listOfVideoGames.get(index).getUniqueID().equals(id)) {
                        listOfVideoGames.remove(index);
                        index = listOfVideoGames.size();
                    }
                }
                break;
            case "d":
                for (int index = 0; index < listOfDVD.size(); index++) {
                    if (listOfDVD.get(index).getUniqueID().equals(id)) {
                        listOfDVD.remove(index);
                        index = listOfDVD.size();
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
        for (int index = 0; index < listOfUsers.size(); index++) {
            if (listOfUsers.get(index).getUserName().equals(username)) {
                listOfUsers.remove(index);
                index = listOfUsers.size();
            }
        }
    }
}
