package library;

import java.util.ArrayList;
import io.*;
import resources.*;
import user.*;


public class LibraryResources {

    private ArrayList<Book> listOfBooks = new ArrayList<>();
    //Need laptop class private ArrayList<Laptop> listOfLaptops = new ArrayList<>();
    //Need dvd class private ArrayList<DVD> listOfDVD = new ArrayList<>()
    private ArrayList<User> listOfUsers = new ArrayList<>();

    public void addBook(Book book){
        listOfBooks.add(book);
    }
    //public void addDVD(DVD dvd){listOfDVD.add(dvd)};
    //public void addLaptop(Laptop laptop){listOfLaptops.add(laptop)};
    public void addUser(User user){listOfUsers.add(user);}

    public User getUser(String username){
        for (User u : listOfUsers){
            if (u.getUserName().equals(username)){
                return u;
            }
        }
        return null;
    }
    public Book getBook(String id){
        for (Book b : listOfBooks){
            if (b.getUniqueID().equals(id)){
                return b;
            }
        }
        return null;
    }
}
