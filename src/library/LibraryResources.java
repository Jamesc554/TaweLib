package library;
import resources.*;
import user.*;

import java.util.ArrayList;

import io.*;
import resources.*;
import user.*;


public class LibraryResources {
	ArrayList<Book> listOfBooks = new ArrayList<>();
	ArrayList<Laptop> listOfLaptops = new ArrayList<>();
	ArrayList<DVD> listOfDVD = new ArrayList<>();
	ArrayList<User> listOfUsers = new ArrayList<>();

	public LibraryResources(){}

	public void addBook(Book book){
		listOfBooks.add(book);
	}
	public void addLaptop(Laptop laptop){
		listOfLaptops.add(laptop);
	}
	public void addDVD(DVD dvd){
		listOfDVD.add(dvd);
	}
	public void addUser(User user){
		listOfUsers.add(user);
	}
	protected User getUser(String username){
		for(User u : this.listOfUsers){
			if(u.getUserName().equals(username)){
				return u;
			}
		}
		return null;
	}
	protected Book getBook(String id){
		for(Book b : this.listOfBooks){
			if(b.getUniqueID().equals(id)){
				return b;
			}
		}
		return null;
	}
	protected DVD getDVD(String id){
		for(DVD d : this.listOfDVD){
			if(d.getUniqueID().equals(id)){
				return d;
			}
		}
		return null;
	}
	protected Laptop getLaptop(String id){
		for(Laptop l : this.listOfLaptops){
			if(l.getUniqueID().equals(id)){
				return l;
			}
		}
		return null;
	}
	public ArrayList<User> getAllUsers(){
		return this.listOfUsers;
	}

	public ArrayList<Book> getListOfBooks() {
		return this.listOfBooks;
	}

	public ArrayList<DVD> getListOfDVD() {
		return this.listOfDVD;
	}

	public ArrayList<Laptop> getListOfLaptops() {
		return this.listOfLaptops;
	}
	protected void removeResource(String id){
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
}
