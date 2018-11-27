package library;
import resources.*;
import user.*;

import java.util.ArrayList;

import io.*;
import resources.*;
import user.*;


public class LibraryResources {
	private static ArrayList<Book> listOfBooks = new ArrayList<>();
	private static ArrayList<Laptop> listOfLaptops = new ArrayList<>();
    private static ArrayList<DVD> listOfDVD = new ArrayList<>();
    private static ArrayList<User> listOfUsers = new ArrayList<>();

	public static void start(){
		listOfUsers = ReadFile.readUsers();
	}

	public static void addBook(Book book){
		listOfBooks.add(book);
	}
	public static void addLaptop(Laptop laptop){
		listOfLaptops.add(laptop);
	}
	public static void addDVD(DVD dvd){
		listOfDVD.add(dvd);
	}
	public static void addUser(User user){
		listOfUsers.add(user);
	}
	protected static User getUser(String username){
		for(User u : listOfUsers){
			if(u.getUserName().equals(username)){
				return u;
			}
		}
		return null;
	}

	public static boolean checkIfValidUsername(String username){
		if (listOfUsers.isEmpty())
			return false;

		for (User u : listOfUsers) {
			if (u.getUserName().equals(username))
				return true;
		}

		return false;
	}

	protected static Book getBook(String id){
		for(Book b : listOfBooks){
			if(b.getUniqueID().equals(id)){
				return b;
			}
		}
		return null;
	}
	protected static DVD getDVD(String id){
		for(DVD d : listOfDVD){
			if(d.getUniqueID().equals(id)){
				return d;
			}
		}
		return null;
	}
	protected static Laptop getLaptop(String id){
		for(Laptop l : listOfLaptops){
			if(l.getUniqueID().equals(id)){
				return l;
			}
		}
		return null;
	}
	public static ArrayList<User> getAllUsers(){
		return listOfUsers;
	}

	public static ArrayList<Book> getListOfBooks() {
		return listOfBooks;
	}

	public static ArrayList<DVD> getListOfDVD() {
		return listOfDVD;
	}

	public static ArrayList<Laptop> getListOfLaptops() {
		return listOfLaptops;
	}
	public static void removeResource(String id){
		String dataType = id.substring(0,1);
		switch (dataType.toLowerCase()){
			case "b":
				for(int index = 0; index < listOfBooks.size(); index++){
					if (listOfBooks.get(index).getUniqueID().equals(id)){
						listOfBooks.remove(index);
						break;
					}
				}
				break;
			case "l":
				for(int index = 0; index < listOfLaptops.size(); index++){
					if (listOfLaptops.get(index).getUniqueID().equals(id)){
						listOfLaptops.remove(index);
						break;
					}
				}
				break;
			case "d":
				for(int index = 0; index < listOfDVD.size(); index++){
					if (listOfDVD.get(index).getUniqueID().equals(id)){
						listOfDVD.remove(index);
						break;
					}
				}
				break;
			default:
				break;
		}

	}
	public static void removeUser(String username){
        for(int index = 0; index < listOfUsers.size(); index++) {
            if (listOfUsers.get(index).getUserName().equals(username)) {
                listOfUsers.remove(index);
                break;
            }
        }
	}
}
