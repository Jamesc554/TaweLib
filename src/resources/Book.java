package resources;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import user.User;
import utils.Queue;

/**
 * This class models a Library book.
 * @author Peter Daish
 * @since 18/11/2018
 */
public class Book extends Resource {
	
	private static String highestBookID; //this is used to calculate a unique BookID.
	
	private int highestCopyID = 0; // this is used to calculate a unique CopyID.
	private String author; //Author of Book
	private String publisher; //Publisher of Book
	private String genre; //Genre of book (Optional)
	private String isbn;//ISBN of book (Optional)
	private ArrayList<String> languages; //The array of languages the book is currently available in (Optional).

	
	/**
	 * The Constructor for Book.
	 * @param author
	 * @param genre
	 * @param publisher
	 * @param isbn
	 * @param lang
	 * @param year
	 * @param title
	 * @param thumbnailImg
	 * @param uniqueID
	 */
	public Book(String year,String title, String thumbnailImg, String uniqueID,
			String author, String genre, String isbn, String publisher, ArrayList<String> lang) {
		
		//Set all the inherited values from Resource...
		super(year, title, thumbnailImg, uniqueID);
		
		//Set all non-inherited values
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.isbn = isbn;
		
		//If lang from file is null, create a new arraylist. otherwise set languages to lang from file.
		if(lang == null) {
			this.languages = new ArrayList<String>();
		} else {
			this.languages = lang;
		}


		
	}
	
	/**
	 * This Method Prints out this Book's attributes for testing purposes.
	 */
	public String toString() {
		return "Year: " + this.year + "\nTitle: " + this.title +
			   "\nThumbnailImageRef: " + this.thumbnailImageRef +
			   "\nuniqueID: " + this.uniqueID +
			   "\nAuthor; " + this.author +
			   "\nPublisher: " + this.publisher +
			   "\nGenre: " + this.genre +
			   "\nISBN: " + this.isbn + 
			   "\nLanguages: " + this.languages +
			   "\nQueueOfReservations: " + this.queueOfReservations +
			   "\nMap Of copies: " + this.arrayListOfCopies + 
			   "\nMap of borrowHistory: " + this.borrowHistory;
	}
	/* #############################################################
	 * ########  BELOW ARE THE GETTERS AND SETTERS OF BOOK  ########
	 * #############################################################
	 */
	
	/**
	 * Sets a value to calculate Book IDs from.
	 * @param hBookID the highest current value of any Book's ID.
	 */
	public void setHighestBookID(String hBookID) {
		this.highestBookID = hBookID;
	}
	
	/**
	 * Returns the ID of the latest Book.
	 * @return highestBookID the highest current id of any book.
	 */
	public String getHighestBookID() {
		return this.highestBookID;
	}
	
	/**
	 * Sets a value to calculate a new copy's id from.
	 * @param hCopyID the latest ID of any copy.
	 */
	public void setHighestCopyID(int hCopyID) {
		this.highestCopyID = hCopyID;
	}
	
	/**
	 * Returns the newest copy's ID
	 * @return highestCopyID the newest copy's ID.
	 */
	public int getHighestCopyID() {
		return this.highestCopyID;
	}
	
	/**
	 * Returns the author of the book
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Sets the author of this book
	 * @param author 
	 * The new author of this book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns the publisher of this book
	 * @return publisher
	 * The publisher of this book
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher of this book
	 * @param publisher
	 * The new publisher of this book
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the genre of this book
	 * @return genre
	 * The genre of this book.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the genre of this book
	 * @param genre
	 * The new genre of this book.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets the ISBN of this book
	 * @return isbn
	 * The isbn of this book
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Sets the ISBN of this book
	 * @param isbn
	 * The new ISBN of this book.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the languages this book is available in
	 * @return languages
	 * The languages this book is available in.
	 */
	public ArrayList<String> getLanguages() {
		return languages;
	}

	/**
	 * Sets the languages this book is available in.
	 * @param languages
	 * The languages this book will become available in.
	 */
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	
	/* #############################################################
	 * ########  BELOW ARE THE COMPLEX METHODS OF BOOK  	########
	 * #############################################################
	 */
	
	/**
	 * Adds a new language to this book
	 * @param newLang
	 * The new languages to be added.
	 */
	public void addLanguage(String newLang) {
		this.languages.add(newLang);
	}
	
	/**
	 * Removes a specific languages from this book
	 * @param langToRemove
	 * The language to be removed.
	 */
	public void removeLanguage(String langToRemove) {
		//search through arrayList and remove language selected.
		for (int i = 0; i < this.languages.size(); i++) {
			if (this.languages.get(i) == langToRemove) {
				this.languages.remove(i);
			}
		}
	}
	
	/**
	 * Adds a copy to this book's unique Array of copies.
	 */
	public void addToCopies() {
		super.addToCopies(generateCopyID());
	}

	/**
	 * Generates a copy ID based off of previous copyID.
	 * @return
	 */
	private String generateCopyID() {
		//
		this.highestCopyID++;
		String hCpyID = Integer.toString(this.highestCopyID);
		return hCpyID;
	}
	
	@Override
	public void removeCopy() {
		if (this.arrayListOfCopies.size() >= 1) {
			super.removeCopy();
			this.highestCopyID--;
		}
	}
	
}